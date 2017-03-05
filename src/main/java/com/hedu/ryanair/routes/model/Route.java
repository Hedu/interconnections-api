package com.hedu.ryanair.routes.model;

/**
 * Created by hedu on 4/03/17.
 */
abstract public class Route {

    protected String airportFrom = null;
    protected String airportTo = null;
    protected boolean newRoute = false;
    protected boolean seasonalRoute = false;

    public String getAirportFrom() {

        return airportFrom;
    }

    public void setAirportFrom(String airportFrom) {
        this.airportFrom = airportFrom;
    }

    public String getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(String airportTo) {
        this.airportTo = airportTo;
    }

    public boolean isNewRoute() {
        return newRoute;
    }

    public void setNewRoute(boolean newRoute) {
        this.newRoute = newRoute;
    }

    public boolean isSeasonalRoute() {
        return seasonalRoute;
    }

    public void setSeasonalRoute(boolean seasonalRoute) {
        this.seasonalRoute = seasonalRoute;
    }
}
