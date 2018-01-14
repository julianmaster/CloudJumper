package com.cloudjumper.game.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnimationManager {
	private static List<Texture> textures = new ArrayList<>();
	private static List<Animation> animations = new LinkedList<>();

	public static boolean load(FileHandle file, float frameDuration, int tileWidth, int tileHeight) {
		Texture texture = new Texture(file);
		textures.add(texture);
		return animations.add(new Animation(frameDuration, TextureRegion.split(texture, tileWidth, tileHeight)[0]));
	}

	public static Animation get(int index) {
		return animations.get(index);
	}

	public static void dispose() {
		for(Texture texture : textures) {
			texture.dispose();
		}
	}
}
