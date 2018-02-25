package com.cloudjumper.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public class Player extends Entity {

	public Player(Body body, Assets asset) {
		super(body, asset);
	}

	public void inputUpdate(float delta) {
		int horizontalForce = 0;

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			getBody().applyForceToCenter(0, 50, false);
		}

		getBody().setLinearVelocity(horizontalForce * 3, getBody().getLinearVelocity().y);
	}
}
