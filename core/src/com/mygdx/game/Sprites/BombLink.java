package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SmashRoyale;

import java.io.File;
import java.util.ArrayList;

public class BombLink extends Form {
    private static final int elixirCost=4;

    public static Texture icon=new Texture("Icons/bomb.png");;
    public BombLink(int y,String dir) {
        super( y,5,0.14f,dir,100,50,50);
        addAction("sprites/Link/bomb/"+ dir,actions.ATTACK);
        addAction("sprites/Link/walk/"+dir,actions.WALK);
        addAction("sprites/Link/death",actions.DEATH);
    }

    @Override
    public void update() {

        if(isNextFrame()){
            switch (getAction()){
                case WALK: getPos().translate(getSpeed(),0);
            }
        }




    }
    public static int getElixirCost(){
        return elixirCost;

    }
}
