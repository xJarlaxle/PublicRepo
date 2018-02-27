package com.example.jarlaxle.movietrailers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class AddNewTrailer extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText yt_id, name, description, id;
    Button actuallyAddTrailerBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yt_id = (EditText) findViewById(R.id.yt_id);
        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        actuallyAddTrailerBtn = (Button) findViewById(R.id.actuallyAddTrailerBtn);
        myDb = new DatabaseHelper(this);

        //The add trailer button that actually does the work
        actuallyAddTrailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = myDb.insertData(yt_id.getText().toString(),
                        name.getText().toString(),
                        description.getText().toString());
                if(success){
                    Toast.makeText(AddNewTrailer.this,"Trailer Added", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddNewTrailer.this,"Error: Not Added", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(AddNewTrailer.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
