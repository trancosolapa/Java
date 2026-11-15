import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== Calculadora Simples ===");

        while (continuar) {
            try {
                System.out.print("\nDigite o primeiro número: ");
                double num1 = scanner.nextDouble();

                System.out.print("Digite a operação (+, -, *, /): ");
                String operacao = scanner.next();

                System.out.print("Digite o segundo número: ");
                double num2 = scanner.nextDouble();

                double resultado = calcular(num1, num2, operacao);
                System.out.printf("Resultado: %.2f%n", resultado);

            } catch (ArithmeticException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }

            System.out.print("\nDeseja fazer outro cálculo? (s/n): ");
            String resposta = scanner.next();
            continuar = resposta.equalsIgnoreCase("s");
        }

        System.out.println("Encerrando a calculadora. Até mais!");
        scanner.close();
    }

    public static double calcular(double num1, double num2, String operacao) {
        switch (operacao) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("divisão por zero não é permitida.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("operação inválida. Use +, -, * ou /.");
        }
    }
}
