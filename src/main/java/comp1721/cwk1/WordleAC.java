//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package comp1721.cwk1;

import java.io.IOException;

public class WordleAC {
    public WordleAC() {
    }

    public static void main(String[] args) throws IOException {
        Game game;
        if (args.length == 1) {
            if(args[0]=="-a"){
                game = new Game(Integer.parseInt(args[0]), "data/words.txt");
            }
            // Player wants to specify the game
            game = new Game(Integer.parseInt(args[0]), "data/words.txt");
        }else if (args.length == 1) {
            // Player wants to specify the game
            game = new Game(Integer.parseInt(args[0]), "data/words.txt");
        }
        else {
            // Play today's game
            game = new Game("data/words.txt");
        }
        game.playAC();
        game.save("build/lastgame.txt");
        game.history("build/history.txt");
    }
}
