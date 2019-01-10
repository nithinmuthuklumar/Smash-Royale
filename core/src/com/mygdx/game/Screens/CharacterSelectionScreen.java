package com.mygdx.game.Screens;
import java.math.*;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.SmashRoyale;
import com.mygdx.game.Sprites.Fighter;
import com.mygdx.game.TxtButton;

import static com.mygdx.game.SmashRoyale.*;
public class CharacterSelectionScreen implements Screen {
    private SmashRoyale game;
    private Stage stage;
    private Texture bkg=new Texture("backgrounds/selectionbkg.png");
    private Fighter curFighter;

    private TxtButton backBtn;
    private TxtButton selectBtn;
    private Label curFighterLabel;
    private ArrayList<TxtButton> iconBtns;
    public CharacterSelectionScreen(SmashRoyale game){
        this.game=game;
        stage=new Stage();
        curFighterLabel=new Label("current:",game.getStyle());
        curFighterLabel.setPosition(screenW-150,450);
        backBtn=new TxtButton("Icons/back.png",game.getStyle(),40,screenH-75);
        selectBtn=new TxtButton("text/select.png",game.getStyle(),screenW/2,screenH/2-75);
        stage.addActor(selectBtn);
        stage.addActor(backBtn);
        stage.addActor(curFighterLabel);
        iconBtns=new ArrayList<TxtButton>();
        for(int i=0;i<game.getFighters().size();i++){
            iconBtns.add(new TxtButton(game.getFighters().get(i).getIcon(),game.getStyle(),140+i*250,140,250,250));
        }
        for(TxtButton b:iconBtns){
            stage.addActor(b);
        }






    }
    public void update(){
        if(backBtn.getClicked()){
            game.setScreen(game.getMenu());
        }
        if(selectBtn.getClicked()){
            game.getPlayer().setChosen(curFighter);
        }
        for(int i=0;i<iconBtns.size();i++){
            if(iconBtns.get(i).getClicked()){
                curFighter=game.getFighters().get(i);
            }
        }

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        curFighter=game.getPlayer().getChosen();

    }

    @Override
    public void render(float delta) {
        update();
        game.getBatch().begin();
        game.getBatch().draw(bkg,0,0,screenW,screenH);
        //need to draw icons for the different types
        game.getBatch().draw(game.getPlayer().getChosen().getIcon(),screenW-200,screenH/2-75,
                (game.getPlayer().getChosen().getIcon().getWidth()*0.5f),
                (game.getPlayer().getChosen().getIcon().getWidth()*0.5f));
        game.getBatch().draw(curFighter.getIcon(),screenW/2-150,screenH/2,curFighter.getIcon().getWidth(),curFighter.getIcon().getHeight());
        game.getBatch().end();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
