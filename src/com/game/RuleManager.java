package com.game;

public class RuleManager {
    public boolean breakShot = true;
    public boolean firstIn = false;
    public boolean pocketed8 = false;

    public boolean pocketed = false;
    public boolean pocketedCue = false;
    public boolean hitAnother = false;
    public boolean correctFirstHit = true;

    public RuleManager(){

    }

    public boolean checkForFoul(){
        return !breakShot && (!pocketed || !hitAnother || !correctFirstHit || pocketedCue);
    }

    public boolean changeTurn(){
        return checkForFoul() || !pocketed;
    }

    public boolean rebreak(){
        return false;
    }

    public void resetValues(){
        breakShot = false;
        pocketed = false;
        pocketedCue = false;
        hitAnother = false;
        correctFirstHit = false;

    }

    public boolean win(boolean p1Turn, int p1s, int p2s){
        if(p1Turn){
            if(p1s == 0 && pocketed8)
                return true;
        }
        else{
            if(p2s == 0 && pocketed8)
                return true;
        }
        return false;
    }

    public boolean lose(boolean p1Turn, int p1s, int p2s){
        if(!breakShot){
            if(p1Turn){
                if(p1s != 0 && pocketed8)
                    return true;
            }
            else{
                if(p2s != 0 && pocketed8)
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "RuleManager{" +
                "breakShot=" + breakShot +
                ", firstIn=" + firstIn +
                ", pocketed=" + pocketed +
                ", pocketed8=" + pocketed8 +
                ", pocketedCue=" + pocketedCue +
                ", hitAnother=" + hitAnother +
                ", correctFirstHit=" + correctFirstHit +
                '}';
    }
}
