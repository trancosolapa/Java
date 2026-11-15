import java.util.Scanner;

public class Lanchonete {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double total = 0.0;
        String itensPedido = "";
        boolean continuar = true;

        while (continuar) {

            System.out.println("----- Menu de Opções Lanchonete -----");
            System.out.println("1. Hamburguer - R$ 10,00");
            System.out.println("2. X-Burguer - R$ 12,00");
            System.out.println("3. Refrigerante - R$ 5,00");
            System.out.println("4. Batata Frita - R$ 8,00");
            System.out.println("5. Cachorro-Quente - R$ 9,00");
            System.out.println("6. Pizza - R$ 15,00");
            System.out.println("7. Pastel - R$ 7,00");
            System.out.println("8. Suco Natural - R$ 6,00");
            System.out.println("9. Milk-shake - R$ 10,00");
            System.out.println("10. Nuggets - R$ 11,00");
            System.out.println("11. Finalizar Pedido");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    total += 10.00;
                    itensPedido += "Hamburguer\n";
                    System.out.println("Hamburguer adicionado!");
                    break;

                case 2:
                    total += 12.00;
                    itensPedido += "X-Burguer\n";
                    System.out.println("X-Burguer adicionado!");
                    break;

                case 3:
                    total += 5.00;
                    itensPedido += "Refrigerante\n";
                    System.out.println("Refrigerante adicionado!");
                    break;

                case 4:
                    total += 8.00;
                    itensPedido += "Batata Frita\n";
                    System.out.println("Batata Frita adicionada!");
                    break;

                case 5:
                    total += 9.00;
                    itensPedido += "Cachorro-Quente\n";
                    System.out.println("Cachorro-Quente adicionado!");
                    break;

                case 6:
                    total += 15.00;
                    itensPedido += "Pizza\n";
                    System.out.println("Pizza adicionada!");
                    break;

                case 7:
                    total += 7.00;
                    itensPedido += "Pastel\n";
                    System.out.println("Pastel adicionado!");
                    break;

                case 8:
                    total += 6.00;
                    itensPedido += "Suco Natural\n";
                    System.out.println("Suco Natural adicionado!");
                    break;

                case 9:
                    total += 10.00;
                    itensPedido += "Milk-shake\n";
                    System.out.println("Milk-shake adicionado!");
                    break;

                case 10:
                    total += 11.00;
                    itensPedido += "Nuggets\n";
                    System.out.println("Nuggets adicionados!");
                    break;

                case 11:
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida, escolha um número de 1 a 11!");
                    break;
            }

            System.out.println();
        }

        System.out.println("\n----- Resumo do Pedido -----");

        if (itensPedido.equals("")) {
            System.out.println("Nenhum item selecionado.");
        } else {
            System.out.print(itensPedido);
        }

        System.out.println("----------------------------");
        System.out.printf("Valor total: R$ %.2f\n", total);
        System.out.println("Obrigado pela preferência!");

        scanner.close();
    }
}
