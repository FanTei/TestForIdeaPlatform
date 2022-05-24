package TestForIdeaPlatform.TestForIdeaPlatform.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TicketList {
    private List<Ticket> tickets;

    public TicketList() {
        this.tickets = new ArrayList<>();
    }
}
