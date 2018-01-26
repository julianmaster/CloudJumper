package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;

public class EntityManager {

	public static Body createPlayer(World world) {
		return null;
	}

	public static Body createPlatform(World world) {
		return null;
	}

	public static Body createBox(int x, int y, int width, int height, boolean isStatic, World world) {
		BodyDef bodyDef = new BodyDef();
		if(isStatic) {
			bodyDef.type = BodyDef.BodyType.KinematicBody;
		}
		else {
			bodyDef.type = BodyDef.BodyType.DynamicBody;
		}
		bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
		bodyDef.fixedRotation = true;

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

		body.createFixture(shape, 1.0f);

		shape.dispose();

		return body;
	}
}
