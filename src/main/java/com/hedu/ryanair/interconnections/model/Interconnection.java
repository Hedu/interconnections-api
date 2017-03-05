package com.hedu.ryanair.interconnections.model;

import java.util.List;

/**
 * Created by hedu on 5/03/17.
 */
abstract public class Interconnection {
    protected int stops;
    protected List<Leg> legs;

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}