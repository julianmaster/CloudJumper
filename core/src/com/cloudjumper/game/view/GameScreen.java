package com.cloudjumper.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.CloudJumper;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.model.Level;
import com.cloudjumper.game.model.LevelGenerator;
import com.cloudjumper.game.utils.TextureManager;

public class GameScreen extends ScreenAdapter {
	private final CloudJumper game;

	private Level level;
	private World world;

	private boolean showDebugPhysics = true;
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private Body player;
	private Body platform;

	public GameScreen(CloudJumper game) {
		this.game = game;
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.8f), false);
//		player = EntityManager.createBox(64, 64, 5, 8, false, world);
//		platform = EntityManager.createBox(64, 16, 16*6, 16, true, world);
		level = LevelGenerator.generateLevel(world);
	}

	@Override
	public void render(float delta) {
		Batch batch = game.getBatch();

		if(Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
			showDebugPhysics = !showDebugPhysics;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
			for(Body[] lines : level.getClouds()) {
				for(Body cloud : lines) {
					if(cloud != null) {
						world.destroyBody(cloud);
					}
				}
			}
			level = LevelGenerator.generateLevel(world);
		}

		batch.begin();
//		batch.draw(TextureManager.getTexture(Assets.BACKGROUND.ordinal()), 0, 0);
		for(Body[] lines : level.getClouds()) {
			for(Body cloud : lines) {
				if(cloud != null) {
					batch.draw(TextureManager.getTexture(Assets.CLOUD_MIDDLE_1.ordinal()), cloud.getPosition().x, cloud.getPosition().y);
				}
			}
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
