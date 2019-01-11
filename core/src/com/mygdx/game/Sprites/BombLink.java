package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

public class BombLink extends Form {
    private static final int elixirCost=4;

    public static Texture icon = new Texture("Icons/bomb.png");

    public BombLink(int y,String dir) {
        super( y,5,0.14f,dir,100,50,50);
        addState("sprites/Link/bomb/" + dir, states.ATTACK);
        addState("sprites/Link/walk/" + dir, states.WALK);
        addState("sprites/Link/death", states.DEATH);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isNextFrame()){
            switch (getState()) {
                case WALK:
                    moveBy(getSpeed(), 0);
            }
        }




    }
    public static int getElixirCost(){
        return elixirCost;

    }
}
