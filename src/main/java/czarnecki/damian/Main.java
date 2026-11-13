package czarnecki.damian;

import czarnecki.damian.Models.Level;
import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Ticket;
import czarnecki.damian.Services.ProjectServices;
import czarnecki.damian.Services.TaskServices;
import czarnecki.damian.Services.TicketServices;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    initializeTickets();
      ProjectServices projectServices = new ProjectServices();
      TicketServices ticketServices = new TicketServices();




      projectServices.CountTicketsPerProject();

      ticketServices.findAllTicketsWithHighestPriority("GAME");
      ticketServices.printNotClosedTickets("GAME");

    //here start cmd interaction
    }


    public static void initializeTickets() {

        // Projects
        Project travShell = new Project();
        travShell.setName("TravShell");
        travShell.setShortAlias("TRAV");
        travShell.setDescription("Travel planning application.");

        Project taskFlow = new Project();
        taskFlow.setName("TaskFlow");
        taskFlow.setShortAlias("TASK");
        taskFlow.setDescription("Task and project management platform.");

        Project gameHub = new Project();
        gameHub.setName("GameHub");
        gameHub.setShortAlias("GAME");
        gameHub.setDescription("Gaming community platform.");

        // Tickets
        new Ticket(1L, "TRAV-1", "Implement user authentication",
                "Implement login, registration and logout functionality.",
                Level.CRITICAL, Level.HIGH, Status.WORKINPROGRESS, travShell);

        new Ticket(2L, "TRAV-2", "Fix Google Maps autocomplete",
                "Search suggestions sometimes fail to appear when typing quickly.",
                Level.HIGH, Level.MEDIUM, Status.OPEN, travShell);

        new Ticket(3L, "TRAV-3", "Improve responsive layout",
                "Adjust the application layout for mobile and tablet screens.",
                Level.MEDIUM, Level.LOW, Status.OPEN, travShell);

        new Ticket(4L, "TRAV-4", "Update application README",
                "Document installation, configuration and deployment.",
                Level.LOW, Level.LOW, Status.CLOSED, travShell);

        new Ticket(5L, "TASK-1", "Implement ticket filtering",
                "Allow users to filter tickets by status, priority and severity.",
                Level.HIGH, Level.MEDIUM, Status.OPEN, taskFlow);

        new Ticket(6L, "TASK-2", "Add project management",
                "Implement project creation, editing and deletion.",
                Level.HIGH, Level.HIGH, Status.WORKINPROGRESS, taskFlow);

        new Ticket(7L, "TASK-3", "Fix ticket ID generation",
                "Prevent duplicate ticket IDs after restarting the application.",
                Level.CRITICAL, Level.CRITICAL, Status.OPEN, taskFlow);

        new Ticket(8L, "TASK-4", "Improve ticket description formatting",
                "Improve the formatting of ticket descriptions in TXT exports.",
                Level.LOW, Level.LOW, Status.CLOSED, taskFlow);

        new Ticket(9L, "GAME-1", "Implement player inventory",
                "Add an inventory system for collecting and managing items.",
                Level.MEDIUM, Level.MEDIUM, Status.OPEN, gameHub);

        new Ticket(10L, "GAME-2", "Fix character movement",
                "Character occasionally gets stuck when moving near obstacles.",
                Level.HIGH, Level.HIGH, Status.WORKINPROGRESS, gameHub);

        new Ticket(11L, "GAME-3", "Optimize loading times",
                "Reduce initial loading times and improve asset loading.",
                Level.CRITICAL, Level.HIGH, Status.OPEN, gameHub);

        new Ticket(12L, "GAME-4", "Add audio settings",
                "Allow players to adjust music and sound effect volume.",
                Level.LOW, Level.MEDIUM, Status.CLOSED, gameHub);
    }
/*
    private static void addTicket(
            Long id,
            String ticketKey,
            String title,
            String description,
            Level priority,
            Level severity,
            Status status,
            Project project) {

        Ticket ticket = new Ticket();

        ticket.setId(id);
        ticket.setTicketKey(ticketKey);
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setPriority(priority);
        ticket.setSeverity(severity);
        ticket.setStatus(status);
        ticket.setProject(project);
        ticket.setTask(null);

        tickets.put(id, ticket);
    }

 */

}