package com.example.player;

import java.util.Scanner;

import com.example.game.Map;
import com.example.game.Sistema;
import com.example.items.Arma;
import com.example.items.Armas;

public class Player {
    // parâmetros
    int vidaMax, vida, forca, dinheiro;
    Boolean temArma;
    Armas seletorArma;
    Arma armaAtual;
    Sistema sis;
    Map map;
    Scanner input = new Scanner(System.in);

    public Player() {
        vidaMax = 100;
        vida = vidaMax;
        forca = 5;
    }

    public float Atacar() {
        float danoFinal;
        if (temArma) {
            danoFinal = forca * armaAtual.getMultiplicador();
        } else
            danoFinal = forca;
        return danoFinal;
    }

    public void PegarArma(Arma arma) {
        temArma = true;
        armaAtual = arma;
    }

    public void Mover(Sistema sis, Map map) {
        this.sis = sis;
        this.map = map;
        boolean erro = false;
        int xPTemp = sis.getxP();
        int yPTemp = sis.getyP();
        int[] posicTemp = sis.getPosicPlayer();

        do {
            erro = false;
            System.out.print("\nPara onde você quer ir?\n");
            System.out.println("");

            if (sis.getPosicPlayer()[0] - 1 >= 0) {
                System.out.print("[1] Cima");
            }
            if (sis.getPosicPlayer()[0] + 1 <= map.getMapa().length - 1) {
                System.out.print("[2] Baixo");
            }
            if (sis.getPosicPlayer()[1] - 1 >= 0) {
                System.out.print("[3] Esquerda");
            }
            if (sis.getPosicPlayer()[1] + 1 <= map.getMapa().length - 1) {
                System.out.print("[4] Direita");
            }
            System.out.println("");

            int resposta = input.nextInt();
            switch (resposta) {
                case 1 -> {
                    if (sis.getPosicPlayer()[0] - 1 >= 0) {
                        sis.setxP(xPTemp -= 1);
                        posicTemp[0] -= 1;
                        sis.setPosicPlayer(posicTemp);
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 2 -> {
                    if (sis.getPosicPlayer()[0] + 1 <= map.getMapa().length - 1) {
                        sis.setxP(xPTemp += 1);
                        posicTemp[0] += 1;
                        sis.setPosicPlayer(posicTemp);
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 3 -> {
                    if (sis.getPosicPlayer()[1] - 1 >= 0) {
                        sis.setyP(yPTemp -= 1);
                        posicTemp[1] -= 1;
                        sis.setPosicPlayer(posicTemp);
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
                case 4 -> {
                    if (sis.getPosicPlayer()[1] + 1 <= map.getMapa().length - 1) {
                        sis.setyP(yPTemp += 1);
                        posicTemp[1] += 1;
                        sis.setPosicPlayer(posicTemp);
                    } else {
                        System.out.println("Não pode nessa direção.");
                        erro = true;
                    }
                }
            }
        } while (erro);

    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public void adicionaDinheiro(int dinheiro) {
        this.dinheiro += dinheiro;
    }
}
