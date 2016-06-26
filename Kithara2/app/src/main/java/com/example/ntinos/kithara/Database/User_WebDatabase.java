package com.example.ntinos.kithara.Database;

import android.app.AlertDialog;
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


public class User_WebDatabase extends AsyncTask<String, Void, String> {


Context context;
AlertDialog alertDialog;
Boolean Login = false;
String email_ = "";
String username_ = "";
public User_AsyncResponce delegate = null;

public User_WebDatabase(Context context, User_AsyncResponce delegate) {
	this.context = context;
	this.delegate = delegate;
}


@Override
protected void onPostExecute(String result) {

	delegate.processFinish(result);
}

@Override
protected void onPreExecute() {
	alertDialog = new AlertDialog.Builder(context).create();
	alertDialog.setTitle("Connect to Server ");
}


@Override
protected String doInBackground(String... params) {

	String method = params[0];

		/* Register */
	if (method.equals("register")) {

		String username = params[1];
		String email = params[2];
		String password = params[3];
		String output = Register(username, email, password);
		return output;

		/*	Login Username	*/
	} else if (method.equals("login_username")) {

		String username = params[1];
		String password = params[2];

		return Login(username, password);


	} else if (method.equals("update")) {
		String username = params[1];
		String email = params[2];
		String password = params[3];

		return Update(username, email, password);
	}
	return null;
}

public String Register(String username, String email, String password) {
	try {

				/* Connect to Database	*/
		URL url = new URL("http://83.212.96.188/kithara/register_user.php");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

				/* Follow Post Method	*/
		con.setRequestMethod("POST");
		con.setDoOutput(true);

				/*	Add New User	*/
		OutputStream OS = con.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
		String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
				URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
				URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
		bufferedWriter.write(data);
		bufferedWriter.flush();
		bufferedWriter.close();
		OS.close();

		InputStream inputStream = con.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
		String responce = "";
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			responce += line;
		}
		bufferedReader.close();
		inputStream.close();
		con.disconnect();

		return responce;

	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

public String Login(String username, String password) {
	try {

				/* Connect to Database	*/
		URL url = new URL("http://83.212.96.188/kithara/login_user.php");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

			/* 	Follow Get Method	*/
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setDoInput(true);

			/*	Take user	*/
		OutputStream outputStream = con.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

		String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
				URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
		bufferedWriter.write(data);
		bufferedWriter.flush();
		bufferedWriter.close();
		outputStream.close();
			/* Read from Web Database	*/
		InputStream inputStream = con.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
		String responce = "";
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			responce += line;
		}
		bufferedReader.close();
		inputStream.close();
		con.disconnect();
		return responce;

	} catch (IOException e) {
		e.printStackTrace();
	}

	return null;
}

public String Update(String username, String email, String password) {
	try {

				/* Connect to Database	*/
		URL url = new URL("http://83.212.96.188/kithara/update_user.php");
		Log.d("username",username);
		Log.d("password",password);
		Log.d("email",email);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

				/* Follow Post Method	*/
		con.setRequestMethod("POST");
		con.setDoOutput(true);

				/*	Add New User	*/
		OutputStream OS = con.getOutputStream();
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
		String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
				URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
				URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
		bufferedWriter.write(data);
		bufferedWriter.flush();
		bufferedWriter.close();
		OS.close();

		InputStream inputStream = con.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
		String responce = "";
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			responce += line;
		}
		bufferedReader.close();
		inputStream.close();
		con.disconnect();

		return responce;

	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
}

public boolean isLogin() {
	return Login;
}

public String GetEmail() {
	return email_;
}


public String GetUsername() {
	return username_;
}

}
