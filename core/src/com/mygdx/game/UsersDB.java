package com.mygdx.game;

import java.io.*;
import java.util.Scanner;

public class UsersDB {
    private String path;
    private Scanner reader;
    private int entriesLength;
    private String[] entries;
    public UsersDB(){
        path="users.txt";
        try {
            setupReader();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public boolean contains(String name){
        for(String s:entries){
            if(s.equals(name)){
                return true;
            }
        }
        return false;


    }
    public void addUser(String name) {

        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(path));
            writer.write(Integer.toString(entriesLength+1));
            for(String s:entries){
                writer.append("\n"+s);

            }

            writer.append("\n"+name);
            writer.close();
            setupReader();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
    private void setupReader() throws IOException{
        reader=new Scanner(new BufferedReader(new FileReader(path)));
        entriesLength=Integer.parseInt(reader.nextLine());

        entries=new String[entriesLength];
        for(int i=0;i<entriesLength;i++){
            entries[i]=reader.nextLine();
        }


    }
}
