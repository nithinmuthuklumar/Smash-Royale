package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.Player;
import com.mygdx.game.SmashRoyale;
import com.mygdx.game.TxtButton;

import static com.mygdx.game.SmashRoyale.screenH;
import static com.mygdx.game.SmashRoyale.screenW;

public class LoginScreen implements Screen {
    private SmashRoyale game;


    private TxtButton loginBtn;
    private TxtButton newProfileBtn;
    private TxtButton confirmBtn;
    private TxtButton backBtn;
    private TextField nameField;
    private Stage stage;
    private Texture title;
    private State state=State.MENU;
    private Player player;

    public enum State{
        LOGIN,NEWPROFILE,MENU
    }
    public LoginScreen(SmashRoyale game){

        //new SmashRoyaleDB();
        this.game=game;
        title=new Texture("text/SmashRoyale.png");
        newProfileBtn=new TxtButton("text/newProfile.png",game.getStyle(),200,200);
        loginBtn=new TxtButton("text/login.png",game.getStyle(),400,400);
        confirmBtn=new TxtButton("text/confirm.png",game.getStyle(),500,300);
        stage=new Stage(game.getViewport());
        nameField=new TextField("",game.getStyle());
        nameField.setPosition(screenW / 2f, screenH / 2f);
        backBtn=new TxtButton("Icons/back.png",game.getStyle(),50,screenH-100);

        nameField.setTextFieldFilter(new TextField.TextFieldFilter() {
            @Override
            public boolean acceptChar(TextField textField, char c) {
                return Character.isLetter(c);
            }
        });
        nameField.setMessageText("Username");






    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(newProfileBtn);
        stage.addActor(loginBtn);

    }
    public void update() {
        if(newProfileBtn.getClicked()){
            state=State.NEWPROFILE;
            stage.clear();
            stage.addActor(nameField);
            stage.addActor(backBtn);
            stage.addActor(confirmBtn);

        }
        if(loginBtn.getClicked()){
            state=State.LOGIN;
            stage.clear();
            stage.addActor(nameField);
            stage.addActor(backBtn);
            stage.addActor(confirmBtn);

        }
        if(confirmBtn.getClicked()){
            String name=nameField.getText();

            if(name.length()==0){
                return;
            }
            switch(state){
                case NEWPROFILE:
                    if(!game.getUsersDB().contains(name)){
                        game.getUsersDB().addUser(name);
                        player=new Player(name,game.getFighters().get(0),"right");
                    }
                    break;
                case LOGIN:
                    if(game.getUsersDB().contains(name)){
                        player=new Player(name,game.getFighters().get(0),"right");
                    }
                    break;
            }

            if(player!=null){
                game.setScreen(game.getMenu());
                game.setPlayer(player);
            }
        }
        if(backBtn.getClicked()){
            state=State.MENU;
            stage.clear();
            stage.addActor(loginBtn);
            stage.addActor(newProfileBtn);
            nameField.clearSelection();
        }
    }
    @Override
    public void render(float delta) {
        update();
        game.getBatch().begin();
        game.getBatch().draw(title,300,screenH-100);
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
