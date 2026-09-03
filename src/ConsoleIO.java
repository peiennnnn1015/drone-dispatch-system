import java.util.Scanner;

/**
 * The {@code ConsoleIO} class provides a simple interface for handling
 * console-based input and output operations.
 * <p>
 * It wraps a {@link Scanner} object to read user input and provides utility
 * methods for displaying prompts and retrieving user responses as strings or
 * integers.
 * </p>
 */
public class ConsoleIO
{
    private final Scanner input;

    /**
     * Default constructor that initializes the scanner to read from standard
     * input ({@code System.in}).
     */
    public ConsoleIO()
    {
        input = new Scanner(System.in);
    }

    /**
     * Constructs a {@code ConsoleIO} object using a provided {@link Scanner}
     * instance.
     *
     * @param console the scanner to use for input
     */
    public ConsoleIO(Scanner console)
    {
        input = console;
    }

    /**
     * Closes the underlying scanner.
     * <p>
     * Once closed, no further input can be read using this object.
     * </p>
     */
    public void close()
    {
        input.close();
    }

    /**
     * Displays a message to the console.
     *
     * @param prompt the message to display
     */
    public void display(String prompt)
    {
        System.out.println(prompt);
    }

    /**
     * Prompts the user for input and returns the entered value as a string.
     *
     * @param prompt the message displayed to the user
     * @return the user's input as a string
     */
    public String getStringInput(String prompt)
    {
        System.out.print(prompt);

        String userInput;
        userInput = input.nextLine();

        return userInput;
    }

    /**
     * Gets validated contact number (basic format check).
     *
     * @param prompt the message displayed to the user
     * @return the validated contact number
     */
    public String getValidContactNumber(String prompt)
    {
        String input = getValidStringInput(prompt);
        boolean isValid = false;

        while (!isValid)
        {
            boolean allDigits = true;

            int i = 0;

            while (i < input.length() && allDigits)
            {
                if (!Character.isDigit(input.charAt(i)))
                {
                    allDigits = false;
                }

                i = i + 1;
            }

            if (!allDigits)
            {
                input = getValidStringInput(
                        "Oops, your phone number can only be digits! " +
                                "Please try again. " +
                                "\n\nEnter customer contact number: ");
            }
            else
            {
                isValid = true;
            }
        }

        return input;
    }

    /**
     * Gets a valid integer input from the user.
     *
     * @param prompt the message displayed to the user
     * @return the valid integer entered by the user
     */
    public int getValidIntInput(String prompt)
    {
        String input = getStringInput(prompt);
        boolean valid = false;
        int value = 0;

        while (!valid)
        {
            try
            {
                value = Integer.parseInt(input);
                valid = true;
            }
            catch (NumberFormatException e)
            {
                input = getStringInput("Invalid input. " +
                        "Please enter a valid number.\n\n" + prompt);
            }
        }

        return value;
    }

    /**
     * Gets validated string input that meets a minimum length requirement.
     *
     * @param prompt the message displayed to the user
     * @param minLength the minimum number of characters required
     * @return the validated input string
     */
    public String getValidStringByMinLength(String prompt, int minLength)
    {
        String input;
        boolean isValid;

        input = getValidStringInput(prompt);
        isValid = false;

        while (!isValid)
        {
            if (input.trim().length() < minLength)
            {
                input = getValidStringInput(
                        "Input must be at least " + minLength +
                                " characters. Please try again.\n\n" +
                                prompt);
            }
            else
            {
                isValid = true;
            }
        }

        String result;
        result = input.trim();

        return result;
    }

    /**
     * Gets validated string input that is not empty.
     *
     * @param prompt the message displayed to the user
     * @return the validated input string
     */
    public String getValidStringInput(String prompt)
    {
        String input;
        boolean isValid;

        input = getStringInput(prompt);
        isValid = false;

        while (!isValid)
        {
            if (input.isBlank())
            {
                input = getStringInput(
                        "Input cannot be empty. Please try again: ");
            }
            else
            {
                isValid = true;
            }
        }

        String result;
        result = input.trim();

        return result;
    }

    /**
     * Prints a blank line to the console.
     * <p>
     * This method is used to improve readability of console output by inserting
     * spacing between sections of text.
     * </p>
     */
    public void lineBreak()
    {
        System.out.println();
    }
}
