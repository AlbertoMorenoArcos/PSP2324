package ut01.Java;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Fecha {
    public static void main(String[] args) {
        String direc = "/home/alberto";
        String home = direc.replaceFirst("\\/", "");
                String home2 = home.replaceAll("\\/", "_");
                System.out.println(home2);
    }
}
