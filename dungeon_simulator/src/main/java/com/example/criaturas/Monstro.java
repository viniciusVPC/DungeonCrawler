package com.example.criaturas;

import java.util.Random;

public class Monstro {
    Random rand = new Random();
    private String nome, adjetivo;
    private int vida;
    private int dano;

    private String nomes[] = { "Frankenstein", "Vampiro", "Zumbi", "Esqueleto", "Fantasma", "Rato" };
    private String adjetivos[] = { "Gordo", "Magro", "Doente", "Esguio", "Musculoso", "Raivoso" };

    public Monstro() {
        this.nome = nomes[rand.nextInt(nomes.length)];
        this.adjetivo = adjetivos[rand.nextInt(adjetivos.length)];
        vida = rand.nextInt(1, 10);
        dano = vida / 2;
    }

    public String getNome() {
        return nome;
    }

    public String getAdjetivo() {
        return adjetivo;
    }

    public int getVida() {
        return vida;
    }

    public int Ataca() {
        System.out.println("O " + nome + " te ataca e te dá " + dano + " de dano.");
        if (dano < 1)
            dano = 1;
        return dano;
    }
}
