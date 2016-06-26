package com.example.ntinos.kithara.Import_Song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ntinos.kithara.MainActivity;
import com.example.ntinos.kithara.R;

import java.util.ArrayList;


public class Import_Song_Basic extends Fragment {

	private EditText title;
	private EditText singer;
	private EditText music;
	private EditText versification;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.import_song_basic, container, false);

		final Button button_next = (Button) view.findViewById(R.id.song_import_basic_next);
		Button button_prev = (Button) view.findViewById(R.id.import_song_basic_back);

		/* Get Song details	*/
		title = (EditText)view.findViewById(R.id.song_name_import_edit);
		singer = (EditText)view.findViewById(R.id.song_singer_import_edit);
		music = (EditText)view.findViewById(R.id.song_creator_import_edit);
		versification = (EditText)view.findViewById(R.id.song_versification_import_edit);

		/*	Button Next	*/
		button_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				ArrayList <String> song_details = new ArrayList<>(3);
				song_details.add(title.getText().toString());
				song_details.add(singer.getText().toString());
				song_details.add(music.getText().toString());
				song_details.add(versification.getText().toString());

				Import_Song_Screen_1 import_song_screen_1 = new Import_Song_Screen_1();

				/*	Set Arguments	*/
				Bundle arg = new Bundle();
				arg.putStringArrayList("song_details", song_details);
				import_song_screen_1.setArguments(arg);

				/*	Start Fragment	*/
				android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
				FragmentTransaction user_registerTransaction = registerManager.beginTransaction();
				user_registerTransaction.replace(R.id.frame, import_song_screen_1);
				user_registerTransaction.addToBackStack(null);
				user_registerTransaction.commit();
			}
		});

		/*	Button Back	*/
		button_prev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), MainActivity.class);
				startActivity(intent);
			}
		});

		return view;
	}
}
