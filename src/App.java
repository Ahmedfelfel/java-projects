
import java.util.Scanner;

/**
 * Calendar Application This application prints the months of a given year with
 * the number of days in each month. It handles leap years for February.
 */
enum YearMonths {
    JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30),
    JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

    private final int days;

    YearMonths(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}

enum LeapYearMonths {
    JANUARY(31), FEBRUARY(29), MARCH(31), APRIL(30), MAY(31), JUNE(30),
    JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

    private final int days;

    LeapYearMonths(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Calendar App!");
            System.out.println("Enter the year (e.g., 2023): ");
            try {
                int year = scanner.nextInt();
                if (isLeapYear(year)) {
                    System.out.println(year + " is a leap year.");
                    for (LeapYearMonths month : LeapYearMonths.values()) {
                        printMonth(month.name(), month.getDays());
                    }
                } else {
                    System.out.println(year + " is not a leap year.");
                    for (YearMonths month : YearMonths.values()) {
                        printMonth(month.name(), month.getDays());
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid year.");
                scanner.next(); // Clear the invalid input
            } finally {
                System.out.println("Do you want to continue? (yes/no)");
                String response = scanner.next();
                if (!response.equalsIgnoreCase("yes")) {
                    System.out.println("Thank you for using the Calendar App!");
                    scanner.close();
                    break;
                }
            }
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void printMonth(String month, int max) {
        System.out.println("Month " + month + ":");
        for (int j = 1; j <= max; j++) {
            if (j % 7 == 1 && j != 1) {
                System.out.println();
            }
            System.out.print(" " + j);
        }
        System.out.println();
    }
}
