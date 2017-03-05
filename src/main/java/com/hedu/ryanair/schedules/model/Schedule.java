package com.hedu.ryanair.schedules.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by hedu on 4/03/17.
 */
abstract public class Schedule {
    protected int month;

    @JsonProperty("days")
    protected List<Day> days;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
