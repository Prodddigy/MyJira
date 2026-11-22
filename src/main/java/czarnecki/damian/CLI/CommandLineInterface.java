package czarnecki.damian.CLI;

import czarnecki.damian.Models.Project;
import czarnecki.damian.Models.Ticket;
import czarnecki.damian.Services.ProjectServices;
import czarnecki.damian.Services.TicketServices;

import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {


    private final TicketServices ticketServices;
    private final ProjectServices projectServices;
    private final TicketCreator ticketCreator;
    private final ConsoleInput command;

    private Project currentProject;
    private boolean running = true;

    public CommandLineInterface(TicketServices ticketServices, ProjectServices projectServices,
                                TicketCreator ticketCreator, ConsoleInput command) {
        this.ticketServices = ticketServices;
        this.projectServices = projectServices;
        this.ticketCreator = ticketCreator;
        this.command = command;
    }

    public void start()
    {
        System.out.println("""
                =================================
                             MyJira
                =================================
                Type 'help' to see available commands.
                """);
        while(running)
        {
            printPrompt();

            String input = command.readString("");

            if(input.isEmpty())
            {
                continue;
            }

            executeCommand(input);

        }
        System.out.println("byeeee o/");
    }

    private void executeCommand(String input)
    {
        String[] args =  input.trim().split("\\s+");

        String command = args[0].toLowerCase();

        switch(command)
        {
            case "help" -> showHelp();

            case "exit" -> running = false;

            case "choose-project" ->
            {
                System.out.println("choose");
                if(args.length<2)
                {
                    System.out.println("Usage: choose-project <project-alias>");
                    return;
                }
                System.out.println("choose2");
                chooseProject(args[1]);
            }
            case "show-projects" -> showCurrentProjects();

            case "create-ticket" -> {
                if(currentProject == null)
                {
                    System.out.println("You need to choose a project first.");
                    return;
                }
                ticketCreator.createTicket(currentProject);
            }

            case "show-tickets" ->
            {
                if(currentProject == null)
                {
                    System.out.println("You need to choose a project first.");
                    return;
                }
                showTicketsOfProject(ticketServices.ListAllOpenedTicketsOfProject(currentProject));
            }/* showTickets(args)*/

           // case "show-top-tickets" -> /*showTopTickets(args)*/;

            default -> System.out.println("Wrong command. Try 'help'.");
        }

    }

    private void showHelp()
    {
        System.out.println("""
                
                ========== AVAILABLE COMMANDS ==========
                
                GENERAL:
                  help
                  exit
                
                PROJECTS:
                  show-projects
                  choose-project <alias>
                  current-project
                
                TICKETS:
                  create-ticket
                  show-tickets [limit]
                  show-top-tickets <number>
                
                ========================================
                """);
    }

    private void printPrompt()
    {
        String projectAlias= currentProject == null
                ? "["+ConsoleColors.RED + "NO PROJECT" + ConsoleColors.RESET + "] > "
                :"["+ConsoleColors.CYAN + currentProject.getShortAlias()+ ConsoleColors.RESET + "] > ";
        System.out.println(projectAlias);


    }

    private void showCurrentProjects()
    {
        List<Project> projects = projectServices.findAll();

        if(projects.isEmpty())
        {
            System.out.println("No projects found.");
            return;
        }

        System.out.println("Projects:");
        for (Project project : projects) {
//TODO color each project with different color
            System.out.printf(
                    "%-8s | %s%n",
                    project.getShortAlias(),
                    project.getName()
            );
        }
    }

    private void chooseProject(String projectAlias)
    {
        System.out.println(projectAlias+ "is null?");
        Project project = projectServices.findByShortAlias(projectAlias).orElse(null);

        if(project == null)
        {
            System.out.println("Project not found.");
            return;
        }

        currentProject = project;
        System.out.println("Chosen project: " + project.getShortAlias());
    }
    private static final int TITLE_WIDTH = 25;
    private void showTicketsOfProject(List<Ticket> tickets)
    {




        if (tickets == null || tickets.isEmpty()) {
            System.out.println("No tickets found.");
            return;
        }

        System.out.println("\nKEY       PRIORITY   STATUS       TITLE");
        System.out.println("-----------------------------------------------");

        for (Ticket ticket : tickets) {

            String title = truncate(
                    ticket.getTitle(),
                    TITLE_WIDTH
            );

            System.out.printf(
                    "%-9s %-10s %-12s %s%n",
                    ticket.getTicketKey(),
                    ticket.getPriority(),
                    ticket.getStatus(),
                    title
            );
        }

        System.out.println("-----------------------------------------------");
        System.out.printf("Total: %d tickets%n", tickets.size());
    }



    private String truncate(String value, int maxLength) {

        if (value == null) {
            return "";
        }

        if (value.length() <= maxLength) {
            return value;
        }

        return value.substring(0, maxLength - 3) + "...";
    }

}
