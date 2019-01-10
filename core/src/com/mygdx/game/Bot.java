package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Sprites.Fighter;
import com.mygdx.game.Sprites.Form;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{
    private Random rand;
    public Bot(ArrayList<Fighter> fighters){

        super("Bot1",fighters.get(new Random().nextInt(fighters.size())),"left");
        rand=new Random();

    }
    @Override
    public void nextMove(ArrayList<Form> forms,Arena arena){
        //if enough elixir
        update();
        if(getElixir()>5){

            forms.add(getChosen().createForm(0,arena.getStartY(rand.nextInt(arena.getNumLanes())),getDirection()));
            useElixir(0);

        }



    }
}
