package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Level {

	private Cloud[][] clouds;

	public Level(Cloud[][] clouds) {
		this.clouds = clouds;
	}

	public Cloud[][] getClouds() {
		return clouds;
	}
}
