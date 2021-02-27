package com.changkon.fastbreackrest.api.wrapper.v1;

import com.changkon.fastbreackrest.dto.Scoreboard;

import java.util.List;

public interface WrapperAPI {
    List<Scoreboard> getScoreboards(String gameDate) throws Exception;
}
