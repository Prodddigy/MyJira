package czarnecki.damian.CLI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleInput {

    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }


    public String readString(String prompt)
    {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public String readNonEmptyString(String prompt)
    {
        while (true)
        {
         String value = readString(prompt);
         if(!value.isBlank())
             return value;

         System.out.println("Input cannot be empty. Try again.");
         // TODO let user exit the creation
        }
    }

    public int readInt(String prompt)
    {
        while(true)
        {
            String value = readString(prompt);

            try {
                return Integer.parseInt(value);
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public <T extends Enum<T>> T readEnum(String prompt, Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        System.out.println(prompt);
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d. %s%n", i + 1, values[i]);
        }
        while(true)
        {
            int choice = readInt("> ");

            if(choice >=1 && choice <= values.length)
            {
                return values[choice-1];
            }
            System.out.println("Please enter a valid option.");
        }
    }

    public LocalDate readDate(String prompt)
    {
        while(true)
        {
            String value = readString(prompt);

            try{
                return LocalDate.parse(value);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date. Use YYYY-MM-DD (e.g. 2026-11-21).");
            }
        }
    }

    public String readOptionaString(String prompt)
    {
        String value = readString(prompt);
        return value.isBlank() ? null : value;
    }

    public boolean readYesNo(String prompt)
    {
        while(true)
        {
            String value = readString(prompt).toLowerCase();

            switch(value)
            {
                case "y","yes" ->{ return true;}
                case "n","no" ->{ return false;}
                default -> System.out.println("Please enter a valid option. Yes or no.");
            }
        }


    }
}
