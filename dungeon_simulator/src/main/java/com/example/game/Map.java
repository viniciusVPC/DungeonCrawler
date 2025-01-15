package com.example.game;

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
        int[] xB = new int[sis.getFaseNum()];
        int[] yB = new int[sis.getFaseNum()];
        ArrayList<int[]> posicBaus = new ArrayList<>();

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                mapa[i][j] = "[ ]";
            }
        }

        do {
            sis.setxP(rand.nextInt(mapa.length));
            sis.setyP(rand.nextInt(mapa.length));
            sis.setxS(rand.nextInt(mapa.length));
            sis.setyS(rand.nextInt(mapa.length));

            for (int i = 0; i < xB.length; i++) {
                do {
                    xB[i] = rand.nextInt(mapa.length);
                    yB[i] = rand.nextInt(mapa.length);
                } while (xB[i] == sis.getxP() && yB[i] == sis.getyP());
            }

        } while ((sis.getxP() == sis.getxS() && sis.getyP() == sis.getyS())
                || (sis.getxP() - sis.getxS() <= 1 && sis.getyP() - sis.getyS() <= 1));
        for (int i = 0; i < sis.getFaseNum(); i++) {
            int[] posicBauTemp = new int[] { xB[i], yB[i] };
            posicBaus.add(posicBauTemp);
        }

        fase = new Fase(posicBaus, xB, yB);
        sis.setPosicPlayer(new int[] { sis.getxP(), sis.getyP() });
        sis.setPosicSaida(new int[] { sis.getxS(), sis.getyS() });
        return fase;
    }

    void ExibeMapa(Player jogador) {
        if (this.jogador == null)
            this.jogador = jogador;
        System.out.println("FASE " + sis.getFaseNum() + ":");
        LimpaMapa();
        System.out.println("Mapa da Fase " + sis.getFaseNum());
        mapa[sis.getxP()][sis.getyP()] = "[P]";
        mapa[sis.getxS()][sis.getyS()] = "[S]";
        for (int i = 0; i < fase.posicBaus.size(); i++) {
            mapa[fase.xB[i]][fase.yB[i]] = "[B]";
        }

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Legenda:\n\tP = Player\n\tS = Saída\n\tB = Baú");
        System.out
                .println("Vida atual: " + sis.getJogador().getVida() + " Dinheiro: " + sis.getJogador().getDinheiro());
        if (jogador.getTemArma())
            System.out.println("Arma atual: " + jogador.getArmaAtual().getNome());
        System.out.println("");
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
