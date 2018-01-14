package com.cloudjumper.game.view;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.CloudJumper;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.model.EntityManager;
import com.cloudjumper.game.utils.TextureManager;

public class GameScreen extends ScreenAdapter {
	private final CloudJumper game;

	private World world = new World(new Vector2(0, -9.8f), true);
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	public GameScreen(CloudJumper game) {
		this.game = game;
	}

	@Override
	public void show() {
		EntityManager.createPlayer(world);
	}

	@Override
	public void render(float delta) {
		Batch batch = game.getBatch();

		batch.begin();
		//batch.draw(TextureManager.getTexture(Assets.BACKGROUND.ordinal()), 0, 0);
		batch.end();

		debugRenderer.render(world, game.getCam().combined.scl(Constants.PPM));

		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose() {
		debugRenderer.dispose();
		world.dispose();
	}
}
