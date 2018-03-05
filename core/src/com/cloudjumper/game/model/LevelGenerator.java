package com.cloudjumper.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.view.Assets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {

	private static Random rand = new Random();

	public static Level generateLevel(World world, Player player) {
		List<Cloud> clouds = new ArrayList<>();
		boolean[][] cloudMap = new boolean[8][8];

		for(int y = Constants.LEVEL_SIZE - 2; y >= 0; y--) {
			for(int x = 0; x < Constants.LEVEL_SIZE - 2; x++) {
				if(cloudMap[y][x] == false && cloudMap[y+1][x] == false && (x == 0 || cloudMap[y+1][x-1] == false) && rand.nextBoolean()) {
					int length = rand.nextInt(3) + 2;
					length = Math.min(cloudMap.length, x + length) - x;

					Body cloudBody = generateCloud(x, y, length, world, player);

					List<Assets> assets = new LinkedList<>();
					for(int index = 0; index < length; index++) {
						if(index == 0) {
							assets.add(Assets.CLOUD_LEFT);
						}
						else if(index == length - 1) {
							assets.add(rand.nextBoolean() ? Assets.CLOUD_RIGHT_1 : Assets.CLOUD_RIGHT_2);
						}
						else {
							assets.add(rand.nextBoolean() ? Assets.CLOUD_MIDDLE_1 : Assets.CLOUD_MIDDLE_2);
						}
						cloudMap[y][x+index] = true;
					}

					clouds.add(new Cloud(cloudBody, assets));

					x += length+2;
				}
			}
		}

		return new Level(clouds);
	}

	private static Body generateCloud(int x, int y, int width, World world, Player player) {
		Body cloudBody = EntityManager.createBox(x * Constants.TILE_SIZE + width * Constants.TILE_SIZE/2,
				y * Constants.TILE_SIZE + Constants.TILE_SIZE/2 - Constants.TOP_OFFSET_CLOUD + Constants.BOTTOM_OFFSET_CLOUD,
				width * Constants.TILE_SIZE - Constants.LEFT_OFFSET_CLOUD - Constants.RIGHT_OFFSET_CLOUD,
				Constants.TILE_SIZE - Constants.TOP_OFFSET_CLOUD - Constants.BOTTOM_OFFSET_CLOUD, true, world);

		cloudBody.setUserData(new Rectangle(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, width * Constants.TILE_SIZE, Constants.TILE_SIZE));

		world.setContactFilter(new ContactFilter() {
			@Override
			public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
				Fixture platformFixture = cloudBody.getFixtureList().first();
				Fixture playerFixture = player.getBody().getFixtureList().first();
				player.getBody().getFixtureList().first();
				if ((fixtureA == platformFixture && fixtureB == playerFixture) || (fixtureB == platformFixture && fixtureA == playerFixture)) {
					Vector2 position = player.getBody().getPosition();
//					if (position.y < m_top + m_radius - 3.0f * 0.005f)
						return false;
//					else
//						return true;
				} else
					return true;
			}
		});

		return cloudBody;
	}
}
