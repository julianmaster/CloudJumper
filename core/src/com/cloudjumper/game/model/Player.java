package com.cloudjumper.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.utils.AnimationManager;
import com.cloudjumper.game.view.Assets;

public class Player extends Entity {

	private Body body;
	private Animation idle;

	private float stateTime;

	public Player(Body body, AnimationManager animationManager) {
		this.body = body;
		this.idle = animationManager.get(Assets.PLAYER_IDLE.filename);
		stateTime = 0f;
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
	public void render(float delta, Batch batch, AssetManager assetManager) {
		stateTime += delta;
		Rectangle shape = (Rectangle)body.getUserData();
		Vector2 position = body.getPosition();
		TextureRegion currentFrame = (TextureRegion)idle.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, position.x * Constants.PPM - Constants.X_OFFSET_PLAYER, position.y * Constants.PPM - Constants.Y_OFFSET_PLAYER);
	}

	public Body getBody() {
		return body;
	}
}
