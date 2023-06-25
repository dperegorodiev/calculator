import java.util.Scanner;
import java.util.regex.Pattern;

public class Calculator extends Converter {
    private static Boolean isArabic = true;

    public static void main(String[] args) throws Exception {

        while (true) {

            Scanner console = new Scanner(System.in);
            System.out.print("Input:");
            String i = console.nextLine();

            String[] input = i.split(" ");
            if (input.length != 3) {
                throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            String operation = input[1];
            String num1 = input[0];
            String num2 = input[2];
            int a;
            int b;


            if (Pattern.matches("[a-zA-Z]+", num1) && (Pattern.matches("[a-zA-Z]+", num2))) {
                a = toArabic(num1);
                b = toArabic(num2);
                isArabic = false;
            } else if (Pattern.matches("[0-9]+", num1) && (Pattern.matches("[0-9]+", num2))) {
                a = Integer.parseInt(num1);
                b = Integer.parseInt(num2);
                isArabic = true;
            } else {
                throw new Exception("используются одновременно разные системы счисления");
            }
            if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
                System.out.println("Output:");
                System.out.println(calc(a, operation, b));

            } else {
                throw new Exception("используйте два числа от 1 до 10");
            }

        }
    }

    public static String calc(int a, String input, int b) throws Exception {

        int result = switch (input) {

            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("символ операции не поддерживается");
        };
        if (isArabic) {
            return Integer.toString(result);
        } else {
            if (result > 0) {
                return toRome(result);
            } else {
                throw new Exception("в римской системе нет отрицательных чисел");
            }

        }

    }
}
