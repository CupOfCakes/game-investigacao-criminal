import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

public class Main{
    public static void main(String[] args){
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

        caso.exibirResumo();
    }
}


