package com.hedu.ryanair;

/**
 * Created by hedu on 4/03/17.
 */
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.hedu.ryanair.routes.RouteUtils;
import com.hedu.ryanair.schedules.ScheduleUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Application {

    //private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        System.out.println(quote.toString());
        //RouteUtils.getRoutes();

        ScheduleUtils.getSchedules("DUB","WRO",2017,6);
    }

}