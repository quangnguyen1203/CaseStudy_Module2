package com.book.service;

import com.book.dal.CardDB;
import com.book.model.Card;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CardService {
    CardDB cardDB = new CardDB();
    Scanner sc = new Scanner(System.in);

    public void loadData() throws IOException {
        cardDB.readFile();
    }

    public String toUpperCase(String string){
        char[] charArray = string.toCharArray();
        boolean foundSpace = true;
        for(int i = 0; i < charArray.length; i++) {
            charArray[i] = Character.toLowerCase(charArray[i]);
            if(Character.isLetter(charArray[i])) {
                if(foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            }
            else {
                foundSpace = true;
            }
        }
        String outputString = String.valueOf(charArray);
        return outputString;
    }

    public String inputName(){
        String name;
        do {
            System.out.println("Nhập họ tên: ");
            name = toUpperCase(sc.nextLine());
        }while (!checkName(name));
        return name;
    }

    public boolean checkName(String name){
        String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public int inputAge(){
        int age;
        do {
            System.out.println("Nhập tuổi: ");
            age = Integer.parseInt(sc.nextLine());
        } while (!checkAge(age));
        return age;
    }

    public boolean checkAge(int age){
        return (0< age && age < 100) ? true : false;
    }

    public String inputSchool(){
        System.out.println("Nhập lớp:");
        return toUpperCase(sc.nextLine());
    }

    public String inputID(){
        System.out.println("Nhập mã phiếu mượn: ");
        while (!sc.hasNextLine()){
            System.out.println("Không tìm thấy. Yêu cầu nhập hợp lệ");
            sc.nextLine();
        }
        String id = sc.nextLine();
        for(int i=0;i<cardDB.cardList.size();i++){
            if(cardDB.cardList.get(i).getId().equals(id)){
                return null;
            }
        }
        return id;
    }

    public String inputBorrowDate(){
        String borrowDate;
        do {
            System.out.println("Nhập ngày mượn: ");
            borrowDate = toUpperCase(sc.nextLine());
        }while (!checkDate(borrowDate));
        return borrowDate;
    }

    public String inputPaymentDate(){
        String paymentDate;
        do {
            System.out.println("Nhập ngày trả: ");
            paymentDate = toUpperCase(sc.nextLine());
        }while (!checkDate(paymentDate));
        return paymentDate;
    }

    public boolean checkDate(String date){
        String regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    public String inputBookID(){
        System.out.println("Nhập số hiệu sách: ");
        return toUpperCase(sc.nextLine());
    }

    public void addBorrow() throws IOException {
        String name = inputName();
        int age = inputAge();
        String school = inputSchool();
        String id = inputID();
        if(id == null) {
            System.out.println("Mã phiếu mượn đã tồn tại");
            return;
        }
        String borrowDate = inputBorrowDate();
        String paymentDate = inputPaymentDate();
        String bookId = inputBookID();
        Card card = new Card(name,age,school,id,borrowDate,paymentDate,bookId);
        cardDB.add(card);
        cardDB.saveFile();
        System.out.println("Bạn đã thêm thành công!!!");
    }

    public void edit() throws IOException {
        System.out.println("Nhập mã phiếu mượn cần sửa: ");
        String id = sc.nextLine();
        boolean check = false;
        for (int i = 0; i < cardDB.cardList.size() ; i++) {
            if(cardDB.cardList.get(i).getId().equals(id)){
                check = true;
                cardDB.cardList.get(i).setName(inputName());
                cardDB.cardList.get(i).setAge(inputAge());
                cardDB.cardList.get(i).setSchool(inputSchool());
                cardDB.cardList.get(i).setBorrowDate(inputBorrowDate());
                cardDB.cardList.get(i).setPaymentDate(inputPaymentDate());
                cardDB.cardList.get(i).setBookID(inputBookID());
                cardDB.saveFile();
                System.out.println("Đã sửa thành công!!!");
            }
        }
        if(!check){
            System.out.println("Không tìm thấy mã phiếu mượn!!!");
        }
    }

    public void delete() throws IOException {
        boolean check = false;
        System.out.println("Nhập mã phiếu mượn: ");
        String id = sc.nextLine();
        for (int i = 0; i < cardDB.cardList.size(); i++) {
            if(cardDB.cardList.get(i).getId().equals(id)){
                check = true;
                displayFormat();
                cardDB.cardList.get(i).displayAll();
                System.out.println("Bạn muốn xóa phiếu mượn này ?");
                System.out.println("Chọn Y(tiếp tục) / khác(thoát)");
                switch (toUpperCase(sc.nextLine())){
                    case "Y":
                        String temp = cardDB.cardList.get(i).getId();
                        cardDB.cardList.remove(cardDB.cardList.get(i));
                        System.out.println("Bạn vừa xóa phiếu mượn <"+temp+"> khỏi danh sách !");
                        cardDB.saveFile();
                        break;

                    default:
                        break;
                }
            }
        }
        if(!check){
            System.out.println("Không tìm thấy số ĐT !");
        }
    }


    public void searchExist(String string){
        boolean check = false;
        for (int i = 0; i < cardDB.cardList.size() ; i++) {
            if(cardDB.cardList.get(i).getId().equals(string)||cardDB.cardList.get(i).getName().equals(string)){
                check = true;
                displayFormat();
                cardDB.cardList.get(i).displayAll();
            }
        }
        if(!check){
            System.out.println("Không tìm thấy trong danh sách.");
        }
    }

    public void searchId(String id){
        searchExist(id);
    }

    public void searchName(String name){
        searchExist(name);
    }

    public void print(){
        if(cardDB.cardList.size()==0){
            System.out.println("Danh sách mượn hiện tại còn trống!!!");
        } else {
            displayFormat();
            for (int i = 0; i < cardDB.cardList.size(); i++) {
                cardDB.cardList.get(i).displayAll();
            }
        }
    }

    public void displayFormat(){
        System.out.printf("| %20s | %5s | %12s | %15s | %12s | %12s | %12s |"
                ,"Họ tên","Tuổi","Lớp","Mã phiếu mượn","Ngày mượn","Ngày trả","Số hiệu sách");
        System.out.println();
    }
}
