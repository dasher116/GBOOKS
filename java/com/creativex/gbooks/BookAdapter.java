package com.creativex.gbooks;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookAdapter  extends ArrayAdapter<BookContents> {


    public BookAdapter(@NonNull Context context, ArrayList<BookContents> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItem = convertView;
        if (convertView == null) {
            ListItem = LayoutInflater.from(getContext()).inflate(R.layout.bookslist, parent, false);
        }
        BookContents bc=getItem(position);
           String aname=bc.authorname();
        TextView name1= (TextView)ListItem.findViewById(R.id.authorname);
        //String autname= Arrays.toString(aname);
        name1.setText(aname);
        String bname=bc.getBookname();
        TextView name2=(TextView)ListItem.findViewById(R.id.Bookname);
        name2.setText(bname);
        String desc=bc.about();
        TextView about=ListItem.findViewById(R.id.description);
        about.setText(desc);
        String link=bc.link();
        TextView links=ListItem.findViewById(R.id.link);
        links.setText(link);
 return ListItem;   }

}