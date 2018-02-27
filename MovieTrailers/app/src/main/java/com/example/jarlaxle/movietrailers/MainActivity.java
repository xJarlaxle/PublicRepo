package com.example.jarlaxle.movietrailers;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TrailerListView";
    DatabaseHelper myDb;
    private ListView listView;
    Button addTrailerNavBtn;

    //Oncreate method, instantiates variables and sets up the onclick listener
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.listView);
        myDb = new DatabaseHelper(this);
        addTrailerNavBtn = (Button) findViewById(R.id.addTrailerNavBtn);
        //Onclick listener that starts a new activity and sends in an intent
        addTrailerNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewTrailer.class);
                //request code is asked for so the app can assure everything is running fine before
                //it populates the list view
                startActivityForResult(intent, 1);
            }
        });

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");

        //get data to append to the list
        Cursor data = myDb.getData();
        ArrayList<String> listData = new ArrayList<>();
        ArrayList<String> yt_idList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> descriptionList = new ArrayList<>();

        while(data.moveToNext()){
            yt_idList.add(data.getString(1));
            nameList.add(data.getString(2));
            descriptionList.add(data.getString(3));


        }//end While

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = adapterView.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You clicked on " + name);
                Cursor data = myDb.getItemID(name);
                int itemID = -1;
                String yt_id = "";
                String description = "";
                float rating = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                    yt_id = data.getString(1);
                    description = data.getString(3);
                    rating = data.getFloat(4);

                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: You clicked on " + name);
                    Intent intent = new Intent(MainActivity.this, TrailerView.class);
                    intent.putExtra("id", itemID);
                    intent.putExtra("yt_id", yt_id);
                    intent.putExtra("name", name);
                    intent.putExtra("description", description);
                    intent.putExtra("rating", rating);
                    startActivityForResult(intent, 1);
                }else{
                    Toast.makeText(MainActivity.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    //Receives the result code from the request and makes sure the app is ready to populate the list
    //view on the new screen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                populateListView();
            }
        }
    }

}
