package com.example.ntinos.kithara.Database;

public class Song {

private long id;
private String title;
private String singer;
private String music;
private String versification;

/*	Lyrics	Songs	*/
private String preamble;
private String lyrics;
private String synchordies;
private String email;

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getLyrics() {
	return lyrics;
}

public void setLyrics(String lyrics) {
	this.lyrics = lyrics;
}

public String getMusic() {
	return music;
}

public void setMusic(String music) {
	this.music = music;
}

public String getSinger() {
	return singer;
}

public void setSinger(String singer) {
	this.singer = singer;
}

public String getPreamble() {
	return preamble;
}

public void setPreamble(String preamble) {
	this.preamble = preamble;
}

public String getSynchordies() {
	return synchordies;
}

public void setSynchordies(String synchordies) {
	this.synchordies = synchordies;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getVersification() {
	return versification;
}

public void setVersification(String versification) {
	this.versification = versification;
}
}
