package org.alerts.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utilities {

    public String timeStringToCron(String timeString) {
        // Validate the input
        if (timeString == null || timeString.isEmpty()) {
            throw new IllegalArgumentException("Time string cannot be null or empty.");
        }

        // Split the time string based on ":" delimiter
        String[] timeParts = timeString.split(":");
        if (timeParts.length < 2 || timeParts.length > 3) {
            throw new IllegalArgumentException("Invalid time format. Expected hh:mm or hh:mm:ss.");
        }

        // Extract hours, minutes, and seconds (default seconds to 0 if not provided)
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = timeParts.length == 3 ? Integer.parseInt(timeParts[2]) : 0;

        // Validate the time range
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Invalid hour value. Hours must be between 0 and 23.");
        }
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Invalid minute value. Minutes must be between 0 and 59.");
        }
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Invalid second value. Seconds must be between 0 and 59.");
        }

        // Generate the cron expression
        return String.format("%d %d %d * * ?", seconds, minutes, hours);
    }

    public static double roundToTwoDecimalPlaces(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
