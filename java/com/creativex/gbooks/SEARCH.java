package com.creativex.gbooks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class SEARCH extends AppCompatActivity {
    public static String s2;
    public static String LOG_TAG="API";
    public String ends="&startIndex=0&maxResults=40";
public static  String s1="https://www.googleapis.com/books/v1/volumes?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        ListView bookslistView=findViewById(R.id.listView);
    }
    public void SearchBooks(View view) {
        SearchView sv= findViewById(R.id.search_text);
        String searchtext=sv.getQuery().toString();
         s2=s1.concat(searchtext);
         s2=s2.concat(ends);
         Log.i(LOG_TAG,s2);
        Intent Books = new Intent(this,BOOKS.class);
        startActivity(Books);
    }


}
