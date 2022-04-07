package comp1721.cwk1;

import java.util.Scanner;


public class Guess {
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);
  private int guessNumber;
  private String chosenWorld;
  // TODO: Implement constructor with int parameter
  public void Guess(int num){
    this.guessNumber = num;
  };
  // TODO: Implement constructor with int and String parameters
  public void Guess(int num,String word){
    this.guessNumber = num;
    this.chosenWorld = word;
  };
  // TODO: Implement getGuessNumber(), returning an int
  public int getGuessNumber(){
    return guessNumber;

  };
  // TODO: Implement getChosenWord(), returning a String
  public String getChosenWord(){
    return chosenWorld;
  };
  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){

  };
  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target){
return "ok";
  };
  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String target){
    return true;
  };
}
