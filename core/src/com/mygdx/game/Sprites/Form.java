package com.mygdx.game.Sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static com.mygdx.game.SmashRoyale.screenH;
import static com.mygdx.game.SmashRoyale.screenW;

public class Form {
    public enum actions{
        WALK,ATTACK,DEATH
    }

    private HashMap<actions, Animation<Texture>> sprites;
    private Point pos;
    private actions action;
    private float stateTime;
    private int speed;
    private float frameDuration;
    private int frameNumber;
    private String dir;
    private int range;
    private Form target;
    private int dmg;
    private int hp;


    public Form(int y,int speed,float frameDuration,String dir,int range,int hp,int dmg){

        this.dir=dir;
        stateTime=0;
        this.frameDuration=frameDuration;
        frameNumber=0;
        this.range=range;
        this.hp=hp;
        this.dmg=dmg;




        sprites=new HashMap<actions, Animation<Texture>>();
        this.speed=dir.equals("right")?speed:-speed;
        action=actions.WALK;
        pos=new Point(getStartX(),y);

    }
    private int getStartX(){
        if(dir.equals("right")){
            return 0;
        }
        return screenW;

    }
    public void addAction(String path,actions a){
        File[] sortedFiles=new File(path).listFiles();
        Arrays.sort(sortedFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getPath().compareTo(o2.getPath());
            }
        });
        ArrayList<Texture> picArrayList=new ArrayList<Texture>();
        for(File f:sortedFiles){
            if(!f.getPath().equals(path+"/.DS_Store")){
                picArrayList.add(new Texture(f.getPath()));
            }
        }
        Texture[] frames=new Texture[picArrayList.size()];
        for(int i=0; i<picArrayList.size();i++){
            frames[i]=picArrayList.get(i);
        }
        getSprites().put(a, new Animation<Texture>(frameDuration,frames));

    }
    public void update(){

    }
    public void draw(SpriteBatch batch){

        stateTime+=Gdx.graphics.getDeltaTime();
        batch.draw(sprites.get(action).getKeyFrame(stateTime,true),pos.x,pos.y);


    }

    public String getDir() {
        return dir;
    }

    public boolean isNextFrame(){
        boolean nextFrame=frameNumber!=(int)(getStateTime() / getFrameDuration());
        frameNumber=(int)(stateTime / frameDuration);
        return nextFrame;

    }
    public void advance(){
        action=actions.WALK;
        target=null;
    }
    public void attack(Form v){
        action=actions.ATTACK;
        target=v;
    }

    public HashMap<actions, Animation<Texture>> getSprites() {
        return sprites;
    }

    public int getSpeed() {
        return speed;
    }

    public Point getPos() {
        return pos;
    }
    public float getStateTime(){
        return stateTime;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public actions getAction(){
        return action;
    }
    public static int getElixirCost(){
        return 0;

    }
    public boolean isAlive(){
        return hp>0;
    }
    public boolean inAttackRange(Form f){
        return Math.abs(f.getPos().x-getPos().x)<=range;
    }
    public void inflictDamage(){
        target.hp-=dmg;
    }

}
