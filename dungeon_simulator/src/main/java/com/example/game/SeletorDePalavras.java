package com.example.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SeletorDePalavras {
    Random rand = new Random();

    public String EscolhePalavraAleatoria(File arquivo) {
        List<String> lista = readFileToList(arquivo);
        String palavra = lista.get(rand.nextInt(lista.size()));
        return palavra;

    }

    public List<String> readFileToList(File arquivo) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
            ArrayList<String> palavras = new ArrayList<String>();
            try {
                String line = br.readLine();
                while (line != null) {
                    palavras.add(line);
                    line = br.readLine();
                }
                return palavras;
            } finally {
                br.close();
            }

        } catch (IOException ioe) {
            // Error handling of malformed path
            System.out.println(ioe.getMessage());
        }
        return null;
    }
}
