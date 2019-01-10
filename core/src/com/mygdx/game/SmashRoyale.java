package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Screens.BattleScreen;
import com.mygdx.game.Screens.CharacterSelectionScreen;
import com.mygdx.game.Screens.LoginScreen;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Sprites.*;

import java.util.ArrayList;

public class SmashRoyale extends Game {

    public static final int screenW=1000;
    public static final int screenH=800;
    private Skin style;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private MenuScreen menu;
    private BattleScreen battle;
    private CharacterSelectionScreen characterSelection;
    private LoginScreen login;
    private ArrayList<Fighter> fighters;
    private ArrayList<Arena> arenas;
    private Arena arena;
    private Player player;
    private Player enemy;
    private UsersDB usersDB;


    @Override
    public void create () {
        fighters=new ArrayList<Fighter>();
        Fighter link=new Fighter("Link","Icons/LinkIcon.png");
        link.getForms().add(SwordLink.class);
        link.getForms().add(BombLink.class);
        link.getForms().add(GrassLink.class);
        fighters.add(link);
        Fighter kirby= new Fighter("Kirby","Icons/KirbyIcon.png");
        kirby.getForms().add(FireKirby.class);
        kirby.getForms().add(FlyingKirby.class);


        fighters.add(kirby);

        usersDB=new UsersDB();
        camera=new OrthographicCamera();
        viewport=new FitViewport(screenW,screenH,camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        batch=new SpriteBatch();
        style=createSkin();
        login=new LoginScreen(this);
        menu=new MenuScreen(this);
        battle=new BattleScreen(this);
        characterSelection=new CharacterSelectionScreen(this);
        arena=new Arena("arenas/3 lane.png");//temporary

        setScreen(login);




    }
    @Override
    public void render(){
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        super.render();
    }
    @Override
    public void resize(int width,int height){
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }
    @Override
    public void dispose(){
        batch.dispose();
    }
    private static Skin createSkin(){
        AssetManager manager=new AssetManager();
        manager.load("neon/skin/neon-ui.json",Skin.class);
        manager.finishLoading();
        return manager.get("neon/skin/neon-ui.json");
    }

    public Arena getArena() {
        return arena;
    }

    public Skin getStyle() {
        return style;
    }

    public SpriteBatch getBatch() { return batch; }

    public Viewport getViewport() {
        return viewport;
    }

    public MenuScreen getMenu() {
        return menu;
    }

    public BattleScreen getBattle() {
        return battle;
    }

    public CharacterSelectionScreen getCharacterSelection() {
        return characterSelection;
    }

    public ArrayList<Fighter> getFighters() {
        return fighters;
    }
    public UsersDB getUsersDB(){
        return usersDB;
    }
    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player p){
        player=p;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player p){ enemy=p; }
}
