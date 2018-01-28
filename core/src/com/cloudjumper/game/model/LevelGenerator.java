package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;

import java.util.Random;

public class LevelGenerator {

	private static Random rand = new Random();

	public static Level generateLevel(World world) {
		Body[][] clouds = new Body[8][8];

		for(int y = clouds.length - 2; y >= 0; y--) {
			for(int x = 0; x < clouds.length - 2; x++) {
				if(clouds[y][x] == null && clouds[y+1][x] == null && (x == 0 || clouds[y+1][x-1] == null) && rand.nextBoolean()) {
					int length = rand.nextInt(3) + 2;
					length = Math.min(clouds.length, x + length) - x;

					Body[] cloud = generateCloud(x, y, length, world);
					for(int index = 0; index < cloud.length; index++) {
						clouds[y][x+index] = cloud[index];
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
