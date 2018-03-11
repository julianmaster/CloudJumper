package com.cloudjumper.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cloudjumper.game.Constants;

public class EntityManager {

	public static Body createBox(int x, int y, int width, int height, boolean isStatic, World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = isStatic ? BodyDef.BodyType.KinematicBody : BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
		bodyDef.fixedRotation = true;

		Body body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

		body.createFixture(shape, 1.0f);

		shape.dispose();

		body.setUserData(new Rectangle(x - width/2, y - height/2, width, height));

		return body;
	}
}
