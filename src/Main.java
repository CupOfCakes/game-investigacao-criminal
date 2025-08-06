import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

abstract class Pessoa {
    String nome;
    int idade;
    String depoimento;
    String profissao;
}

class Detetive extends Pessoa{
    //funções de investigação
}

class Suspeito extends Pessoa{
    boolean culpado;

}

class Vitima extends Pessoa{
    String causa;
}

class Pista{
    String descricao;
    boolean verdadeira;
}

class Caso {
    String titulo;
    String descricao;
    Pessoa vitima;
    ArrayList<Suspeito> suspeitos;
    ArrayList<Pista> pistas;

    Caso() {
        suspeitos = new ArrayList<>();
        pistas = new ArrayList<>();
    }

    void exibirResumo(){
        System.out.println("Caso: " + titulo);
        System.out.println("- Vítima: " + vitima.nome + "(" + vitima.profissao + ")" + " (" + descricao + ")");
        System.out.println("- Suspeitos:");

        int i = 1;
        for (Suspeito s : suspeitos){
            System.out.println("    " + i + ". " + s.nome + "(" + s.profissao + ")" + ": " + s.depoimento);
            i++;
        }

        System.out.println("Pistas:");

        for (Pista p : pistas){
            System.out.println("    - " + p.descricao);
        }

        for (Suspeito s : suspeitos) {
            if (s.culpado) {
                System.out.println("- Culpado: " + s.nome);
                break; 
            }
        }
    }
}

class Game{
    public static void startGame() {
        // Pagina de inicio
        System.out.println("----------------------------------------------");
        System.out.println("  Sistema de Investigação - Jogo de Detetive");
        System.out.println("----------------------------------------------");
    }

    public static Detetive configDetetive(Scanner scanner) {
        // Configuração do detetive
        System.out.println("Configuração do Detetive:");

        System.out.print("Digite o nome do detetive: ");
        String nomeDetetive = scanner.nextLine();

        Detetive Detetive = new Detetive();
        Detetive.nome = nomeDetetive;

        return Detetive;
    }

    public static void menuInicial(Detetive detetive, Caso caso, Scanner scanner) {
        // Menu principal do jogo
        System.out.println("Bem-vindo, " + detetive.nome + "!");
        System.out.println("1. Iniciar novo caso");
        System.out.println("2. Sair");
        
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (escolha == 1) {
            //menuCaso(caso);;
        } else {
            System.out.println("Saindo do jogo...");
            System.exit(0);
        }
    }

    public static void menuCaso(Detetive detetive, Caso caso, Scanner scanner) {
        System.out.println("Resumo do caso:");
        caso.exibirResumo();
        
        System.out.println("----------------------------------------------");
        
        while (true) {
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Interrogar suspeitos");
            System.out.println("2. Ver pistas");
            System.out.println("3. Acusar suspeito");
            System.out.println("4. Sair do caso");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            if (escolha == 1) {
                interrogarSuspeitos(caso, scanner);
            } else if (escolha == 2) {
                verPistas(caso, scanner);
            } else if (escolha == 3) {
                //acusarSuspeito(caso);
                break;
            } else if (escolha == 4) {
                menuInicial(detetive, caso, scanner);
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }

    public static void interrogarSuspeitos(Caso caso, Scanner scanner) {
        
        for(Suspeito s : caso.suspeitos) {
            System.out.println("- " + s.nome + " (" + s.profissao + "): ");
        }

        System.out.println("Escolha um suspeito para interrogar");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (escolha < 1 || escolha > caso.suspeitos.size()) {
            System.out.println("Suspeito inválido.");
            return;
        }

        Suspeito suspeito = caso.suspeitos.get(escolha - 1);
        System.out.println("Depoimento de " + suspeito.nome + ": " + suspeito.depoimento);
        
    }

    public static void verPistas(Caso caso, Scanner scanner) {
        System.out.println("Pistas encontradas:");
        int i = 1;
        for (Pista p : caso.pistas) {
            System.out.println(i + ". " + p.descricao);
            i++;
        }

        System.out.println("Investigar alguma pista?[s/n]");
        String resposta = scanner.nextLine();

        resposta = resposta.trim();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("Escolha uma pista para investigar:");
            int escolha = scanner.nextInt();
            scanner.close();

            if (escolha < 1 || escolha > caso.pistas.size()) {
                System.out.println("Pista inválida.");
                return;
            }

            Pista pista = caso.pistas.get(escolha - 1);
            System.out.println("Investigando: " + pista.descricao);
            if (pista.verdadeira) {
                System.out.println("Essa pista é verdadeira!");
            } else {
                System.out.println("Essa pista é falsa.");
            }
        } else {
            System.out.println("Voltando ao menu principal...");
        }


    }

    public static void acusarSuspeito(Caso caso, Scanner scanner) {
        System.out.println("Escolha um suspeito para acusar:");
        int i = 1;
        for (Suspeito s : caso.suspeitos) {
            System.out.println(i + ". " + s.nome + " (" + s.profissao + ")");
            i++;
        }

        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        if (escolha < 1 || escolha > caso.suspeitos.size()) {
            System.out.println("Suspeito inválido.");
            return;
        }

        Suspeito suspeito = caso.suspeitos.get(escolha - 1);
        if (suspeito.culpado) {
            System.out.println("Você acusou corretamente: " + suspeito.nome + " é o culpado!");
            System.out.println("Caso encerrado com sucesso!");
        } else {
            System.out.println("Você acusou incorretamente: " + suspeito.nome + " é inocente.");
            return;
        }
    }

}

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        Caso caso = new Caso();
        int quantSuspeitos = 3; // Número de suspeitos
        int quantPistas = 2; // Número de pistas

        // Listas de dados
        ArrayList<String> nomes = new ArrayList<>();
        Collections.addAll(nomes, "Carlos", "Lucia", "George", "Ana", "Paulo", "Fernanda", "Roberto");

        ArrayList<String> profissoes = new ArrayList<>();
        Collections.addAll(profissoes, "Professor", "Advogada", "Engenheiro", "Médica", "Artista", "Jornalista", "Desempregado");

        ArrayList<String> pistas = new ArrayList<>();
        Collections.addAll(pistas, "Pegadas com lama perto da janela.",
            "Um bilhete rasgado sem sentido.",
            "Copo quebrado no chão.",
            "Relógio parado às 22h.",
            "Porta arrombada.");

        String[] depoimentos = {
            "Eu estava na sala ao lado!",
            "Isso é um absurdo!",
            "Não vi nada, estava escuro.",
            "Eu estava dormindo.",
            "Estava no jardim.",
            "Não conheço a vítima."
        };
        String[] titulos = {"Mistério na Biblioteca", "Roubo no Museu", "Crime na Festa"};

        String[] descricoes = {
            "Desaparecido apos um evento literário, o mistério envolve uma biblioteca antiga.",
            "Dono do Museu foi encontrado morto, e todos os objetos valiosos desapareceram.",
            "Corpo encontrado perto da festa."
        };

        int indiceTitulo = rand.nextInt(titulos.length);
        caso.titulo = titulos[indiceTitulo];
        caso.descricao = descricoes[indiceTitulo];

        Collections.shuffle(nomes, rand);

        
        // Criando a vítima
        Vitima vitima = new Vitima();
        vitima.nome = nomes.get(0);
        vitima.profissao = profissoes.get(0);
        vitima.idade = 18 + rand.nextInt(63);
        vitima.depoimento = depoimentos[rand.nextInt(depoimentos.length)];
        caso.vitima = vitima;


        int indiceCulpado = 1 + rand.nextInt(quantSuspeitos);


        // Criando suspeitos aleatórios
        for (int i = 1; i <= quantSuspeitos; i++) {
            Suspeito s = new Suspeito();
            s.nome = nomes.get(i);
            s.profissao = profissoes.get(i);
            s.idade = 18 + rand.nextInt(63);
            s.depoimento = depoimentos[rand.nextInt(depoimentos.length)];
            s.culpado = (i == indiceCulpado); // Apenas um suspeito é culpado   
            caso.suspeitos.add(s);
        }

        // Criando pistas

        Collections.shuffle(pistas, rand);

        for (int i = 0; i < quantPistas; i++) {
            Pista p = new Pista();
            p.descricao = pistas.get(i);
            p.verdadeira = rand.nextBoolean();
            caso.pistas.add(p);
        }

        // Iniciando o jogo
        Game.startGame();
        Detetive detetive = Game.configDetetive(scanner);
        Game.menuInicial(detetive, caso, scanner);
        System.out.println("Obrigado por jogar!");
    }
}


