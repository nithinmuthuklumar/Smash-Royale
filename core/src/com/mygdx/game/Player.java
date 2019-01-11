package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Sprites.Fighter;

import java.lang.reflect.InvocationTargetException;

public class Player {
    private String name;
    private String direction;
    private Fighter chosen;
    private int curLane;
    private int elixir;
    private final int maxElixir=20;
    private float time;
    public Player(String name, Fighter chosen,String direction){
        this.name=name;
        this.chosen=chosen;
        this.direction=direction;
        time=0;
        curLane=0;
        elixir=0;
    }

    public String getName() {
        return name;
    }
    public String getDirection() {
        return direction;
    }
    public Fighter getChosen(){
        return chosen;
    }
    public void setChosen(Fighter f){
        chosen=f;
    }

    public void nextMove(Stage forms, Arena arena) {

    }
    public void update(){
        time+=Gdx.graphics.getDeltaTime();
        if(time>=1){
            increaseElixir();
            time=0;
        }
    }
    private void increaseElixir(){
        elixir= (elixir+1>maxElixir)?maxElixir:elixir+1;
    }
    public int getElixir(){
        return elixir;
    }
    public boolean enoughElixir(int n){

        try {
            if(elixir>=Integer.parseInt(getChosen().getForms().get(n).getMethod("getElixirCost").invoke(null).toString())){
                return true;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;

    }
    public int getMaxElixir(){
        return maxElixir;
    }
    public int getCurLane() {
        return curLane;
    }
    public void useElixir(int n){
        try {
            elixir-=Integer.parseInt(getChosen().getForms().get(n).getMethod("getElixirCost").invoke(null).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void setCurLane(int curLane) {
        this.curLane = curLane;
    }
}
