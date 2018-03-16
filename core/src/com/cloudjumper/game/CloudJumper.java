package com.cloudjumper.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cloudjumper.game.utils.AnimationManager;
import com.cloudjumper.game.utils.AudioManager;
import com.cloudjumper.game.view.Assets;
import com.cloudjumper.game.view.GameScreen;

public class CloudJumper extends Game {
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private AssetManager assetManager;
	private AnimationManager animationManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);
		assetManager = new AssetManager();
		animationManager = new AnimationManager();

		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f);
		cam.update();

		load();

		this.setScreen(new GameScreen(this));
	}

	private void load() {
		assetManager.load(Assets.BACKGROUND.filename, Texture.class);
		assetManager.load(Assets.CLOUD_LEFT.filename, Texture.class);
		assetManager.load(Assets.CLOUD_MIDDLE_1.filename, Texture.class);
		assetManager.load(Assets.CLOUD_MIDDLE_2.filename, Texture.class);
		assetManager.load(Assets.CLOUD_RIGHT_1.filename, Texture.class);
		assetManager.load(Assets.CLOUD_RIGHT_2.filename, Texture.class);
		assetManager.load(Assets.PLAYER_IDLE.filename, Texture.class);
		assetManager.load(Assets.PLAYER_RUN.filename, Texture.class);
		assetManager.finishLoading();

		animationManager.load(Assets.PLAYER_IDLE.filename, assetManager.get(Assets.PLAYER_IDLE.filename), 0.800f, Constants.TILE_SIZE, Constants.TILE_SIZE);
		animationManager.load(Assets.PLAYER_RUN.filename, assetManager.get(Assets.PLAYER_RUN.filename), 0.070f, Constants.TILE_SIZE, Constants.TILE_SIZE);
	}

	@Override
	public void render () {
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
	}
	
	@Override
	public void dispose () {
		screen.dispose();
		batch.dispose();
		assetManager.dispose();
		AudioManager.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}
}
