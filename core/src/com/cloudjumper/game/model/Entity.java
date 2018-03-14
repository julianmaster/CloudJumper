package com.cloudjumper.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public abstract class Entity {

	public abstract void render(float delta, Batch batch, AssetManager manager);
}
