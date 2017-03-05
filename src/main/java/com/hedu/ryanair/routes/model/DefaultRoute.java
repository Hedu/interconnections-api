package com.hedu.ryanair.routes;

/**
 * Created by hedu on 4/03/17.
 */
public class DefaultRoute extends Route {
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("airportFrom = ").append(airportFrom);
        sb.append(", airportTo = ").append(airportTo);
        sb.append(", newRoute = ").append(newRoute);
        sb.append(", seasonalRoute = ").append(seasonalRoute);

        return sb.toString();
    }
}