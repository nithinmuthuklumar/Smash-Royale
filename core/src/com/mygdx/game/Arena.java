package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;

import static com.mygdx.game.SmashRoyale.screenW;

public class Arena {
    private Texture img;
    private int numLanes;
    private ArrayList<Rectangle> lanes=new ArrayList<Rectangle>();
    public Arena(String path){

        img=new Texture(path);
        lanes.add(new Rectangle(0,430,screenW,180));
        lanes.add(new Rectangle(0,610,screenW,180));
        lanes.add(new Rectangle(0,250,screenW,180));
        numLanes=3;





    }

    public Texture getImg() {
        return img;
    }
    public int getStartY(int lane){
        //if(n<numLanes){
        //    return lanePos.get(n);
        //}
        return (int)lanes.get(lane).getCenterY();
    }

    public int getNumLanes() {
        return numLanes;
    }


    public Rectangle getLane(int i){
        return lanes.get(i);
    }
}
