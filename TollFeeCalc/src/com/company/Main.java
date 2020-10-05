package com.company;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("\n==============NEW FILE===============\n");
        } catch (IOException e){
            System.err.println("Cought an IOException");
        } catch (ArithmeticException e) {
            System.err.println("Cought an ArithmeticException");
        } catch (NullPointerException e) {
            System.err.println("Cought an NullPointerException");
        } catch (NumberFormatException e) {
            System.err.println("Cought an NumberFormatException");
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("The Array is out of bounds, please adjust the lenght");
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Cought an StringIndexOutOfBoundsException");
        } catch (DateTimeParseException e) {
            System.err.println("The format of the input files are wrong, please adjust the format");
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        int totalDayFee = 0;
        int lastValue = 0;
        int currentValue;
        boolean isNewDay;
        LocalDateTime intervalStart = dates[0];

        for(LocalDateTime date: dates) {
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            isNewDay = intervalStart.getDayOfYear() != date.getDayOfYear();
            System.out.println(date.toString());
            if (getTollFeePerPassing(date) == 0) {
                System.out.println("Free Hour/Day/Month");
            }else if (isNewDay) {
                intervalStart = date;
                System.out.println("This is a new day, the current totalDayFee is: "+ totalDayFee);
                totalFee += Math.min(60,totalDayFee);
                System.out.println("The daily fee has been added to the total fee and than reset");
                totalDayFee = 0;
                totalDayFee += getTollFeePerPassing(date);
                System.out.println("todays price of: " + getTollFeePerPassing(date) +
                        " has been added to the new daily fee");
            } else if(diffInMinutes >= 60 || intervalStart.equals(date) || totalDayFee == 0) {
                intervalStart = date;
                lastValue = getTollFeePerPassing(date);
                totalDayFee += getTollFeePerPassing(date);
                System.out.println("+ " + getTollFeePerPassing(date) + " to the daily fee");
            } else {
                currentValue = getTollFeePerPassing(date);
                if (currentValue > lastValue){
                    totalDayFee += currentValue-lastValue;
                    System.out.println("- " + lastValue);
                    System.out.println("+ " + currentValue);
                    System.out.println("This passage cost more than the last within one hour");
                    System.out.println("The cost of this passage will be applied insted of the last one");
                    lastValue = getTollFeePerPassing(date);
                } else {
                    System.out.println("One of the last passages within one hour was charged insted");
                    System.out.println("This passage was free");
                }
            } System.out.println(" ");
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
        System.out.println("\n==============NEW FILE===============\n");
        TollFeeCalculator("src\\Test1.txt");
        TollFeeCalculator("src\\Test2.txt");
        TollFeeCalculator("src\\Test3.txt");
        TollFeeCalculator("src\\Test4.txt");
    }
}