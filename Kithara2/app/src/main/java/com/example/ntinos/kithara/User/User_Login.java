package com.example.ntinos.kithara.User;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class User_Login extends Fragment {


    Button btnLogin, btnCancel, btnRegister;
    EditText txtusername, txtpassword;
    private String username;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_login, container, false);

        txtusername = (EditText) view.findViewById(R.id.txtUsername);
        txtpassword = (EditText) view.findViewById(R.id.txtPassword);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked_Login();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked_Cancel();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked_Register();
            }
        });

        return view;
    }


    public void buttonClicked_Login() {

		/*	Take User Details	*/
        username = txtusername.getText().toString();
        password = txtpassword.getText().toString();
        loginWebDB();
    }


    public void loginWebDB() {

        ConnectivityManager connManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connManager.getActiveNetworkInfo();
        if (wifi != null) {
                /*	Login with username to Local Database	*/
            User_WebDatabase user_webDatabase = (User_WebDatabase) new User_WebDatabase(getContext(), new User_AsyncResponce() {
                @Override
                public void processFinish(String output) {
                    if (!output.equals("Login_Failed")) {
                            /*	Hold Season	*/
                        ((Application) getActivity().getApplication()).setUser(username, password, output);
                        ((Application) getActivity().getApplication()).setIsLogin(true);

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Successful Login");
                        alertDialog.setMessage("Welcome \n" + username);
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();

							/*	 Move to Home Page after Login	*/
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                    } else {

							/* Invalid username or password	*/
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("Invalid username or password\n");
                        alertDialog.setCancelable(true);
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                    }

                }

            }).execute("login_username", username, password);

        } else {
            AlertDialog.Builder alert_builder = new AlertDialog.Builder(getContext());
            alert_builder.setCancelable(false);
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
    }

    public void buttonClicked_Register() {

        Toast.makeText(getActivity(), "Register Screen", Toast.LENGTH_LONG).show();

		/* Move to Register Screen	*/
        User_Register user_register = new User_Register();
        android.support.v4.app.FragmentManager user_registerManager = getActivity().getSupportFragmentManager();
        FragmentTransaction user_registerTransaction = user_registerManager.beginTransaction();
        user_registerTransaction.replace(R.id.frame, user_register);
        user_registerTransaction.addToBackStack(null);
        user_registerTransaction.commit();
    }


    public void buttonClicked_Cancel() {

		/* Return to Home Screen	*/
        Toast.makeText(getActivity(), "Αρχική Σελίδα ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
    }

}