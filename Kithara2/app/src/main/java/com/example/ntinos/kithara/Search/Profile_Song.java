package com.example.ntinos.kithara.Search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ntinos.kithara.R;


public class Profile_Song extends Fragment {

    String[] song;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.profile_song, container, false);

		/* Get Arguments */
        Bundle arguments = this.getArguments();
        if (arguments != null) {

            song = getArguments().getStringArray("song");
        }

        Button back = (Button) view.findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });


        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(song[0]);

        final TextView preamble = (TextView) view.findViewById(R.id.Preamble);
        song[6] = song[6].replaceAll("<br />", "\n");
        preamble.setText(song[6]);
        song[4] = song[4].replaceAll("<br />", "\n");
        song[5] = song[5].replaceAll("<br />", "\n");
        String[] synchordies = song[6].split("\\r?\\n");
        final String[] lyrics = song[5].split("\\r?\\n");
        final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layout);
        TextViewSong(view, synchordies, lyrics, relativeLayout);

		/*	Songs Details	*/
        TextView versification = (TextView) view.findViewById(R.id.versification_text);
        versification.setText(song[3]);

        TextView music = (TextView) view.findViewById(R.id.Music_text);
        music.setText(song[3]);

		/* 	Transpose 	*/
        final Spinner transpose = (Spinner) view.findViewById(R.id.transpose);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.transpose, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transpose.setAdapter(adapter);
        transpose.setSelection(6);
        transpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-6))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "E";
                            } else {
                                str1 += "D#";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "F";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G";
                            } else {
                                str1 += "F#";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A";
                            } else {
                                str1 += "G#";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "A#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D";
                            } else {
                                str1 += "C#";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "E";
                            } else {
                                str += "D#";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "F";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G";
                            } else {
                                str += "F#";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A";
                            } else {
                                str += "G#";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "A#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D";
                            } else {
                                str += "C#";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-5))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "F#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G#";
                            } else {
                                str1 += "G";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A#";
                            } else {
                                str1 += "A";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "B";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C#";
                            } else {
                                str1 += "C";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D#";
                            } else {
                                str1 += "D";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "E";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "F#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G#";
                            } else {
                                str += "G";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A#";
                            } else {
                                str += "A";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "B";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C#";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D#";
                            } else {
                                str += "D";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-4))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F#";
                            } else {
                                str1 += "F";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "G";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A";
                            } else {
                                str1 += "G#";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "B";
                            } else {
                                str1 += "A#";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "C";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D";
                            } else {
                                str1 += "C#";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "E";
                            } else {
                                str1 += "D#";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F#";
                            } else {
                                str += "F";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "G";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A";
                            } else {
                                str += "G#";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "B";
                            } else {
                                str += "A#";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "C";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D";
                            } else {
                                str += "C#";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "E";
                            } else {
                                str += "D#";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-3))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G";
                            } else {
                                str1 += "F#";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "G#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A#";
                            } else {
                                str1 += "A";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "C#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D#";
                            } else {
                                str1 += "D";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G";
                            } else {
                                str += "F#";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "G#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A#";
                            } else {
                                str += "A";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "C#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D#";
                            } else {
                                str += "D";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "E";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-2))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G#";
                            } else {
                                str1 += "G";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "A";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "B";
                            } else {
                                str1 += "A#";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C#";
                            } else {
                                str1 += "C";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "D";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "E";
                            } else {
                                str1 += "D#";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F#";
                            } else {
                                str1 += "F";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G#";
                            } else {
                                str += "G";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "A";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "B";
                            } else {
                                str += "A#";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C#";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "B";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "E";
                            } else {
                                str += "D#";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F#";
                            } else {
                                str += "F";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(-1))) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A";
                            } else {
                                str1 += "G#";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "A#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D";
                            } else {
                                str1 += "C#";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "D#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G";
                            } else {
                                str1 += "F#";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A";
                            } else {
                                str += "G#";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "A#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D";
                            } else {
                                str += "C#";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "D#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "E";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G";
                            } else {
                                str += "F#";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals(String.valueOf(0))) {

                    preamble.setText(song[4]);
                    String[] synch = song[6].split("\\r?\\n");
                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+1")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "C#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D#";
                            } else {
                                str1 += "D";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "F#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G#";
                            } else {
                                str1 += "G";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A#";
                            } else {
                                str1 += "A";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "C#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D#";
                            } else {
                                str += "D";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "E";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "F#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G#";
                            } else {
                                str += "G";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A#";
                            } else {
                                str += "A";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+2")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C#";
                            } else {
                                str1 += "C";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "D";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "E";
                            } else {
                                str1 += "D#";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F#";
                            } else {
                                str1 += "F";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "G";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A";
                            } else {
                                str1 += "G#";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "B";
                            } else {
                                str1 += "A#";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C#";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "D";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "E";
                            } else {
                                str += "D#";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F#";
                            } else {
                                str += "F";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "G";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A";
                            } else {
                                str += "G#";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "B";
                            } else {
                                str += "A#";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+3")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D";
                            } else {
                                str1 += "C#";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "D#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G";
                            } else {
                                str1 += "F#";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "G#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A#";
                            } else {
                                str1 += "A";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D";
                            } else {
                                str += "C#";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "D#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "E";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G";
                            } else {
                                str += "F#";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "G#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A#";
                            } else {
                                str += "A";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+4")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D#";
                            } else {
                                str1 += "D";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "E";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F#";
                            } else {
                                str1 += "F";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G#";
                            } else {
                                str1 += "G";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "A";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "B";
                            } else {
                                str1 += "A#";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C#";
                            } else {
                                str1 += "C";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D#";
                            } else {
                                str += "D";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "E";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F#";
                            } else {
                                str += "F";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G#";
                            } else {
                                str += "G";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "A";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "B";
                            } else {
                                str += "A#";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C#";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+5")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "E";
                            } else {
                                str1 += "D#";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "F";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G";
                            } else {
                                str1 += "F#";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A";
                            } else {
                                str1 += "G#";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "A#";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C";
                            } else {
                                str1 += "B";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D";
                            } else {
                                str1 += "C#";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "E";
                            } else {
                                str += "D#";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "F";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G";
                            } else {
                                str += "F#";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A";
                            } else {
                                str += "G#";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "A#";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C";
                            } else {
                                str += "B";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D";
                            } else {
                                str += "C#";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                } else if (parent.getItemAtPosition(position).toString().equals("+6")) {

                    String new_synchordies = song[6];
                    String preamble_synch = song[4];

					/*	Transpose Synchordies	*/
                    String str1 = "";
                    for (int i = 0; i < new_synchordies.length() - 1; i++) {

                        if (new_synchordies.charAt(i) == 'A') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "F";
                            } else {
                                str1 += "E";
                            }
                        } else if (new_synchordies.charAt(i) == 'B') {
                            str1 += "F#";
                        } else if (new_synchordies.charAt(i) == 'C') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "G#";
                            } else {
                                str1 += "G";
                            }
                        } else if (new_synchordies.charAt(i) == 'D') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "A#";
                            } else {
                                str1 += "A";
                            }
                        } else if (new_synchordies.charAt(i) == 'E') {
                            str1 += "B";

                        } else if (new_synchordies.charAt(i) == 'F') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "C#";
                            } else {
                                str1 += "C";
                            }
                        } else if (new_synchordies.charAt(i) == 'G') {
                            if (new_synchordies.charAt(i + 1) == '#') {
                                str1 += "D#";
                            } else {
                                str1 += "D";
                            }
                        } else if (new_synchordies.charAt(i) == '\n') {
                            str1 += "\n";
                        } else if (new_synchordies.charAt(i) == ' ') {
                            str1 += " ";
                        } else if (new_synchordies.charAt(i) == '7') {
                            str1 += "7";
                        }
                    }

					/*	Transpose Preamble	*/
                    String str = "";
                    for (int i = 0; i < preamble_synch.length() - 1; i++) {

                        if (preamble_synch.charAt(i) == 'A') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "F";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == 'B') {
                            str += "F#";
                        } else if (preamble_synch.charAt(i) == 'C') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "G#";
                            } else {
                                str += "G";
                            }
                        } else if (preamble_synch.charAt(i) == 'D') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "A#";
                            } else {
                                str += "A";
                            }
                        } else if (preamble_synch.charAt(i) == 'E') {
                            str += "B";

                        } else if (preamble_synch.charAt(i) == 'F') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "C#";
                            } else {
                                str += "C";
                            }
                        } else if (preamble_synch.charAt(i) == 'G') {
                            if (preamble_synch.charAt(i + 1) == '#') {
                                str += "D#";
                            } else {
                                str += "D";
                            }
                        } else if (preamble_synch.charAt(i) == '\n') {
                            str += "\n";
                        } else if (preamble_synch.charAt(i) == ' ') {
                            str += " ";
                        }
                    }


                    preamble.setText(str);
                    String[] synch = str1.split("\\r?\\n");

                    relativeLayout.removeAllViews();
                    TextViewSong(view, synch, lyrics, relativeLayout);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    private RelativeLayout TextViewSong(View view, String[] synchordies, String[] lyrics, RelativeLayout relativeLayout) {


        TextView[] lyrics_text = new TextView[lyrics.length];
        TextView[] synchordies_text = new TextView[synchordies.length];
        RelativeLayout.LayoutParams[] lyrics_param = new RelativeLayout.LayoutParams[lyrics.length];
        RelativeLayout.LayoutParams[] synchordies_param = new RelativeLayout.LayoutParams[synchordies.length];

		/*	Synchordies Text View */
        synchordies_text[0] = new TextView(view.getContext());
        synchordies_text[0].setId(Integer.valueOf("300"));
        synchordies_text[0].setText(synchordies[0]);
        synchordies_text[0].setTextColor(ContextCompat.getColor(view.getContext(), R.color.Red));
        synchordies_param[0] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        synchordies_param[0].setMargins(0, 50, 0, 0);
        synchordies_text[0].setLayoutParams(synchordies_param[0]);
        relativeLayout.addView(synchordies_text[0]);

		/*	Lyrics Text View */
        lyrics_text[0] = new TextView(view.getContext());
        lyrics_text[0].setId(Integer.valueOf("400"));
        lyrics_text[0].setText(lyrics[0]);
        lyrics_text[0].setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.songs_title));
        lyrics_param[0] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lyrics_param[0].addRule(RelativeLayout.BELOW, synchordies_text[0].getId());
        lyrics_text[0].setLayoutParams(lyrics_param[0]);
        relativeLayout.addView(lyrics_text[0]);

        for (int i = 1; i < lyrics.length; i++) {
            /*	if lyrics have synchordies */
            if (i < synchordies.length) {

				/* 	Synchordies Text view	*/
                synchordies_text[i] = new TextView(this.getContext());
                synchordies_text[i].setId(Integer.valueOf("30" + i));

                synchordies_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                if (i != 0) {
                    synchordies_param[i].addRule(RelativeLayout.BELOW, lyrics_text[i - 1].getId());
                }

                synchordies_text[i].setText(synchordies[i]);
                synchordies_text[i].setTextColor(ContextCompat.getColor(view.getContext(), R.color.Red));
                synchordies_text[i].setLayoutParams(synchordies_param[i]);
                relativeLayout.addView(synchordies_text[i]);

				/*	Create lyrics view	*/
                lyrics_text[i] = new TextView(this.getContext());
                lyrics_text[i].setId(Integer.valueOf("40" + i));
                lyrics_text[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.songs_title));

                lyrics_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lyrics_param[i].addRule(RelativeLayout.BELOW, synchordies_text[i].getId());

                lyrics_text[i].setText(lyrics[i]);
                lyrics_text[i].setLayoutParams(lyrics_param[i]);
                relativeLayout.addView(lyrics_text[i]);

			/* if lyrics not have synchordies */
            } else {
				/*	Create lyrics view	*/
                lyrics_text[i] = new TextView(this.getContext());
                lyrics_text[i].setId(Integer.valueOf("40" + i));
                lyrics_text[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.songs_title));

				/* Create params for lyrics view */
                lyrics_param[i] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lyrics_param[i].addRule(RelativeLayout.BELOW, lyrics_text[i - 1].getId());

                lyrics_text[i].setText(lyrics[i]);
                lyrics_text[i].setLayoutParams(lyrics_param[i]);
                relativeLayout.addView(lyrics_text[i]);
            }
        }
        return relativeLayout;
    }

}
