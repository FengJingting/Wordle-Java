// Main program for COMP1721 Coursework 1
// DO NOT CHANGE THIS!

package comp1721.cwk1;

import java.io.IOException;


public class Wordle {
  public static void main(String[] args) throws IOException {
        Game game;
        // check if it is accessible version or not
        int ifAC=0;
        if(args.length == 1){
            // if the command is "-a",open accessible version and Play today's game
          if(args[0].equals("-a")){
            ifAC = 1;
            game = new Game("data/words.txt");
          }else{
            // Player wants to specify the game
            game = new Game(Integer.parseInt(args[0]), "data/words.txt");
          }
        } else if (args.length == 2) {
          ifAC=1;
           // if the command is "-a",open accessible version and player wants to specify the game
          game = new Game(Integer.parseInt(args[1]), "data/words.txt");
        }else {
            //Play today's game
          game = new Game("data/words.txt");
        }
        // run ac version or normal version
        if(ifAC==1){
          game.playAC();
        }else{
          game.play();
        }
        game.save("build/lastgame.txt");
        game.history("build/history.txt");
      }
}
