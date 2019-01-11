package com.mygdx.game.Sprites;

import com.badlogic.gdx.scenes.scene2d.Action;

public class DeathAction extends Action {
    @Override
    public boolean act(float delta) {
        getActor().remove();
        return true;

    }
}
