package com.example.ibrahim_01.notebookapp;

import android.content.Context;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by ibrahim-01 on 12/15/2016.
 */
public class NoteAdapter extends ArrayAdapter<Note> {




        public NoteAdapter(Context context, ArrayList<Note> notes  ) {
        super(context,0, notes);
    }

    @Override
    public View getView(int position, View convertView , ViewGroup parent){
        // get data item for this position
        Note note = getItem(position);

        // check ifa n existing is being reused otherwise inflate a new view from custom row layour
        if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout,parent,false);

        }


        //Grab the references of the views woth data associated with the note its referencing
        TextView productName = (TextView) convertView.findViewById(R.id.noteTitle);
        TextView productDesc = (TextView) convertView.findViewById(R.id.noteBody);
        TextView productPrice = (TextView) convertView.findViewById(R.id.priceValue);
        ImageView productImage = (ImageView) convertView.findViewById(R.id.imageViewlistItem);



        //fill each new row with the data associated to it woth the note its referencing
        productPrice.setText(note.getPrice());
        productName.setText(note.getproductName());
        productDesc.setText(note.getproductDescription());
        productImage.setImageResource(note.getAssoicatedDrawable());


        return  convertView;











    }
}
