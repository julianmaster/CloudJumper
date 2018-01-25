package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;

public class EntityManager {

	public static Body createPlayer(World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(64 / Constants.PPM, 64 / Constants.PPM);
		bodyDef.fixedRotation = true;

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(5 / 2 / Constants.PPM, 8 / 2 / Constants.PPM);

		body.createFixture(shape, 1.0f);

		shape.dispose();

		return body;
	}
}
