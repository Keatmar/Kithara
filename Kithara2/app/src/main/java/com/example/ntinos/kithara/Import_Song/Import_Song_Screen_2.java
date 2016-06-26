package com.example.ntinos.kithara.Import_Song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ntinos.kithara.Custom_Hardware.Keyboard_Synchordies;
import com.example.ntinos.kithara.R;

import java.util.ArrayList;
import java.util.Objects;

public class Import_Song_Screen_2  extends Fragment{

	private Keyboard_Synchordies keyboard_synchordies;
	private String lyrics_synchordies = "";
	private EditText[] lyrics_edit;
	private String lyrics = "";
	private ArrayList<String> song_details;
	private String song__preamble;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.import_song_screen_2, container, false);

		/*	Arguments	*/
		Bundle arguments = this.getArguments();
		if (arguments != null) {
			lyrics = getArguments().getString("lyrics");
			song__preamble = getArguments().getString("song_preamble");
			song_details = new ArrayList<>(3);
			song_details = getArguments().getStringArrayList("song_details");
		}

		// Custom Keyboard
		//Keyboard width-height
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
				dp = 520f;
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
				dp = 540f;
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

		/*	Button next	*/
		Button button_next = (Button) view.findViewById(R.id.button_next);
		button_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button_Next(view);
			}
		});

		/*	Button back	*/
		Button button_back = (Button) view.findViewById(R.id.button_back);
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getFragmentManager().popBackStack();

			}
		});

		if (lyrics != null) {       // if lyrics is not empty

			Lyrics_Edit(view, lyrics);
		}

		return view;
	}


	/*	Button Next	*/
	public void Button_Next(View view){
		if(lyrics_edit != null) {
			int length = lyrics_edit.length;
			for (int i = 0; i < length; i++) {
				if (!Objects.equals(lyrics_edit[i].getText().toString(), "")) {
					lyrics_synchordies = lyrics_synchordies + lyrics_edit[i].getText().toString() + "\n";
				}else {
					lyrics_synchordies = lyrics_synchordies + "\0";
					break;
				}
			}

			Import_Song_Final import_song_final = new Import_Song_Final();

			/*	Arguments	*/
			Bundle arg = new Bundle();
			arg.putString("lyrics",lyrics);
			arg.putString("song_preamble",song__preamble);
			arg.putStringArrayList("song_details",song_details);
			arg.putString("lyrics_synchordies",lyrics_synchordies);
			import_song_final.setArguments(arg);

			/*	Start Fragment	*/
			android.support.v4.app.FragmentManager registerManager = getActivity().getSupportFragmentManager();
			FragmentTransaction user_registerTransaction = registerManager.beginTransaction();
			user_registerTransaction.replace(R.id.frame, import_song_final);
			user_registerTransaction.addToBackStack(null);
			user_registerTransaction.commit();
		}

	}

	public void Lyrics_Edit(View view,String lyrics) {

		String[] lyrics_line = lyrics.split("\\r?\\n");                                            // split lyrics to new line

		/* Create Layout  */
		RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.lyrics_layout);
		TextView[] lyrics_text = new TextView[lyrics_line.length];
		lyrics_edit = new EditText[lyrics_line.length];
		RelativeLayout.LayoutParams[] text_param = new RelativeLayout.LayoutParams[lyrics_line.length];
		RelativeLayout.LayoutParams[] edit_param = new RelativeLayout.LayoutParams[lyrics_line.length];

		/* Create First TextView :view first row of lyrics */
		lyrics_text[0] = new TextView(view.getContext());
		String text_id = String.valueOf(100);
		lyrics_text[0].setId(Integer.valueOf(text_id));

		/* Create First EditText  */
		lyrics_edit[0] = new EditText(view.getContext());
		String edit_id = String.valueOf(200);
		lyrics_edit[0].setId(Integer.valueOf(edit_id));

		/* Layout for textView */
		text_param[0] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		text_param[0].addRule(RelativeLayout.BELOW, lyrics_edit[0].getId());
		text_param[0].setMargins(10, 0, 0, 0);

		/* Layout for editText */
		edit_param[0] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		edit_param[0].addRule(RelativeLayout.ALIGN_END, lyrics_text[0].getId());
		edit_param[0].setMargins(10, 10, 0, 0);

		/* Fill EditText */
		lyrics_edit[0].setLayoutParams(edit_param[0]);
		layout.addView(lyrics_edit[0]);
		lyrics_edit[0].setInputType(InputType.TYPE_CLASS_TEXT);
		lyrics_edit[0].setGravity(Gravity.TOP);
		lyrics_edit[0].setImeOptions(EditorInfo.IME_ACTION_NEXT);
		lyrics_edit[0].setSingleLine(true);
		keyboard_synchordies.registerEditText(lyrics_edit[0].getId(), view);


		/* Fill TextView */
		lyrics_text[0].setText(lyrics_line[0]);
		lyrics_text[0].setTextSize(getResources().getDimension(R.dimen.lyrics));
		lyrics_text[0].setLayoutParams(text_param[0]);
		layout.addView(lyrics_text[0]);

		for (int i=1; i<lyrics_line.length; i++) {

			/* Create First TextView :view first row of lyrics */
			lyrics_text[i] = new TextView(view.getContext());
			text_id = "10" + String.valueOf(i);
			lyrics_text[i].setId(Integer.valueOf(text_id));

			/* Create First EditText  */
			lyrics_edit[i] = new EditText(view.getContext());
			edit_id = "20" + String.valueOf(i);
			lyrics_edit[i].setId(Integer.valueOf(edit_id));

			/* Layout for textView */
			text_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			text_param[i].addRule(RelativeLayout.BELOW, lyrics_edit[i].getId());
			//text_param[i].setMargins(10, 0, 0, 0);

			/* Layout for editText */
			edit_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			edit_param[i].addRule(RelativeLayout.ALIGN_END, lyrics_text[i].getId());
			edit_param[i].addRule(RelativeLayout.BELOW, lyrics_text[i - 1].getId());

			/* Fill EditText */
			lyrics_edit[i].setLayoutParams(edit_param[i]);
			lyrics_edit[i].setInputType(InputType.TYPE_CLASS_TEXT);
			lyrics_edit[i].setGravity(Gravity.TOP);
			layout.addView(lyrics_edit[i]);
			keyboard_synchordies.registerEditText(lyrics_edit[i].getId(), view);

			/* Fill TextView */
			lyrics_text[i].setText(lyrics_line[i]);
			lyrics_text[i].setTextSize(getResources().getDimension(R.dimen.lyrics));
			lyrics_text[i].setLayoutParams(text_param[i]);
			layout.addView(lyrics_text[i]);
		}
	}

}
