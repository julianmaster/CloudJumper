package com.cloudjumper.game;

public class Constants {
	public static final int CAMERA_WIDTH = 128;
	public static final int CAMERA_HEIGHT = 128;
	public static final int WINDOM_ZOOM = 6;
	public static final int WINDOW_WIDTH = CAMERA_WIDTH * WINDOM_ZOOM;
	public static final int WINDOW_HEIGHT = CAMERA_HEIGHT * WINDOM_ZOOM;

	public static final int TILE_SIZE = 16;

	public static final float PPM = 16;

	public static final int LEVEL_SIZE = 8;

	public static final int TOP_OFFSET_CLOUD = 3;
	public static final int BOTTOM_OFFSET_CLOUD = 2;
	public static final int LEFT_OFFSET_CLOUD = 1;
	public static final int RIGHT_OFFSET_CLOUD = 1;

	public static final float X_OFFSET_PLAYER = TILE_SIZE / 2 - 0.5f;
	public static final float Y_OFFSET_PLAYER = TILE_SIZE / 4;
}
