package projetodungeoncrawler;
import projetodungeoncrawler.armas.*;

public class Player {
    //parâmetros
    int vidaMax, vida, forca;
    Boolean temArma;
    Armas seletorArma;
    Arma armaAtual;
    
    public Player(){
        vidaMax = 100;
        vida = vidaMax;
        forca = 5;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    public float Atacar(){
        float danoFinal;
        if(temArma){
            danoFinal = forca*armaAtual.getMultiplicador();
        }else danoFinal = forca;
        return danoFinal;
    }
    
    public void PegarArma(Arma arma){
        temArma = true;
        armaAtual = arma;
    }
}
