package com.example.ntinos.kithara.Custom_Hardware;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.ntinos.kithara.R;


public class Keyboard_Synchordies {

	private KeyboardView keyboardView;
	private EditText editText;
	private Activity HostActivity;
	public KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener() {

		//KEY SYNCHRORDIES
		private final static int KEY_A = 55;			//A
		private final static int KEY_a = 56;			//A# || Bb
		private final static int KEY_B = 57;			//B
		private final static int KEY_C = 58;			//C
		private final static int KEY_c = 59;			//C# ||  Db
		private final static int KEY_D = 60;			//D
		private final static int KEY_d = 61;			//D# || Eb
		private final static int KEY_E = 62;			//E
		private final static int KEY_F = 63;			//F
		private final static int KEY_f = 64;			//F# || Gb
		private final static int KEY_G = 65;			//G
		private final static int KEY_g = 66;			//G# || Ab
		private final static int KEY_m = 67;			//minors || minore
		private final static int KEY_7 = 68;
		private final static int KEY_dim = 73;			//diministry
		private final static int KEY_b = 74;

		//Action Keys
		private final static int KEY_BACKSPACE = 69;		//Backspace delete
		private final static int KEY_SPACE = 70;			//Space
		private final static int KEY_NEW_LINE = 71;			//New Line
		private final static int KEY_DOWN = 72;				//Hide Keyboard

		@Override
		public void onPress(int primaryCode) {

			Editable editable = editText.getText();
			int start = editText.getSelectionStart();

			//Apply Key
			if (primaryCode == KEY_A)
				editable.append("A");
			if (primaryCode == KEY_a)
				editable.append("A#");
			if (primaryCode == KEY_B)
				editable.append("B");
			if (primaryCode == KEY_C)
				editable.append("C");
			if (primaryCode == KEY_c)
				editText.append("C#");
			if (primaryCode == KEY_D)
				editText.append("D");
			if (primaryCode == KEY_d)
				editText.append("D#");
			if (primaryCode == KEY_E)
				editText.append("E");
			if (primaryCode == KEY_F)
				editText.append("F");
			if (primaryCode == KEY_f)
				editText.append("F#");
			if (primaryCode == KEY_G)
				editText.append("G");
			if (primaryCode == KEY_g)
				editText.append("G#");
			if (primaryCode == KEY_m)
				editText.append("m ");
			if (primaryCode == KEY_7)
				editable.append("7");
			if (primaryCode == KEY_b)
				editable.append("b");
			if (primaryCode == KEY_dim)
				editable.append("dim");
			if(primaryCode == KEY_SPACE)
				editText.append(" ");

			//Action Key
			if (primaryCode == KEY_BACKSPACE ){
				if ( editable != null && start>0 ){
					editable.delete(start -1,start);
				}
			}
			if ( primaryCode == KEY_NEW_LINE ) {
				editText.append("\r\n");
			}
			if ( primaryCode == KEY_DOWN ){
				hideCustomKeyboard();
			}
		}

		@Override
		public void onRelease(int primaryCode) {

		}

		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
		}

		@Override
		public void onText(CharSequence text) {

		}

		@Override
		public void swipeLeft() {

		}

		@Override
		public void swipeRight() {

		}

		@Override
		public void swipeDown() {

		}

		@Override
		public void swipeUp() {

		}


	};

	public Keyboard_Synchordies(Activity host, View view, int viewid, int layoutid,float dp) {
		HostActivity = host;
		keyboardView = (KeyboardView)view.findViewById(viewid);
		Point size = new Point();
		HostActivity.getWindowManager().getDefaultDisplay().getSize(size);
		int width = size.x;
		int height = size.y;

		DisplayMetrics metrics = view.getContext().getResources().getDisplayMetrics();
		float fpixels = metrics.density*dp;

		int pixels = (int) (fpixels);
		keyboardView.setKeyboard(new Keyboard(HostActivity, layoutid, R.xml.keyboard_synchordies, pixels, pixels));
		keyboardView.setPreviewEnabled(false); 														// NOTE Do not show the preview balloons
		keyboardView.setOnKeyboardActionListener(mOnKeyboardActionListener);

		/* Hide the standard keyboard initially */
		HostActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/** Returns whether the CustomKeyboard is visible. */
	public boolean isCustomKeyboardVisible() {
		return keyboardView.getVisibility() == View.VISIBLE;
	}

	/** Make the CustomKeyboard visible, and hide the system keyboard for view v. */
	public void showCustomKeyboard( View v ,EditText editText) {
		this.editText = editText;
		keyboardView.setVisibility(View.VISIBLE);
		keyboardView.setEnabled(true);

		if( v!=null ){
			((InputMethodManager)HostActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	/** Make the CustomKeyboard invisible. */
	public void hideCustomKeyboard() {
		keyboardView.setVisibility(View.GONE);
		keyboardView.setEnabled(false);
	}

	public void registerEditText(int id , final View view) {

		final EditText editText = (EditText)view.findViewById(id);		// Find the EditText
		editText.setCursorVisible(true);
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {

					showCustomKeyboard(v,editText);

				} else {

					hideCustomKeyboard();
				}

			}
		});

		editText.setOnClickListener(new View.OnClickListener() {
			// NOTE By setting the on click listener, we can show the custom keyboard again, by tapping on an edit box that already had focus (but that had the keyboard hidden).
			@Override public void onClick(View v) {
				showCustomKeyboard(v,editText);
			}
		});

		// Disable standard keyboard hard way
		editText.setOnTouchListener(new View.OnTouchListener() {
			@Override public boolean onTouch(View view, MotionEvent event) {
				EditText edittext = (EditText) view;
				int inType = edittext.getInputType();
				edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
				edittext.setTextIsSelectable(true);
				edittext.setTextSize(HostActivity.getResources().getDimension(R.dimen.keyboard_size));
				edittext.setTextColor(Color.RED);
				edittext.onTouchEvent(event);
				edittext.setInputType(inType);
				return true; // Consume touch event
			}
		});

		editText.setInputType(editText.getInputType() | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
	}
}
