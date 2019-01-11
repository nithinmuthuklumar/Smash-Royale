package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

public class SwordLink extends Form {
    public static Texture icon=new Texture("Icons/sword.png");
    private static int elixirCost = 5;




    public SwordLink(int y,String dir){
        super(y,8,0.14f,dir,15,3000,20);

        addState("sprites/Link/sword/" + dir, states.ATTACK);
        addState("sprites/Link/run/" + dir, states.WALK);
        addState("sprites/Link/death", states.DEATH);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        if(isNextFrame()){

            switch (getState()) {
                case WALK: {
                    moveBy(getSpeed(), 0);
                    break;
                }
                case ATTACK: {
                    inflictDamage();
                    break;
                }
            }
        }
    }
    public static int getElixirCost(){
        return elixirCost;

    }
}
