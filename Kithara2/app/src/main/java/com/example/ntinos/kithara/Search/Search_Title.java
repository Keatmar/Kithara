package com.example.ntinos.kithara.Search;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ntinos.kithara.Database.Song_AsyncResponce;
import com.example.ntinos.kithara.Database.Song_WebDatabase;
import com.example.ntinos.kithara.R;
import com.example.ntinos.kithara.User.User_Login;

public class Search_Title extends Fragment {

    private Spinner greek_spinner;
    private Spinner english_spinner;
    private Spinner numeric_spinner;
    private EditText search;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_title, container, false);

        greek_spinner = (Spinner) view.findViewById(R.id.spinner_greek);
        english_spinner = (Spinner) view.findViewById(R.id.spinner_english);
        numeric_spinner = (Spinner) view.findViewById(R.id.spinner_numeric);
        search = (EditText) view.findViewById(R.id.search_title_edit);

        Button greek_button = (Button) view.findViewById(R.id.btn_greek);
        Button english_button = (Button) view.findViewById(R.id.btn_english);
        Button numeric_button = (Button) view.findViewById(R.id.btn_numeric);
        Button search_button = (Button) view.findViewById(R.id.btnSearch_title);

        greek_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi = connManager.getActiveNetworkInfo();

				/*	Connect to the internet	*/
                if (wifi != null) {
                    letterGreekSearch(v);
                } else {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                    alert_builder.setCancelable(false);
                    alert_builder.setMessage("You need Internet to get songs");
                    alert_builder.setTitle("Do you want to open wifi?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            letterGreekSearch(view);
                        }
                    });
                    alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    AlertDialog alertDialog = alert_builder.create();
                    alertDialog.show();

                }
            }
        });

        english_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi = connManager.getActiveNetworkInfo();

                if (wifi != null) {
                    letterEnglishSearch(v);
                } else {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                    alert_builder.setCancelable(false);
                    alert_builder.setMessage("You need Internet to get songs");
                    alert_builder.setTitle("Do you want to open wifi?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            letterEnglishSearch(view);
                        }
                    });
                    alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    AlertDialog alertDialog = alert_builder.create();
                    alertDialog.show();

                }
            }
        });

        numeric_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi = connManager.getActiveNetworkInfo();

                if (wifi != null) {
                    letterNumericSearch(v);
                } else {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                    alert_builder.setCancelable(false);
                    alert_builder.setMessage("You need Internet to get songs");
                    alert_builder.setTitle("Do you want to open wifi?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            letterNumericSearch(view);
                        }
                    });
                    alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    AlertDialog alertDialog = alert_builder.create();
                    alertDialog.show();

                }
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi = connManager.getActiveNetworkInfo();

                if (wifi != null) {
                    buttonSearch(v);
                } else {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                    alert_builder.setCancelable(false);
                    alert_builder.setMessage("You need Internet to get songs");
                    alert_builder.setTitle("Do you want to open wifi?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                            buttonSearch(view);
                        }
                    });
                    alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    AlertDialog alertDialog = alert_builder.create();
                    alertDialog.show();

                }
            }
        });
        return view;
    }


    private void letterGreekSearch(View view) {

        String letter = greek_spinner.getSelectedItem().toString();
        Song_WebDatabase song_webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
            @Override
            public void processFinish(String output) {

				/* Start Result title page 	*/
                Result_title result_title = new Result_title();

				/* Set Arguments	*/
                Bundle arg = new Bundle();
                arg.putString("output", output);
                result_title.setArguments(arg);

				/* Start Fragmanet	*/
                android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = registerManager.beginTransaction();
                transaction.replace(R.id.frame, result_title);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        song_webDatabase.execute("Search_title_by_letter", letter);

    }

    private void letterEnglishSearch(View view) {
        String letter = english_spinner.getSelectedItem().toString();
        Song_WebDatabase song_webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
            @Override
            public void processFinish(String output) {
                        /* Start Result title page 	*/
                Result_title result_title = new Result_title();
                        /* Set Arguments	*/
                Bundle arg = new Bundle();
                arg.putString("output", output);
                result_title.setArguments(arg);

						/* Start Fragmanet	*/
                android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = registerManager.beginTransaction();
                transaction.replace(R.id.frame, result_title);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        song_webDatabase.execute("Search_title_by_letter", letter);
    }

    private void letterNumericSearch(View view) {

        String letter = numeric_spinner.getSelectedItem().toString();
        Song_WebDatabase song_webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
            @Override
            public void processFinish(String output) {

						/* Start Result title page 	*/
                Result_title result_title = new Result_title();
						/* Set Arguments	*/
                Bundle arg = new Bundle();
                arg.putString("output", output);
                result_title.setArguments(arg);

						/* Start Fragmanet	*/
                android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = registerManager.beginTransaction();
                transaction.replace(R.id.frame, result_title);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        song_webDatabase.execute("Search_title_by_letter", letter);
    }

    private void buttonSearch(View view) {
        String query = search.getText().toString();
        Song_WebDatabase song_webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
            @Override
            public void processFinish(String output) {
                        /* Start Result title page 	*/
                Result_title result_title = new Result_title();
                        /* Set Arguments	*/
                Bundle arg = new Bundle();
                arg.putString("output", output);
                result_title.setArguments(arg);

						/* Start Fragmanet	*/
                android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = registerManager.beginTransaction();
                transaction.replace(R.id.frame, result_title);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        song_webDatabase.execute("search", query);
    }

}
