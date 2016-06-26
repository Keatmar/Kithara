package com.example.ntinos.kithara.User;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ntinos.kithara.Application;
import com.example.ntinos.kithara.Database.User_AsyncResponce;
import com.example.ntinos.kithara.Database.User_WebDatabase;
import com.example.ntinos.kithara.R;


public class User_Edit extends Fragment {
    private EditText username;
    private EditText password;
    private EditText password_rep;
    private EditText currentPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_edit, container, false);


        Button btnAccept = (Button) view.findViewById(R.id.btnAccept);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        username = (EditText) view.findViewById(R.id.Username_register_edit);
        password = (EditText) view.findViewById(R.id.Password_edit);
        password_rep = (EditText) view.findViewById(R.id.Password_edit_repeat);
        currentPassword = (EditText) view.findViewById(R.id.currentPassword_edit);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfile(v);

            }
        });
        return view;
    }


    public void changeProfile(View view) {
        String str_username = username.getText().toString();
        String str_currentPassword = currentPassword.getText().toString();
        String str_password = password.getText().toString();
        String str_password_rep = password_rep.getText().toString();
        String str_email = ((Application) getActivity().getApplication()).getEmail();

	    /*	Change to Web DB */
        if (str_currentPassword.equals(((Application) getActivity().getApplication()).getPassword())) {
            if (str_password.equals(str_password_rep)) {



	    /* Change season */
                if (str_username.equals("")) {
                    if (str_password.length() > 4) {
                        ((Application) getActivity().getApplication()).setPassword(str_password);
                    } else {
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Password should at least 4 character");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        return;
                    }
                } else if (str_password.equals("")) {
                    if (str_username.length() > 4) {
                        ((Application) getActivity().getApplication()).setPassword(str_username);
                    } else {
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Username should at least 4 character");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        return;
                    }
                } else {
                    if (str_password.length() > 4) {
                        ((Application) getActivity().getApplication()).setPassword(str_password);
                    } else {
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Password should at least 4 character");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        return;
                    }
                    if (str_username.length() > 4) {
                        ((Application) getActivity().getApplication()).setPassword(str_username);
                    } else {
                        AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                        alert_builder.setCancelable(true);
                        alert_builder.setMessage("Username should at least 4 character");
                        AlertDialog alertDialog = alert_builder.create();
                        alertDialog.show();
                        return;
                    }
                }

                User_WebDatabase user_Web_database = new User_WebDatabase(getContext(), new User_AsyncResponce() {
                    @Override
                    public void processFinish(String output) {

                    }
                });

                user_Web_database.execute("update", str_username, str_email, str_password);
                Toast.makeText(view.getContext(), "User Profile ", Toast.LENGTH_SHORT).show();

                User_Profile user = new User_Profile();
                FragmentTransaction userTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                userTransaction.replace(R.id.frame, user);
                userTransaction.commit();

            } else {
                AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
                alert_builder.setCancelable(true);
                alert_builder.setMessage("Not Equal Password ");
                AlertDialog alertDialog = alert_builder.create();
                alertDialog.show();

            }
        } else {
            AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
            alert_builder.setCancelable(true);
            alert_builder.setMessage("Current Password is false\n Please enter correct password");
            AlertDialog alertDialog = alert_builder.create();
            alertDialog.show();
        }

    }
}