package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.SmashRoyale;
import com.mygdx.game.Sprites.Form;
import com.mygdx.game.TxtButton;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static com.mygdx.game.SmashRoyale.screenH;
import static com.mygdx.game.SmashRoyale.screenW;

public class BattleScreen implements Screen, InputProcessor {
    private InputMultiplexer multiplexer=new InputMultiplexer();
    private Stage stage=new Stage();
    private SmashRoyale game;
    private ArrayList<Form> onscreen=new ArrayList<Form>();
    private ShapeRenderer shapeRenderer=new ShapeRenderer();
    private ArrayList<TxtButton> formButtons=new ArrayList<TxtButton>();


    public BattleScreen(SmashRoyale game){
        this.game=game;
        Gdx.gl.glLineWidth(5);
        shapeRenderer.setColor(0,0,0,1);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
    }
    public void update(){
        game.getEnemy().nextMove(onscreen,game.getArena());
        game.getPlayer().update();
        for(int i=0;i<formButtons.size();i++){

            if(formButtons.get(i).getClicked()&&game.getPlayer().enoughElixir(i)){
                onscreen.add(game.getPlayer().getChosen().createForm(i,game.getArena().getStartY(game.getPlayer().getCurLane()),game.getPlayer().getDirection()));
                game.getPlayer().useElixir(i);
            }
        }
        //looping backward so that if an item is deleted it does not mess up the index
        for(int i=onscreen.size()-1;i>=0;i--){
            Form f=onscreen.get(i);
            f.update();
            if(f.getPos().x<0||f.getPos().x>screenW||!f.isAlive()){
                onscreen.remove(i);
            }
        }
        //instead of sorting to get closest enemy we take the first enemy in the proper lane since it was added in that order

        for(Form a:onscreen){
            boolean change=false;
            for(Form v:onscreen){
                if(a!=v&&a.getPos().y==v.getPos().y&&a.inAttackRange(v)&&!a.getDir().equals(v.getDir())){
                    change=true;
                    a.attack(v);
                }
            }
            if(!change){
                a.advance();
            }

        }
    }
    @Override
    public void show(){
        game.getPlayer().setCurLane(0);
        Gdx.input.setInputProcessor(multiplexer);

        for(int i=0;i<game.getPlayer().getChosen().getForms().size();i++){
            formButtons.add(new TxtButton(game.getPlayer().getChosen().getFormsIcon(i), game.getStyle(), 100 + 100 * i, 100, 100, 100));
        }
        for(TxtButton t: formButtons){
            stage.addActor(t);
        }

    }

    @Override
    public void render(float delta) {
        update();


        shapeRenderer.setProjectionMatrix(game.getBatch().getProjectionMatrix());
        game.getBatch().begin();
        game.getBatch().draw(game.getArena().getImg(),0,0);
        for(Form f:onscreen){
            f.draw(game.getBatch());
        }

        game.getBatch().end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(int i=0;i<game.getArena().getNumLanes();i++){
            if(game.getArena().getLane(i).contains(Gdx.input.getX(), mouseToScreenY(Gdx.input.getY()))||i==game.getPlayer().getCurLane()){

                shapeRenderer.rect((int)game.getArena().getLane(i).getX(),
                        (int)game.getArena().getLane(i).getY(),
                        (int)game.getArena().getLane(i).getWidth(),
                        (int)game.getArena().getLane(i).getHeight());
            }
        }
        shapeRenderer.end();
        stage.draw();

    }
    public int mouseToScreenY(int y){
        return screenH-y;

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
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for(int i=0;i<game.getArena().getNumLanes();i++){
            if(game.getArena().getLane(i).contains(screenX,mouseToScreenY(screenY))){
                game.getPlayer().setCurLane(i);
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
