package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Calculator")
public class MainTEST {

    @Test
    @DisplayName("Saturday,Sunday or July ? ")
    void isTollFreeDate(){
        // Insert 3 dates that are weekends or during july month, if the test passes the method works

        LocalDateTime date1 = LocalDateTime.parse("2020-07-03 20:00" // Date1
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date2 = LocalDateTime.parse("2020-06-07 20:00" // date2
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date3 = LocalDateTime.parse("2020-06-06 20:00" // date3
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        assertTrue(Main.isTollFreeDate(date1));
        assertTrue(Main.isTollFreeDate(date2));
        assertTrue(Main.isTollFreeDate(date3));
    }

    @Test
    @DisplayName("Test 4 bugs")
    void getTotalFeeCost(){
        // Test 1
        // Checks if the first and last date is accounted for and gets debited, the first date should cost 18
        // The third should get the price to 31, if either the first or third isnt accounted for, the test fails

        String[] dateStrings = new String[] {"2020-10-14 15:45","2020-10-15 18:31","2020-10-16 17:01"};

        LocalDateTime[] dateArray = new LocalDateTime[dateStrings.length];
        for(int i = 0; i < dateArray.length; i++) {
            dateArray[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        // Test 2
        // If the first date is 05:01 and the second is 06:00, the second should still take payment
        // due to the first being a free hour, this test makes sure that if the first passage is free
        // than the interval of 1 hour should not be triggered by it.

        String[] dateStrings2 = new String[] {"2020-10-14 05:45","2020-10-14 06:00","2020-10-14 06:55"};

        LocalDateTime[] dateArray2 = new LocalDateTime[dateStrings2.length];
        for(int i = 0; i < dateArray2.length; i++) {
            dateArray2[i] = LocalDateTime.parse(dateStrings2[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        // Test 3
        // This makes sure that the most expensive price within one hour range is used, not the first one
        // We test 3 diffrent dates that are all within one hour , and see if it returns the highest of those 3

        String[] dateStrings3 = new String[] {"2020-09-23 06:01", "2020-09-23 07:00", "2020-09-23 06:30"};

        LocalDateTime[] dateArray3 = new LocalDateTime[dateStrings3.length];
        for(int i = 0; i < dateArray3.length; i++) {
            dateArray3[i] = LocalDateTime.parse(dateStrings3[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        // Test 4
        // This test makes sure that the total fee can go above 60 if the inputfile contains passages
        // that isnt on the same day, while still keeping the maximum daily fee at 60.

        String[] dateStrings4 = new String[] {"2020-10-01 06:10", "2020-10-02 06:34",
                "2020-10-05 07:25", "2020-10-06 08:20",
                "2020-10-07 11:00", "2020-10-08 06:00",
                "2020-10-08 07:00", "2020-10-08 08:00",
                "2020-10-08 09:00", "2020-10-08 10:00",
                "2020-10-08 11:00", "2020-10-08 12:00",
                "2020-10-08 13:00", "2020-10-08 14:00",
                "2020-10-08 15:00", "2020-10-08 16:00",
                "2020-10-08 17:00", "2020-10-09 15:48",
                "2020-10-12 17:29", "2020-10-13 18:01",
                "2020-10-14 18:40"};

        LocalDateTime[] dateArray4 = new LocalDateTime[dateStrings4.length];
        for(int i = 0; i < dateArray4.length; i++) {
            dateArray4[i] = LocalDateTime.parse(dateStrings4[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        System.out.println("Test 1" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray), 31);
        System.out.println("Test 2" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray2), 13);
        System.out.println("Test 3" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray3), 18);
        System.out.println("Test 4" + "\n--------");
        assertEquals(Main.getTotalFeeCost(dateArray4), 159);
    }

    @Test
    @DisplayName("Test if all the set times take the proper fee")
    void getTollFeePerPassing (){

        LocalDateTime date1 = LocalDateTime.parse("2020-10-01 06:10" // Date1
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date2 = LocalDateTime.parse("2020-10-02 06:34" // date2
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date3 = LocalDateTime.parse("2020-10-05 07:25" // date3
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date4 = LocalDateTime.parse("2020-10-06 08:20" // Date4
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date5 = LocalDateTime.parse("2020-10-07 11:00" // date5
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date6 = LocalDateTime.parse("2020-10-08 15:25" // date6
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date7 = LocalDateTime.parse("2020-10-09 15:48" // Date7
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date8 = LocalDateTime.parse("2020-10-12 17:29" // date8
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date9 = LocalDateTime.parse("2020-10-13 18:01" // date9
                , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date10 = LocalDateTime.parse("2020-10-14 18:40" // date10
                ,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime date11 = LocalDateTime.parse("2020-10-15 05:30" // date11
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
