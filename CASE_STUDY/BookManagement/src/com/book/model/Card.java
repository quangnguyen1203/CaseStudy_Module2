package com.book.model;

public class Card {
    private String name;
    private int age;
    private String school;
    private String id;
    private String borrowDate;
    private String paymentDate;
    private String bookID;

    public Card(String name, int age, String school, String id, String borrowDate, String paymentDate, String bookID) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.id = id;
        this.borrowDate = borrowDate;
        this.paymentDate = paymentDate;
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String toStringCSV(){
        return name+ "," + age + "," + school + "," + id + "," + borrowDate  + "," + paymentDate + "," + bookID + "\n";
    }

    public void displayAll(){
        System.out.printf("| %20s | %5s | %12s | %15s | %12s | %12s | %12s |",
                name,age,school,id,borrowDate,paymentDate,bookID);
        System.out.println();
    }
}
