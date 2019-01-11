package com.mygdx.game.Sprites;

public class FireKirby extends Form {
    private static final int elixirCost=4;
    public FireKirby(int y,String dir) {
        super( y,3,0.16f,dir,125,100,25);
        addState("sprites/Kirby/firekirby/attack/" + dir, states.ATTACK);
        addState("sprites/Kirby/firekirby/walk/" + dir, states.WALK);
        addState("sprites/Kirby/firekirby/death", states.DEATH);
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
