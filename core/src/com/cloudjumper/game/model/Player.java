package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public class Player extends Entity {

	public Player(Body body, Assets asset) {
		super(body, asset);
	}
}
