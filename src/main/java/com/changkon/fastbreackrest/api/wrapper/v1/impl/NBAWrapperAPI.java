package com.changkon.fastbreackrest.api.wrapper.v1.impl;

import com.changkon.fastbreackrest.api.wrapper.v1.WrapperAPI;
import com.changkon.fastbreackrest.mapper.ScoreboardMapper;
import com.changkon.fastbreackrest.dto.Scoreboard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.utils.URIBuilder;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class NBAWrapperAPI implements WrapperAPI {

    private static final String LEAGUEID = "00";
    private static final String BASEURL = "https://stats.nba.com/stats";
    private static final String SCOREBOARD = "/scoreboardV2";
    private ObjectMapper mapper = new ObjectMapper();

//    private CloseableHttpClient getHttpClient() {
//        HttpRequestInterceptor refererRequestInterceptor = new HttpRequestInterceptor() {
//            @Override
//            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
//                httpRequest.addHeader("Referer", "https://stats.nba.com/");
//                httpRequest.addHeader("Accept", "*/*");
//                httpRequest.addHeader("User-Agent", "Firefox/55.0");
//            }
//        };
//
//        return HttpClients.custom().addInterceptorFirst(refererRequestInterceptor).build();
//    }

    @Override
    public List<Scoreboard> getScoreboards(String gameDate) throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        String endpoint = String.format("%s%s", BASEURL, SCOREBOARD);
        URIBuilder uriBuilder = new URIBuilder(endpoint);
        uriBuilder.setParameter("LeagueID", LEAGUEID);
        uriBuilder.setParameter("DayOffset", "0");
        uriBuilder.setParameter("GameDate", gameDate);

        HttpRequest httpRequest = HttpRequest.newBuilder()
            .GET()
            .uri(uriBuilder.build())
            .header("Referer", "https://stats.nba.com")
            .header("Accept", "*/*")
            .timeout(Duration.ofSeconds(10))
            .build();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ScoreboardMapper mapper = new ScoreboardMapper();
        List<Scoreboard> scoreboards = mapper.fromNba(response.body());
        return scoreboards;
    }
}
