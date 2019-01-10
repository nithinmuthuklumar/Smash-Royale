package com.mygdx.game.Sprites;

public class FireKirby extends Form {
    private static final int elixirCost=4;
    public FireKirby(int y,String dir) {
        super( y,3,0.16f,dir,125,100,25);
        addAction("sprites/Kirby/firekirby/attack/"+dir,actions.ATTACK);
        addAction("sprites/Kirby/firekirby/walk/"+dir,actions.WALK);
        addAction("sprites/Kirby/firekirby/death",actions.DEATH);
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
