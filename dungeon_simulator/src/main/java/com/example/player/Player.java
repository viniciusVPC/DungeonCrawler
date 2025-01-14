package com.example.player;

import java.util.Random;
import java.util.Scanner;

import javax.naming.ldap.Rdn;

import com.example.combate.Combate;
import com.example.game.Map;
import com.example.game.Sistema;
import com.example.items.Arma;
import com.example.items.Armas;

public class Player {
    // parâmetros
    int vidaMax, vida, forca, dinheiro;
    Boolean temArma = false;
    Armas seletorArma;
    Arma armaAtual;
    Sistema sis;
    Map map;
    Scanner input = new Scanner(System.in);
    boolean erro = false;
    String nome;
    int nivel;
    Combate combate = new Combate();

    Random rand = new Random();

    public Player() {
        vidaMax = 100;
        vida = vidaMax;
        forca = 5;
        nome = "Vinícius";
        nivel = 1;
        // TODO vida e força depender de classe escolhida pelo player
        // TODO player escolher nome
    }

    public void Opcoes(Sistema sis, Map map) {
        this.sis = sis;
        this.map = map;
        do {
            erro = false;
            System.out.println("O que você deseja fazer?");
            System.out.println("");
            System.out.print("[1] Andar ");
            System.out.print("[2] Inventário ");
            System.out.println("");
            int resposta = input.nextInt();
            switch (resposta) {
                case 1:
                    Mover();
                    break;
                case 2:
                    ExibeInventario();
                    break;
                default:
                    break;
            }
        } while (erro);
    }

    public float Atacar() {
        float danoFinal;
        if (temArma) {
            danoFinal = forca + 1 * armaAtual.getMultiplicador();
        } else
            danoFinal = forca;
        return danoFinal;
    }

    public void PegarArma(Arma arma) {
        temArma = true;
        armaAtual = arma;
    }

    public void Mover() {
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
        int surpresa = rand.nextInt(10);
        if (surpresa <= 4)
            combate.AtaqueSurpresa(sis, this);
    }

    public void ExibeInventario() {
        sis.MudaTela();
        System.out.println("----------------------------");
        System.out.println("         INVENTÁRIO         ");
        System.out.println("----------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Nível: " + nivel);
        System.out.println("Arma atual: " + (temArma ? armaAtual.getNome() : "nenhuma"));
        if (temArma) {
            System.out.println(armaAtual.getDescricao());
            System.out.println("Dano: " + armaAtual.getMultiplicador());
            System.out.println("Área: " + armaAtual.getAreaDano());
        }
        // TODO exibir armadura
        // TODO exibir lista de itens consumíveis
        sis.EnterParaProsseguir();
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

    public Boolean getTemArma() {
        return temArma;
    }

    public void setTemArma(Boolean temArma) {
        this.temArma = temArma;
    }

    public Arma getArmaAtual() {
        return armaAtual;
    }

    public void setArmaAtual(Arma armaAtual) {
        this.armaAtual = armaAtual;
    }

}
