package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing Calculator")
public class MainTEST {

    private final Main testingMain = new Main();

    @Test
    @DisplayName("Lördag/Söndag/Juli ? ")
    void isTollFreeDate(){

        LocalDateTime date1 = LocalDateTime.parse("2020-07-03 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Juli månad

        LocalDateTime date2 = LocalDateTime.parse("2020-06-07 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Söndag

        LocalDateTime date3 = LocalDateTime.parse("2020-06-06 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); // Måndag

        assertTrue(Main.isTollFreeDate(date1));
        assertTrue(Main.isTollFreeDate(date2));
        assertTrue(Main.isTollFreeDate(date3));
    }

    @Test
    @DisplayName("Test if 2 drives is on the same day")
    void getTotalFeeCost(){

    }


    @Test
    @DisplayName("Test for first instance of date")
    void aaa(){

    }
}
