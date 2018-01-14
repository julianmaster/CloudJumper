package com.cloudjumper.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.LinkedList;
import java.util.List;

public class AudioManager {
	private static List<Sound> sounds = new LinkedList<>();
	private static List<Music> musics = new LinkedList<>();

	public static boolean loadSound(FileHandle fileHandle) {
		return sounds.add(Gdx.audio.newSound(fileHandle));
	}

	public static boolean loadMusic(FileHandle fileHandle) {
		return musics.add(Gdx.audio.newMusic(fileHandle));
	}

	public static Sound getSound(int index) {
		return sounds.get(index);
	}

	public static Music getMusic(int index) {
		return musics.get(index);
	}

	public static void dispose() {
		for(Sound sound : sounds) {
			sound.dispose();
		}

		for(Music music : musics) {
			music.dispose();
		}
	}
}
