package com.hedu.ryanair.schedules.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by hedu on 4/03/17.
 */
public class Flight {
    private int number;

    @JsonProperty("departureTime")
    private String departureTime;

    @JsonProperty("arrivalTime")
    private String arrivalTime;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("number = ").append(number);
        sb.append(", departureTime = ").append(departureTime);
        sb.append(", arrivalTime = ").append(arrivalTime);

        return sb.toString();
    }
}
