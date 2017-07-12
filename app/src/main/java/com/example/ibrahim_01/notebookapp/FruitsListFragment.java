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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * A simple {@link Fragment} subclass.
 */
public class FruitsListFragment extends ListFragment {

    public String readJSONFeed(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream content = new BufferedInputStream(
                    urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }
    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }


        protected void onPostExecute(String result) {
            try {
                JSONArray jsonArray = new JSONArray(result);
                Log.i("JSON", "Number of surveys in feed: " +
                        jsonArray.length());
                //---print out the content of the json feed---
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Toast.makeText(getActivity()  ,
                            jsonObject.getString("vegetableDescription") +
                                    " - " + jsonObject.getString("vegetableName"),
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Note> notes;
    private  NoteAdapter noteAdapter;

    @Override
    public  void  onActivityCreated(Bundle savedInstanceState){

            super.onActivityCreated(savedInstanceState);

        new ReadJSONFeedTask().execute(
                "http://10.0.2.2:1320/groccery/Fruits");


//        NoteDBAdapter dbAdapter = new NoteDBAdapter(getActivity().getBaseContext());
//        dbAdapter.open();
//        notes = dbAdapter.getAllNotes();
//        dbAdapter.close();



        notes = new ArrayList<Note>();
        notes.add(new Note("SINDH MANGOES","the best mangos in town, i dont know why but it is just accept, and i will not be convincing you","44",Note.Category.MANGO));
        notes.add(new Note("APPLE ","the best apples in town, i dont know why but it is just accept, and i will not be convincing you","55",Note.Category.APPLE));
        notes.add(new Note("BANANA","the best bannans in town, i dont know why but it is just accept, and i will not be convincing you","35",Note.Category.BANANA));
        notes.add(new Note("POMAGARANTE","the best pomegrante  in town, i dont know why but it is just accept, and i will not be convincing you","74",Note.Category.POMAGARANTE));
        notes.add(new Note("AMRASS MANGOES","the best mangos in town 2, i dont know why but it is just accept, and i will not be convincing you","36",Note.Category.MANGO));
        notes.add(new Note("SINDH MANGOES, just checking bro i dont know why but it is just accept, and i will not be convincing you","the best mangos in town, i dont know why but it is just accept, and i will not be convincing you","35",Note.Category.MANGO));
        notes.add(new Note("APPLE 2 ","the best apples in town, i dont know why but it is just accept, and i will not be convincing you","35",Note.Category.APPLE));
        notes.add(new Note("BANANA 2","the best bannas in town, i dont know why but it is just accept, and i will not be convincing you","12",Note.Category.BANANA));
        notes.add(new Note("POMAGARANTE 2","the best pomegrante  in town, i dont know why but it is just accept, and i will not be convincing you","22",Note.Category.POMAGARANTE));
        notes.add(new Note("AMRASS MANGOES 2 ","the best mangos in town 2, i dont know why but it is just accept, and i will not be convincing you","66",Note.Category.MANGO));



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
