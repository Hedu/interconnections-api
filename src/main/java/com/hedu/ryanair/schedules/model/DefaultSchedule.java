package com.hedu.ryanair.schedules.model;

/**
 * Created by hedu on 4/03/17.
 */
public class DefaultSchedule extends Schedule
{
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("month = ").append(month);
        for ( int i = 0; i < days.size(); ++i) {
            sb.append(", day").append(i).append(" = ").append(days.get(i).toString());
        }

        return sb.toString();
    }
}
