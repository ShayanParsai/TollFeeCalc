package com.company;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main { // Shayan + Jesper

    public static void TollFeeCalculator(String inputFile) {

        try {
            Scanner sc = new Scanner(new File(inputFile));
            String[] dateStrings = sc.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
            for(int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            System.out.println("The total fee for the input file is: " + getTotalFeeCost(dates));
            System.out.println("\n=====NEW FILE======\n");
        } catch(IOException e) {
            System.err.println("Could not read file " + inputFile);
            System.out.println("\n=====NEW FILE======\n");
        } catch (Exception e){
            System.err.println("The dates in the file are in a wrong format");
            System.out.println("\n=====NEW FILE======\n");
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        int totalDayFee = 0;
        LocalDateTime intervalStart = dates[0];

        for(LocalDateTime date: dates) {
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            long diffInDays = intervalStart.until(date, ChronoUnit.DAYS);
            System.out.println(date.toString());

            if (getTollFeePerPassing(date) == 0) {
                System.out.println("Free Hour/Day/Month");
            } else if(diffInMinutes >= 60 || diffInDays > 0 || intervalStart.equals(date) || totalDayFee == 0) {
                intervalStart = date;
                totalDayFee += getTollFeePerPassing(date);
                System.out.println("+ " + getTollFeePerPassing(date) + " to the daily fee");
                if (diffInDays > 0) {
                    System.out.println("This is a new day, the current totalDayFee is: "+ totalDayFee);
                    totalFee += Math.min(60,totalDayFee);
                    totalDayFee = 0;
                    System.out.println("The daily fee has been added to totalFee and than reset");
                }
            } else {
                System.out.println("You have recently passed the toll within the hour, no fee for this passage");
            }
            System.out.println(" ");
        }
        totalFee += Math.min(60,totalDayFee);
        return totalFee;
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
        System.out.println("\n=====NEW FILE======\n");

        TollFeeCalculator("src\\Test1.txt");
        TollFeeCalculator("src\\Test2.txt");
        TollFeeCalculator("src\\Test3.txt");
    }
}