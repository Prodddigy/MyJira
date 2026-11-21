package czarnecki.damian.CLI;

import czarnecki.damian.Models.Project;
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
                    TICKET MANAGEMENT SYSTEM
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
        String[] args = input.split("\\s+ ");

        String command = args[0].toLowerCase();

        switch(command)
        {
            case "help" -> showHelp();

            case "exit" -> running = false;

            case "choose-project" ->
            {
                if(args.length<2)
                {
                    System.out.println("Usage: chooose-project <project-alias>");
                    return;
                }

                chooseProject(args[1]);
            }
            case "current-projects" -> showCurrentProjects();

            case "create-ticket" -> createTicket();

            case "show-tickets" -> showTickets(args);

            case "show-top-tickets" -> showTopTickets(args);

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
        List<Project> projects = projectServices.
    }


}
