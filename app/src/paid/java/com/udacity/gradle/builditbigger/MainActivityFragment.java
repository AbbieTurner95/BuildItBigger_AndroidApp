package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.example.com.jokefactory.DisplayJokeActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public boolean testFlag = false;
    ProgressBar progressBar = null;
    public String loadedJoke = null;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = root.findViewById(R.id.c_joke_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getComputerJoke();
            }
        });

        Button button1 = root.findViewById(R.id.n_joke_btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getNormalJoke();
            }
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return root;
    }

    public void getComputerJoke(){
        new EndpointCompJokesAsyncTask().execute(this);
    }

    public void getNormalJoke(){
        new EndpointNormalJokesAsyncTask().execute(this);
    }

    public void launchDisplayJokeActivity(){
        if (!testFlag) {
            Context context = getActivity();

            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(context.getString(R.string.jokeEnvelope), loadedJoke);

            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }
}