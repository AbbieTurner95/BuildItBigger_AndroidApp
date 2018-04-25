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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


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
        Button button1 = root.findViewById(R.id.n_joke_btn);

        progressBar =  root.findViewById(R.id.joke_progressbar);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getComputerJoke();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getNormalJoke();
            }
        });

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


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