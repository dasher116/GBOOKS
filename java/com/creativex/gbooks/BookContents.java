package com.creativex.gbooks;

import java.util.Date;

public class BookContents {
    private String authourname;
    private String bookname;
    private String description;
    private String link;
    private Date  yearofpublish;
    BookContents(String a,String b,String c,String d){
        authourname=a;
        bookname=b;
        description=c;
        link=d;
    }
    public String authorname(){
        return  authourname;
    }
    public String getBookname(){
        return  bookname;
    }
    public String about(){
        return  description;
    }
    public String link(){
        return  link;
    }

}
