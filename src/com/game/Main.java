package com.game;

public class Main {
    public static void main(String args[]){
        while(true){
            try{
                Game.main(args);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
