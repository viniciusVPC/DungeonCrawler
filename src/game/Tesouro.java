package game;

import java.util.Random;

public class Tesouro {
    Random rand = new Random();
    private String nome, adjetivo;
    
    private String nomes[] = {"C�lice", "Anel", "Morango", "Pergaminho", "Metal", "Cr�nio"};
    private String adjetivos[] = {"Dourado", "Prateado", "Fr�gil", "Duradouro", "Esquisito", "Secreto"};
    
    public Tesouro(){
        this.nome = nomes[rand.nextInt(nomes.length)];
        this.adjetivo = adjetivos[rand.nextInt(adjetivos.length)];
    }

    public String getNome() {
        return nome;
    }

    public String getAdjetivo() {
        return adjetivo;
    }
}
