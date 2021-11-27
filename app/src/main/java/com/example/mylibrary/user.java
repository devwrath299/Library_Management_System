package com.example.mylibrary;

import android.net.Uri;

public class user {
    public user(){

    }

    String username,title,bookid,author,cost,quantity,bookshelf,address,contact,email;
   String  uri;

    public String  getUri() {
        return uri;
    }

    public void setUri(String  uri) {
        this.uri = uri;
    }

    public  user(String a)
    {
      username=a;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public user(String a, String b, String c, String d)
    {
        username=a;
        contact=b;
        email=c;
        address=d;
    }
    public user(String a, String b, String c, String d,String e)
    {
        username=a;
        contact=b;
        email=c;
        address=d;
        uri=e;
    }

    public user(String a,String b,String c,String d,String e,String f)
    {
        title=a;
        bookid=b;
        author=c;
        cost=d;
        quantity=e;
        bookshelf=f;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }
}
