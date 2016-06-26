package com.example.ntinos.kithara.Database;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Song_WebDatabase extends AsyncTask<String, Void, String> {

    private Context context;
    public Song_AsyncResponce delegate = null;

    public Song_WebDatabase(Context context, Song_AsyncResponce delegate) {
        this.context = context;
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];

		/* Register */
        switch (method) {
            case "import_song":
                Song song = new Song();
                song.setEmail(params[1]);
                song.setTitle(params[2]);
                song.setSinger(params[3]);
                song.setMusic(params[4]);
                song.setVersification(params[5]);
                song.setPreamble(params[6]);
                song.setLyrics(params[7]);
                song.setSynchordies(params[8]);
                song.setEmail(params[9]);


                return importSong(song);
            case "search":
                return search(params[1]);
            case "Search_title_by_letter":

                return searchTitleLetter(params[1]);
            case "Search_title_by_email":
                return searchTitleEmail(params[1]);
        }
        return null;
    }

    public String importSong(Song song) {
        try {
            /* Connect to Database	*/
            URL url = new URL("http://83.212.96.188/kithara/song_import.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            try {

				/* Follow Post Method	*/
                con.setRequestMethod("POST");
                con.setDoOutput(true);

				/*	Add New Song	*/
                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(song.getEmail(), "UTF-8") + "&" +
                        URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(song.getTitle(), "UTF-8") + "&" +
                        URLEncoder.encode("singer", "UTF-8") + "=" + URLEncoder.encode(song.getSinger(), "UTF-8") + "&" +
                        URLEncoder.encode("music", "UTF-8") + "=" + URLEncoder.encode(song.getMusic(), "UTF-8") + "&" +
                        URLEncoder.encode("versification", "UTF-8") + "=" + URLEncoder.encode(song.getVersification(), "UTF-8") + "&" +
                        URLEncoder.encode("preamble", "UTF-8") + "=" + URLEncoder.encode(song.getPreamble(), "UTF-8") + "&" +
                        URLEncoder.encode("lyrics", "UTF-8") + "=" + URLEncoder.encode(song.getLyrics(), "UTF-8") + "&" +
                        URLEncoder.encode("synchordies", "UTF-8") + "=" + URLEncoder.encode(song.getSynchordies(), "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = con.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String responce = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    responce += line;
                }
                bufferedReader.close();
                inputStream.close();

                return responce;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String searchTitleLetter(String letter) {

        try {

			/* Connect to Database	*/
            URL url = new URL("http://83.212.96.188/kithara/song_title_search.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            try {

				/* Follow Post Method	*/
                con.setRequestMethod("POST");
                con.setDoOutput(true);

				/*	Add New Song	*/
                OutputStream outputStream = con.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("letter", "UTF-8") + "=" + URLEncoder.encode(letter.toUpperCase(), "UTF-8") + "&" +
                        URLEncoder.encode("letter_lower", "UTF-8") + "=" + URLEncoder.encode(letter.toLowerCase(), "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = con.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String responce = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    responce += line;
                }
                bufferedReader.close();
                inputStream.close();

                return responce;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String searchTitleEmail(String email) {
        try {

			/* Connect to Database	*/
            URL url = new URL("http://83.212.96.188/kithara/song_title_search_by_email.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            try {

				/* Follow Post Method	*/
                con.setRequestMethod("POST");
                con.setDoOutput(true);

				/*	Add New Song	*/
                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = con.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String responce = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    responce += line;
                }
                bufferedReader.close();
                inputStream.close();


                return responce;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String search(String query){
        try {

			/* Connect to Database	*/
            URL url = new URL("http://83.212.96.188/kithara/song_search.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();


            try {

				/* Follow Post Method	*/
                con.setRequestMethod("POST");
                con.setDoOutput(true);

				/*	Add New Song	*/
                OutputStream outputStream = con.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = con.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String responce = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    responce += line;
                }
                bufferedReader.close();
                inputStream.close();
                Log.d("WE", responce);
                return responce;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
