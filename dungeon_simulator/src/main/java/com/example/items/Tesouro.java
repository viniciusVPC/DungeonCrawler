package com.example.items;

import java.io.File;
import java.util.Random;

import com.example.game.SeletorDePalavras;
import com.example.game.Sistema;

public class Tesouro {
    Random rand = new Random();
    private String nome, adjetivo;
    private int valor;
    SeletorDePalavras seletor = new SeletorDePalavras();
    Sistema sis = null;
    private boolean masculino;

    public Tesouro(Sistema sis) {
        if (this.sis == null)
            this.sis = sis;
        masculino = rand.nextBoolean();
        File fileNome, fileAdjetivo;
        if (masculino) {
            fileNome = new File("dungeon_simulator\\src\\main\\resources\\nomesTesourosMasc.txt");
            fileAdjetivo = new File("dungeon_simulator\\src\\main\\resources\\adjetivosTesousosMasc.txt");
        } else {
            fileNome = new File("dungeon_simulator\\src\\main\\resources\\nomesTesourosFem.txt");
            fileAdjetivo = new File("dungeon_simulator\\src\\main\\resources\\adjetivosTesousosFem.txt");
            // TODO colocar arquivos relativos ao .jar final
        }

        this.nome = seletor.EscolhePalavraAleatoria(fileNome);
        this.adjetivo = seletor.EscolhePalavraAleatoria(fileAdjetivo);
        this.valor = rand.nextInt(sis.getFaseNum() * 10, (sis.getFaseNum() + 2) * 10);
    }

    public String getNome() {
        return nome;
    }

    public String getAdjetivo() {
        return adjetivo;
    }

    public int getValor() {
        return valor;
    }

    public String isMasculino() {
        if (masculino)
            return "o";
        return "a";
    }
}
