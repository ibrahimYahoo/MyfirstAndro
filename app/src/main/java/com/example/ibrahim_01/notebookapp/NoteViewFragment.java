package com.example.ibrahim_01.notebookapp;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View FragmentLayout = inflater.inflate(R.layout.fragment_note_view, container, false);

        TextView productName = (TextView) FragmentLayout.findViewById(R.id.ViewNoteTitle);
        TextView productPrice = (TextView) FragmentLayout.findViewById(R.id.PriceValueForDetailView);

        TextView productDescription = (TextView) FragmentLayout.findViewById(R.id.ViewNoteMessage);
        ImageView productImage = (ImageView) FragmentLayout.findViewById(R.id.ViewNoteIcon);


        Intent intent = getActivity().getIntent();

        productName.setText(intent.getExtras().getString(MainActivity.product_Namme_extra));
        productPrice.setText(intent.getExtras().getString(MainActivity.product_price_extra));

        productDescription.setText(intent.getExtras().getString(MainActivity.product_description_extra));


        Note.Category noteCat = (Note.Category)intent.getSerializableExtra(MainActivity.product_category_extra);

        productImage.setImageResource(Note.CategoryToDrawable(noteCat));







        return FragmentLayout;
    }







}
