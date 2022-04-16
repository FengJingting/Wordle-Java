package comp1721.cwk1;


import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Game {
    private int gameNumber;
    private String target;
    private String choseWordList;// record the user's guess word in one game
    private int winOrnot; //check if the user has win
    private int guessTime; //record the time the user guess until get the correct answer or the game is finish
    // TODO: Implement constructor with String parameter
    public Game(String filename) throws IOException {
        // check if the file exits, if not, throw an IOException
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        // get the date elapse from 2021,6,19
        LocalDate localDatenow = LocalDate.now();
        LocalDate localDatepass = LocalDate.of(2021,6,19);
        long between = ChronoUnit.DAYS.between(localDatepass, localDatenow);
        // make the gameNumber be the date elapse from 2021,6,19
        gameNumber = new Long(between).intValue();
        // print out the game information
        System.out.println("WORDLE "+gameNumber);
        // new wordlist,get the target word
        WordList wordList =  new WordList(filename);
        target = wordList.getWord(gameNumber).substring(0, 5);
//        System.out.println(target);
    };
    // TODO: Implement constructor with int and String parameters
    public Game(int num,String filename) throws IOException {
        // check if the file exits, if not, throw an IOException
        File file = new File(filename);
        if (!file.exists()){
            throw new IOException("File does not exist");
        }
        // specify the game number
        gameNumber = num;
        // print out the game information
        System.out.println("WORDLE "+gameNumber);
        // new wordlist,get the target word
        WordList wordList =  new WordList(filename);
        target = wordList.getWord(gameNumber).substring(0, 5);
//        System.out.println(target);
    };
    // TODO: Implement play() method
    public void play(){
        for(int i=1;i<=6;i++){
            Guess guess;
            // print out the guess result
            System.out.print("Enter guess("+i+"/6):");
            Scanner word = new Scanner(System.in);
            String words = word.next();
            if(words.isEmpty()) {
                guess = new Guess(i);
            }else{
                guess = new Guess(i,words);
            }
            choseWordList+=guess.compareWith(target);
            choseWordList+="\n";
            System.out.println(guess.compareWith(target));
            // check how many times user has get the correct correct word
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
        // if the user does not get the correct word
        winOrnot = 0;
        System.out.println("Nope-Better luck next time!");
    };
    // TODO: Implement playAC() method
    public void playAC(){
        for(int i=1;i<=6;i++){
            Guess guess = new Guess(i);
            // print out the guess result in ac version
            guess.readFromPlayer();
            choseWordList+=guess.compareWithAC(target);
            choseWordList+="\n";
            System.out.println(guess.compareWithAC(target));
            // check how many times user has get the correct correct word
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
        // if the user does not get the correct word
        winOrnot = 0;
        System.out.println("Nope-Better luck next time!");
    };
    // TODO: Implement save() method, with a String parameter
    public void save(String filename) throws IOException {
        // write the chosen word list into file
        File file = new File(filename);
        PrintWriter pw = new PrintWriter(new FileWriter(file)) ;
        pw.println(choseWordList);
        pw.flush();
        pw.close();
    };
    // TODO: Implement history() method, with a String parameter
    public void history(String filename)throws IOException {
        // check if the file exits, if not, throw an IOException
        File file = new File(filename);
        if(!file.exists()){
            throw new IOException("File does not exist");
        }
        // write the gameNumber,winOrnot,guessTime into history file
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        // write the gameNumber
        String tgameNumber = ""+gameNumber;
        bw.write(tgameNumber);
        bw.write("\n");
        // write the winOrnot
        String twinOrnot = ""+winOrnot;
        bw.write(twinOrnot);
        bw.write("\n");
        // write the guessTime
        String tguessTime = ""+guessTime;
        bw.write(tguessTime);
        bw.write("\n");
        bw.close();
        // read from the history file
        // create a map to store the guess words time
        Map map = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String contentLine = br.readLine();
        int totalGame = 0;
        double winNumber = 0;
        int winFlag = 0;
        // create an array to record the winning streak
        List streak = new ArrayList();
        while (contentLine != null) {
            totalGame += 1;
            // when read the game number
            if(totalGame%3==1){
                if(map.containsKey(contentLine)){
                    map.put(contentLine,(int)map.get(contentLine)+1 );
                }else{
                    map.put(contentLine, 1);
                }
            }
            // when read win or not
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

        // number of games played
        System.out.println("Number of game played: "+totalGame/3);
        // percentage of games that were wins
        double percentage = winNumber/(totalGame/3);
        System.out.print("Percentage of games that were wins: ");
        System.out.printf("%1.2f",percentage*100);
        System.out.print("% \n");
        // Length of the current winning streak:
        System.out.println("Length of the current winning streak: "+ streak.get(streak.size()-1));
        // Longest winning streak:
        System.out.println("Longest winning streak: "+ Collections.max(streak));
        // histogram of guess distribution
        Distribution chart = new Distribution(map);
        chart.run(map);
    };
}
