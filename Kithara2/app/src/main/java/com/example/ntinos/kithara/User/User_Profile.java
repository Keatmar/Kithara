package com.example.ntinos.kithara.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ntinos.kithara.Application;
import com.example.ntinos.kithara.Database.Song_AsyncResponce;
import com.example.ntinos.kithara.Database.Song_WebDatabase;
import com.example.ntinos.kithara.MainActivity;
import com.example.ntinos.kithara.R;
import com.example.ntinos.kithara.Search.Profile_Song;
import com.example.ntinos.kithara.Search.Result_title;

import java.util.Arrays;


public class User_Profile extends Fragment {


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	final View view = inflater.inflate(R.layout.user_profile, container, false);

		/*	View methods */
	TextView username = (TextView) view.findViewById(R.id.profile_username);
	TextView password = (TextView) view.findViewById(R.id.profile_password);
	TextView email = (TextView) view.findViewById(R.id.profile_email);
	ImageView user_image = (ImageView) view.findViewById(R.id.image_user);
	Button logout = (Button) view.findViewById(R.id.profile_logout);
	Button edit = (Button) view.findViewById(R.id.btnEdit);


	//user_image.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_beer));			//set image

		/*	Get personal details for User	*/
	String take_username = ((Application) getActivity().getApplication()).getUsername();                        //get Username
	String take_password = ((Application) getActivity().getApplication()).getPassword();                        //get Password
	String take_email = ((Application) getActivity().getApplication()).getEmail();                            //get E-mail

		/*	View personal detail for User	*/
	username.setText(take_username);
	password.setText(take_password);
	email.setText(take_email);


		/*		My Songs	*/
	Song_WebDatabase song_webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
		@Override
		public void processFinish(String output) {

			String[][] songs = GetTitle(output);
			SongsResults(view, songs);

		}
	});
	song_webDatabase.execute("Search_title_by_email", take_email);

		/*		Buttons	*/
	edit.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), "User Edit Selected", Toast.LENGTH_SHORT).show();
			User_Edit user = new User_Edit();
			FragmentTransaction userTransaction = getActivity().getSupportFragmentManager().beginTransaction();
			userTransaction.replace(R.id.frame, user);
			userTransaction.commit();

		}
	});

		/*	Logout button	*/
	logout.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Logout(v);
		}
	});

	return view;
}


private String[][] GetTitle(String output) {
	String[] sums_songs = output.split("@");
	Arrays.sort(sums_songs);
	String[][] title = new String[sums_songs.length][];
	for (int i = 0; i < sums_songs.length; i++) {
		title[i] = sums_songs[i].split("<n>");
	}

	return title;
}

/*	Appear Songs Results	*/
private void SongsResults(View view, final String[][] sums_songs) {

	RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.result_title);
	TextView[] songsViews = new TextView[sums_songs.length];
	RelativeLayout.LayoutParams[] songsViewsParams = new RelativeLayout.LayoutParams[sums_songs.length];

	songsViews[0] = new TextView(view.getContext());
	songsViews[0].setId(Integer.valueOf("100"));
	songsViews[0].setText(sums_songs[0][0]);
	songsViews[0].setTextColor(ContextCompat.getColor(view.getContext(), R.color.purple));
	songsViews[0].setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.songs_title));

	songsViews[0].setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			MoveToProfileSong(v, sums_songs[0]);
		}
	});

	songsViewsParams[0] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	songsViews[0].setLayoutParams(songsViewsParams[0]);
	layout.addView(songsViews[0]);

	for (int i = 1; i < sums_songs.length; i++) {

		songsViews[i] = new TextView(view.getContext());
		songsViews[i].setId(Integer.valueOf("10" + i));
		songsViews[i].setText(sums_songs[i][0]);
		songsViews[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.songs_title));
		songsViews[i].setTextColor(ContextCompat.getColor(view.getContext(), R.color.purple));

		final int finalI = i;
		songsViews[i].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MoveToProfileSong(v, sums_songs[finalI]);
			}
		});

		songsViewsParams[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		songsViewsParams[i].addRule(RelativeLayout.BELOW, songsViews[i - 1].getId());
		songsViewsParams[i].setMargins(0, 20, 0, 0);
		songsViews[i].setLayoutParams(songsViewsParams[i]);
		layout.addView(songsViews[i]);
	}

}
	private void MoveToProfileSong(View view, String[] title) {

		Profile_Song profile_song = new Profile_Song();

		/* 	Set Arguments	*/
		Bundle arg = new Bundle();
		arg.putStringArray("song", title);
		profile_song.setArguments(arg);

		/* Start Fragmanet	*/
		android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = registerManager.beginTransaction();
		transaction.replace(R.id.frame, profile_song);
		transaction.addToBackStack(null);
		transaction.commit();
	}
private void Logout(View view) {
	((Application) getActivity().getApplication()).setUser(null, null, null);
	((Application) getActivity().getApplication()).setIsLogin(false);
	Toast.makeText(getActivity(), "Αρχική Σελίδα ", Toast.LENGTH_SHORT).show();
	Intent intent = new Intent(getActivity(), MainActivity.class);
	getActivity().startActivity(intent);
}
}
