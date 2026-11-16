import java.util.ArrayList;
import java.util.Scanner;

class Livro {
    int id;
    String titulo;
    String autor;
    int ano;
    boolean disponivel = true;
    Usuario emprestadoPara = null;

    Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    void exibir() {
        String status = "Disponível";
        if (!disponivel) {
            status = "Emprestado para " + emprestadoPara.nome;
        }
        System.out.println("[" + id + "] " + titulo + " - " + autor + " (" + ano + ") | " + status);
    }
}

class Usuario {
    int id;
    String nome;
    int livrosEmPosse = 0;

    Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    void exibir() {
        System.out.println("[" + id + "] " + nome + " | Livros em posse: " + livrosEmPosse);
    }
}

public class Biblioteca {
    static ArrayList<Livro> livros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static int proximoIdLivro = 1;
    static int proximoIdUsuario = 1;

    static final int LIMITE_LIVROS = 3;
    static final double MULTA_POR_DIA = 2.0;

    public static void main(String[] args) {
        livros.add(new Livro(proximoIdLivro++, "Dom Casmurro", "Machado de Assis", 1899));
        livros.add(new Livro(proximoIdLivro++, "O Cortiço", "Aluísio Azevedo", 1890));
        livros.add(new Livro(proximoIdLivro++, "Clean Code", "Robert C. Martin", 2008));
        usuarios.add(new Usuario(proximoIdUsuario++, "André"));
        usuarios.add(new Usuario(proximoIdUsuario++, "Maria"));

        int opcao;
        do {
            System.out.println("\n===== BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Buscar livro");
            System.out.println("4 - Cadastrar usuário");
            System.out.println("5 - Listar usuários");
            System.out.println("6 - Emprestar livro");
            System.out.println("7 - Devolver livro");
            System.out.println("8 - Livros emprestados");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarLivro();
                    break;
                case 4:
                    cadastrarUsuario();
                    break;
                case 5:
                    listarUsuarios();
                    break;
                case 6:
                    emprestar();
                    break;
                case 7:
                    devolver();
                    break;
                case 8:
                    listarEmprestados();
                    break;
                case 0:
                    System.out.println("Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        livros.add(new Livro(proximoIdLivro++, titulo, autor, ano));
        System.out.println("Livro cadastrado!");
    }

    static void listarLivros() {
        if (livros.size() == 0) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n--- Livros ---");
        for (int i = 0; i < livros.size(); i++) {
            livros.get(i).exibir();
        }
    }

    static void buscarLivro() {
        System.out.print("Buscar por título ou autor: ");
        String termo = scanner.nextLine().toLowerCase();
        boolean achou = false;

        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (l.titulo.toLowerCase().contains(termo) || l.autor.toLowerCase().contains(termo)) {
                l.exibir();
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        usuarios.add(new Usuario(proximoIdUsuario++, nome));
        System.out.println("Usuário cadastrado!");
    }

    static void listarUsuarios() {
        if (usuarios.size() == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("\n--- Usuários ---");
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).exibir();
        }
    }

    static void emprestar() {
        listarUsuarios();
        System.out.print("ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        Usuario usuario = buscarUsuario(idUsuario);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (usuario.livrosEmPosse >= LIMITE_LIVROS) {
            System.out.println("Esse usuário já está com " + LIMITE_LIVROS + " livros (limite).");
            return;
        }

        listarLivros();
        System.out.print("ID do livro: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();
        Livro livro = buscarLivro(idLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (!livro.disponivel) {
            System.out.println("Esse livro já está emprestado.");
            return;
        }

        livro.disponivel = false;
        livro.emprestadoPara = usuario;
        usuario.livrosEmPosse++;
        System.out.println(usuario.nome + " pegou \"" + livro.titulo + "\". Prazo: 7 dias.");
    }

    static void devolver() {
        listarEmprestados();
        System.out.print("ID do livro a devolver: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();
        Livro livro = buscarLivro(idLivro);

        if (livro == null || livro.disponivel) {
            System.out.println("Não há empréstimo desse livro.");
            return;
        }

        System.out.print("Quantos dias de atraso? (0 se devolveu no prazo): ");
        int diasAtraso = scanner.nextInt();
        scanner.nextLine();

        if (diasAtraso > 0) {
            double multa = diasAtraso * MULTA_POR_DIA;
            System.out.println("Multa: R$ " + multa);
        }

        livro.emprestadoPara.livrosEmPosse--;
        livro.emprestadoPara = null;
        livro.disponivel = true;
        System.out.println("Livro devolvido!");
    }

    static void listarEmprestados() {
        boolean achou = false;
        System.out.println("\n--- Emprestados ---");
        for (int i = 0; i < livros.size(); i++) {
            if (!livros.get(i).disponivel) {
                livros.get(i).exibir();
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhum livro emprestado.");
        }
    }

    static Livro buscarLivro(int id) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).id == id) {
                return livros.get(i);
            }
        }
        return null;
    }

    static Usuario buscarUsuario(int id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).id == id) {
                return usuarios.get(i);
            }
        }
        return null;
    }
}