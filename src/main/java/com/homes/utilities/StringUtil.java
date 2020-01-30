package com.homes.utilities;

import com.homes.model.Village;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class StringUtil
{
    public String prepareData(List<Object> obj)
    {
        String result = obj.stream().map(Object::toString)
                .collect(Collectors.joining(","));

        return result;
    }

    public int retrieve_duration(String str)
    {
        int duration = 0;
        Pattern p = Pattern.compile("[0-9]*");
        Matcher matcher = p.matcher(str);
        if(matcher.find())
        {
            duration = Integer.parseInt(matcher.group());
        }
        return duration;
    }
}
