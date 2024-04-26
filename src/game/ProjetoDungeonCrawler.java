package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProjetoDungeonCrawler {

    String[][] mapa;
    Random rand = new Random();
    ArrayList<Monstro> monstros = new ArrayList();
    Scanner input = new Scanner(System.in);
    int rounds = 0;
    Player jogador = new Player();
    int fase = 1;
    int[] posicPlayer, posicTesouro;
    ArrayList<int[]> posicItens = new ArrayList();
    //int[][] posicItem;
    Tesouro tesouroFase;

    int xP, yP, xT, yT;
    int[] xI;
    int[] yI;

    public static void main(String[] args) {
        ProjetoDungeonCrawler projeto = new ProjetoDungeonCrawler();
        projeto.Start();
    }

    void GeraMapa() {
        mapa = new String[5 + fase - 1][5 + fase - 1];
        tesouroFase = new Tesouro();
        xI = new int[fase];
        yI = new int[fase];

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = "[ ]";
            }
        }

        do {
            xP = rand.nextInt(mapa.length);
            yP = rand.nextInt(mapa.length);
            xT = rand.nextInt(mapa.length);
            yT = rand.nextInt(mapa.length);

            for (int i = 0; i < xI.length; i++) {
                do {
                    xI[i] = rand.nextInt(mapa.length);
                    yI[i] = rand.nextInt(mapa.length);
                } while (xI[i] == xP && yI[i] == yP);
            }

        } while ((xP == xT && yP == yT) || (xP - xT <= 1 && yP - yT <= 1));
        for (int i = 0; i < fase; i++) {
            int[] posicItemTemp = new int[]{xI[i], yI[i]};
            posicItens.add(posicItemTemp);
        }
        posicPlayer = new int[]{xP, yP};
        posicTesouro = new int[]{xT, yT};
    }

    void LimpaMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = "[ ]";
            };
        }
    }

    void ExibeMapa() {
        MudaTela();
        System.out.println("FASE " + fase + ":");
        LimpaMapa();
        System.out.println("Mapa da Fase " + fase);
        mapa[xP][yP] = "[P]";
        mapa[xT][yT] = "[T]";
        for (int i = 0; i < posicItens.size(); i++) {
            mapa[xI[i]][yI[i]] = "[I]";
        }

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Legenda:\n\tP = Player\n\tT = Tesouro");
        System.out.println("Vida atual: " + jogador.getVida() + " Dinheiro: " + jogador.getDinheiro());
    }

    public void Start() {
        System.out.println("===============================================");
        System.out.println("Seja bem vindo ao DungeonCrawler feito em Java!");
        System.out.println("Autor: Vinícius Pereira da Costa");
        System.out.println("===============================================");
        EnterParaProsseguir();

        GeraMapa();
        LoopPrincipal();
    }

    public void ProximaFase() {
        MudaTela();
        System.out.println("Parabéns! Você passou para a próxima fase!");
        fase++;
        GeraMapa();
        EnterParaProsseguir();
        LoopPrincipal();
    }

    public void LoopPrincipal() {
        int surpresa;
        do {

            if (xP == xT && yP == yT) {
                PegaTesouro();
                ProximaFase();
            } else {

                if ("[I]".equals(mapa[xP][yP])) {
                    PegaItem();
                }
            }
            ExibeMapa();

            Mover();
            surpresa = rand.nextInt(10);
        } while (surpresa <= 4);
        AtaqueSurpresa();
    }

    public void AtaqueSurpresa() {
        int quantMonstros = rand.nextInt(1, 1 + fase);
        MudaTela();
        System.out.println("ATAQUE SURPRESA");
        System.out.println("Tu te deparas com " + quantMonstros + " monstros.");
        for (int i = 0; i < quantMonstros; i++) {
            Monstro monstro = new Monstro();
            monstros.add(monstro);
            System.out.print(introduzMonstro(monstro) + "\n");
        }
        Combate();
        FimDoCombate();
        LoopPrincipal();
    }

    public void PegaTesouro() {
        MudaTela();
        System.out.println("Parabéns você chegou ao tesouro!");
        System.out.println("O tesouro é: um " + tesouroFase.getNome() + " " + tesouroFase.getAdjetivo());
        EnterParaProsseguir();
    }

    public void PegaItem() {
        MudaTela();
        System.out.println("Parabéns, você achou um item!");
        for (int i = 0; i < posicItens.size(); i++) {
            if (posicItens.get(i)[0] == xP && posicItens.get(i)[1] == yP) {
                posicItens.remove(i);
            }
        }
        EnterParaProsseguir();
    }

    public void Mover() {
        boolean erro = false;

        do {
            erro = false;
            System.out.print("\nPara onde você quer ir?\n");
            System.out.println("");

            if (posicPlayer[0] - 1 >= 0) {
                System.out.print("[1] Cima");
            }
            if (posicPlayer[0] + 1 <= mapa.length - 1) {
                System.out.print("[2] Baixo");
            }
            if (posicPlayer[1] - 1 >= 0) {
                System.out.print("[3] Esquerda");
            }
            if (posicPlayer[1] + 1 <= mapa.length - 1) {
                System.out.print("[4] Direita");
            }
            System.out.println("");

            int resposta = input.nextInt();
            switch (resposta) {
                case 1 -> {
                    if (posicPlayer[0] - 1 >= 0) {
                        xP -= 1;
                        posicPlayer[0] -= 1;
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 2 -> {
                    if (posicPlayer[0] + 1 <= mapa.length - 1) {
                        xP += 1;
                        posicPlayer[0] += 1;
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 3 -> {
                    if (posicPlayer[1] - 1 >= 0) {
                        yP -= 1;
                        posicPlayer[1] -= 1;
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 4 -> {
                    if (posicPlayer[1] + 1 <= mapa.length - 1) {
                        yP += 1;
                        posicPlayer[1] += 1;
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
            }
        } while (erro);

    }

    public void Combate() {
        int resposta;
        do {
            rounds++;
            System.out.println("ROUND: " + rounds);
            System.out.print("O que tu vais fazer?\n[1] Atacar\n[2] Curar\n[3] Fugir\n");
            resposta = input.nextInt();
            switch (resposta) {
                case 1 -> {
                    MudaTela();
                    System.out.println("Aqui tu atacas.");
                    Ataque();
                }
                case 2 ->
                    System.out.println("Aqui tu te curas.");
                case 3 ->
                    System.out.println("Aqui tu foges.");
                default ->
                    System.out.println("Resposta errada.");
            }
            if (monstros.size() <= 0) {
                break;
            }
            RoundInimigo();
        } while (resposta != 0);
    }

    public void Ataque() {
        System.out.println("Qual inimigo tu queres atacar?");
        for (int i = 0; i < monstros.size(); i++) {
            System.out.print("[" + (i + 1) + "] " + monstros.get(i).getNome() + "\n");
        }
        System.out.print("\n");
        int resposta = input.nextInt();
        MudaTela();
        System.out.println("Tu atacas e derrota o " + monstros.get(resposta - 1).getNome());
        monstros.remove(monstros.get(resposta - 1));
        if (monstros.size() >= 1) {
            System.out.println("Agora restam somente os: ");
            exibeMonstros();
        } else {
            System.out.println("Todos os inimigos foram derrotados!");
        }
        EnterParaProsseguir();
    }

    public void RoundInimigo() {
        MudaTela();
        for (Monstro monstro : monstros) {
            jogador.setVida(jogador.getVida() - monstro.Ataca());
            System.out.println("Você agora está com " + jogador.getVida() + " pontos de vida");
        }
    }

    public void FimDoCombate() {
        MudaTela();
        rounds = 0;
        int premio = rand.nextInt(15);
        System.out.println("Você encontra " + premio + " moedas.");
        jogador.adicionaDinheiro(premio);
        EnterParaProsseguir();
    }

    public void exibeMonstros() {
        for (int i = 0; i < monstros.size(); i++) {
            System.out.print(monstros.get(i).getNome() + "\t");
        }
        System.out.println("");
    }

    public String introduzMonstro(Monstro monstro) {
        String texto = "Um " + monstro.getNome() + " " + monstro.getAdjetivo() + " com " + monstro.getVida() + " pontos de vida.";
        return texto;
    }

    public void MudaTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------");
    }

    public void EnterParaProsseguir() {
        Scanner s = new Scanner(System.in);
        System.out.println("Pressione ENTER para prosseguir.");
        s.nextLine();
    }
}
