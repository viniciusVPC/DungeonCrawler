package com.example.player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.example.combate.Combate;
import com.example.game.Map;
import com.example.game.Sistema;
import com.example.items.Arma;
import com.example.items.Armas;
import com.example.items.Tesouro;

public class Player {
    // parâmetros
    int vidaMax, vida, forca, dinheiro;
    Boolean temArma = false;
    Armas seletorArma;
    Arma armaAtual;
    Sistema sis;
    ArrayList<Tesouro> listaTesouros = new ArrayList<>();
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
                    System.out.println("resposta inválida");
                    erro = true;
                    break;
            }
        } while (erro);
    }

    public int Atacar() {
        float danoFinal;
        if (temArma) {
            danoFinal = forca + 1 * armaAtual.getMultiplicador();
        } else
            danoFinal = forca;
        return Math.round(danoFinal);
    }

    public void PegarTesouro(Tesouro tesouro) {
        System.out.println("Você coloca " + tesouro.isMasculino() + " " + tesouro.getNome() + " na sua mochila");
        listaTesouros.add(tesouro);
    }

    public void PegarArma(Arma arma) {
        if (temArma) {
            do {
                System.out.println("Você deseja trocar sua arma atual (" + armaAtual.getNome() + " dano: "
                        + armaAtual.getMultiplicador() +
                        ") pela nova arma (" + arma.getNome() + " dano: " + armaAtual.getMultiplicador() + ")?");
                System.out.println("[1] Sim     [2] Não");
                int resposta = input.nextInt();
                switch (resposta) {
                    case 1:
                        System.out.println("Você larga a sua arma atual e pega a nova arma (" + arma.getNome() + ")");
                        armaAtual = arma;
                        break;
                    case 2:
                        System.out.println("Você decide manter sua arma atual.");

                    default:
                        System.out.println("resposta inválida");
                        erro = true;
                        break;
                }
            } while (erro);
        }
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
                System.out.print("[1] Cima ");
            }
            if (sis.getPosicPlayer()[0] + 1 <= map.getMapa().length - 1) {
                System.out.print("[2] Baixo ");
            }
            if (sis.getPosicPlayer()[1] - 1 >= 0) {
                System.out.print("[3] Esquerda ");
            }
            if (sis.getPosicPlayer()[1] + 1 <= map.getMapa().length - 1) {
                System.out.print("[4] Direita ");
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
        System.out.println("");
        System.out.println("Arma atual: " + (temArma ? armaAtual.getNome() : "nenhuma"));
        if (temArma) {
            System.out.println(armaAtual.getDescricao());
            System.out.println("Dano: " + armaAtual.getMultiplicador());
            System.out.println("Área: " + armaAtual.getAreaDano());
        }
        if (!listaTesouros.isEmpty()) {
            System.out.println("Tesouros: ");
            for (Tesouro tesouro : listaTesouros) {
                System.out.println(tesouro.getNome() + " " + tesouro.getAdjetivo());
            }
        }
        System.out.println("");
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
