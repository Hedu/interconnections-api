package com.hedu.ryanair.interconnections.model;

import java.util.List;

/**
 * Created by hedu on 5/03/17.
 */
public class DefaultInterconnection extends Interconnection{

    public DefaultInterconnection(int stops, List<Leg> legs) {
        this.stops = stops;
        this.legs = legs;
    }
}
