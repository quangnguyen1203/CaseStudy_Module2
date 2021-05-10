package com.book.service;

import java.io.IOException;
import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);
    CardService cardService = new CardService();
    public void showMenu(){
        System.out.println("*********** QUẢN LÝ VIỆC MƯỢN SÁCH, TRẢ SÁCH **********");
        System.out.println("1. Thêm ");
        System.out.println("2. Sửa ");
        System.out.println("3. Xóa ");
        System.out.println("4. Tìm kiếm ");
        System.out.println("5. Hiển thị toàn bộ danh sách ");
        System.out.println("6. Thoát ");
    }

    public void continueConfirm() {
        System.out.println("Nhấn Y để tiếp tục, nhấn N để thoát.");
        String choice;
        while (true) {
            choice = sc.nextLine();
            switch (choice){
                case "Y": {
                    performContact();
                    break;
                }
                case "N": {
                    System.out.println("Thoát.");
                    System.exit(0);
                }
                default:
                    System.out.println("Bạn nhập sai ");
                    continueConfirm();
            }
        }
    }

    public void searchBook() {
        System.out.println("Nhấn I để tìm kiếm theo mã phiếu mượn, nhấn N để tìm kiếm theo tên.");
        String choice;
        while (true) {
            choice = sc.nextLine();
            switch (choice){
                case "I": {
                    System.out.println("Nhập mã phiếu mượn: ");
                    cardService.searchId(sc.nextLine());
                    continueConfirm();
                    break;
                }
                case "N": {
                    System.out.println("Nhập họ tên: ");
                    cardService.searchName(sc.nextLine());
                    continueConfirm();
                    break;
                }
                default:
                    System.out.println("Bạn nhập sai!! ");
                    continueConfirm();
            }
        }
    }

    public void performContact(){
        String choose;
        while (true){
            showMenu();
            choose = sc.nextLine();
            switch (choose){
                case "1":
                    try {
                        cardService.addBorrow();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        cardService.edit();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        cardService.delete();
                        continueConfirm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    searchBook();
                    continueConfirm();
                    break;
                case "5":
                    cardService.print();
                    continueConfirm();
                    break;
                case "6":
                    System.out.println("Thoát.");
                    System.exit(0);
                default:
                    System.out.println("Yêu cầu nhập lại: ");
            }
        }
    }
}
