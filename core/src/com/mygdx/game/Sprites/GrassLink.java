package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

public class GrassLink extends Form {
    public static Texture icon=new Texture("Icons/sword.png");
    public GrassLink(int y,String dir){
        super(y,0,0,dir,0,20,20);
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
}
