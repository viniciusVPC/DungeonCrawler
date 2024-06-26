package game;
import armas.Armas;
import armas.Arma;

public class Player {
    //parāmetros
    int vidaMax, vida, forca, dinheiro;
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
    
    public int getDinheiro(){
        return dinheiro;
    }
    
    public void adicionaDinheiro(int dinheiro){
        this.dinheiro += dinheiro;
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
