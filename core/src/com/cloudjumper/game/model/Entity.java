package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public class Entity {
	private Body body;
	private Assets asset;

	public Entity(Body body, Assets asset) {
		this.body = body;
		this.asset = asset;
	}

	public Assets getAsset() {
		return asset;
	}

	public Body getBody() {
		return body;
	}
}
