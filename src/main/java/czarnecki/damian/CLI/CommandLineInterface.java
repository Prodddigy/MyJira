package czarnecki.damian.CLI;

import czarnecki.damian.Models.Project;
import czarnecki.damian.Services.ProjectServices;
import czarnecki.damian.Services.TicketServices;

import java.util.Scanner;

public class CommandLineInterface {

    private final Scanner scanner;
    private final TicketServices ticketServices;
    private final ProjectServices projectServices;

    private Project currentProject;
    private boolean running = true;

    public CommandLineInterface( TicketServices ticketServices, ProjectServices projectServices) {
        this.scanner = new  Scanner(System.in);
        this.ticketServices = ticketServices;
        this.projectServices = projectServices;
    }

    public void start()
    {
        System.out.println("Welcome to MyJira.");
        System.out.println("Use `help` for available options.");

        while(running)
        {
            printPrompt();

            String input = scanner.nextLine().trim();

            if(input.isEmpty())
            {
                continue;
            }

            executeCommand(input);

        }
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
                    System.out.println("Usage: chooose-project <project-name>");
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

                Available commands:

                help
                exit
                show-projects
                choose-project <alias>
                current-project
                create-ticket
                show-tickets [limit]
                show-top-tickets <number>

                """);
    }

    private void printPrompt()
    {
        String projectAlias= currentProject == null
                ? "NO PROJECT"
                :currentProject.getShortAlias();
        System.out.println("["+ConsoleColors.CYAN + projectAlias + ConsoleColors.RESET + "] > ");


    }


}
