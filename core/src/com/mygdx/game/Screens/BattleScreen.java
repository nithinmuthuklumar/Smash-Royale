package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.SmashRoyale;
import com.mygdx.game.TxtButton;

import java.util.ArrayList;

import static com.mygdx.game.SmashRoyale.screenH;

public class BattleScreen extends InputAdapter implements Screen {
    private InputMultiplexer multiplexer=new InputMultiplexer();
    private Stage ui = new Stage();
    private SmashRoyale game;
    private Stage entities = new Stage();
    private ShapeRenderer shapeRenderer=new ShapeRenderer();
    private ArrayList<TxtButton> formButtons=new ArrayList<TxtButton>();


    public BattleScreen(SmashRoyale game){
        this.game=game;
        Gdx.gl.glLineWidth(5);
        shapeRenderer.setColor(0,0,0,1);
        multiplexer.addProcessor(ui);
        multiplexer.addProcessor(this);
    }
    public void update(){
        game.getEnemy().nextMove(entities, game.getArena());
        game.getPlayer().update();
        for(int i=0;i<formButtons.size();i++){

            if(formButtons.get(i).getClicked()&&game.getPlayer().enoughElixir(i)){
                entities.addActor(game.getPlayer().getChosen().createForm(i, game.getArena().getStartY(game.getPlayer().getCurLane()), game.getPlayer().getDirection()));
                game.getPlayer().useElixir(i);
            }

        }

        //looping backward so that if an item is deleted it does not mess up the index
        /*for(int i = entities.getActors().size-1; i>=0; i--){
            Form f= (Form)entities.getActors().get(i);
            f.update();
            if(f.getPos().x<0||f.getPos().x>screenW||!f.isAlive()){
                entities.remove(i);
            }
        }*/
        //instead of sorting to get closest enemy we take the first enemy in the proper lane since it was added in that order
        /*
        for(Form a: entities){
            boolean change=false;
            for(Form v: entities){
                if(a!=v&&a.getPos().y==v.getPos().y&&a.inAttackRange(v)&&!a.getDir().equals(v.getDir())){
                    change=true;
                    a.attack(v);
                }
            }
            if(!change){
                a.advance();
            }

        }
        */
    }
    @Override
    public void show(){
        game.getPlayer().setCurLane(0);
        Gdx.input.setInputProcessor(multiplexer);

        for(int i=0;i<game.getPlayer().getChosen().getForms().size();i++){
            formButtons.add(new TxtButton(game.getPlayer().getChosen().getFormsIcon(i), game.getStyle(), 100 + 100 * i, 100, 100, 100));
        }
        for(TxtButton t: formButtons){
            ui.addActor(t);
        }

    }

    @Override
    public void render(float delta) {
        update();


        shapeRenderer.setProjectionMatrix(game.getBatch().getProjectionMatrix());
        game.getBatch().begin();
        game.getBatch().draw(game.getArena().getImg(),0,0);
        game.getBatch().end();
        entities.act();
        entities.draw();
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

        ui.draw();

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
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for(int i=0;i<game.getArena().getNumLanes();i++){
            if(game.getArena().getLane(i).contains(screenX,mouseToScreenY(screenY))){
                game.getPlayer().setCurLane(i);
                return true;
            }
        }
        return false;

    }

}
