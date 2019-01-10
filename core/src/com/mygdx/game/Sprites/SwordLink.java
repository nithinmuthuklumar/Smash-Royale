package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

public class SwordLink extends Form {
    public static Texture icon=new Texture("Icons/sword.png");
    private static int elixirCost=6;




    public SwordLink(int y,String dir){
        super(y,8,0.14f,dir,15,3000,20);

        addAction("sprites/Link/sword/"+ dir,actions.ATTACK);
        addAction("sprites/Link/run/"+dir,actions.WALK);
        addAction("sprites/Link/death",actions.DEATH);
    }
    @Override
    public void update() {
        if(isNextFrame()){

            switch (getAction()){
                case WALK: {
                    getPos().translate(getSpeed(),0);
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
