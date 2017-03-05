package com.hedu.ryanair.schedules.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by hedu on 4/03/17.
 */
public class Day {
    @JsonProperty("day")
    private int day;

    @JsonProperty("flights")
    private List<Flight> flights;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("day = ").append(day);
        for ( int i = 0; i < flights.size(); ++i) {
            sb.append(", flight").append(i).append(" = ").append(flights.get(i).toString());
        }

        return sb.toString();
    }
}