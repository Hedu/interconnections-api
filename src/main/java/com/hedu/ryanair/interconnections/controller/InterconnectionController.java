package com.hedu.ryanair.interconnections.controller;

import com.hedu.ryanair.interconnections.model.Interconnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hedu on 5/03/17.
 */

@RestController
public class InterconnectionController {

    @GetMapping("/interconnections")
    public Interconnection interconnection(
            @RequestParam(value="departure") String departure,
            @RequestParam(value="arrival") String arrival,
            @RequestParam(value="departureDateTime") String departureDateTime,
            @RequestParam(value="arrivalDateTime") String arrivalDateTime) {

        System.out.println("departure: " + departure);
        System.out.println("arrival: " + arrival);
        System.out.println("departureDateTime: " + departureDateTime);
        System.out.println("arrivalDateTime: " + arrivalDateTime);

        return null;
    }
}