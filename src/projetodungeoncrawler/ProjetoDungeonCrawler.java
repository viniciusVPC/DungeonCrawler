package projetodungeoncrawler;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ProjetoDungeonCrawler {
Random rand = new Random();
ArrayList<Monstro> monstros = new ArrayList();
Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ProjetoDungeonCrawler projeto = new ProjetoDungeonCrawler();
        projeto.Start();
    }
    
    public void Start(){
        int quantMonstros = rand.nextInt(5);
        System.out.println("Tu te deparas com " + quantMonstros + " monstros.");
        for (int i = 0; i < quantMonstros; i++) {
            Monstro monstro = new Monstro();
            monstros.add(monstro);
            System.out.print(introduzMonstro(monstro) + "\n");
        }
        Combate();
    }
    
    public void Combate(){
        int resposta;
        do{
            System.out.print("O que tu vais fazer?\n[1] Atacar\n[2] Curar\n[3] Fugir\n");
            resposta = input.nextInt();
            switch(resposta){
                case 1 -> {
                    System.out.println("Aqui tu atacas.");
                    Ataque();
                }
                case 2 -> System.out.println("Aqui tu te curas.");
                case 3 -> System.out.println("Aqui tu foges.");
                default -> System.out.println("Resposta errada.");
            }
        }while(resposta != 0);
    }
    
    public void Ataque(){
        System.out.println("Qual inimigo tu queres atacar?");
        for (int i = 0; i < monstros.size(); i++) {
            System.out.print("[" + (i+1) + "] " + monstros.get(i).getNome() + "\n");
        }
        System.out.print("\n");
        int resposta = input.nextInt();
        System.out.println("Tu atacas e derrota o " + monstros.get(resposta-1).getNome());
        monstros.remove(monstros.get(resposta-1));
        System.out.println("Agora restam somente os: ");
        exibeMonstros();
    }
    
    public void exibeMonstros(){
        for (int i = 0; i < monstros.size(); i++) {
            System.out.print(monstros.get(i).getNome() + "\t");
        }
    }
    
    public String introduzMonstro(Monstro monstro){
        String texto = "Um " + monstro.getNome() + " " + monstro.getAdjetivo() + " com " + monstro.getVida() + " pontos de vida.";
        return texto;
    }
}
