package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;
import java.util.List;

public class Main {

    //konstanter för spelet
    public static final int MAX_WIDTH_GAME = 40;//40
    public static final int MAX_HEIGHT_GAME = 20;//20
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

        //Startmeddelande
        String start = "Press any button to start the game ...";
        for (int i = 0; i < start.length(); i++) {
            terminal.putCharacter(start.charAt(i));
        }//End for-loop startmeddelande
        //-------------------------------------------------------

        //Main Game loop
        boolean runGame = true;

        while(runGame){ //Wait for a key to be pressed to start the game

            MovePlayer(player, terminal);
            GameLogic(player, treet); //lägg in do/while loop
            UpdateScreen(player, treet, terminal);



        }//End while - Main Game loop()

        //End Main Game loop

    }//End Main

    private static boolean GameLogic(Player player, Treet treet){

        if(player.x == treet.x && player.y == treet.y){
            System.out.println("You ate a treet!");
            treet.moveTreet();
        }


        return true;
    }//End GameLogic

    public static void UpdateScreen(Player player, Treet treet, Terminal terminal){

        terminal.clearScreen();//clear screen

        //Treet
        terminal.moveCursor(treet.x,treet.y);
        terminal.applyForegroundColor(255, 0, 0); //red, green, blue
        terminal.putCharacter('\u2588'); //full block
        //Player
        terminal.moveCursor(player.x,player.y);
        terminal.applyForegroundColor(40, 187, 70); //red, green, blue
        terminal.putCharacter('\u2588'); //full block

        terminal.moveCursor(0,0);

    }//end method() UpdateScreen

    private static void MovePlayer(Player player, Terminal terminal) throws InterruptedException {

        Key key;
        do{
            Thread.sleep(5);//Låt processorn vila lite.
            key =terminal.readInput();
        } while(key == null);

        switch (key.getKind()){
            case ArrowLeft:
                if (player.x > 0) {
                    player.x -= MOVEMENT_PER_CELL;
                }
                break;
            case ArrowUp:
                if(player.y > 0) {
                    player.y -= MOVEMENT_PER_CELL;
                }
                break;
            case ArrowRight:
                if(player.x < MAX_WIDTH_GAME) {
                    player.x += MOVEMENT_PER_CELL;
                }
                break;
            case ArrowDown:
                if(player.y < MAX_HEIGHT_GAME) {
                    player.y += MOVEMENT_PER_CELL;
                }
                break;
            default:
                player.x += 0;
                player.y += 0;
                //strunta i deafaultvärdet!
                //ev. defaultvärde
        }//End switch
        //System.out.println(key.getCharacter()+ " " + key.getKind());
    }//End MovePlayer

}//End Class Main
