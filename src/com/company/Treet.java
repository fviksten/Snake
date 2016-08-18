package com.company;

import java.util.Random;

/**
 * Created by Administrator on 2016-08-18.
 */
public class Treet {

    public int x;
    public int y;
    public int min = 0;
    public int maxWidthGame;
    public int maxHeightGame;

    public Treet (int startPosX, int startPosY, int maxWidthGame, int maxHeightGame){

        this.x = startPosX;
        this.y = startPosY;
        this.maxWidthGame = maxWidthGame;
        this.maxHeightGame = maxHeightGame;

    }//End constructor

    public void moveTreet(){
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        Random rand = new Random();
        int randXvalue = rand.nextInt((maxWidthGame-0)+1)+0;
        int randYvalue = rand.nextInt((maxHeightGame-0)+1)+0;
        x = randXvalue;
        y = randYvalue;
    }//End moveTreet

}//End Treet Class
