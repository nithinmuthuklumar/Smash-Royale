package com.mygdx.game.Sprites;
public class Kirby extends Fighter {
    public Kirby(){
        super("Kirby","Icons/KirbyIcon.png");
        getForms().add(FireKirby.class);
        getForms().add(FlyingKirby.class);

    }
}



