package com.example.ntinos.kithara.Import_Song;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ntinos.kithara.Application;
import com.example.ntinos.kithara.Database.Song_AsyncResponce;
import com.example.ntinos.kithara.Database.Song;
import com.example.ntinos.kithara.Database.Song_WebDatabase;
import com.example.ntinos.kithara.MainActivity;
import com.example.ntinos.kithara.R;

import java.util.ArrayList;

public class Import_Song_Final extends Fragment{

	private String lyrics;
	private String song__preamble;
	private ArrayList<String> song_details;
	private String lyrics_synchordies;
	private Song song;
	private String email;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.import_screen_final, container, false);

		/*	Get Arguments	*/
		Bundle arguments = this.getArguments();
		if (arguments != null) {
			lyrics = getArguments().getString("lyrics");
			song__preamble = getArguments().getString("song_preamble");
			song_details = new ArrayList<>(3);
			song_details = getArguments().getStringArrayList("song_details");
			lyrics_synchordies = getArguments().getString("lyrics_synchordies");
		}

		/*		Set title	*/
		final TextView title = (TextView)view.findViewById(R.id.title);
		title.setText(song_details.get(0));

		/* 		Set songs details	*/
		TextView singer = (TextView)view.findViewById(R.id.singer_name);
		singer.setText(song_details.get(1));

		TextView music = (TextView)view.findViewById(R.id.music_name);
		music.setText(song_details.get(2));

		TextView versification = (TextView)view.findViewById(R.id.versification_name);
		versification.setText(song_details.get(3));


		/*	Split lyrics and lyrics synchordies for end line */
		String[] raw_lyrics = lyrics.split("\\r?\\n");
		String[] raw_synchordies = lyrics_synchordies.split("\\r?\\n");

		/*	Initialize Layout */
		RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.song_view);
		RelativeLayout.LayoutParams[] song_view_synch_param = new RelativeLayout.LayoutParams[raw_synchordies.length];
		RelativeLayout.LayoutParams[] song_view_param = new RelativeLayout.LayoutParams[raw_lyrics.length];

		/*	Set preamble synchordies */
		TextView preamble = (TextView)view.findViewById(R.id.Preamble);
		preamble.setText(song__preamble + "\n");

		/*	Initialize TextView	*/
		final TextView [] song_view = new TextView[raw_lyrics.length];
		TextView [] song_view_synch = new TextView[raw_synchordies.length];

		/*	Initialize lyrics */
		TextView lyrics_view = (TextView)view.findViewById(R.id.lyrics);

		/*	Set text lyrics and lyrics synchordies	*/
		String song_view_synch_id;
		String song_view_id;

		for ( int i=0; i<raw_lyrics.length; i++){

			/*	if lyrics have synchordies */
			if (i<raw_synchordies.length) {

				/* 	Create synch view	*/
				song_view_synch[i] = new TextView(this.getContext());
				song_view_synch_id = "10"+String.valueOf(i);
				song_view_synch[i].setId(Integer.valueOf(song_view_synch_id));

				/* View of sych	*/
				song_view_synch_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				if (i != 0){
					song_view_synch_param[i].addRule(RelativeLayout.BELOW,song_view[i-1].getId());
				}else{
					song_view_synch_param[i].addRule(RelativeLayout.BELOW,preamble.getId());
				}

				song_view_synch[i].setText(raw_synchordies[i]);
				song_view_synch[i].setTextColor(ContextCompat.getColor(view.getContext(), R.color.Red));
				song_view_synch[i].setLayoutParams(song_view_synch_param[i]);
				layout.addView(song_view_synch[i]);

				/*	Create lyrics view	*/
				song_view[i] = new TextView(this.getContext());
				song_view_id = "20"+String.valueOf(i);
				song_view[i].setId(Integer.valueOf(song_view_id));

				/* Create params for lyrics view */
				song_view_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				song_view_param[i].addRule(RelativeLayout.BELOW, song_view_synch[i].getId());

				song_view[i].setText(raw_lyrics[i]);
				song_view[i].setLayoutParams(song_view_param[i]);
				layout.addView(song_view[i]);

			/* if lyrics not have synchordies */
			}else {
				/*	Create lyrics view	*/
				song_view[i] = new TextView(this.getContext());
				song_view_id = "20"+String.valueOf(i);
				song_view[i].setId(Integer.valueOf(song_view_id));

				/* Create params for lyrics view */
				song_view_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				song_view_param[i].addRule(RelativeLayout.BELOW, song_view[i - 1].getId());

				song_view[i].setText(raw_lyrics[i]);
				song_view[i].setLayoutParams(song_view_param[i]);
				layout.addView(song_view[i]);
			}
		}


		Button back = (Button)view.findViewById(R.id.button_back);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStack();
			}
		});

		Button import_button = (Button)view.findViewById(R.id.button_next);
		import_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				/*	Create Song	*/
				song = new Song();
				song.setTitle(song_details.get(0));
				song.setMusic(song_details.get(2));
				song.setSinger(song_details.get(1));
				song.setVersification(song_details.get(3));
				song.setPreamble(song__preamble);
				song.setLyrics(lyrics);
				song.setSynchordies(lyrics_synchordies);
				email = ((Application)getActivity().getApplication()).getEmail();
				Log.e("SAd0",email);
				/*	Import song into web database	*/
				Song_WebDatabase webDatabase = new Song_WebDatabase(getContext(), new Song_AsyncResponce() {
					@Override
					public void processFinish(String output) {
						AlertDialog.Builder alert_builder = new AlertDialog.Builder(view.getContext());
						alert_builder.setCancelable(false);
					   	alert_builder.setMessage("Song Import To Database");
						alert_builder.setTitle("Great!!!");
						AlertDialog alertDialog = alert_builder.create();
						alertDialog.show();

						Intent intent = new Intent(getActivity(), MainActivity.class);
						getActivity().startActivity(intent);
					}
				});
				webDatabase.execute("import_song",email,song.getTitle(),song.getMusic(),song.getSinger(),song.getVersification(),song.getPreamble(),song.getLyrics(),song.getSynchordies(),email);
			}
		});

		return view;
	}
}
