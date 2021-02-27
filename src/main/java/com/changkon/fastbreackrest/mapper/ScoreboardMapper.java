package com.changkon.fastbreackrest.mapper;

import com.changkon.fastbreackrest.models.BasketballLeague;
import com.changkon.fastbreackrest.dto.Scoreboard;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

public class ScoreboardMapper {

    private Scoreboard.Team getTeam(ReadContext ctx, String gameId, int teamId) {
        String lineScoreJsonPath = String.format("$.resultSets[?(@.name=='LineScore')].rowSet[?(@[2]=='%s' && @[3]==%d)][*]", gameId, teamId);
        String standingsJsonPath = "$.resultSets[?(@.name=='EastConfStandingsByDay' || @.name=='WestConfStandingsByDay')].rowSet[*]";
        List<Object> lineScores = ctx.read(lineScoreJsonPath, List.class);
        List<List<Object>> teamStandings = ctx.read(standingsJsonPath, List.class);
        Optional<List<Object>> optTeamStanding = teamStandings.stream()
            .filter(team -> (int) team.get(0) == teamId)
            .findFirst();

        if (lineScores.size() > 0 && optTeamStanding.isPresent()) {
            List<Object> teamStanding = optTeamStanding.get();

            Scoreboard.Team team = new Scoreboard.Team();
            team.setCity(lineScores.get(5).toString());
            team.setAbbr(lineScores.get(4).toString());
            team.setName(lineScores.get(6).toString());
            team.setTeamId(String.valueOf(teamId));
            int teamPoints = (int) Optional.ofNullable(lineScores.get(22))
                .orElse(Integer.valueOf("0"));
            team.setPoints(teamPoints);
            int[] pointsByQuarter = lineScores.stream()
                .skip(8)
                .limit(14)
                .filter(i -> i instanceof Integer)
                .mapToInt(e -> (int)e)
                .toArray();
            team.setPointsByQuarter(pointsByQuarter);

            String teamConference = teamStanding.get(4).toString();
            team.setConference(teamConference);
            team.setWins((int) teamStanding.get(7));
            team.setLoss((int) teamStanding.get(8));

            String[] homeRecord = teamStanding.get(10).toString().split("-");
            String[] roadRecord = teamStanding.get(11).toString().split("-");

            team.setHomeWins(Integer.parseInt(homeRecord[0]));
            team.setHomeLoss(Integer.parseInt(homeRecord[1]));

            team.setAwayWins(Integer.parseInt(roadRecord[0]));
            team.setAwayLoss(Integer.parseInt(roadRecord[1]));

            int currentSeed = IntStream.range(0, teamStandings.size())
                .filter(i -> teamStandings.get(i).get(0).equals(teamId))
                .findFirst()
                .orElse(0) % (teamStandings.size() / 2);
            team.setCurrentSeed(currentSeed);
            return team;
        } else {
            return null;
        }
    }

    public List<Scoreboard> fromNba(String in) throws IOException {
        ReadContext ctx = JsonPath.parse(in);
        List<List<Object>> gameHeaders = ctx.read("$.resultSets[?(@.name=='GameHeader')].rowSet[*]", List.class);

        List<Scoreboard> scoreboards = new ArrayList<Scoreboard>();
        // Each game
        for (List<Object> gameDetail : gameHeaders) {
            Scoreboard scoreboard = new Scoreboard();
            scoreboard.setLeague(BasketballLeague.NBA.getLeague());
            scoreboard.setGameId((String) gameDetail.get(2));
            scoreboard.setSeason((String) gameDetail.get(8));
            scoreboard.setFinished(gameDetail.get(4).equals("Final") ? true : false);
            scoreboard.setDateTimeEst(LocalDateTime.parse((String) gameDetail.get(0)));
            scoreboard.setArenaName((String) gameDetail.get(15));
            // Do home team
            int homeTeamId = (Integer) gameDetail.get(6);
            int awayTeamId = (Integer) gameDetail.get(7);
            String gameId = (String) gameDetail.get(2);
            scoreboard.setHomeTeam(getTeam(ctx, gameId, homeTeamId));
            scoreboard.setAwayTeam(getTeam(ctx, gameId, awayTeamId));
            scoreboards.add(scoreboard);
        }

        return scoreboards;
    }
}
