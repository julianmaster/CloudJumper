package com.cloudjumper.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cloudjumper.game.utils.AnimationManager;
import com.cloudjumper.game.utils.AudioManager;
import com.cloudjumper.game.utils.TextureManager;
import com.cloudjumper.game.view.GameScreen;

public class 	CloudJumper extends Game {
	private SpriteBatch batch;
	private OrthographicCamera cam;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);

		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0f);
		cam.update();

		load();

		this.setScreen(new GameScreen(this));
	}

	private void load() {
		TextureManager.load(Gdx.files.internal("background.png"));
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
		TextureManager.dispose();
		AnimationManager.dispose();
		AudioManager.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCam() {
		return cam;
	}
}
