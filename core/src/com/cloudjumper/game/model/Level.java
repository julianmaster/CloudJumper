package com.cloudjumper.game.model;

import java.util.List;

public class Level {
	private List<Cloud> clouds;

	public Level(List<Cloud> clouds) {
		this.clouds = clouds;
	}

	public List<Cloud> getClouds() {
		return clouds;
	}
}
