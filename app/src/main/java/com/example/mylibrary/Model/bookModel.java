package com.example.mylibrary.Model;

public class bookModel {
    bookModel()
    {

    }


    String bookname,author,bookid,shelfno,cost,quantity,image;


    public bookModel(String bookname, String author, String bookid, String shelfno, String cost, String quantity, String image) {
        this.bookname = bookname;
        this.author = author;
        this.bookid = bookid;
        this.shelfno = shelfno;
        this.cost = cost;
        this.quantity = quantity;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
