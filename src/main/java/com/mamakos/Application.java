package com.mamakos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Trigram t = new Trigram();

        BufferedReader bf = new BufferedReader(new FileReader("WarAndPeace.txt"));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = bf.readLine()) != null){
            if(!line.equals("")) {
                sb.append(line);
                sb.append(" ");
            }
        }
        String book = sb.toString();
        t.add(book);
        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("====");
            t.writeStory();
        }

    }
}
