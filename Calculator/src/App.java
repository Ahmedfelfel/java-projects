
import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            System.out.print("Enter your calculation only (+,-,*,/): ");
            String num = "";
            String input = scan.nextLine();
            Stack<Character> stack = new Stack<>();
            Stack<String> numStack = new Stack<>(); // Stack to hold numbers as strings
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (Character.isDigit(c) || c == '.' || c == '+' || c == '-' || c == '*' || c == '/') {
                    if (Character.isDigit(c)) {
                        num += c; // Collect digits to form a number
                    } else {
                        numStack.push(num); // Push the number to the stack
                        num = ""; // Reset num for the next number
                        stack.push(c);
                    }
                } else {
                    System.out.println("Invalid character: " + c);
                    return;
                }
            }
            System.out.println("Stack contents: " + stack);
            System.out.println("Number stack contents: " + numStack);
            double result = 0;
            while (!stack.isEmpty() || !numStack.isEmpty()) {
                result += Double.parseDouble(numStack.pop()); // Pop the last number from numStack
                char c = stack.pop();
                if (c == '+') {
                    result += Double.parseDouble(numStack.pop());
                } else if (c == '-') {
                    result -= Double.parseDouble(numStack.pop());
                } else if (c == '*') {
                    result *= Double.parseDouble(numStack.pop());
                } else if (c == '/') {
                    double divisor = Double.parseDouble(numStack.pop());
                    if (divisor != 0) {
                        result /= divisor;
                    } else {
                        System.out.println("Division by zero is not allowed.");
                        return;
                    }
                }
            }
            System.out.println("Result: " + result);
            //scan.close();
            if (stack.isEmpty()) {
                System.out.println("Calculation completed successfully.");
            } else {
                System.out.println("There are still items in the stack, indicating an incomplete calculation.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scan.close();
        }
    }
}
