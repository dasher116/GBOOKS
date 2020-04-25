package com.creativex.gbooks;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.creativex.gbooks.SEARCH.s2;

public class BOOKS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);
        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        asyncactivity as = new asyncactivity();
        as.execute(s2);
    }

    class asyncactivity extends AsyncTask<String, Void, ArrayList<BookContents>> {
        @Override
        protected void onPreExecute() {
            ProgressBar spin = findViewById(R.id.spinnid);
            spin.setVisibility(View.VISIBLE);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                Log.i("hii", String.valueOf(e));
            }

        }

        @Override
        protected ArrayList<BookContents> doInBackground(String... strings) {
            ArrayList<BookContents> books = FetchData.extract(strings[0]);
            return books;
        }
        protected  void onPostExecute(ArrayList<BookContents> books){
           // TextView nulltext=findViewById(R.id.Null);
            //nulltext.setVisibility(View.INVISIBLE);
            ListView list=(ListView)findViewById(R.id.listView) ;
           BookAdapter adapt=new BookAdapter(getBaseContext(),books);
          list.setAdapter(adapt);
        }
    }
}
