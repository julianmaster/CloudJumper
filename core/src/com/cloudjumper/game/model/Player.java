package com.cloudjumper.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public class Player extends Entity {

	private Body body;
	private Assets asset;

	public Player(Body body, Assets asset) {
		this.body = body;
		this.asset = asset;
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
			body.applyForceToCenter(0, 50, false);
		}

		body.setLinearVelocity(horizontalForce * 3, body.getLinearVelocity().y);
	}

	@Override
	public void render(float delta, Batch batch, AssetManager manager) {
		Rectangle shape = (Rectangle)body.getUserData();

		batch.draw(manager.get(asset.filename, Texture.class), shape.x, shape.y);
	}

	public Body getBody() {
		return body;
	}
}
