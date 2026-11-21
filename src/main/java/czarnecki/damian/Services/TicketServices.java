package czarnecki.damian.Services;

import czarnecki.damian.Models.DTO.TicketDTO;
import czarnecki.damian.Models.Level;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Ticket;
import czarnecki.damian.Repositories.ITicketRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TicketServices {

    private final ITicketRepository ticketRepository;

    public TicketServices(ITicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public Ticket createTicket(TicketDTO ticketDTO)
    {
        validateRequest(ticketDTO);

        Ticket ticket = new Ticket();
        ticket.setId();
        ticket.setTitle(ticketDTO.title());
        ticket.setDescription(ticketDTO.description());
        ticket.setProject(ticketDTO.project());
        ticket.setCreatedAt(ticketDTO.date());
        ticket.setSeverity(ticketDTO.severity());
        ticket.setPriority(ticketDTO.priority());
        ticket.setTicketKey();
        ticket.setTask(ticketDTO.task());



        return ticketRepository.Save(ticket);
    }

    private void validateRequest(TicketDTO ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Ticket request cannot be null."
            );
        }

        if (ticket.title() == null || ticket.title().isBlank()) {
            throw new IllegalArgumentException(
                    "Ticket title cannot be empty."
            );
        }

        if (ticket.project() == null) {
            throw new IllegalArgumentException(
                    "Ticket must belong to a project."
            );
        }

        if (ticket.priority() == null ||
                ticket.severity() == null ||
                ticket.status() == null) {

            throw new IllegalArgumentException(
                    "Priority, severity and status are required."
            );
        }
    }


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
