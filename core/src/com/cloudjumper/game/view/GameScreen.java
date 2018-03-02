package com.cloudjumper.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.CloudJumper;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.model.*;
import com.cloudjumper.game.utils.TextureManager;

public class GameScreen extends ScreenAdapter {
	private final CloudJumper game;

	private Level level;
	// Box2D
	private World world;

	private boolean showDebugPhysics = true;
	private boolean showBackground = true;
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private Player player;

	public GameScreen(CloudJumper game) {
		this.game = game;
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.8f), false);
		player = new Player(EntityManager.createBox(64, 64, 5, 8, false, world), null);
		level = LevelGenerator.generateLevel(world);
	}

	@Override
	public void render(float delta) {
		Batch batch = game.getBatch();

		if(Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
			showDebugPhysics = !showDebugPhysics;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
			for(Cloud cloud : level.getClouds()) {
				world.destroyBody(cloud.getBody());
			}
			level = LevelGenerator.generateLevel(world);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
			showBackground = !showBackground;
		}

		player.inputUpdate(delta);

		batch.begin();
		if(showBackground) {
			batch.draw(TextureManager.getTexture(Assets.BACKGROUND.ordinal()), 0, 0);
		}
		for(Cloud cloud : level.getClouds()) {
			cloud.render(delta, batch);
		}
		batch.end();

		if(showDebugPhysics) {
			debugRenderer.render(world, game.getCam().combined.scl(Constants.PPM));
		}

		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		world.dispose();
	}
}
