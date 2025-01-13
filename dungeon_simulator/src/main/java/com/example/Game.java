package com.example;

import java.util.Random;

import com.example.game.*;

public class Game {

    Random rand = new Random();
    Sistema sis = new Sistema();
    Fase fase;
    Map map = new Map();

    public void Start() {
        System.out.println("=====================================================");
        System.out.println("Seja bem vindo ao Simulador de Dungeon feito em Java!");
        System.out.println("Autor: Vinícius Pereira da Costa");
        System.out.println("=====================================================");
        sis.EnterParaProsseguir();

        fase = map.GeraMapa(sis);
        sis.setFase(fase);
        sis.LoopPrincipal(map);
    }
}
