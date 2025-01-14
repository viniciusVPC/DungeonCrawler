package com.example.game;

import java.util.Random;
import java.util.Scanner;

import com.example.combate.Combate;
import com.example.items.Arma;
import com.example.items.Armas;
import com.example.player.Player;

public class Sistema {

    Map map;
    Fase fase;
    int faseNum = 1;
    int[] posicPlayer, posicTesouro;
    int xP, yP, xT, yT;
    Player jogador = new Player();
    Combate combate = new Combate();
    Armas armas = new Armas();

    Random rand = new Random();

    public void LoopPrincipal(Map map) {
        this.map = map;
        while (true) {

            if (xP == xT && yP == yT) {
                PegaTesouro();
                ProximaFase();
            } else {
                if ("[I]".equals(map.getMapa()[xP][yP])) {
                    PegaItem();
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

    public void PegaTesouro() {
        MudaTela();
        System.out.println("Parabéns você chegou ao tesouro!");
        System.out.println(
                "O tesouro é: um " + fase.getTesouroFase().getNome() + " " + fase.getTesouroFase().getAdjetivo());
        EnterParaProsseguir();
    }

    public void PegaItem() {
        MudaTela();
        System.out.println("Parabéns, você achou um item!");
        Arma novaArma = armas.ArmaRandom();
        System.out.println(ExibeItem(novaArma));
        jogador.PegarArma(novaArma);

        for (int i = 0; i < fase.posicItens.size(); i++) {
            if (fase.posicItens.get(i)[0] == xP && fase.posicItens.get(i)[1] == yP) {
                fase.posicItens.remove(i);
            }
        }
        EnterParaProsseguir();
    }

    public String ExibeItem(Arma arma) {
        String texto = "Você conseguiu uma arma: " + arma.getNome();
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

    public int[] getPosicTesouro() {
        return posicTesouro;
    }

    public void setPosicTesouro(int[] posicTesouro) {
        this.posicTesouro = posicTesouro;
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

    public int getxT() {
        return xT;
    }

    public void setxT(int xT) {
        this.xT = xT;
    }

    public int getyT() {
        return yT;
    }

    public void setyT(int yT) {
        this.yT = yT;
    }

    public Player getJogador() {
        return jogador;
    }

    public void setJogador(Player jogador) {
        this.jogador = jogador;
    }

}
