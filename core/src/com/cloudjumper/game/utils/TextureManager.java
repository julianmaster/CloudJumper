package com.cloudjumper.game.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;
import java.util.List;

public class TextureManager {
	private static List<TextureRegion> textures = new LinkedList<>();

	public static boolean load(FileHandle file) {
		return textures.add(new TextureRegion(new Texture(file)));
	}

	public static TextureRegion getTexture(int textureIndex) {
		return textures.get(textureIndex);
	}

	public static void dispose() {
		for(TextureRegion texture : textures) {
			texture.getTexture().dispose();
		}
	}
}
