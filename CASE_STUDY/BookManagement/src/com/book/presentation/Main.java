package com.book.presentation;

import com.book.service.CardService;
import com.book.service.MenuService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        CardService cardService = new CardService();
        try {
            cardService.loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuService.performContact();
    }
}
