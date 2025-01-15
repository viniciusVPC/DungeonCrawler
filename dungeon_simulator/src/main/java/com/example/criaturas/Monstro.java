package com.example.criaturas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.game.SeletorDePalavras;
import com.example.game.Sistema;

public class Monstro {
    Random rand = new Random();
    private String nome, adjetivo;
    private int vida, vidaAtual;
    SeletorDePalavras seletor = new SeletorDePalavras();
    private boolean masculino;
    Sistema sis = null;

    public Monstro(Sistema sis) {
        if (this.sis == null)
            this.sis = sis;
        masculino = rand.nextBoolean(); // true macho, false femea
        File fileNome, fileAdjetivo;
        if (masculino) {
            fileNome = new File("dungeon_simulator/src/main/resources/monstrosMasc.txt");
            fileAdjetivo = new File("dungeon_simulator/src/main/resources/adjetivosMasc.txt");
        } else {
            fileNome = new File("dungeon_simulator/src/main/resources/monstrosFem.txt");
            fileAdjetivo = new File("dungeon_simulator/src/main/resources/adjetivosFem.txt");
            // TODO colocar arquivos relativos ao .jar final (tipo a .minecraft)
        }

        this.nome = seletor.EscolhePalavraAleatoria(fileNome);
        this.adjetivo = seletor.EscolhePalavraAleatoria(fileAdjetivo);

        vida = rand.nextInt(5 * (sis.getFaseNum() - 1), 5 * (sis.getFaseNum() + 1));
        if (vida == 0)
            vida = 1;
        vidaAtual = vida;
    }

    public String getNome() {
        return nome;
    }

    public String getAdjetivo() {
        return adjetivo;
    }

    public int getVida() {
        return vidaAtual;
    }

    public boolean isMasculino() {
        return masculino;
    }

    public void receberDano(float dano) {
        vidaAtual -= dano;
    }

    public int Ataca() {
        int dano = rand.nextInt(vida / 2, vida * 2);
        if (dano < 1)
            dano = 1;
        System.out.println((masculino ? "O " : "A ") + nome + " te ataca e te dá " + dano + " de dano.");
        return dano;
    }

}
