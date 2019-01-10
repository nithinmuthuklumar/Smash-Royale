package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class TxtButton extends TextButton {

    private Texture image;
    private boolean isClicked;
    public TxtButton(Texture img,Skin style,int x,int y,int w,int h){
        super("",style);
        setWidth(w);
        setHeight(h);
        setX(x-w/2);
        setY(y-h/2);
        image=img;
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isClicked=true;
            }
        });
    }
    public TxtButton(Texture img,Skin style,int x,int y){
        this(img,style,x,y,img.getWidth(),img.getHeight());
    }
    public TxtButton(String path,Skin style,int x,int y){
        this(new Texture(path),style,x,y);
    }
    @Override
    public void draw(Batch batch, float parentAlpha){

        super.draw(batch,parentAlpha);
        batch.draw(image,getX(),getY(),image.getWidth(),image.getHeight());

    }
    public boolean getClicked(){
        if(isClicked){
            isClicked=false;
            return true;
        }
        return false;
    }

}

