package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Test two bugs")
    void getTotalFeeCost(){
        // Test 1
        // Checks if the first date is accounted for and gets debited, the first date should cost 18
        // The second should drive the price above 19, otherwise the first date isnt accounted

        String[] dateStrings = new String[] {"2020-10-14 15:45","2020-10-15 18:29"};
                                   // Dont touch this time ^                  ^Enter a time between 06:00 - 18:29
        LocalDateTime[] dateArray = new LocalDateTime[2];
        for(int i = 0; i < dateArray.length; i++) {
            dateArray[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        // Test 2
        // If the first date is 05:01 and the second is 06:00, the second should still take payment
        // due to the first being a free hour, this test makes sure that if the first passage is free,
        // the next one should charge even if its within an hour

        String[] dateStrings2 = new String[] {"2020-10-14 05:30" // <- Set this time between 05:01 to 05:59
                ,"2020-10-14 06:00"};// <- Dont touch this time

        LocalDateTime[] dateArray2 = new LocalDateTime[2];
        for(int i = 0; i < dateArray2.length; i++) {
            dateArray2[i] = LocalDateTime.parse(dateStrings2[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }

        assertTrue(Main.getTotalFeeCost(dateArray) > 19 && Main.getTotalFeeCost(dateArray2) > 1);
        // The test can be run on one separetly aswell, by just removing one of the conditions above
    }
}
