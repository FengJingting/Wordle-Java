package comp1721.cwk1;


import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;


import static java.util.Collections.*;

public class Game {
    private int gameNumber;
    private String target;
    private String choseWordList;
    private int winOrnot;
    private int guessTime;
    // TODO: Implement constructor with String parameter
    public Game(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        LocalDate localDatenow = LocalDate.now();
        LocalDate localDatepass = LocalDate.of(2021,6,19);
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
        System.out.println(target.length());
        System.out.println(target);
    };
    // TODO: Implement play() method
    public void play(){
        for(int i=1;i<=6;i++){
            Guess guess = new Guess(i);
            guess.readFromPlayer();
            choseWordList+=guess.compareWith(target);
            choseWordList+="\n";
            System.out.println(guess.compareWith(target));
            if(guess.matches(target)){
                if(i==1){
                    System.out.println("Superb - Got it in one!");
                }else if(i==6){
                    System.out.println("That was a close call!");
                }else{
                    System.out.println("Well done!");
                }
                guessTime = i;
                winOrnot = 1;
                return;
            }
        }
        winOrnot = 0;
        System.out.println("Nope-Better luck next time!");
    };
    // TODO: Implement save() method, with a String parameter
    public void save(String filename) throws IOException {
        File file = new File(filename);
        PrintWriter pw = new PrintWriter(new FileWriter(file)) ;
        pw.println(choseWordList);
        pw.flush();
        pw.close();
    };

    public void history(String filename)throws IOException {
        File file = new File(filename);
        if(!file.exists()){
            throw new IOException("File does not exist");
        }
        // write into
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        String tgameNumber = ""+gameNumber;
        bw.write(tgameNumber);
        bw.write("\n");
        String twinOrnot = ""+winOrnot;
        bw.write(twinOrnot);
        bw.write("\n");
        String tguessTime = ""+guessTime;
        bw.write(tguessTime);
        bw.write("\n");
        bw.close();
        // read from
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String contentLine = br.readLine();
        int totalGame = 0;
        double winNumber = 0;
        int winFlag = 0;
        List streak = new ArrayList();
        while (contentLine != null) {
            totalGame += 1;
            if(totalGame%3==2){
                if(contentLine.equals("1")){
                    winNumber += 1;
                    winFlag += 1;
                } else{
                streak.add(winFlag);
                winFlag = 0;
                }
            }
            contentLine = br.readLine();
        }
        bw.close();
        streak.add(winFlag);
        System.out.println("Number of game played: "+totalGame/3);
        double percentage = winNumber/(totalGame/3);
        System.out.print("Percentage of games that were wins: ");
        System.out.printf("%1.2f",percentage*100);
        System.out.print("% \n");
        System.out.println("Length of the current winning streak: "+ streak.get(streak.size()-1));
        System.out.println("Longest winning streak: "+ Collections.max(streak));
    };
}
