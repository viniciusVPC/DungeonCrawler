package projetodungeoncrawler;
import java.util.Random;

public class Monstro {
    //parametros:
    Random rand = new Random();
    private String nome, adjetivo;
    private int vida;
    
    private String nomes[] = {"Frankenstein", "Vampiro", "Zumbi", "Esqueleto", "Fantasma", "Rato"};
    private String adjetivos[] = {"Gordo", "Magro", "Doente", "Esguio", "Musculoso", "Raivoso"};
    
    public Monstro() {
        this.nome = nomes[rand.nextInt(nomes.length)];
        this.adjetivo = adjetivos[rand.nextInt(adjetivos.length)];
        vida = rand.nextInt(0, 10);
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
}