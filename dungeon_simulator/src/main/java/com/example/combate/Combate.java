package com.example.combate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.example.criaturas.Monstro;
import com.example.game.Sistema;
import com.example.player.Player;

public class Combate {

    int quantMonstros;
    ArrayList<Monstro> monstros = new ArrayList<>();
    int round = 0;
    Random rand = new Random();
    Sistema sis;

    Scanner input = new Scanner(System.in);
    Player jogador;

    public void AtaqueSurpresa(Sistema sis, Player jogador) {
        this.sis = sis;
        this.jogador = jogador;
        quantMonstros = rand.nextInt(1, 1 + sis.getFaseNum());
        sis.MudaTela();
        System.out.println("ATAQUE SURPRESA");
        System.out.println("Tu te deparas com " + quantMonstros + " monstros.");

        for (int i = 0; i < quantMonstros; i++) {
            Monstro monstro = new Monstro();
            monstros.add(monstro);
            System.out.print(introduzMonstro(monstro) + "\n");
        }
        Luta();
        FimDoCombate();
    }

    public String introduzMonstro(Monstro monstro) {
        String texto = "Um " + monstro.getNome() + " " + monstro.getAdjetivo() + " com " + monstro.getVida()
                + " pontos de vida.";
        return texto;
    }

    public void exibeMonstros() {
        for (int i = 0; i < monstros.size(); i++) {
            System.out.print(monstros.get(i).getNome() + "\t");
        }
        System.out.println("");
    }

    public void Luta() {
        int resposta;
        do {
            round++;
            System.out.println("ROUND: " + round);
            System.out.print("O que tu vais fazer?\n[1] Atacar\n[2] Curar\n[3] Fugir\n");
            resposta = input.nextInt();
            switch (resposta) {
                case 1 -> {
                    sis.MudaTela();
                    System.out.println("Aqui tu atacas.");
                    Ataque();
                }
                case 2 ->
                    System.out.println("Aqui tu te curas.");
                // TODO curar
                case 3 ->
                    System.out.println("Aqui tu foges.");
                // TODO fugir com probabilidade de erro
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
        sis.MudaTela();
        System.out.println("Tu atacas e derrota o " + monstros.get(resposta - 1).getNome());
        // TODO dano subtrair vida e não matar de uma vez.
        monstros.remove(monstros.get(resposta - 1));
        if (monstros.size() >= 1) {
            System.out.println("Agora resta somente: ");
            exibeMonstros();
        } else {
            System.out.println("Todos os inimigos foram derrotados!");
        }
        sis.EnterParaProsseguir();
    }

    public void RoundInimigo() {
        sis.MudaTela();
        for (Monstro monstro : monstros) {
            jogador.setVida(jogador.getVida() - monstro.Ataca());
            System.out.println("Você agora está com " + jogador.getVida() + " pontos de vida");
        }
    }

    public void FimDoCombate() {
        sis.MudaTela();
        round = 0;
        int premio = rand.nextInt(15);
        // TODO mudar premio de acordo com fase
        System.out.println("Você encontra " + premio + " moedas.");
        jogador.adicionaDinheiro(premio);
        sis.EnterParaProsseguir();

    }
}
