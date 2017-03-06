package com.hedu.ryanair.schedules.util;

import com.hedu.ryanair.schedules.model.DefaultSchedule;
import com.hedu.ryanair.schedules.model.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hedu on 4/03/17.
 */
public class ScheduleService {

    public static final String SCHEDULES_URL =
        "https://api.ryanair.com/timetable/3/schedules/{departure}/{arrival}/years/{year}/months/{month}";

    public static Schedule getSchedules(String departure, String arrival, int year, int month) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("departure", departure);
        uriParams.put("arrival", arrival);
        uriParams.put("year", String.valueOf(year));
        uriParams.put("month", String.valueOf(month));

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(SCHEDULES_URL);

        String uri = builder.buildAndExpand(uriParams).toUri().toString();

        try {
            ResponseEntity<DefaultSchedule> responseEntity = restTemplate.getForEntity(uri, DefaultSchedule.class);
            Schedule schedule = responseEntity.getBody();

            //System.out.println(schedule.toString());

            return schedule;
        }
        catch (HttpClientErrorException hcee) {
            System.out.println("Error connecting to url: " + uri);
            return null;
        }

    }
}
