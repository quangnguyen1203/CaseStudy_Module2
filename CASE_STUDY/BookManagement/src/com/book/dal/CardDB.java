package com.book.dal;

import com.book.model.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardDB {
    public static Scanner sc = new Scanner(System.in);
    public static List<Card> cardList = new ArrayList<>();
    public static final String BOOK_FILE = "C:\\Module-2\\CaseStudy_Module2\\CASE_STUDY\\BookManagement\\data\\cards.csv";

    public void saveFile() throws IOException {
        File file = new File(BOOK_FILE);
        if(!file.exists()){
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(BOOK_FILE);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        for (int i = 0; i < cardList.size() ; i++) {
            bos.write(cardList.get(i).toStringCSV().getBytes());
        }
        bos.flush();
        bos.close();
        fos.close();
    }

    public void readFile() throws IOException {
        File file = new File(BOOK_FILE);
        if(!file.exists()){
            return;
        }

        FileReader fr = new FileReader(BOOK_FILE);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            String[] str = line.split(",");
            Card card = new Card(str[0],Integer.parseInt(str[1]),str[2],str[3],str[4],str[5],str[6]);
            add(card);
        }
    }
    public void add(Card card){
        cardList.add(card);
    }
}
