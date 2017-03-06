package com.hedu.ryanair.routes.util;

import com.hedu.ryanair.routes.model.DefaultRoute;
import com.hedu.ryanair.routes.model.Route;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by hedu on 4/03/17.
 */
public class RouteService {

    public static final String ROUTES_URL = "https://api.ryanair.com/core/3/routes/";

    public static List<Route> getRoutes() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<DefaultRoute[]> responseEntity = restTemplate.getForEntity(ROUTES_URL, DefaultRoute[].class);
        Route[] routes = responseEntity.getBody();

        /*for (Route route: routes) {
            System.out.println(route.toString());
        }*/

        return Arrays.asList(routes);
    }

    static public List<Route> getRoutesByAirportFrom(String airportFrom) {

        return RouteUtil.getRoutesByAirportFrom(getRoutes(), airportFrom);
    }

    static public List<Route> getRoutesByAirportTo(String airportTo) {

        return RouteUtil.getRoutesByAirportTo(getRoutes(), airportTo);
    }

    static public List<Route> getRoutesByAirportFromOrAirportTo(String airportFrom, String airportTo) {

        return RouteUtil.getRoutesByAirportFromOrAirportTo(getRoutes(), airportFrom, airportTo);
    }

}
