package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.view.Assets;

import java.util.Random;

public class LevelGenerator {

	private static Random rand = new Random();

	public static Level generateLevel(World world) {
		Cloud[][] clouds = new Cloud[8][8];

		for(int y = clouds.length - 2; y >= 0; y--) {
			for(int x = 0; x < clouds.length - 2; x++) {
				if(clouds[y][x] == null && clouds[y+1][x] == null && (x == 0 || clouds[y+1][x-1] == null) && rand.nextBoolean()) {
					int length = rand.nextInt(3) + 2;
					length = Math.min(clouds.length, x + length) - x;

					Body[] cloud = generateCloud(x, y, length, world);
					for(int index = 0; index < cloud.length; index++) {
						if(index == 0) {
							clouds[y][x+index] = new Cloud(cloud[index], Assets.CLOUD_LEFT);
						}
						else if(index == cloud.length - 1) {
							clouds[y][x+index] = new Cloud(cloud[index],rand.nextBoolean() ? Assets.CLOUD_RIGHT_1 : Assets.CLOUD_RIGHT_2);
						}
						else {
							clouds[y][x+index] = new Cloud(cloud[index], rand.nextBoolean() ? Assets.CLOUD_MIDDLE_1 : Assets.CLOUD_MIDDLE_2);
						}
					}

					x += length+2;
				}
			}
		}

		return new Level(clouds);
	}

	private static Body[] generateCloud(int x, int y, int width, World world) {
		Body[] cloud = new Body[width];

		for(int i = 0; i < width; i++) {
			cloud[i] = EntityManager.createBox((x + i) * Constants.TILE_SIZE + Constants.TILE_SIZE/2,y * Constants.TILE_SIZE + Constants.TILE_SIZE/2,
					Constants.TILE_SIZE, Constants.TILE_SIZE, true, world);
		}

		return cloud;
	}
}
