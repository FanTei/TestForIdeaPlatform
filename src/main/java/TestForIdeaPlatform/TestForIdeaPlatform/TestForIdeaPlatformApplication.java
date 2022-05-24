package TestForIdeaPlatform.TestForIdeaPlatform;

import TestForIdeaPlatform.TestForIdeaPlatform.entity.Ticket;
import TestForIdeaPlatform.TestForIdeaPlatform.entity.TicketList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication
public class TestForIdeaPlatformApplication {
    public static void main(String[] args) throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        TicketList ticketList = mapper.readValue(Paths.get("src/main/resources/tickets.json").toFile(), TicketList.class);
        List<Long> allFlightTimesInMinutes = getAllFlightTimesInMinutes(ticketList);
        long time = getAverageFlightTime(allFlightTimesInMinutes);
        long hours = time / 60;
        long minutes = time % 60;
        System.out.println("hours:" + hours + "\nminutes:" + minutes);
        long percentile = calculatePercentile(allFlightTimesInMinutes);
        System.out.println("Percentile");
        hours = percentile / 60;
        minutes = percentile % 60;
        System.out.println("hours:" + hours + "\nminutes:" + minutes);
    }

    private static List<Long> getAllFlightTimesInMinutes(TicketList ticketList) throws ParseException {
        List<Long> allFlightTimesInMinutes = new ArrayList<>();
        List<Ticket> tickets = ticketList.getTickets();
        SimpleDateFormat format = new SimpleDateFormat("dd:MM:yy");
        SimpleDateFormat parser = new SimpleDateFormat("dd:MM:yy:HH:mm");
        for (Ticket ticket : tickets) {
            String arrivalString = format.format(ticket.getArrivalDate()) + ":" + ticket.getArrivalTime();
            Date arrivalDate = parser.parse(arrivalString);
            String departureString = format.format(ticket.getDepartureDate()) + ":" + ticket.getDepartureTime();
            Date departureDate = parser.parse(departureString);
            long diff = arrivalDate.getTime() - departureDate.getTime();
            long diffToMinutes = TimeUnit.MILLISECONDS.toMinutes(diff);
            allFlightTimesInMinutes.add(diffToMinutes);
        }
        return allFlightTimesInMinutes;
    }

    private static long getAverageFlightTime(List<Long> allFlightTimesInMinutes) throws ParseException {
        long sum = allFlightTimesInMinutes.stream().mapToLong(Long::longValue).sum();
        return sum / allFlightTimesInMinutes.size();
    }

    private static long calculatePercentile(List<Long> allFlightTimesInMinutes) {
        Collections.sort(allFlightTimesInMinutes);
        int index = (int) Math.ceil(90 / 100.0 * allFlightTimesInMinutes.size());
        return allFlightTimesInMinutes.get(index - 1);
    }
}
