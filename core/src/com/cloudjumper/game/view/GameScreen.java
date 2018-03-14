package com.cloudjumper.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;
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
	private boolean showSprite = true;
	private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

	private Player player;

	public GameScreen(CloudJumper game) {
		this.game = game;
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.8f), false);
		player = new Player(EntityManager.createBox(64, 64, 5, 8, false, world), null);
		level = LevelGenerator.generateLevel(world, player);

		world.setContactFilter(new ContactFilter() {
			@Override
			public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
				Fixture playerFixture = player.getBody().getFixtureList().first();
				if (playerFixture == fixtureA || playerFixture == fixtureB) {
					Cloud c = null;
					for(Cloud cloud : level.getClouds()) {
						Fixture cloudFixture = cloud.getBody().getFixtureList().first();
						if(cloudFixture == fixtureA || cloudFixture == fixtureB) {
							c = cloud;
							break;
						}
					}

					if(c != null) {
						Vector2 positionPlayer = player.getBody().getPosition();
						Rectangle rectanglePlayer = ((Rectangle)player.getBody().getUserData());
						Vector2 positionCloud = c.getBody().getPosition();
						Rectangle rectangleCloud = ((Rectangle)c.getBody().getUserData());
						if(positionPlayer.y < positionCloud.y + rectangleCloud.height/2/Constants.PPM - rectanglePlayer.height*0.005f/Constants.PPM) {
							return false;
						}
						else {
							return true;
						}
					}
					else {
						return true;
					}
				}
				else {
					return true;
				}
			}
		});
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
			level = LevelGenerator.generateLevel(world, player);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
			showSprite = !showSprite;
		}

		player.inputUpdate(delta);

		batch.begin();
		if(showSprite) {
			batch.draw(TextureManager.getTexture(Assets.BACKGROUND.ordinal()), 0, 0);
			for(Cloud cloud : level.getClouds()) {
				cloud.render(delta, batch);
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
