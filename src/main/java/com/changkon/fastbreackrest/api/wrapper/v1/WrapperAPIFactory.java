package com.changkon.fastbreackrest.api.wrapper.v1;

import com.changkon.fastbreackrest.api.wrapper.v1.impl.NBAWrapperAPI;
import com.changkon.fastbreackrest.models.BasketballLeague;

public class WrapperAPIFactory {

    public static WrapperAPI getWrapperAPI(BasketballLeague league) {
        switch (league) {
            case GLEAGUE:
                return null;
            case NBA:
            default:
                return new NBAWrapperAPI();
        }
    }

}
