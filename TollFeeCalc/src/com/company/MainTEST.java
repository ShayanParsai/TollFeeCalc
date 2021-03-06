package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Main")
public class MainTEST {

    @Test
    @DisplayName("Testing DateTimeParse")
    void TollFeeCalculator1 () {
        String expectedOutput1 = "The format of the strings in the file are wrong, please adjust them";
        assertEquals(expectedOutput1,Main.TollFeeCalculator("src\\DateTimeParseExceptionTest.txt"));
    }

    @Test
    @DisplayName("Testing FileNotFount")
    void TollFeeCalculator2 () {
        String expectedOutput2 = "That file cannot be found";
        assertEquals(expectedOutput2,Main.TollFeeCalculator("src\\WrongFileName.txt"));
    }

    @Test
    @DisplayName("Testing NoSuchElement")
    void TollFeeCalculator3 () {
        String expectedOutput3 = "No elements can be found in that file";
        assertEquals(expectedOutput3,Main.TollFeeCalculator("src\\NoSuchElement.txt"));
    }

    @Test
    @DisplayName("Saturday,Sunday or July ? ")
    void isTollFreeDate(){

        LocalDateTime date1 = LocalDateTime.parse("2020-07-03 20:00"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date2 = LocalDateTime.parse("2020-06-07 20:00"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date3 = LocalDateTime.parse("2020-06-06 20:00"
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertTrue(Main.isTollFreeDate(date1));
        assertTrue(Main.isTollFreeDate(date2));
        assertTrue(Main.isTollFreeDate(date3));
    }

    @Test
    @DisplayName("Is the first and last date accounted for?")
    void getTotalFeeCost1(){

        String[] dateStrings = new String[] {"2020-10-14 15:45","2020-10-15 18:31","2020-10-16 17:01"};

        LocalDateTime[] dateArray = new LocalDateTime[dateStrings.length];
        for(int i = 0; i < dateArray.length; i++) {
            dateArray[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        System.out.println("Test 1" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray), 31);
    }

    @Test
    @DisplayName("Hour interval ignores free hours")
    void getTotalFeeCost2(){

        // If the first date is 05:01 and the second is 06:00, the second should still take payment

        String[] dateStrings2 = new String[] {"2020-10-14 05:45","2020-10-14 06:00","2020-10-14 06:55"};

        LocalDateTime[] dateArray2 = new LocalDateTime[dateStrings2.length];
        for(int i = 0; i < dateArray2.length; i++) {
            dateArray2[i] = LocalDateTime.parse(dateStrings2[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        System.out.println("Test 2" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray2), 13);
    }

    @Test
    @DisplayName("Most expensive of the hour payment")
    void getTotalFeeCost3(){

        // This makes sure that the most expensive price within one hour range is used, not the first one

        String[] dateStrings3 = new String[] {"2020-09-23 06:01", "2020-09-23 07:00", "2020-09-23 06:30"};

        LocalDateTime[] dateArray3 = new LocalDateTime[dateStrings3.length];
        for(int i = 0; i < dateArray3.length; i++) {
            dateArray3[i] = LocalDateTime.parse(dateStrings3[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        System.out.println("Test 3" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray3), 18);
    }

    @Test
    @DisplayName("Max 60 per day")
    void getTotalFeeCost4(){

        String[] dateStrings4 = new String[] {"2020-10-01 06:10", "2020-10-02 06:34",
                "2020-10-05 07:25", "2020-10-06 08:20", "2020-10-07 11:00", "2020-10-08 06:00",
                "2020-10-08 07:00", "2020-10-08 08:00", "2020-10-08 09:00", "2020-10-08 10:00",
                "2020-10-08 11:00", "2020-10-08 12:00", "2020-10-08 13:00", "2020-10-08 14:00",
                "2020-10-08 15:00", "2020-10-08 16:00", "2020-10-08 17:00", "2020-10-09 15:48",
                "2020-10-12 17:29", "2020-10-13 18:01", "2020-10-14 18:40"};

        LocalDateTime[] dateArray4 = new LocalDateTime[dateStrings4.length];
        for(int i = 0; i < dateArray4.length; i++) {
            dateArray4[i] = LocalDateTime.parse(dateStrings4[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        System.out.println("Test 4" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray4), 159);
    }

    @Test
    @DisplayName("Test if all the set times take the proper fee")
    void getTollFeePerPassing (){

        LocalDateTime date1 = LocalDateTime.parse("2020-10-01 06:10"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date2 = LocalDateTime.parse("2020-10-02 06:34"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date3 = LocalDateTime.parse("2020-10-05 07:25"
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date4 = LocalDateTime.parse("2020-10-06 08:20"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date5 = LocalDateTime.parse("2020-10-07 11:00"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date6 = LocalDateTime.parse("2020-10-08 15:25"
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date7 = LocalDateTime.parse("2020-10-09 15:48"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date8 = LocalDateTime.parse("2020-10-12 17:29"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date9 = LocalDateTime.parse("2020-10-13 18:01"
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date10 = LocalDateTime.parse("2020-10-14 18:40"
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date11 = LocalDateTime.parse("2020-10-15 05:30"
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertEquals(Main.getTollFeePerPassing(date1),8);
        assertEquals(Main.getTollFeePerPassing(date2),13);
        assertEquals(Main.getTollFeePerPassing(date3),18);
        assertEquals(Main.getTollFeePerPassing(date4),13);
        assertEquals(Main.getTollFeePerPassing(date5),8);
        assertEquals(Main.getTollFeePerPassing(date6),13);
        assertEquals(Main.getTollFeePerPassing(date7),18);
        assertEquals(Main.getTollFeePerPassing(date8),13);
        assertEquals(Main.getTollFeePerPassing(date9),8);
        assertEquals(Main.getTollFeePerPassing(date10),0);
        assertEquals(Main.getTollFeePerPassing(date11),0);
    }
}
