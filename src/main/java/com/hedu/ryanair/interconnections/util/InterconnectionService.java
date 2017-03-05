package com.hedu.ryanair.interconnections.util;

import com.hedu.ryanair.interconnections.model.Interconnection;
import com.hedu.ryanair.routes.model.Route;
import com.hedu.ryanair.routes.util.RouteService;

import java.util.List;

/**
 * Created by hedu on 5/03/17.
 */
public class InterconnectionService {
    static public List<Interconnection> getInterconnections(
        String departure, String arrival, String departureDateTime, String arrivalDateTime) {

        List<Route> routes = RouteService.getRoutesByAirportFromOrAirportTo(departure, arrival);

    }
}
