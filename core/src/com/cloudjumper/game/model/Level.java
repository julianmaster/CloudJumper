package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Level {

	private Body[][] clouds;

	public Level(Body[][] clouds) {
		this.clouds = clouds;
	}

	public Body[][] getClouds() {
		return clouds;
	}
}
