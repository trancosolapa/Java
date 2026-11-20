import java.util.Scanner;

class Personagem {
    String nome;
    String classe;
    int tipo; // 1 = Guerreiro, 2 = Mago, 3 = Arqueiro
    int nivel = 1;
    int xp = 0;
    int vidaMax;
    int vida;
    int manaMax;
    int mana;
    int ataque;
    int defesa;
    int ouro = 50;
    int pocoes = 2;

    Personagem(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;

        if (tipo == 1) {
            classe = "Guerreiro";
            vidaMax = 120;
            manaMax = 20;
            ataque = 15;
            defesa = 8;
        } else if (tipo == 2) {
            classe = "Mago";
            vidaMax = 80;
            manaMax = 60;
            ataque = 18;
            defesa = 3;
        } else {
            classe = "Arqueiro";
            vidaMax = 100;
            manaMax = 30;
            ataque = 16;
            defesa = 5;
        }
        vida = vidaMax;
        mana = manaMax;
    }

    int calcularDano(int defesaAlvo) {
        int variacao = (int) (Math.random() * 6); // 0 a 5
        int dano = ataque + variacao - defesaAlvo;
        if (dano < 1) {
            dano = 1;
        }
        return dano;
    }

    void receberDano(int dano) {
        vida = vida - dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    boolean estaVivo() {
        return vida > 0;
    }

    void usarPocao() {
        pocoes--;
        vida = vida + 40;
        if (vida > vidaMax) {
            vida = vidaMax;
        }
    }

    void ganharMana(int quantidade) {
        mana = mana + quantidade;
        if (mana > manaMax) {
            mana = manaMax;
        }
    }

    void ganharXp(int quantidade) {
        xp = xp + quantidade;
        while (xp >= nivel * 50) {
            xp = xp - nivel * 50;
            nivel++;
            vidaMax = vidaMax + 15;
            manaMax = manaMax + 5;
            ataque = ataque + 3;
            defesa = defesa + 1;
            vida = vidaMax;
            mana = manaMax;
            System.out.println("*** LEVEL UP! Você chegou ao nível " + nivel + "! ***");
        }
    }

    void exibir() {
        System.out.println("\n=== " + nome + " (" + classe + ") ===");
        System.out.println("Nível: " + nivel + " | XP: " + xp + "/" + (nivel * 50));
        System.out.println("Vida: " + vida + "/" + vidaMax);
        System.out.println("Mana: " + mana + "/" + manaMax);
        System.out.println("Ataque: " + ataque + " | Defesa: " + defesa);
        System.out.println("Ouro: " + ouro + " | Poções: " + pocoes);
    }
}

class Inimigo {
    String nome;
    int vida;
    int ataque;
    int defesa;
    int xpRecompensa;
    int ouroRecompensa;

    Inimigo(String nome, int vida, int ataque, int defesa, int xpRecompensa, int ouroRecompensa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.xpRecompensa = xpRecompensa;
        this.ouroRecompensa = ouroRecompensa;
    }

    int calcularDano(int defesaAlvo) {
        int variacao = (int) (Math.random() * 4); // 0 a 3
        int dano = ataque + variacao - defesaAlvo;
        if (dano < 1) {
            dano = 1;
        }
        return dano;
    }

    void receberDano(int dano) {
        vida = vida - dano;
        if (vida < 0) {
            vida = 0;
        }
    }

    boolean estaVivo() {
        return vida > 0;
    }
}

public class RPG {
    static Personagem heroi;
    static Scanner scanner = new Scanner(System.in);
    static boolean jogando = true;

    static final int PRECO_POCAO = 25;
    static final int PRECO_DESCANSO = 20;

    public static void main(String[] args) {
        System.out.println("===== AVENTURA RPG =====");
        System.out.print("Nome do seu herói: ");
        String nome = scanner.nextLine();

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro (muita vida e defesa)");
        System.out.println("2 - Mago (muita mana e poder de fogo)");
        System.out.println("3 - Arqueiro (equilibrado)");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo < 1 || tipo > 3) {
            System.out.println("Opção inválida, você será um Guerreiro.");
            tipo = 1;
        }

        heroi = new Personagem(nome, tipo);
        System.out.println("\nBem-vindo, " + heroi.nome + " o " + heroi.classe + "!");

        while (jogando) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Explorar (lutar)");
            System.out.println("2 - Status");
            System.out.println("3 - Loja");
            System.out.println("4 - Descansar na estalagem");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    batalhar();
                    break;
                case 2:
                    heroi.exibir();
                    break;
                case 3:
                    loja();
                    break;
                case 4:
                    descansar();
                    break;
                case 0:
                    System.out.println("Até a próxima aventura!");
                    jogando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    static Inimigo gerarInimigo(int nivel) {
        int sorteio = (int) (Math.random() * 3); // 0, 1 ou 2

        if (sorteio == 0) {
            return new Inimigo("Goblin", 40 + nivel * 10, 8 + nivel * 2, 2 + nivel, 20 + nivel * 5, 15 + nivel * 5);
        } else if (sorteio == 1) {
            return new Inimigo("Esqueleto", 50 + nivel * 12, 10 + nivel * 2, 4 + nivel, 25 + nivel * 5, 20 + nivel * 5);
        } else {
            return new Inimigo("Orc", 70 + nivel * 15, 12 + nivel * 3, 5 + nivel, 35 + nivel * 6, 30 + nivel * 6);
        }
    }

    static void batalhar() {
        Inimigo inimigo = gerarInimigo(heroi.nivel);
        System.out.println("\nUm " + inimigo.nome + " apareceu!");

        boolean fugiu = false;

        while (heroi.estaVivo() && inimigo.estaVivo() && !fugiu) {
            System.out.println("\n" + heroi.nome + ": " + heroi.vida + "/" + heroi.vidaMax
                    + " HP | " + heroi.mana + " MP");
            System.out.println(inimigo.nome + ": " + inimigo.vida + " HP");
            System.out.println("1 - Atacar");
            System.out.println("2 - Habilidade especial");
            System.out.println("3 - Usar poção (" + heroi.pocoes + ")");
            System.out.println("4 - Fugir");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            boolean turnoGasto = true;

            if (escolha == 1) {
                int dano = heroi.calcularDano(inimigo.defesa);
                inimigo.receberDano(dano);
                heroi.ganharMana(5);
                System.out.println("Você causou " + dano + " de dano!");
            } else if (escolha == 2) {
                turnoGasto = usarHabilidade(inimigo);
            } else if (escolha == 3) {
                if (heroi.pocoes > 0) {
                    heroi.usarPocao();
                    System.out.println("Você bebeu uma poção e recuperou vida!");
                } else {
                    System.out.println("Você não tem poções!");
                    turnoGasto = false;
                }
            } else if (escolha == 4) {
                if (Math.random() < 0.5) {
                    System.out.println("Você conseguiu fugir!");
                    fugiu = true;
                } else {
                    System.out.println("Você não conseguiu fugir!");
                }
            } else {
                System.out.println("Opção inválida.");
                turnoGasto = false;
            }

            if (!inimigo.estaVivo()) {
                break;
            }

            if (turnoGasto && !fugiu) {
                int danoInimigo = inimigo.calcularDano(heroi.defesa);
                heroi.receberDano(danoInimigo);
                System.out.println(inimigo.nome + " te atacou e causou " + danoInimigo + " de dano!");
            }
        }

        if (!heroi.estaVivo()) {
            System.out.println("\nVocê foi derrotado... GAME OVER.");
            jogando = false;
        } else if (!inimigo.estaVivo()) {
            System.out.println("\nVocê derrotou o " + inimigo.nome + "!");
            System.out.println("Recompensa: " + inimigo.xpRecompensa + " XP e " + inimigo.ouroRecompensa + " de ouro.");
            heroi.ouro = heroi.ouro + inimigo.ouroRecompensa;
            heroi.ganharXp(inimigo.xpRecompensa);
        }
    }

    static boolean usarHabilidade(Inimigo inimigo) {
        int custo;
        if (heroi.tipo == 1) {
            custo = 10;
        } else if (heroi.tipo == 2) {
            custo = 20;
        } else {
            custo = 15;
        }

        if (heroi.mana < custo) {
            System.out.println("Mana insuficiente! Essa habilidade custa " + custo + " MP.");
            return false;
        }

        heroi.mana = heroi.mana - custo;
        int dano;

        if (heroi.tipo == 1) {
            dano = heroi.calcularDano(inimigo.defesa) * 2;
            System.out.println("Golpe Poderoso! Você causou " + dano + " de dano!");
        } else if (heroi.tipo == 2) {
            dano = heroi.ataque + 10; // ignora a defesa
            System.out.println("Bola de Fogo! Você causou " + dano + " de dano!");
        } else {
            dano = heroi.calcularDano(inimigo.defesa) + heroi.calcularDano(inimigo.defesa);
            System.out.println("Tiro Duplo! Você causou " + dano + " de dano!");
        }

        inimigo.receberDano(dano);
        return true;
    }

    static void loja() {
        System.out.println("\n--- LOJA ---");
        System.out.println("Seu ouro: " + heroi.ouro);
        System.out.println("1 - Comprar poção (" + PRECO_POCAO + " de ouro)");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            if (heroi.ouro >= PRECO_POCAO) {
                heroi.ouro = heroi.ouro - PRECO_POCAO;
                heroi.pocoes++;
                System.out.println("Poção comprada! Agora você tem " + heroi.pocoes + ".");
            } else {
                System.out.println("Ouro insuficiente.");
            }
        }
    }

    static void descansar() {
        if (heroi.ouro >= PRECO_DESCANSO) {
            heroi.ouro = heroi.ouro - PRECO_DESCANSO;
            heroi.vida = heroi.vidaMax;
            heroi.mana = heroi.manaMax;
            System.out.println("Você descansou e recuperou toda a vida e mana!");
        } else {
            System.out.println("Você precisa de " + PRECO_DESCANSO + " de ouro pra descansar.");
        }
    }
}