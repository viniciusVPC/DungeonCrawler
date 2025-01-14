package com.example.game;

import com.example.items.Tesouro;
import com.example.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Map {

    Sistema sis;
    Random rand = new Random();
    Player jogador = null;
    String[][] mapa;
    Fase fase;

    public Fase GeraMapa(Sistema sis) {
        this.sis = sis;
        mapa = new String[5 + sis.getFaseNum() - 1][5 + sis.getFaseNum() - 1];
        Tesouro tesouroFase = new Tesouro();
        int[] xI = new int[sis.getFaseNum()];
        int[] yI = new int[sis.getFaseNum()];
        ArrayList<int[]> posicItens = new ArrayList<>();

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = "[ ]";
            }
        }

        do {
            sis.setxP(rand.nextInt(mapa.length));
            sis.setyP(rand.nextInt(mapa.length));
            sis.setxT(rand.nextInt(mapa.length));
            sis.setyT(rand.nextInt(mapa.length));

            for (int i = 0; i < xI.length; i++) {
                do {
                    xI[i] = rand.nextInt(mapa.length);
                    yI[i] = rand.nextInt(mapa.length);
                } while (xI[i] == sis.getxP() && yI[i] == sis.getyP());
            }

        } while ((sis.getxP() == sis.getxT() && sis.getyP() == sis.getyT())
                || (sis.getxP() - sis.getxT() <= 1 && sis.getyP() - sis.getyT() <= 1));
        for (int i = 0; i < sis.getFaseNum(); i++) {
            int[] posicItemTemp = new int[] { xI[i], yI[i] };
            posicItens.add(posicItemTemp);
        }

        fase = new Fase(tesouroFase, posicItens, xI, yI);
        sis.setPosicPlayer(new int[] { sis.getxP(), sis.getyP() });
        sis.setPosicTesouro(new int[] { sis.getxT(), sis.getyT() });
        return fase;
    }

    void ExibeMapa(Player jogador) {
        if (this.jogador == null)
            this.jogador = jogador;
        System.out.println("FASE " + sis.getFaseNum() + ":");
        LimpaMapa();
        System.out.println("Mapa da Fase " + sis.getFaseNum());
        mapa[sis.getxP()][sis.getyP()] = "[P]";
        mapa[sis.getxT()][sis.getyT()] = "[T]";
        for (int i = 0; i < fase.posicItens.size(); i++) {
            mapa[fase.xI[i]][fase.yI[i]] = "[I]";
        }

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Legenda:\n\tP = Player\n\tT = Tesouro");
        System.out
                .println("Vida atual: " + sis.getJogador().getVida() + " Dinheiro: " + sis.getJogador().getDinheiro());
        if (jogador.getTemArma())
            System.out.println("Arma atual: " + jogador.getArmaAtual().getNome());
    }

    void LimpaMapa() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = "[ ]";
            }
            ;
        }
    }

    public String[][] getMapa() {
        return mapa;
    }

    public Fase getFase() {
        return fase;
    }

}
