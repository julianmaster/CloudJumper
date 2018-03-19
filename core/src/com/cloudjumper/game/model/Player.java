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
	private Animation run;
	private Animation jump;
	private Animation fall;
	private Animation land;

	private Animation currentAnimation;
	private float stateTime;
	private boolean isJump = false;
	private int horizontalForce;

	public Player(Body body, AnimationManager animationManager) {
		this.body = body;
		this.idle = animationManager.get(Assets.PLAYER_IDLE.filename);
		this.run = animationManager.get(Assets.PLAYER_RUN.filename);
		this.jump = animationManager.get(Assets.PLAYER_JUMP.filename);
		this.fall = animationManager.get(Assets.PLAYER_FALL.filename);
		this.land = animationManager.get(Assets.PLAYER_LAND.filename);
		this.currentAnimation = idle;
		stateTime = 0f;
	}

	public void inputUpdate(float delta) {
		horizontalForce = 0;

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			horizontalForce -= 1;
			if(!isJump) {
				currentAnimation = run;
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			horizontalForce += 1;
			if(!isJump) {
				currentAnimation = run;
			}
		}

		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			body.applyForceToCenter(0, 50, false);
			stateTime = 0f;
			isJump = true;
		}

		if(body.getLinearVelocity().y > 0.2) {
			currentAnimation = jump;
		}
		else if(body.getLinearVelocity().y < 0.2 && currentAnimation == jump) {
			stateTime = 0f;
			currentAnimation = fall;
		}
		else if(body.getLinearVelocity().y == 0 && currentAnimation == fall) {
			stateTime = 0f;
			currentAnimation = land;
			isJump = false;
		}

		if(!isJump && horizontalForce == 0 && (currentAnimation == land ? currentAnimation.isAnimationFinished(stateTime) : true)) {
			currentAnimation = idle;
		}

		body.setLinearVelocity(horizontalForce * 3, body.getLinearVelocity().y);
	}

	@Override
	public void render(float delta, Batch batch, AssetManager assetManager) {
		stateTime += delta;
		Rectangle shape = (Rectangle)body.getUserData();
		Vector2 position = body.getPosition();
		boolean looping = currentAnimation == run || currentAnimation == idle;
		TextureRegion currentFrame = (TextureRegion)currentAnimation.getKeyFrame(stateTime, looping);
		if(horizontalForce == -1 && !currentFrame.isFlipX()) {
			currentFrame.flip(horizontalForce == -1, false);
		}
		else if(horizontalForce != -1 && currentFrame.isFlipX()) {
			currentFrame.flip(horizontalForce != -1, false);
		}
		batch.draw(currentFrame, position.x * Constants.PPM - Constants.X_OFFSET_PLAYER, position.y * Constants.PPM - Constants.Y_OFFSET_PLAYER);
	}

	public Body getBody() {
		return body;
	}
}
