// Program to calculate various distances

// Import scanner
import java.util.Scanner;

// Declare class
public class Distance
{

    // Declare Method
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        double x1;
        double y1;
        double x2;
        double y2;
        double euclideanDistance;
        double simDistance;
        int manhattanDistance;
        int x1Int;
        int y1Int;
        int x2Int;
        int y2Int;

        // Get user input for double values
        System.out.println("Enter x1, y1, x2, y2 (double): ");
        x1 = input.nextDouble();
        y1 = input.nextDouble();
        x2 = input.nextDouble();
        y2 = input.nextDouble();

        // Test the distance calculation methods for double values
        euclideanDistance = euclid(x1, y1, x2, y2);
        System.out.println("Euclidean Distance (double): " + euclideanDistance);

        simDistance = sim(x1, y1, x2, y2);
        System.out.println("Sim Distance (double): " + simDistance);

        // Declare & get user input for int values
        System.out.println("Enter x1, y1, x2, y2 (int): ");
        x1Int = input.nextInt();
        y1Int = input.nextInt();
        x2Int = input.nextInt();
        y2Int = input.nextInt();

        // Test the distance calculation methods for int values
        manhattanDistance = manhattan(x1Int, y1Int, x2Int, y2Int);
        System.out.println("Manhattan Distance (int): " + manhattanDistance);

        // Close the scanner to avoid resource leaks
        input.close();
    }

    // Euclidean distance calc
    public static double euclid(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // Manhattan distance calc
    public static int manhattan(int x1, int y1, int x2, int y2)
    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // Sim distance calc
    public static double sim(double x1, double y1, double x2, double y2)
    {
        double num = (x1 * x2) + (y1 * y2);
        double den = norm(x1, y1) * norm(x2, y2);
        return num / den;
    }

    // Method to calculate the norm
    private static double norm(double x, double y)
    {
        return Math.sqrt(x * x + y * y);
    }

}
