package com.example.jarlaxle.movietrailers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class TrailerView extends AppCompatActivity{

    private static final String TAG = "TrailerView";
    private Integer id;
    private String yt_id;
    private String name;
    private String description;
    private Float rating;
    private static final String API_KEY = "AIzaSyAnrXU3WaavDT6cLnipFY2eYWr0f8bzrxk";
    DatabaseHelper myDb;
    TextView txtMovieName;
    RatingBar rtngBrMovieTrailerRating;
    TextView txtMovieDescription;
    Button backBtn;
    Button deleteTrailerBtn;

    //Handles the different buttons on the screen to send off to the appropriate CRUD function in the
    //DB helper class
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_view);
        txtMovieName = (TextView) findViewById(R.id.txtMovieName);
        rtngBrMovieTrailerRating = (RatingBar) findViewById(R.id.rtngBrMovieTrailerRating);
        txtMovieDescription = (TextView) findViewById(R.id.txtMovieDescription);
        backBtn = (Button)  findViewById(R.id.backBtn);
        deleteTrailerBtn = (Button) findViewById(R.id.deleteTrailerBtn);
        myDb = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();
        id = receivedIntent.getIntExtra("id", -1);
        yt_id = receivedIntent.getStringExtra("yt_id");
        name = receivedIntent.getStringExtra("name");
        description = receivedIntent.getStringExtra("description");
        rating = receivedIntent.getFloatExtra("rating", 0.0f);

        txtMovieName.setText(name);
        txtMovieDescription.setText(description);
        rtngBrMovieTrailerRating.setRating(rating);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Done", "Done");
                setResult(RESULT_OK, intent);
                //end the current activity so the list is clickable again
                //intent navigation won't let the user select from the list anymore
                finish();
            }
        });

        deleteTrailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = myDb.deleteData(id);
                if(result > 0){
                    Log.d(TAG, "Successfully deleted trailer for " + name);
                    Toast.makeText(TrailerView.this, name + " was successfully deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "Error: " + name + " could not be deleted");
                    Toast.makeText(TrailerView.this, "Error: " + name + " could not be deleted." , Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent.putExtra("Done", "Done");
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        rtngBrMovieTrailerRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            if(myDb.updateRating(id.toString(), yt_id,name, description, rating)){
                Toast.makeText(TrailerView.this, "Rating updated!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(TrailerView.this, "Unable to update rating", Toast.LENGTH_SHORT).show();
            }
            }
        });

        //http://help.dimsemenov.com/kb/wordpress-royalslider-tutorials/wp-how-to-get-youtube-api-key
        //https://console.developers.google.com/apis/credentials?project=trailersapp-188722
        YouTubePlayerSupportFragment myYoutubeFragment = (YouTubePlayerSupportFragment)getSupportFragmentManager().findFragmentById(R.id.my_youtube_fragment);
        myYoutubeFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.loadVideo(yt_id);
                    player.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });



    }

}
