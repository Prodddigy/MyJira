package czarnecki.damian.Services;

import czarnecki.damian.Models.Level;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Ticket;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TicketServices {

    public void findTicketsWithHighestPriority()
    {
        List<Ticket> highPriorityTickets = Ticket.getTickets().values()
                .stream()
                .filter(t -> t.getStatus() == Status.WORKINPROGRESS)
                .filter(t-> t.getPriority() == Level.HIGH)
                .toList();

    }
    public void sortTicketsByPriority()
    {
        List<Ticket> sortedTickets = Ticket.getTickets().values()
                .stream()
                .sorted(
                        Comparator.comparing(Ticket::getPriority)
                                .thenComparing(Ticket::getSeverity))
                //    .thenComparing()
                .toList();
    }

    public void findAllTicketsWithHighestPriority(String projectCodeName)
    {
        List<Ticket> sortedTickets = Ticket.getTickets().values().stream()
                .filter(t -> Objects.equals(t.getProject().getShortAlias(), projectCodeName))
                .filter(t -> t.getPriority() == Level.CRITICAL)
                .toList();
        System.out.println("Found " + sortedTickets.size() + " tickets with highest priority");

    }


    public void printNotClosedTickets(String projectAlias) {

        List<Ticket> filteredTickets = Ticket.getTickets().values().stream()
                .filter(ticket -> ticket.getProject() != null)
                .filter(ticket -> ticket.getProject().getShortAlias()
                        .equalsIgnoreCase(projectAlias))
                .filter(ticket -> ticket.getStatus() != Status.CLOSED)
                .sorted(Comparator.comparing(Ticket::getTicketKey))
                .toList();

        if (filteredTickets.isEmpty()) {
            System.out.println("No active tickets found for project: " + projectAlias);
            return;
        }

        System.out.println("\nActive tickets for project: " + projectAlias);
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-12s | %s%n", "TICKET KEY", "TITLE");
        System.out.println("------------------------------------------------------------");

        for (Ticket ticket : filteredTickets) {
            System.out.printf("%-12s | %s%n",
                    ticket.getTicketKey(),
                    ticket.getTitle());
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total: " + filteredTickets.size() + " ticket(s)\n");
    }

}
