package comp1721.cwk1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Guess {
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);
  private int guessNumber;
  private String chosenWord;
  // TODO: Implement constructor with int parameter
  public Guess(int num){
    // if the num is less than 1 or greater than 6,throw an GameException
    if(1<=num && num<=6){
      guessNumber = num;
    }else{
      throw new GameException("Invalid");
    }
  };
  // TODO: Implement constructor with int and String parameters
  public Guess(int num,String word){
    // if the num is less than 1 or greater than 6,throw an GameException
    if(1<=num && num<=6){
      guessNumber = num;
    }else{
      throw new GameException("Invalid  guessNumber");
    }

    // if the word length does not equal to 5,throw an GameException
    if(word.length()==5){
      for(int i=0;i<5;i++){
        if( (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') || (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')){
          continue;
        }else{
          throw new GameException("Invalid  chosenWord!");
        }
      }
      chosenWord = word.toUpperCase(Locale.ROOT);
    }else{
      throw new GameException("Invalid chosenWord!\n");
    }
  };
  // TODO: Implement getGuessNumber(), returning an int
  public int getGuessNumber(){
    return guessNumber;
  };
  // TODO: Implement getChosenWord(), returning a String
  public String getChosenWord(){
    return chosenWord;
  };
  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){
    System.out.print("Enter guess("+guessNumber+"/6):");
    String word =  INPUT.next();
    if(word.length()==5){
      for(int i=0;i<5;i++){
        if( (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') || (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')){
          continue;
        }else{
          throw new GameException("Invalid  chosenWord!");
        }
      }
      chosenWord = word.toUpperCase(Locale.ROOT);
    }else{
      throw new GameException("Invalid chosenWord!\n");
    }
  };
  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target) {
    String result = "";
    for (int i = 0; i <= 4; i++) {
      char ct = target.charAt(i);
      char cc = chosenWord.charAt(i);
      if (cc == ct) {
        // if the char is correct
        result +="\033[30;102m ";
        result += cc;
        result +=" \033[0m";
      } else if (target.indexOf(cc) == -1) {
        // if the char is not correct
        result +="\033[30;107m ";
        result += cc;
        result +=" \033[0m";
      } else {
        // if the char is in wrong place
        result +="\033[30;103m ";
        result += cc;
        result +=" \033[0m";
      }
    }
    return result;
  };
  // add suffix for 1st 2nd 3rd and th for others
  public String addSuffix(List result){
    String out = "";
    for(int i = 1; i <= result.size(); i++ ){
      if(result.get(i-1).equals(1)){
        out+=result.get(i-1);
        out+="st ";
      }else if(result.get(i-1).equals(2)){
        out+=result.get(i-1);
        out+="nd ";
      }else if(result.get(i-1).equals(3)){
        out+=result.get(i-1);
        out+="rd ";
      }else{
        out+=result.get(i-1);
        out+="th ";
      }
    }
    return out;
  };
  // TODO: Implement compareWithAC(), giving it a String parameter and String return type
  public String compareWithAC(String target) {
    List resultSame = new ArrayList(); // store the correct char
    List resultExits = new ArrayList(); // store the char not in the same place
    String result = "";
    for (int i = 0; i <= 4; i++) {
      char ct = target.charAt(i);
      char cc = chosenWord.charAt(i);
      if (cc == ct) {
        // if the char is correct
        resultSame.add(i+1);
      } else if (target.indexOf(cc) == -1) {
        // if the char is not correct
        continue;
      } else {
        // if the char is in wrong place
        resultExits.add(i+1);
      }
    }
    // add suffix and result
    result += addSuffix(resultSame);
    if(resultSame.size()>0){
      result+="perfect!";
    }
    // add suffix and result
    result += addSuffix(resultExits);
    if(resultExits.size()>0){
      result+="correct but in wrong place!";
    }
    // add suffix and result
    if(result.length()==0){
      result+="You haven't got the correct character!";
    }
    return result;
  };
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String target){
    // check if the chosen word is same as the target
    if (target.equals(chosenWord)) {
      return true;
    }else{
      return false;
    }
  };
}
