package com.hedu.ryanair.routes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * Created by hedu on 4/03/17.
 */
public class RouteUtils {

    public static final String ROUTES_URL = "https://api.ryanair.com/core/3/routes/";

    public static Route[] getRoutes() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<DefaultRoute[]> responseEntity = restTemplate.getForEntity(ROUTES_URL, DefaultRoute[].class);
        Route[] routes = responseEntity.getBody();

        for (Route route: routes) {
            System.out.println(route.toString());
        }
        return routes;
    }

}
