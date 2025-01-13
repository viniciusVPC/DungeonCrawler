package com.example.game;

import java.util.ArrayList;

import com.example.items.Tesouro;

public class Fase {

    Tesouro tesouroFase;
    ArrayList<int[]> posicItens = new ArrayList<>();
    int[] xI;
    int[] yI;

    public Fase(Tesouro tesouroFase, ArrayList<int[]> posicItens, int[] xI, int[] yI) {
        this.tesouroFase = tesouroFase;
        this.posicItens = posicItens;
        this.xI = xI;
        this.yI = yI;
    }

    public Tesouro getTesouroFase() {
        return tesouroFase;
    }

    public void setTesouroFase(Tesouro tesouroFase) {
        this.tesouroFase = tesouroFase;
    }

    public ArrayList<int[]> getPosicItens() {
        return posicItens;
    }

    public void setPosicItens(ArrayList<int[]> posicItens) {
        this.posicItens = posicItens;
    }

    public int[] getxI() {
        return xI;
    }

    public void setxI(int[] xI) {
        this.xI = xI;
    }

    public int[] getyI() {
        return yI;
    }

    public void setyI(int[] yI) {
        this.yI = yI;
    }

}
