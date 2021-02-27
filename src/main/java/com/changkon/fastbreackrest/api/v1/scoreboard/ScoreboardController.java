package com.changkon.fastbreackrest.api.v1.scoreboard;

import com.changkon.fastbreackrest.api.wrapper.v1.WrapperAPI;
import com.changkon.fastbreackrest.api.wrapper.v1.WrapperAPIFactory;
import com.changkon.fastbreackrest.models.BasketballLeague;
import com.changkon.fastbreackrest.dto.Scoreboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/scoreboard")
public class ScoreboardController {

    private Logger logger = LoggerFactory.getLogger("ScoreboardController");

    @GetMapping(value = "", produces="application/json")
    public ResponseEntity<List<Scoreboard>> getScoreboard(@RequestParam(value = "league") BasketballLeague league, @RequestParam(value = "gameDate") String gameDate) {
        logger.info("Scoreboard Request");
        WrapperAPI apiService = WrapperAPIFactory.getWrapperAPI(league);
        try {
            List<Scoreboard> scoreboard = apiService.getScoreboards(gameDate);
            if (scoreboard == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
            }

            return ResponseEntity.ok(scoreboard);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
