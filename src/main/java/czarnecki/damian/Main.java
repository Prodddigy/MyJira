package czarnecki.damian;

import czarnecki.damian.CLI.CommandLineInterface;
import czarnecki.damian.CLI.ConsoleInput;
import czarnecki.damian.CLI.TicketCreator;
import czarnecki.damian.Models.Level;
import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Status;
import czarnecki.damian.Models.Ticket;
import czarnecki.damian.Repositories.ITicketRepository;
import czarnecki.damian.Repositories.InMemoryTicketRepository;
import czarnecki.damian.Repositories.ProjectRepository;
import czarnecki.damian.Services.ProjectServices;
import czarnecki.damian.Services.TaskServices;
import czarnecki.damian.Services.TicketServices;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        initializeTickets();

        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        InMemoryTicketRepository ticketRepository = new  InMemoryTicketRepository();
        ProjectRepository projectRepository = new  ProjectRepository();
        ProjectRepository.projects = new LinkedHashMap<>(Project.getProjects());
        System.out.println(Project.getProjects().size()+"size");
        for (var project: ProjectRepository.projects.values())
        {
            System.out.println(project);
        }
        for (var project: ProjectRepository.projects.keySet())
        {
            System.out.println(project);
        }


        TicketServices ticketServices = new TicketServices(ticketRepository);


        ProjectServices projectServices = new ProjectServices(projectRepository);

        TicketCreator ticketCreator = new TicketCreator(ticketServices,input);

        CommandLineInterface cli = new CommandLineInterface(ticketServices,
                projectServices,ticketCreator,input);
        // TicketServices ticketServices = new TicketServices(new );
        projectServices.CountTicketsPerProject();
        cli.start();




//      ticketServices.findAllTicketsWithHighestPriority("GAME");
//      ticketServices.printNotClosedTickets("GAME");

    //here start cmd interaction
    }


    public static void initializeTickets() {

        // Projects
        Project travShell = new Project("TravShell","TRAV","Travel planning application.");
//        travShell.setName("TravShell");
//        travShell.setShortAlias("TRAV");
//        travShell.setDescription("Travel planning application.");

        Project taskFlow = new Project("TaskFlow","TASK","Task and project management platform.");
//        taskFlow.setName("TaskFlow");
//        taskFlow.setShortAlias("TASK");
//        taskFlow.setDescription("Task and project management platform.");

        Project gameHub = new Project("GameHub","GAME","Game hub application.");
//        gameHub.setName("GameHub");
//        gameHub.setShortAlias("GAME");
//        gameHub.setDescription("Gaming community platform.");

        // Tickets
        new Ticket( "TRAV-1", "Implement user authentication",
                "Implement login, registration and logout functionality.",
                Level.CRITICAL, Level.HIGH, Status.WORKINPROGRESS,LocalDate.now(), travShell);

        new Ticket( "TRAV-2", "Fix Google Maps autocomplete",
                "Search suggestions sometimes fail to appear when typing quickly.",
                Level.HIGH, Level.MEDIUM, Status.OPEN,LocalDate.now(), travShell);

        new Ticket( "TRAV-3", "Improve responsive layout",
                "Adjust the application layout for mobile and tablet screens.",
                Level.MEDIUM, Level.LOW, Status.OPEN,LocalDate.now(), travShell);

        new Ticket( "TRAV-4", "Update application README",
                "Document installation, configuration and deployment.",
                Level.LOW, Level.LOW, Status.CLOSED,LocalDate.now(), travShell);

        new Ticket( "TASK-1", "Implement ticket filtering",
                "Allow users to filter tickets by status, priority and severity.",
                Level.HIGH, Level.MEDIUM, Status.OPEN,LocalDate.now().plusDays(2), taskFlow);

        new Ticket( "TASK-2", "Add project management",
                "Implement project creation, editing and deletion.",
                Level.HIGH, Level.HIGH, Status.WORKINPROGRESS,LocalDate.now().plusDays(2), taskFlow);

        new Ticket( "TASK-3", "Fix ticket ID generation",
                "Prevent duplicate ticket IDs after restarting the application.",
                Level.CRITICAL, Level.CRITICAL, Status.OPEN,LocalDate.now().plusDays(2), taskFlow);

        new Ticket( "TASK-4", "Improve ticket description formatting",
                "Improve the formatting of ticket descriptions in TXT exports.",
                Level.LOW, Level.LOW, Status.CLOSED,LocalDate.now().plusDays(2), taskFlow);

        new Ticket( "GAME-1", "Implement player inventory",
                "Add an inventory system for collecting and managing items.",
                Level.MEDIUM, Level.MEDIUM, Status.OPEN,LocalDate.now().plusDays(2), gameHub);

        new Ticket( "GAME-2", "Fix character movement",
                "Character occasionally gets stuck when moving near obstacles.",
                Level.HIGH, Level.HIGH, Status.WORKINPROGRESS,LocalDate.now().plusDays(2), gameHub);

        new Ticket( "GAME-3", "Optimize loading times",
                "Reduce initial loading times and improve asset loading.",
                Level.CRITICAL, Level.HIGH, Status.OPEN,LocalDate.now().plusDays(2), gameHub);

        new Ticket( "GAME-4", "Add audio settings",
                "Allow players to adjust music and sound effect volume.",
                Level.LOW, Level.MEDIUM, Status.CLOSED,LocalDate.now().plusDays(2), gameHub);
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