package com.cloudjumper.game.model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.Constants;
import com.cloudjumper.game.view.Assets;

import java.util.List;

public class Cloud extends Entity {
    private Body body;
    private List<Assets> assets;

    public Cloud(Body body, List<Assets> assets) {
        this.body = body;
        this.assets = assets;
    }

    @Override
    public void render(float delta, Batch batch, AssetManager assetManager) {
        Rectangle shape = (Rectangle)body.getUserData();

        for(int i = 0; i < shape.width / Constants.TILE_SIZE; i++) {
            batch.draw(assetManager.get(assets.get(i).filename, Texture.class), shape.x + i * Constants.TILE_SIZE, shape.y);
        }
    }

    public Body getBody() {
        return body;
    }
}
