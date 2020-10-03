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
    @DisplayName("Test if the first date is accounted for")
    void getTotalFeeCost(){
        // Checks if the first date is accounted for and gets debited, the first date should cost 18
        // The second should drive the price above 19, otherwise the first date isnt accounted

        String[] dateStrings = new String[] {"2020-10-14 15:45","2020-10-15 18:29"};
                                   // Dont touch this time ^                  ^Enter a time between 06:00 - 18:29
        LocalDateTime[] dateArray = new LocalDateTime[2];
        for(int i = 0; i < dateArray.length; i++) {
            dateArray[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        assertTrue(Main.getTotalFeeCost(dateArray) > 19);
    }
}
