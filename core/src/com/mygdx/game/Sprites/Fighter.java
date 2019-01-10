package com.mygdx.game.Sprites;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Fighter {
    private String name;
    private Texture icon;
    ArrayList<Class<? extends Form>> forms;


    public Fighter(String n,String picPath){
        forms=new ArrayList<Class<? extends Form>>();
        name=n;
        icon=new Texture(picPath);
    }
    public Form createForm(int n,int y,String dir){
        try {
            return forms.get(n).getConstructor(int.class,String.class).newInstance(y,dir);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public Texture getIcon() {
        return icon;
    }
    public ArrayList<Class<? extends Form>> getForms(){
        return forms;
    }
    public Texture getFormsIcon(int i){
        try {
            return (Texture)getForms().get(i).getField("icon").get(null);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;

    }

}
