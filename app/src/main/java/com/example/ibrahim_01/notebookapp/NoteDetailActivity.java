package com.example.ibrahim_01.notebookapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        setTitle(R.string.TitleForDetail);


        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragmentToLaunch =
                (MainActivity.FragmentToLaunch) intent.getSerializableExtra(MainActivity.note_fragment_to_launch_extra);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (fragmentToLaunch){
            case VIEW:


                NoteViewFragment bf = new NoteViewFragment();
                setTitle("View Details");
                ft.add(R.id.noteContainer,bf);

                break;
            case EDIT:
                NoteEditFragment ef = new NoteEditFragment();
                setTitle("Edit Details");
                ft.add(R.id.noteContainer,ef);
                break;
            case CREATE:
                NoteEditFragment cf = new NoteEditFragment();
                setTitle("create note");
                ft.add(R.id.noteContainer,cf);

        }


        ft.commit();



    }
}
