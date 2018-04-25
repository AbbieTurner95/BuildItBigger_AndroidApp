package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointNormalJokesAsyncTask extends AsyncTask<com.udacity.gradle.builditbigger.MainActivityFragment, Void, String> {
        private static MyApi myApiService = null;
        private com.udacity.gradle.builditbigger.MainActivityFragment mainActivityFragment;

        @Override
        protected String doInBackground(com.udacity.gradle.builditbigger.MainActivityFragment... params) {

            mainActivityFragment = params[0];
            Context context = mainActivityFragment.getActivity();

            if(myApiService == null) {
                MyApi.Builder builder = new
                        MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest)

                                    throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });

                myApiService = builder.build();
            }

            try {
                return myApiService.tellNormalJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mainActivityFragment.loadedJoke = result;
            mainActivityFragment.launchDisplayJokeActivity();
        }
}