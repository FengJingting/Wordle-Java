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
    if(1<=num && num<=6){
      guessNumber = num;
    }else{
      throw new GameException("Invalid");
    }
  };
  // TODO: Implement constructor with int and String parameters
  public Guess(int num,String word){
    if(1<=num && num<=6){
      guessNumber = num;
    }else{
      throw new GameException("Invalid  guessNumber");
    }

    if(word.length()==5){
      chosenWord = word.toUpperCase(Locale.ROOT);
    }else{
      throw new GameException("Invalid chosenWord");
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
    chosenWord = INPUT.next().toUpperCase(Locale.ROOT);
  };
  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target) {
    String result = "";
    for (int i = 0; i <= 4; i++) {
      char ct = target.charAt(i);
      char cc = chosenWord.charAt(i);
      if (cc == ct) {
        result +="\033[30;102m ";
        result += cc;
        result +=" \033[0m";
      } else if (target.indexOf(cc) == -1) {
        result +="\033[30;107m ";
        result += cc;
        result +=" \033[0m";
      } else {
        result +="\033[30;103m ";
        result += cc;
        result +=" \033[0m";
      }
    }
    return result;
  };
  // add suffix
  public String addSuffix(List result){
    String out = "";
    for(int i = 1; i <= result.size(); i++ ){
//      System.out.print(result.get(i-1));
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
    List resultSame = new ArrayList();
    List resultExits = new ArrayList();
    String result = "";
    for (int i = 0; i <= 4; i++) {
      char ct = target.charAt(i);
      char cc = chosenWord.charAt(i);
      if (cc == ct) {
        resultSame.add(i+1);
      } else if (target.indexOf(cc) == -1) {
        continue;
      } else {
        resultExits.add(i+1);
      }
    }
    result += addSuffix(resultSame);
    if(resultSame.size()>0){
      result+="perfect!";
    }
    result += addSuffix(resultExits);
    if(resultExits.size()>0){
      result+="correct but in wrong place!";
    }
    if(result.length()==0){
      result+="You haven't got the correct character!";
    }
    return result;
  };
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String target){
    if (target.equals(chosenWord)) {
      return true;
    }else{
      return false;
    }
  };
}
