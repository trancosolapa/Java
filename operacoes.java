import java.util.Scanner;

public class CalculadoraMatematica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("----- Menu de Opções -----");
            System.out.println("1. Saber se o número é par ou ímpar");
            System.out.println("2. Calcular o fatorial de um número");
            System.out.println("3. Calcular a área de um retângulo");
            System.out.println("4. Encontrar a área de um triângulo");
            System.out.println("5. Calcular a média de duas notas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Digite um número: ");
                    int num = scanner.nextInt();

                    if (num % 2 == 0) {
                        System.out.println("Par");
                    } else {
                        System.out.println("Ímpar");
                    }
                    break;

                case 2:
                    System.out.print("Escolha um número: ");
                    int n = scanner.nextInt();

                    long fatorial = 1;

                    for (int i = 1; i <= n; i++) {
                        fatorial *= i;
                    }

                    System.out.println("O fatorial de " + n + " é " + fatorial);
                    break;

                case 3:
                    System.out.print("Digite a base do retângulo: ");
                    double baseR = scanner.nextDouble();

                    System.out.print("Digite a altura do retângulo: ");
                    double alturaR = scanner.nextDouble();

                    double areaR = baseR * alturaR;

                    System.out.println("A área do retângulo é: " + areaR);
                    break;

                case 4:
                    System.out.print("Digite a base do triângulo: ");
                    double baseT = scanner.nextDouble();

                    System.out.print("Digite a altura do triângulo: ");
                    double alturaT = scanner.nextDouble();

                    double areaT = (baseT * alturaT) / 2;

                    System.out.println("A área do triângulo é: " + areaT);
                    break;

                case 5:
                    System.out.print("Digite a primeira nota: ");
                    double nota1 = scanner.nextDouble();

                    System.out.print("Digite a segunda nota: ");
                    double nota2 = scanner.nextDouble();

                    double media = (nota1 + nota2) / 2;

                    System.out.println("A média das notas é: " + media);
                    break;

                case 0:
                    System.out.println("Programa encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente!");
            }

            System.out.println();

        } while (opcao != 0);

        scanner.close();
    }
}
