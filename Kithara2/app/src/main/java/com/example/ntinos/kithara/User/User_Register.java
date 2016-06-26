package com.example.ntinos.kithara.User;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ntinos.kithara.Application;
import com.example.ntinos.kithara.Database.User_AsyncResponce;
import com.example.ntinos.kithara.Database.User_WebDatabase;
import com.example.ntinos.kithara.MainActivity;
import com.example.ntinos.kithara.R;

public class User_Register extends Fragment {

    public EditText username_edit, email_edit, password_edit, password_repeat_edit;
    public Button accept, cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_register, container, false);

        username_edit = (EditText) view.findViewById(R.id.Username_register_edit);
        email_edit = (EditText) view.findViewById(R.id.Email_register_edit);
        password_edit = (EditText) view.findViewById(R.id.Password_register_edit);
        password_repeat_edit = (EditText) view.findViewById(R.id.Password_register_edit_repeat);
        accept = (Button) view.findViewById(R.id.Accept_register);
        cancel = (Button) view.findViewById(R.id.Cancel_register);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_Accept(v, container);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_Accept(v, container);
            }
        });

        return view;
    }


    public void onClick_Accept(View view, final ViewGroup container) {
        switch (view.getId()) {

			/*	Add New User	*/
            case R.id.Accept_register:
                ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo wifi = connManager.getActiveNetworkInfo();
                if (wifi != null) {
                    final String username = username_edit.getText().toString();
                    final String email = email_edit.getText().toString();
                    final String password = password_edit.getText().toString();

				    /* Check if details agree with roles */
                    if (username.length() < 4) {

                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Small username");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        break;
                    }
                    if (email.length() < 4) {

                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Small email");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        break;
                    }
                    if (!smallPassword()) {
                        if (matchPasswords()) {
                                /*	Import User into my Web Server */
                            User_WebDatabase user_Web_database = new User_WebDatabase(getContext(), new User_AsyncResponce() {
                                @Override
                                public void processFinish(String output) {
                                    int flag = 0;

									/*	Check if someone use this username	*/
                                    if (output.equals("Someone use this username")) {
                                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                                        alert_builder.setCancelable(true);
                                        alert_builder.setMessage("Someone use this username");
                                        AlertDialog alertDialog = alert_builder.create();
                                        alertDialog.show();
                                        flag = -1;
                                    }

									/*	Check if someone use this email	*/
                                    if (output.equals("Someone use this email")) {
                                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                                        alert_builder.setCancelable(true);
                                        alert_builder.setMessage("Someone use this email");
                                        AlertDialog alertDialog = alert_builder.create();
                                        alertDialog.show();
                                        flag = -2;
                                    }
                                    /*	Import User into Local Server */
                                    if (flag == 0) {
                                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                                        alertDialog.setCancelable(true);
                                        alertDialog.setTitle("Successful Register");
                                        alertDialog.setMessage("Welcome \n" + username);
                                        AlertDialog alertDialog1 = alertDialog.create();
                                        alertDialog1.show();

										/*	 Go To User Profile Screen when importing my user	*/
                                        Toast.makeText(getActivity(), "Αρχική Σελίδα", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        getActivity().startActivity(intent);
                                    }

                                }
                            });
                            user_Web_database.execute("register", username, email, password);
                            break;
                        } else {
                            AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                            alert_builder.setCancelable(true);
                            alert_builder.setMessage("Passwords is not equal");
                            AlertDialog alertDialog = alert_builder.create();
                            alertDialog.show();
                            break;
                        }
                    }else {
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Password is small");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        break;
                    }
                } else {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                    alert_builder.setCancelable(true);
                    alert_builder.setMessage("You need Internet to get songs");
                    alert_builder.setTitle("Do you want to open wifi?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
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
            case R.id.Cancel_register:            //Cancel register
                Toast.makeText(getActivity(), "User_Login Selected", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }

    }


    public boolean smallPassword() {
        String pass = password_edit.getText().toString();
        return pass.length() < 4;
    }

    public boolean matchPasswords() {
        String pass1 = password_edit.getText().toString();
        String pass2 = password_repeat_edit.getText().toString();
        return pass1.equals(pass2);

    }
}
