package com.example.items;

import java.util.Random;

public class Armas {

    Random rand = new Random();

    public Arma[] armas = {
            new Arma(1, 0.5f, "Espada de Madeira",
                    "Uma simples espada de madeira. Não parecer ser muito forte."),
            new Arma(1, 1, "Espada de Ferro",
                    "Uma espada resistente feita de ferro. Tem uma força considerável."),
            new Arma(3, 0.f, "Cajado Mágico",
                    "Um cajado capaz de atacar em área. Tem um poder relativamente fraco.")
    };

    public Arma ArmaRandom() {
        return armas[rand.nextInt(armas.length)];
    }
}
