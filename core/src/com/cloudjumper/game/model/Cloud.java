package com.cloudjumper.game.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.cloudjumper.game.view.Assets;

public class Cloud extends Entity {

    public Cloud(Body body, Assets asset) {
        super(body, asset);
    }
}
