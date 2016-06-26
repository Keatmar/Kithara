package com.example.ntinos.kithara.Import_Song;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ntinos.kithara.Custom_Hardware.Keyboard_Synchordies;
import com.example.ntinos.kithara.R;

import java.util.ArrayList;


public class Import_Song_Screen_1 extends Fragment {

	private EditText song_lyrics;
	private ArrayList <String> song_details;
	private EditText song_preamble_edit;
	private Keyboard_Synchordies keyboard_synchordies;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.import_song_screen_1, container, false);

		/* Get Arguments */
		Bundle arguments = this.getArguments();
		if (arguments != null) {
			song_details = new ArrayList<>(3);
			song_details = getArguments().getStringArrayList("song_details");
		}


		song_preamble_edit = (EditText) view.findViewById(R.id.song_preamble_import_edit);
		song_preamble_edit.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				song_preamble_edit.requestLayout();
				getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
				return false;
			}
		});

		/*	Custom Keyboard metrics	*/
		float dp;
		DisplayMetrics metrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		switch(metrics.densityDpi){

			case DisplayMetrics.DENSITY_280:
				dp = 500f;
				break;
			case DisplayMetrics.DENSITY_360:
				dp = 500f;
				break;
			case DisplayMetrics.DENSITY_400:
				dp = 800f;
				break;
			case DisplayMetrics.DENSITY_420:
				dp = 660f;
				break;
			case DisplayMetrics.DENSITY_560:
				dp = 660f;
				break;
			case DisplayMetrics.DENSITY_LOW:
				dp = 520f;
				break;
			case DisplayMetrics.DENSITY_MEDIUM:
				dp = 520f;
				break;
			case DisplayMetrics.DENSITY_HIGH:
				dp = 510f;
				break;
			case DisplayMetrics.DENSITY_XHIGH:
				dp = 630f;
				break;
			case DisplayMetrics.DENSITY_XXHIGH:
				dp = 600f;
				break;
			case DisplayMetrics.DENSITY_XXXHIGH:
				dp = 600f;
				break;
			default:
				dp = 0f;
		}

		keyboard_synchordies = new Keyboard_Synchordies( getActivity(),view , R.id.keyboard_cordless ,R.xml.keyboard_synchordies ,dp);
		keyboard_synchordies.registerEditText(R.id.song_preamble_import_edit, view);


		song_lyrics = (EditText) view.findViewById(R.id.song_lyrics_edit);							//Lyrics edit


		/*	Button Next	*/
		final Button button_next = (Button) view.findViewById(R.id.import_song_next);

		button_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String lyrics = song_lyrics.getText().toString();
				String song_preamble = song_preamble_edit.getText().toString();

				Import_Song_Screen_2 import_song_screen_2 = new Import_Song_Screen_2();

				/* Set Arguments	*/
				Bundle arg = new Bundle();
				arg.putStringArrayList("song_details",song_details);
				arg.putString("lyrics", lyrics);
				arg.putString("song_preamble", song_preamble);
				import_song_screen_2.setArguments(arg);

				/* Start Fragmanet	*/
				android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
				FragmentTransaction user_registerTransaction = registerManager.beginTransaction();
				user_registerTransaction.replace(R.id.frame, import_song_screen_2);
				user_registerTransaction.addToBackStack(null);
				user_registerTransaction.commit();
			}
		});


		/*	Button back	*/
		Button button_back = (Button) view.findViewById(R.id.import_song_back);
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStack();

			}
		});

		return view;
	}

}
