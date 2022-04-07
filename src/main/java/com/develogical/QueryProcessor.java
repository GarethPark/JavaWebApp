package com.develogical;

import java.util.Arrays;
import java.util.Comparator;

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
        }
        return "";
    }
}
