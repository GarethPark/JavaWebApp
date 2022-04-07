package com.develogical;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class QueryProcessor {

    public String process(String query) {
        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.toLowerCase().contains("what is your name")){
            return "Gareth & Lucas";
        } else if (query.toLowerCase().contains("which of the following numbers is the largest")){
            return Arrays.stream(
                    query
                            .replaceAll("\\s+", "")
                            .split(":")[2]
                            .split(","))
                    .max(Comparator.comparingInt(Integer::parseInt)).get();
        } else if (query.toLowerCase().contains("multiplied")){
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(query.split(":")[1]);
            int total = 1;
            while(m.find()) {
                total*= Integer.parseInt(m.group());
            }
            return Integer.toString(total);
        } else if (query.toLowerCase().contains("minus")){
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(query.split(":")[1]);
            int total = 0;
            m.find();
            total += Integer.parseInt(m.group());
            m.find();
            total -= Integer.parseInt(m.group());
            return Integer.toString(total);
        } else if (query.toLowerCase().contains("minister")) {
            return "2017";
        } else if (query.toLowerCase().contains("square")) {
            return Arrays.stream(
                    query
                            .replaceAll("\\s+", "")
                            .split(":")[2]
                            .split(",")).filter(value -> {
                double sq = Math.sqrt(Integer.parseInt(value));
                double cb = Math.cbrt(Integer.parseInt(value));
                return (sq - Math.floor(sq)) == 0 && (cb - Math.floor(cb)) == 0;
            }).collect(Collectors.joining(","));
        }
        return "";
    }
}
