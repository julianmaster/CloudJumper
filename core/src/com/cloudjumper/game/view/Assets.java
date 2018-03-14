package com.cloudjumper.game.view;

public enum Assets {
	BACKGROUND("background.png"),
	CLOUD_LEFT("cloudleft.png"),
	CLOUD_MIDDLE_1("cloudmiddle1.png"),
	CLOUD_MIDDLE_2("cloudmiddle2.png"),
	CLOUD_RIGHT_1("cloudright1.png"),
	CLOUD_RIGHT_2("cloudright2.png");

	public String filename;

	Assets(String filename) {
		this.filename = filename;
	}
}
