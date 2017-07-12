package com.example.ibrahim_01.notebookapp;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VegetableListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private  NoteAdapter noteAdapter;

    @Override
    public  void  onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);


        notes = new ArrayList<Note>();
        notes.add(new Note("Cabages","the best cabages in town, i dont know why but it is just accept, and i will not be convincing you","46",Note.Category.CABBAGE));
        notes.add(new Note("ONION","the best ONIONS in town, i dont know why but it is just accept, and i will not be convincing you","46",Note.Category.ONION));
        notes.add(new Note("BROCOLI","the best BROCOLI in town, i dont know why but it is just accept, and i will not be convincing you","47",Note.Category.BROCLI));
        notes.add(new Note("BEANS","the best BEANS  in town, i dont know why but it is just accept, and i will not be convincing you","63",Note.Category.BEANS));
        notes.add(new Note("TOMATO","the best TOMATO in town 2, i dont know why but it is just accept, and i will not be convincing you","74",Note.Category.TOMATO));
        notes.add(new Note("RED CHILLIES, just checking bro i dont know why but it is just accept, and i will not be convincing you","the best mangos in town, i dont know why but it is just accept, and i will not be convincing you","63",Note.Category.RED_CHILE));
        notes.add(new Note("Cabages A-one","the best cabages in town, i dont know why but it is just accept, and i will not be convincing you","63",Note.Category.CABBAGE));
        notes.add(new Note("ONION A-2","the best ONIONS in town, i dont know why but it is just accept, and i will not be convincing you","73",Note.Category.ONION));
        notes.add(new Note("BROCOLI china ka maal","the best BROCOLI in town, i dont know why but it is just accept, and i will not be convincing you","73",Note.Category.BROCLI));
        notes.add(new Note("BEANS swag","the best BEANS  in town, i dont know why but it is just accept, and i will not be convincing you","36",Note.Category.BEANS));



        noteAdapter = new NoteAdapter(getActivity(),notes);
        setListAdapter(noteAdapter);

        /*String[] datasource = {"hello","my","name","is","hello","my","name","is"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,datasource);

        setListAdapter(arrayAdapter);*/

        registerForContextMenu(getListView());

    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // give me the position of the whatever note i long pressed on
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        switch (item.getItemId()){

            case R.id.edit:
                LaunchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT ,rowPosition);
                Log.d("menu clicks", "we pressed menu clicked");
                return true;
            case R.id.delete:
                //LaunchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT ,rowPosition);
                Log.d("menu clicks", "we pressed menu clicked");
                return true;
        }

        return super.onContextItemSelected(item);
    }

    public  void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);


        LaunchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW ,position);

    }

    private  void LaunchNoteDetailActivity(MainActivity.FragmentToLaunch ftl ,int position){

        // grab the note information associated with whatever note item we clicked on
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent  = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.product_Namme_extra,note.getproductName());
        intent.putExtra(MainActivity.product_price_extra,note.getPrice());
        intent.putExtra(MainActivity.product_description_extra,note.getproductDescription());
        intent.putExtra(MainActivity.product_category_extra,note.getCategory());
        intent.putExtra(MainActivity.product_id_extra,note.getproductID());

        switch (ftl){
            case VIEW:
                intent.putExtra(MainActivity.note_fragment_to_launch_extra,MainActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(MainActivity.note_fragment_to_launch_extra,MainActivity.FragmentToLaunch.EDIT);
                break;


        }


        startActivity(intent);



    }



}
