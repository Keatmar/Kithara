package com.example.ntinos.kithara;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ntinos.kithara.Import_Song.Import_Song_Basic;
import com.example.ntinos.kithara.Search.Search_Title;
import com.example.ntinos.kithara.User.User_Login;
import com.example.ntinos.kithara.User.User_Profile;

public class MainActivity extends AppCompatActivity {

//Defining Variables
private DrawerLayout drawerLayout;
private AlertDialog.Builder alert_builder;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	//Set a Toolbar to replace the ActionBar
	Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
	setSupportActionBar(toolbar);


	//Initialize Navigation View
	NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

	//Initialize Navigation Header
	View navigation_Header = LayoutInflater.from(this).inflate(R.layout.navigation_header, null);
	navigationView.addHeaderView(navigation_Header);

	ImageView nav_image = (ImageView) navigation_Header.findViewById(R.id.nav_profile_image);
	TextView nav_username = (TextView) navigation_Header.findViewById(R.id.nav_username);
	TextView nav_email = (TextView) navigation_Header.findViewById(R.id.nav_email);

	nav_image.setImageResource(R.mipmap.ic_user_no_icon);                                      // Set Image
	String nav_username_text = ((Application) getApplication()).getUsername();                   // Set Username
	String nav_email_text = ((Application) getApplication()).getEmail();                         // Set E-mail

	if (nav_username_text == null || nav_email_text == null) {
		nav_username.setText(R.string.navigation_username);
		nav_email.setText(R.string.navigation_email);
	} else {
		nav_username.setText(nav_username_text);
		nav_email.setText(nav_email_text);
	}
	alert_builder = new AlertDialog.Builder(this);
	//Initialize Navigation Menu
	navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(MenuItem menuItem) {
			if (menuItem.isChecked())
				menuItem.setChecked(false);
			else
				menuItem.setChecked(true);

			drawerLayout.closeDrawers();


			switch (menuItem.getItemId()) {

				case R.id.import_song_nav:
					if (((Application) getApplication()).returnLogin()) {
						Toast.makeText(getApplicationContext(), "Import Song Selected", Toast.LENGTH_SHORT).show();
						Import_Song_Basic import_songBasic = new Import_Song_Basic();
						FragmentTransaction userTransaction = getSupportFragmentManager().beginTransaction();
						userTransaction.replace(R.id.frame, import_songBasic);
						userTransaction.commit();
						return true;
					} else {
						alert_builder.setCancelable(true);
						alert_builder.setMessage("You need login to access in this page");
						alert_builder.setTitle("Do you want to login?");
						alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								User_Login user_login = new User_Login();
								FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
								transaction.replace(R.id.frame, user_login);
								transaction.commit();
							}
						});
						alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(MainActivity.this, "Select other screen", Toast.LENGTH_LONG).show();

							}
						});

						AlertDialog alertDialog = alert_builder.create();
						alertDialog.show();

						//alert_builder.setNegativeButton("move to login", DialogInterface.OnClickListener )
						return false;
					}

				case R.id.title_nav:
					Toast.makeText(getApplicationContext(), "Search Title Selected", Toast.LENGTH_SHORT).show();
					Search_Title title = new Search_Title();
					FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
					transaction.replace(R.id.frame, title);
					transaction.commit();
					return true;
				default:
					Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
					return true;
			}
		}
	});

	drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

	ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
		@Override
		public void onDrawerClosed(View drawerView) {
			// Code here will be triggered once the menu_navigation closes as we dont want anything to happen so we leave this blank
			super.onDrawerClosed(drawerView);
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			// Code here will be triggered once the menu_navigation open as we dont want anything to happen so we leave this blank

			super.onDrawerOpened(drawerView);
		}
	};

	//Setting the actionbarToggle to menu_navigation layout
	drawerLayout.setDrawerListener(actionBarDrawerToggle);

	//calling sync state is necessay or else your hamburger icon wont show up
	actionBarDrawerToggle.syncState();

		/* 	Main Fragment	 */
	Main main = new Main();
	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.frame, main);
	transaction.commit();
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}


@Override
public boolean onOptionsItemSelected(MenuItem item) {

	int id = item.getItemId();

	switch (id) {

		case R.id.acc_user:

			if (!((Application) this.getApplication()).returnLogin()) {                                  // If user not Login
				Toast.makeText(getApplicationContext(), "User Login Selected", Toast.LENGTH_SHORT).show();
				User_Login user = new User_Login();
				FragmentTransaction userTransaction = getSupportFragmentManager().beginTransaction();
				userTransaction.replace(R.id.frame, user);
				userTransaction.commit();
				return true;
			} else {                                                                                          //if user user_login
				Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_LONG).show();
				User_Profile user_profile = new User_Profile();                                // go to profile user screen
				FragmentManager user_registerManager = getSupportFragmentManager();
				FragmentTransaction registerTransaction = user_registerManager.beginTransaction();
				registerTransaction.replace(R.id.frame, user_profile, "User_Profile" + "Register");
				registerTransaction.addToBackStack(null);
				registerTransaction.commit();
				return true;
			}
		case R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			this.startActivity(intent);
			return true;
	}


	return super.onOptionsItemSelected(item);
}
}
