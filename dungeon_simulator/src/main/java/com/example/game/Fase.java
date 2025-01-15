package com.example.game;

import java.util.ArrayList;

import com.example.items.Tesouro;

public class Fase {

    Tesouro tesouroFase;
    ArrayList<int[]> posicBaus = new ArrayList<>();
    int[] xB;
    int[] yB;

    public Fase(ArrayList<int[]> posicBaus, int[] xB, int[] yB) {
        this.posicBaus = posicBaus;
        this.xB = xB;
        this.yB = yB;
    }

    public ArrayList<int[]> getPosicBaus() {
        return posicBaus;
    }

    public void setPosicBaus(ArrayList<int[]> posicBaus) {
        this.posicBaus = posicBaus;
    }

    public int[] getxB() {
        return xB;
    }

    public void setxB(int[] xB) {
        this.xB = xB;
    }

    public int[] getyB() {
        return yB;
    }

    public void setyB(int[] yB) {
        this.yB = yB;
    }

}
