public class Aluno {
    String nome;
    String matricula;
    double[] notas;

    public Aluno(String nome, String matricula, double[] notas) {
        this.nome = nome;
        this.matricula = matricula;
        this.notas = notas;
    }

    public void calcularMedia() {
        double soma = 0;

        for (int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }

        double media = soma / notas.length;

        System.out.printf("Media: %.2f%n", media);
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Matricula: " + matricula);
    }
}

class Main {
    public static void main(String[] args) {

        double[] notas1 = {7.0, 8.0, 6.0};
        double[] notas2 = {6.0, 10.0, 8.0};
        double[] notas3 = {9.0, 8.5, 10.0};
        double[] notas4 = {7.5, 9.0, 8.0};
        double[] notas5 = {10.0, 9.5, 9.0};
        double[] notas6 = {8.0, 7.5, 9.0};

        Aluno aluno1 = new Aluno("Andre", "123", notas1);
        Aluno aluno2 = new Aluno("Cuca", "456", notas2);
        Aluno aluno3 = new Aluno("Neymar Jr.", "789", notas3);
        Aluno aluno4 = new Aluno("Pelé", "101", notas4);
        Aluno aluno5 = new Aluno("Gabigol", "202", notas5);
        Aluno aluno6 = new Aluno("Rodrygo", "303", notas6);

        aluno1.exibirInformacoes();
        aluno1.calcularMedia();

        System.out.println();

        aluno2.exibirInformacoes();
        aluno2.calcularMedia();

        System.out.println();

        aluno3.exibirInformacoes();
        aluno3.calcularMedia();

        System.out.println();

        aluno4.exibirInformacoes();
        aluno4.calcularMedia();

        System.out.println();

        aluno5.exibirInformacoes();
        aluno5.calcularMedia();

        System.out.println();

        aluno6.exibirInformacoes();
        aluno6.calcularMedia();
    }
}
