package com.homes.utilities;

import java.util.Date;

public class DateUtil
{
    public static int getTimeDifference(Date date1, Date date2)
    {
        long diff = date2.getTime() - date1.getTime();
        int diffInHours = (int) diff / (60 * 60 * 1000);
        return diffInHours;
    }

}
