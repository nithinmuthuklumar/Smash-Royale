package com.mygdx.game.Sprites;

import com.badlogic.gdx.utils.DelayedRemovalArray;

import java.util.ArrayList;

public class FormGroup{
    ArrayList<Form> group;
    public FormGroup(){
        group=new ArrayList<Form>();
    }
    public void removeDead(){
        for(Form f:group){
            if(!f.isAlive()){


            }
        }


    }

    public ArrayList<Form> getGroup() {
        return group;
    }
}
