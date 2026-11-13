package czarnecki.damian.Services;

import czarnecki.damian.Models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectServices {

    public void CountTicketsPerProject()
    {
        Map<String,Long> ticketsPerProject = Ticket.getTickets().values().stream()
                .collect(Collectors.groupingBy(
                        ticket -> ticket.getProject().getShortAlias(),
                        Collectors.counting()
                ));
        System.out.println(ticketsPerProject);

    }
}
