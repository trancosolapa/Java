class ContaBancaria {
    private String titular;
    private double saldo;
    private String numeroConta;

    ContaBancaria(String titular, String numeroConta, double saldo) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Deposito realizado com sucesso!");
        } else {
            System.out.println("Valor invalido!");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Saldo insuficiente ou valor invalido!");
        }
    }

    public void transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferencia realizada!");
        } else {
            System.out.println("Transferencia nao realizada!");
        }
    }

    public void mostrarSaldo() {
        System.out.println("Titular: " + titular);
        System.out.println("Conta: " + numeroConta);
        System.out.println("Saldo: R$ " + saldo);
    }

    public void alterarTitular(String novoTitular) {
        titular = novoTitular;
        System.out.println("Titular alterado com sucesso!");
    }

    public String getTitular() {
        return titular;
    }
}

class Main {
    public static void main(String[] args) {

        ContaBancaria conta1 = new ContaBancaria("Joao", "001", 5000);
        ContaBancaria conta2 = new ContaBancaria("Maria", "002", 2500);
        ContaBancaria conta3 = new ContaBancaria("Pedro", "003", 1000);

        System.out.println("=== CONTA DO JOAO ===");
        conta1.mostrarSaldo();

        conta1.depositar(500);
        conta1.mostrarSaldo();

        conta1.sacar(300);
        conta1.mostrarSaldo();

        conta1.sacar(1500);
        conta1.mostrarSaldo();

        System.out.println("\n=== CONTA DA MARIA ===");
        conta2.mostrarSaldo();

        conta2.depositar(1000);
        conta2.sacar(500);
        conta2.mostrarSaldo();

        System.out.println("\n=== CONTA DO PEDRO ===");
        conta3.mostrarSaldo();

        conta3.depositar(200);
        conta3.depositar(300);
        conta3.sacar(100);
        conta3.mostrarSaldo();

        System.out.println("\n=== TRANSFERENCIA ===");
        conta1.transferir(conta2, 1000);

        System.out.println("\nSaldo do Joao:");
        conta1.mostrarSaldo();

        System.out.println("\nSaldo da Maria:");
        conta2.mostrarSaldo();

        System.out.println("\n=== ALTERAR TITULAR ===");
        conta3.alterarTitular("Carlos");
        conta3.mostrarSaldo();

        System.out.println("\n=== CONSULTAR TITULAR ===");
        System.out.println("Titular: " + conta1.getTitular());

        System.out.println("\n=== FINALIZADO ===");
    }
}

