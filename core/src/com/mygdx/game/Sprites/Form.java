package com.mygdx.game.Sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static com.mygdx.game.SmashRoyale.screenW;

public class Form extends Actor {
    private HashMap<states, Animation<Texture>> sprites;
    private states state;
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


        sprites = new HashMap<states, Animation<Texture>>();
        this.speed=dir.equals("right")?speed:-speed;
        state = states.WALK;
        setX(getStartX());
        setY(y);

    }

    public void addState(String path, states a) {
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

    private int getStartX() {
        if (dir.equals("right")) {
            return 0;
        }
        return screenW;

    }

    @Override
    public void act(float delta) {

        //loop backwards so that the index isn't affected by removal
        try {
            for (int i = getStage().getActors().size - 1; i >= 0; i--) {
                Form f = (Form) getStage().getActors().get(i);
                if (!f.isAlive()) {
                    System.out.println(true);
                    //deathActions.add whatever will be here later
                    f.remove();
                    return;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //get a target if its possible

        boolean change = false;
        for (Actor a : getStage().getActors()) {
            Form f = (Form) a;
            //the earlier the form was added, the closer it is

            if (this != f && getY() == f.getY() && inAttackRange(f) && !getDir().equals(f.getDir())) {
                change = true;
                setTarget(f);
                break;
            }

        }
        if (!change) {
            advance();
        }


        stateTime += delta;
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(sprites.get(state).getKeyFrame(stateTime, true), getX(), getY());
    }

    public boolean isNextFrame(){
        boolean nextFrame = frameNumber != (int) (getstateTime() / getFrameDuration());
        frameNumber=(int)(stateTime / frameDuration);
        return nextFrame;

    }

    public String getDir() {
        return dir;
    }

    private void advance() {
        state = states.WALK;
        target=null;
    }

    private void setTarget(Form v) {
        state = states.ATTACK;
        target=v;
    }

    public HashMap<states, Animation<Texture>> getSprites() {
        return sprites;
    }

    public float getstateTime() {
        return stateTime;
    }

    public int getSpeed() {
        return speed;
    }

    public states getState() {
        return state;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public boolean inAttackRange(Form f) {
        return Math.abs(f.getX() - getX()) <= range;
    }
    public static int getElixirCost(){
        return 0;

    }
    public boolean isAlive(){
        return hp>0;
    }

    public enum states {
        WALK, ATTACK, DEATH
    }
    public void inflictDamage(){
        target.hp-=dmg;
    }

}
