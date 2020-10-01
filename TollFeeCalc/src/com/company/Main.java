package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void TollFeeCalculator(String inputFile) {

        try {
            Scanner sc = new Scanner(new File(inputFile));
            String[] dateStrings = sc.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
            for(int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            System.out.println("The total fee for the input file is: " + getTotalFeeCost(dates));
        } catch(IOException e) {
            System.err.println("Could not read file " + inputFile);
        } catch (Exception e){
            System.err.println("The dates in the file are in a wrong format");
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        LocalDateTime intervalStart = dates[0];
        for(LocalDateTime date: dates) {
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            long diffInDays = intervalStart.until(date, ChronoUnit.DAYS);
            System.out.println(date.toString());
            if(diffInMinutes > 60 || diffInDays > 0 ) {
                totalFee += getTollFeePerPassing(date);
                intervalStart = date;
                if (getTollFeePerPassing(date) == 0){
                    System.out.println("Free hour or day");
                }
                else {
                    System.out.println("A fee of: " + getTollFeePerPassing(date)
                            + " has been added, the new totalFee is: " + totalFee);
                }
            } else {
                System.out.println("You have recently passed the toll within the hour, no fee for this passage");
            }
        }
        return Math.min(totalFee, 60);
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date)) return 0;
        int hour = date.getHour();
        int minute = date.getMinute();
        if (hour == 6 && minute <= 29) return 8;
        else if (hour == 6) return 13;
        else if (hour == 7) return 18;
        else if (hour == 8 && minute <= 29) return 13;
        else if (hour >= 8 && hour <= 14) return 8;
        else if (hour == 15 && minute <= 29) return 13;
        else if (hour == 15 || hour == 16) return 18;
        else if (hour == 17) return 13;
        else if (hour == 18 && minute <= 29) return 8;
        else return 0;
    }

    public static boolean isTollFreeDate(LocalDateTime date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7 || date.getMonth().getValue() == 7;
    }

    public static void main(String[] args)  {
        System.out.println("=============");

        TollFeeCalculator("src\\Test1.txt");
        System.out.println("=============");

        TollFeeCalculator("src\\Test2.txt");
        System.out.println("=============");

        TollFeeCalculator("src\\Test3.txt");
        System.out.println("=============");
    }
}