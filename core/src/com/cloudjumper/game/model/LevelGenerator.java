package com.cloudjumper.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.view.Assets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LevelGenerator {

	private static Random rand = new Random();

	public static Level generateLevel(World world) {
		List<Cloud> clouds = new ArrayList<>();
		boolean[][] cloudMap = new boolean[8][8];

		for(int y = Constants.LEVEL_SIZE - 2; y >= 0; y--) {
			for(int x = 0; x < Constants.LEVEL_SIZE - 2; x++) {
				if(cloudMap[y][x] == false && cloudMap[y+1][x] == false && (x == 0 || cloudMap[y+1][x-1] == false) && rand.nextBoolean()) {
					int length = rand.nextInt(3) + 2;
					length = Math.min(cloudMap.length, x + length) - x;

					Body cloudBody = generateCloud(x, y, length, world);
//					for(int index = 0; index < cloud.length; index++) {
//						if(index == 0) {
//							clouds[y][x+index] = new Cloud(cloud[index], Assets.CLOUD_LEFT);
//						}
//						else if(index == cloud.length - 1) {
//							clouds[y][x+index] = new Cloud(cloud[index],rand.nextBoolean() ? Assets.CLOUD_RIGHT_1 : Assets.CLOUD_RIGHT_2);
//						}
//						else {
//							clouds[y][x+index] = new Cloud(cloud[index], rand.nextBoolean() ? Assets.CLOUD_MIDDLE_1 : Assets.CLOUD_MIDDLE_2);
//						}
//					}

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

	private static Body generateCloud(int x, int y, int width, World world) {
		Body cloudBody = EntityManager.createBox(x * Constants.TILE_SIZE + width * Constants.TILE_SIZE/2,
				y * Constants.TILE_SIZE + Constants.TILE_SIZE/2 - Constants.TOP_OFFSET_CLOUD + Constants.BOTTOM_OFFSET_CLOUD,
				width * Constants.TILE_SIZE - Constants.LEFT_OFFSET_CLOUD - Constants.RIGHT_OFFSET_CLOUD,
				Constants.TILE_SIZE - Constants.TOP_OFFSET_CLOUD - Constants.BOTTOM_OFFSET_CLOUD, true, world);

		cloudBody.setUserData(new Rectangle(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, width * Constants.TILE_SIZE, Constants.TILE_SIZE));

		return cloudBody;
	}
}
