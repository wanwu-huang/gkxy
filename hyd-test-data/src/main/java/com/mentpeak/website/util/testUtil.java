package com.mentpeak.website.util;

import java.util.Arrays;

public class testUtil {

    public static void main(String[] args) {

//        String arr = "10岁： [0-40)、 \n" +
//                "11岁： [0-39)、\n" +
//                "12岁： [0-42)、 \n" +
//                "13岁： [0-46)、\n" +
//                "14岁： [0-49)、 \n" +
//                "15岁： [0-51)、 \n" +
//                "16岁： [0-52) ";

        String arr = "10岁： [0-40）\n" +
                "11岁： [0-39)\n" +
                "12岁： [0-42）\n" +
                "13岁： [0-46）\n" +
                "14岁： [0-49）\n" +
                "15岁： [0-51）\n" +
                "16岁： [0-52）\n";

        String[] split = arr.split("、");
        Arrays.asList(split).forEach(s -> {
            String[] str = s.split("岁：");
            System.out.println(str[0]+"----------"+str[1].trim());
        });
    }
}
