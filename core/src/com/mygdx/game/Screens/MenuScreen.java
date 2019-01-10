package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Bot;
import com.mygdx.game.SmashRoyale;
import com.mygdx.game.TxtButton;
import static com.mygdx.game.SmashRoyale.*;

public class MenuScreen implements Screen {
    private Stage stage;

    private SmashRoyale game;
    private Texture bkg=new Texture("backgrounds/menubkg.jpg");
    private TxtButton playBtn;
    private TxtButton optionBtn;
    private TxtButton characterSelectionBtn;
    public MenuScreen(SmashRoyale game){
        playBtn=new TxtButton("text/battle.png",game.getStyle(),screenW/2,screenH/2);
        optionBtn=new TxtButton("text/options.png",game.getStyle(),200,screenH-100);
        characterSelectionBtn=new TxtButton("text/CharacterSelection.png",game.getStyle(),200,600);
        stage=new Stage(game.getViewport());
        stage.addActor(optionBtn);
        stage.addActor(characterSelectionBtn);
        stage.addActor(playBtn);
        this.game=game;

    }
    public void update(){
        if(characterSelectionBtn.getClicked()){
            game.setScreen(game.getCharacterSelection());
        }
        if(playBtn.getClicked()){
            game.setEnemy(new Bot(game.getFighters()));
            game.setScreen(game.getBattle());
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        update();
        game.getBatch().begin();
        game.getBatch().draw(bkg,0,0);
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
