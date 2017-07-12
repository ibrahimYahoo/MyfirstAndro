package com.example.ibrahim_01.notebookapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.FileDescriptor;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    ImageButton productImage;
    private static final int REQUEST_OPEN_RESULT_CODE = 0;
    private Uri mImageUri;
    EditText productName;
    EditText productPrice;
    EditText productDescription;
    AlertDialog confirmDialogObject;
    Uri myuri;




    public NoteEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mImageUri == null){}
        else {

            mImageUri = Uri.parse(savedInstanceState.getString("imageuri"));
        }
        setRetainInstance(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View FragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);
        productImage = (ImageButton) FragmentLayout.findViewById(R.id.editNoteButton);

        if(savedInstanceState != null){

            Glide.with(this)
                    .load(mImageUri)
                    .into(productImage);
        }

        productName = (EditText) FragmentLayout.findViewById(R.id.EditNoteTitle);
        productPrice = (EditText) FragmentLayout.findViewById(R.id.editTextPriceValueForEditDetailView);

        productDescription = (EditText) FragmentLayout.findViewById(R.id.EditNoteMessage);
        Intent intent = getActivity().getIntent();

        productName.setText(intent.getExtras().getString(MainActivity.product_Namme_extra,""));
        productPrice.setText(intent.getExtras().getString(MainActivity.product_price_extra,""));
        productDescription.setText(intent.getExtras().getString(MainActivity.product_description_extra,""));

        Button saveNote1 = (Button) FragmentLayout.findViewById(R.id.saveNote);
        saveNote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialogObject.show();

            }
        });





        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                intent1.setType("image/*");
                startActivityForResult(intent1, REQUEST_OPEN_RESULT_CODE);
            }
        });





        buildConfirmDialog();




        return FragmentLayout;
    }




    @Override
    public void onSaveInstanceState(Bundle outState) {

        if(mImageUri != null){
            outState.putString("imageuri",mImageUri.toString());}
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if(requestCode == REQUEST_OPEN_RESULT_CODE && resultCode == Activity.RESULT_OK ) {
            if(resultData != null) {
                mImageUri = resultData.getData();

                Glide.with(this)
                        .load(mImageUri)
                        .into(productImage);
            }
        }
    }

    private void buildConfirmDialog(){
        AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
        confirmDialog.setTitle("are your sure");
        confirmDialog.setMessage("are you sure you want to save this note");

        confirmDialog.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("save note", "titele" + productName.getText() + "message = " +
                        productDescription.getText() + "product price = " + productPrice.getText() + "imagelocation " + mImageUri.toString());
//                NoteDBAdapter dbAdapter = new NoteDBAdapter(getActivity().getBaseContext());
//                dbAdapter.open();
//                Intent intent = getActivity().getIntent();
//
//                if(intent.getExtras().getString(MainActivity.note_fragment_to_launch_extra,"") == MainActivity.FragmentToLaunch.CREATE.toString() ){
//                    dbAdapter.createNote(productName.getText() + "",productDescription + "",productPrice.getText() + "" ,mImageUri.toString());
//
//
//
//                }




                Intent intent1 = new Intent(getActivity(),MainActivity.class);
                startActivity(intent1);
            }
        });
        confirmDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do nothing
            }
        });

        confirmDialogObject = confirmDialog.create();
    }






}
