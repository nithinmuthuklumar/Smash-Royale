package com.mygdx.game.Sprites;

public class Link extends Fighter {
    public Link(){
        super("Link","Icons/LinkIcon.png");
        getForms().add(SwordLink.class);
        getForms().add(BombLink.class);
        getForms().add(GrassLink.class);

    }



}
