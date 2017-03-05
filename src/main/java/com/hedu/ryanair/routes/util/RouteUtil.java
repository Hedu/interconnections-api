package com.hedu.ryanair.routes.util;

import com.hedu.ryanair.routes.model.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hedu on 5/03/17.
 */
public class RouteUtil {

    static public List<Route> getRoutesByAirportFrom(List<Route> routes, String airportFrom) {
        return getRoutesByAirportFromOrAirportTo(routes, airportFrom, null);
    }

    static public List<Route> getRoutesByAirportTo(List<Route> routes, String airportTo) {
        return getRoutesByAirportFromOrAirportTo(routes, null, airportTo);
    }

    static public List<Route> getRoutesByAirportFromOrAirportTo(
            List<Route> routes, String airportFrom, String airportTo) {

        List<Route> result = new ArrayList<>();
        if (routes == null || routes.isEmpty() || ( airportFrom == null && airportTo == null)) {
            return result;
        }

        for (Route route: routes) {
            if (route.getAirportFrom() == airportFrom || route.getAirportTo() == airportTo) {
                result.add(route);
            }
        }
        return result;
    }
}
