package com.hedu.ryanair.interconnections.util;

import com.hedu.ryanair.interconnections.model.DefaultInterconnection;
import com.hedu.ryanair.interconnections.model.Interconnection;
import com.hedu.ryanair.interconnections.model.Leg;
import com.hedu.ryanair.routes.model.Route;
import com.hedu.ryanair.routes.util.RouteService;
import com.hedu.ryanair.routes.util.RouteUtil;
import com.hedu.ryanair.schedules.model.Day;
import com.hedu.ryanair.schedules.model.Flight;
import com.hedu.ryanair.schedules.model.Schedule;
import com.hedu.ryanair.schedules.util.ScheduleService;
import com.hedu.ryanair.utils.JavaUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by hedu on 5/03/17.
 */
public class InterconnectionService {
    static public List<Interconnection> getInterconnections(
            String departure, String arrival, String departureDateTime, String arrivalDateTime) {

        List<Interconnection> interconnections = new ArrayList<>();

        List<Route> totalRoutes = RouteService.getRoutes();
        List<Route> routesFromDeparture = RouteUtil.getRoutesByAirportFrom(totalRoutes,departure);
        List<Route> routesToArrival = RouteUtil.getRoutesByAirportTo(totalRoutes,arrival);

        //create a map which key is the first route and the value is the second route
        Map<Route, Route> routes = new HashMap<>();
        for (Route firstRoute: routesFromDeparture) {
            if (firstRoute.getAirportFrom() == departure && firstRoute.getAirportTo() == arrival) {
                routes.put(firstRoute, null );
            }
            else {
                for (Route secondRoute : routesToArrival) {
                    if (firstRoute.getAirportTo().equals(secondRoute.getAirportFrom()))
                        routes.put(firstRoute, secondRoute);
                }
            }
        }

        LocalDateTime departureDate = JavaUtils.getISOTime(departureDateTime);
        LocalDateTime arrivalDate = JavaUtils.getISOTime(arrivalDateTime);
        LocalDateTime tempDate = departureDate;

        //create a map containing all the months between dates
        Map<Integer, Set<Integer>> yearAndMonth = new HashMap<>();
        while (arrivalDate.isAfter(tempDate)) {
            int year = tempDate.getYear();
            int month = tempDate.getMonthValue();
            Set<Integer> months = new HashSet<>();
            if (yearAndMonth.containsKey(year)) {
                months = yearAndMonth.get(year);
            }
            months.add(month);
            yearAndMonth.put(year, months);
            tempDate = tempDate.plusMonths(1);
        }

        for (Route firstRoute: routes.keySet()) {
            Route secondRoute = routes.get(firstRoute);
            List<Schedule> firstSchedules = new ArrayList<>();
            List<Schedule> secondSchedules = new ArrayList<>();
            for (int year: yearAndMonth.keySet()) {
                for (int month: yearAndMonth.get(year)) {

                    Schedule firstSchedule = ScheduleService.getSchedules(firstRoute.getAirportFrom(), firstRoute.getAirportTo(), year, month);
                    if (firstSchedule != null) {
                        firstSchedules.add(firstSchedule);
                    }
                    if (secondRoute != null) {
                        Schedule secondSchedule = ScheduleService.getSchedules(secondRoute.getAirportFrom(), secondRoute.getAirportTo(), year, month);
                        if (secondSchedule != null) {
                            secondSchedules.add(secondSchedule);
                        }
                    }
                }
                if (!firstSchedules.isEmpty()) {
                    if (secondRoute == null) {
                        interconnections.addAll(getDirectInterconnections(firstRoute, firstSchedules, year, departureDate, arrivalDate));
                    } else if (!secondSchedules.isEmpty()){
                        interconnections.addAll(getInterconnections(
                                firstRoute, secondRoute, firstSchedules, secondSchedules, year, departureDate, arrivalDate));
                    }
                }
            }
        }
        return interconnections;
    }

    static public List<Interconnection> getDirectInterconnections(
            Route route, List<Schedule> schedules, int year, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        List<Interconnection> interconnections = new ArrayList<>();
        for (Schedule schedule : schedules) {
            int month = schedule.getMonth();
            for (Day day : schedule.getDays()) {
                for (Flight flight : day.getFlights()) {
                    LocalTime flightDepartureTime = JavaUtils.getTime(flight.getDepartureTime());
                    LocalTime flightArrivalTime = JavaUtils.getTime(flight.getArrivalTime());

                    LocalDateTime flightDepartureDate = LocalDateTime.of(
                            year,
                            month,
                            day.getDay(),
                            flightDepartureTime.getHour(),
                            flightDepartureTime.getMinute());
                    LocalDateTime flightArrivalDate = LocalDateTime.of(
                            year,
                            month,
                            day.getDay(),
                            flightArrivalTime.getHour(),
                            flightArrivalTime.getMinute());

                    if (!flightDepartureDate.isBefore(departureDate) && !flightArrivalDate.isAfter(arrivalDate)) {

                        List<Leg> legs = new ArrayList<>();
                        Leg leg = new Leg(
                                route.getAirportFrom(),
                                route.getAirportTo(),
                                JavaUtils.getISOTime(flightDepartureDate),
                                JavaUtils.getISOTime(flightArrivalDate));
                        legs.add(leg);

                        Interconnection interconnection = new DefaultInterconnection(0, legs);
                        interconnections.add(interconnection);
                    }
                }
            }
        }
        return interconnections;
    }

    static public List<Interconnection> getInterconnections(
            Route firstRoute, Route secondRoute, List<Schedule> firstSchedules, List<Schedule> secondSchedules, int year, LocalDateTime departureDate, LocalDateTime arrivalDate) {

        List<Interconnection> interconnections = new ArrayList<>();
        for (Schedule firstSchedule : firstSchedules) {
            int firstMonth = firstSchedule.getMonth();
            for (Day firstDay : firstSchedule.getDays()) {
                for (Flight firstFlight : firstDay.getFlights()) {
                    for (Schedule secondSchedule: secondSchedules) {
                        int secondMonth = secondSchedule.getMonth();
                        for (Day secondDay : secondSchedule.getDays()) {
                            if (secondDay.getDay() >= firstDay.getDay()) {
                                for (Flight secondFlight : secondDay.getFlights()) {
                                    LocalTime firstFlightDepartureTime = JavaUtils.getTime(firstFlight.getDepartureTime());
                                    LocalTime firstFlightArrivalTime = JavaUtils.getTime(firstFlight.getArrivalTime());
                                    LocalTime secondFlightDepartureTime = JavaUtils.getTime(secondFlight.getDepartureTime());
                                    LocalTime secondFlightArrivalTime = JavaUtils.getTime(secondFlight.getArrivalTime());


                                    LocalDateTime firstFlightDepartureDate = LocalDateTime.of(
                                            year,
                                            firstMonth,
                                            firstDay.getDay(),
                                            firstFlightDepartureTime.getHour(),
                                            firstFlightDepartureTime.getMinute());
                                    LocalDateTime firstFlightArrivalDate = LocalDateTime.of(
                                            year,
                                            firstMonth,
                                            firstDay.getDay(),
                                            firstFlightArrivalTime.getHour(),
                                            firstFlightArrivalTime.getMinute());
                                    LocalDateTime secondFlightDepartureDate = LocalDateTime.of(
                                            year,
                                            secondMonth,
                                            secondDay.getDay(),
                                            secondFlightDepartureTime.getHour(),
                                            secondFlightDepartureTime.getMinute());
                                    LocalDateTime secondFlightArrivalDate = LocalDateTime.of(
                                            year,
                                            secondMonth,
                                            secondDay.getDay(),
                                            secondFlightArrivalTime.getHour(),
                                            secondFlightArrivalTime.getMinute());

                                    if (firstFlightArrivalDate.plusHours(2).isBefore(secondFlightDepartureDate) &&
                                            !firstFlightDepartureDate.isBefore(departureDate) &&
                                            !secondFlightArrivalDate.isAfter(arrivalDate)) {

                                        List<Leg> legs = new ArrayList<>();
                                        Leg firstLeg = new Leg(
                                                firstRoute.getAirportFrom(),
                                                firstRoute.getAirportTo(),
                                                JavaUtils.getISOTime(firstFlightDepartureDate),
                                                JavaUtils.getISOTime(firstFlightArrivalDate));
                                        Leg secondLeg = new Leg(
                                                secondRoute.getAirportFrom(),
                                                secondRoute.getAirportTo(),
                                                JavaUtils.getISOTime(secondFlightDepartureDate),
                                                JavaUtils.getISOTime(secondFlightArrivalDate));
                                        legs.add(firstLeg);
                                        legs.add(secondLeg);
                                        Interconnection interconnection = new DefaultInterconnection(1, legs);
                                        interconnections.add(interconnection);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return interconnections;
    }
    
}
