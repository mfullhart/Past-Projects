//Program to calculate tip based on service quality
import java.util.Scanner;

public class Tips {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //Collects user inputs
        System.out.println("Welcome to the tip calculator!");

        System.out.print("Enter the bill amount: ");
        double bill = input.nextDouble();

        System.out.print("Enter the service level(awful, bad, ok, good, great): ");
        String quality = input.next();

        //Set tip percentage as a double
        double tipPercentage;

        //If else statements to determine the correct tip percentage
        if (quality.equals("great"))
        {
            tipPercentage = 0.25;
        }
        else if (quality.equals("good"))
        {
            tipPercentage = 0.20;
        }
        else if (quality.equals("ok"))
        {
            tipPercentage = 0.15;
        }
        else if (quality.equals("bad"))
        {
            tipPercentage = 0.10;
        }
        else if (quality.equals("awful"))
        {
            tipPercentage = 0.0;
        }
        else
        {
            System.out.println("Invalid service level.");
            return;
        }

        //Equations to calculate tip amount and total bill
        double tipAmount = bill * tipPercentage;
        double newTotal = bill + tipAmount;

        //Prints tip and amount
        System.out.printf("The tip percent is %.0f%%\n", tipPercentage * 100);
        System.out.printf("The tip amount is $%.2f\n", tipAmount);
        System.out.printf("The new total is $%.2f\n", newTotal);

    }
}
