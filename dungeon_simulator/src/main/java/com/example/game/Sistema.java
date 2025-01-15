package com.example.game;

import java.util.Random;
import java.util.Scanner;

import com.example.combate.Combate;
import com.example.items.Arma;
import com.example.items.Armas;
import com.example.items.Tesouro;
import com.example.player.Player;

public class Sistema {

    Map map;
    Fase fase;
    int faseNum = 1;
    int[] posicPlayer, posicSaida;
    int xP, yP, xS, yS;
    Player jogador = new Player();
    Combate combate = new Combate();
    Armas armas = new Armas();

    Random rand = new Random();

    public void LoopPrincipal(Map map) {
        this.map = map;
        while (true) {

            if (xP == xS && yP == yS) {
                alcancaSaida();
                ProximaFase();
            } else {
                if ("[B]".equals(map.getMapa()[xP][yP])) {
                    AbreBau();
                }
            }
            MudaTela();
            map.ExibeMapa(jogador);

            jogador.Opcoes(this, map);
        }
    }

    public void EnterParaProsseguir() {
        Scanner s = new Scanner(System.in);
        System.out.println("Pressione ENTER para prosseguir.");
        s.nextLine();
    }

    public void alcancaSaida() {
        MudaTela();
        System.out.println("Parabéns você chegou à saída!");
        // TODO eventos aleatórios de fim de fase.
        EnterParaProsseguir();
    }

    public void AbreBau() {
        MudaTela();
        System.out.println("Parabéns, você achou um baú!");
        if (rand.nextInt(10) <= 3) {
            Arma novaArma = armas.ArmaRandom();
            System.out.println(ExibeArma(novaArma));
            jogador.PegarArma(novaArma);
        } else {
            Tesouro tesouro = new Tesouro(this);
            System.out.println(ExibeTesouro(tesouro));
            jogador.PegarTesouro(tesouro);
        }

        for (int i = 0; i < fase.posicBaus.size(); i++) {
            if (fase.posicBaus.get(i)[0] == xP && fase.posicBaus.get(i)[1] == yP) {
                fase.posicBaus.remove(i);
            }
        }
        EnterParaProsseguir();
    }

    public String ExibeArma(Arma arma) {
        String texto = "Você conseguiu uma arma: " + arma.getNome();
        return texto;
    }

    public String ExibeTesouro(Tesouro tesouro) {
        String texto = "Você conseguiu um tesouro: " + tesouro.getNome() + " " + tesouro.getAdjetivo();
        return texto;
    }

    public void ProximaFase() {
        MudaTela();
        System.out.println("Parabéns! Você passou para a próxima fase!");
        faseNum++;
        fase = map.GeraMapa(this);
        EnterParaProsseguir();
        LoopPrincipal(map);
    }

    public void MudaTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------");
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public int getFaseNum() {
        return faseNum;
    }

    public void setFaseNum(int faseNum) {
        this.faseNum = faseNum;
    }

    public int[] getPosicPlayer() {
        return posicPlayer;
    }

    public void setPosicPlayer(int[] posicPlayer) {
        this.posicPlayer = posicPlayer;
    }

    public int[] getPosicSaida() {
        return posicSaida;
    }

    public void setPosicSaida(int[] posicSaida) {
        this.posicSaida = posicSaida;
    }

    public int getxP() {
        return xP;
    }

    public void setxP(int xP) {
        this.xP = xP;
    }

    public int getyP() {
        return yP;
    }

    public void setyP(int yP) {
        this.yP = yP;
    }

    public int getxS() {
        return xS;
    }

    public void setxS(int xT) {
        this.xS = xT;
    }

    public int getyS() {
        return yS;
    }

    public void setyS(int yT) {
        this.yS = yT;
    }

    public Player getJogador() {
        return jogador;
    }

    public void setJogador(Player jogador) {
        this.jogador = jogador;
    }

}
