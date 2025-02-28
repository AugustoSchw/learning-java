import java.util.Scanner;

public class Tests {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.nextLine();
        int x;
        try {
            x = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println(e + " can't format to a number");
            return;
        }
        System.out.println(x);

    }


    public static void checkNumber(int number) {
        if (number > 0) {
            System.out.println("positive");
        } else if (number < 0) {
            System.out.println("negative");
        } else {
            System.out.println("zero");
        }
    }

    public static long toMilesPerHour(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            return -1;
        }
        return Math.round(kilometersPerHour / 1.609);
    }


    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0) {
            System.out.println("Invalid Value");
            return;
        }
        long result = toMilesPerHour(kilometersPerHour);

        System.out.println(kilometersPerHour + " km/h = " + result + " mi/h");
    }
}

