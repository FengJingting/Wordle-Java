package comp1721.cwk1;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Game {
    private int gameNumber;
    private String target;
    // TODO: Implement constructor with String parameter
    public Game(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        LocalDate localDatenow = LocalDate.now();
        LocalDate localDatepass = LocalDate.of(2022,2,10);
        long between = ChronoUnit.DAYS.between(localDatepass, localDatenow);
        gameNumber = new Long(between).intValue();
        System.out.println("WORDLE "+gameNumber);
        WordList wordList =  new WordList(filename);
        target = wordList.getWord(gameNumber).substring(0, 5);
        System.out.println(target.length());
        System.out.println(target);
    };
    // TODO: Implement constructor with int and String parameters
    public Game(int num,String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        gameNumber = num;
        System.out.println("WORDLE "+gameNumber);
        WordList wordList =  new WordList(filename);
        target = wordList.getWord(gameNumber).substring(0, 5);
    };
    // TODO: Implement play() method
    public void play(){
        for(int i=1;i<=6;i++){
            Guess guess = new Guess(i);
            guess.readFromPlayer();
            System.out.println(guess.compareWith(target));
            if(guess.matches(target)){
                System.out.println("Well done!");
                return;
            }
        }
        System.out.println("Nope-Better luck next time!");
    };
    // TODO: Implement save() method, with a String parameter
    public void save(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        FileWriter fileWritter = new FileWriter(file.getName(),true);
//        fileWritter.write();
        fileWritter.close();
    };
}
