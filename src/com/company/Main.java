package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //konstanter för spelet
    public static final int MAX_WIDTH_GAME = 20;//40
    public static final int MAX_HEIGHT_GAME = 10;//20
    public static final int MOVEMENT_PER_CELL = 1;


    public static void main(String[] args) throws InterruptedException {
	    // write your code here
        System.out.println("Snake R'n Roll!");

        //Setup Terminal
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        //creates player
        Player player = new Player(5,5);
        //create first treet
        Treet treet = new Treet(10,10,MAX_WIDTH_GAME,MAX_HEIGHT_GAME);

        //create lista som håller svansen
        List<Tail> theTail = new ArrayList<>();
        //skapa första svansbiten lika som spelaren och lägg till i svanslistan.
        Tail firstTail = new Tail(player.x, player.y);
        theTail.add(firstTail);

        //create object for tailhelper
        TailHelper tailhelp = new TailHelper(theTail);

        //Startmeddelande
        String start = "Press any button to start the game ...";
        for (int i = 0; i < start.length(); i++) {
            terminal.putCharacter(start.charAt(i));
        }//End for-loop startmeddelande
        //-------------------------------------------------------

        //Main Game loop
        boolean runGame = true;

        while(runGame){ //Wait for a key to be pressed to start the game

            //ta ut x och y värden från sista svansbiten()
            int lastTailX = tailhelp.getLastX();
            int lastTailY = tailhelp.getLastY();

            MovePlayer(player,theTail,lastTailX,lastTailY, terminal);
            GameLogic(player, treet, theTail, lastTailX, lastTailY); //lägg in do/while loop
            UpdateScreen(player, treet, theTail, terminal);



        }//End while - Main Game loop()

        //End Main Game loop

    }//End Main

    private static boolean GameLogic(Player player, Treet treet, List<Tail>theTail, int lastX, int lastY){

        if(player.x == treet.x && player.y == treet.y){
            System.out.println("You ate a treet!");
            treet.moveTreet();
            Tail tail = new Tail(lastX,lastY);
            theTail.add(tail);
        }


        return true;
    }//End GameLogic

    public static void UpdateScreen(Player player, Treet treet, List<Tail>theTail, Terminal terminal){

        terminal.clearScreen();//clear screen

        //Treet
        terminal.moveCursor(treet.x,treet.y);
        terminal.applyForegroundColor(255, 0, 0); //red, green, blue
        terminal.putCharacter('\u2588'); //full block
        //Player
        terminal.moveCursor(player.x,player.y);
        terminal.applyForegroundColor(40, 187, 70); //red, green, blue
        terminal.putCharacter('\u2588'); //full block

        //theTail
        for (int i = 0; i < theTail.size(); i++) {

            terminal.moveCursor(theTail.get(i).x,theTail.get(i).y);
            terminal.applyForegroundColor(40, 187, 70); //red, green, blue
            terminal.putCharacter('\u2588'); //full block
            terminal.moveCursor(0,0);

        }//End for-loop in updateScreen

        terminal.moveCursor(0,0);

    }//end method() UpdateScreen

    private static void MovePlayer(Player player, List<Tail>theTail,int lastX,int lastY, Terminal terminal) throws InterruptedException {

        Thread.sleep(250);

        Key key;
        key =terminal.readInput();
        if(key == null){

            player.directionX = player.directionX;
            player.directionX = player.directionX;

        }else {




            switch (key.getKind()) {
                case ArrowLeft:
                    player.directionX = -1;
                    player.directionY = 0;
                    break;
                case ArrowUp:
                    player.directionX = 0;
                    player.directionY = -1;
                    break;
                case ArrowRight:
                    player.directionX = 1;
                    player.directionY = 0;
                    break;
                case ArrowDown:
                    player.directionX =0;
                    player.directionY = 1;
                    break;
                default:
                    player.x += 0;
                    player.y += 0;
                    //strunta i deafaultvärdet!
                    //ev. defaultvärde
            }//End switch
            //System.out.println(key.getCharacter()+ " " + key.getKind());
        }//end else

        player.x += player.directionX;
        player.y += player.directionY;

        //får varandras värden.
        //Börja med att änden får värdet framför
        for (int i = theTail.size()-1; i > 0; i--) {

            theTail.get(i).x = theTail.get(i-1).x;
            theTail.get(i).y = theTail.get(i-1).y;


        }//End for-loop

        //Första svansbiten får players koordinater.
        theTail.get(0).x = player.x;
        theTail.get(0).y = player.y;




    }//End MovePlayer Method.

}//End Class Main
