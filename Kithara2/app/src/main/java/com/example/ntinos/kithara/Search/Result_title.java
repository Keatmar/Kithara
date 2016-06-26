package com.example.ntinos.kithara.Search;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ntinos.kithara.R;

import java.util.Arrays;


public class Result_title extends Fragment {
String output;


@Nullable
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	final View view = inflater.inflate(R.layout.result_title, container, false);

		/* Get Arguments */
	Bundle arguments = this.getArguments();
	if (arguments != null) {

		output = getArguments().getString("output");
	}

	if (output != null && !output.equals("NO RESULTS")) {

		output = output.replace("?:", "\n");
		String[][] songs = GetTitle(output);
		SongsResults(view, songs);

	} else if (output != null && output.equals("NO RESULTS")) {
		RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.result_title);
		TextView textView = new TextView(getContext());
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		textView.setText("No Songs Founded \n Try another letter please");
		textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.no_song));
		textView.setLayoutParams(params);
		layout.addView(textView);
	} else {
		AlertDialog.Builder alert_builder = new AlertDialog.Builder(view.getContext());
		alert_builder.setCancelable(true);
		alert_builder.setMessage("Check your internet");
		alert_builder.setTitle("Failed to connect with server");
		AlertDialog alertDialog = alert_builder.create();
		alertDialog.show();
		startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
		getFragmentManager().popBackStack();
	}

	Button back = (Button) view.findViewById(R.id.button_back);
	back.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			getFragmentManager().popBackStack();

		}
	});

	return view;
}

/* 	Return titles	*/
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
}
