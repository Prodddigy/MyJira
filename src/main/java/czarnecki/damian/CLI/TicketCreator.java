package czarnecki.damian.CLI;

import czarnecki.damian.Models.*;
import czarnecki.damian.Models.DTO.TicketDTO;
import czarnecki.damian.Services.TicketServices;

import java.time.LocalDate;

public class TicketCreator {

    private final TicketServices ticketServices;
    private final ConsoleInput input;

    public TicketCreator(TicketServices ticketServices, ConsoleInput input) {
        this.ticketServices = ticketServices;
        this.input = input;
    }

    public void createTicket(Project currentProject)
    {
        if(currentProject == null)
        {
            System.out.println("Please select a project first.");
            return;
        }

        System.out.println("Creating ticket...");
        String title = input.readNonEmptyString("Title: ");
        String description = input.readString("Description: ");
        Level priority = input.readEnum("Priority: ", Level.class);
        Level severity = input.readEnum("Severity: ", Level.class);
        Status status = input.readEnum("Status: ", Status.class);
        Task task = null;

        if(input.readYesNo("Assign to task? "))
        {
            //TODO assign to task optional
        }

        LocalDate createdAt = LocalDate.now();

        TicketDTO ticketDTO = new TicketDTO( title,
                description,
                priority,
                severity,
                status,
                createdAt,
                currentProject,
                task);
//        try{
            Ticket ticket = ticketServices.createTicket(ticketDTO);

            System.out.println("Ticket created successfully:" + ticket.getTicketKey());
//        } catch (Exception e) {
//            System.out.println("Error creating ticket:" + e.getMessage());
//        }
    }
}
