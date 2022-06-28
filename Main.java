/**
 * Driver for program one. 
 * 1. Read the file and assign the values to human and pets.
 * 2. Apply Gale-Shapley Algo to find the stable match
 * 
 * @author Xiao Gao
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    Scanner reader = null;
    // open the file
    try {
      File file = new File("program1data.txt");
      reader = new Scanner(file);
    } catch (FileNotFoundException e) {
      System.out.println("File out found!");
      e.printStackTrace();
      System.exit(0);
    }

    // get the size and initialize human and pet array
    int size = Integer.parseInt(reader.nextLine());
    Human[] humanArray = new Human[size];
    Pet[] petArray = new Pet[size];

    // Read in humans
    readHuman(reader, humanArray);

    // assign pet preference to each human
    assignPetPreference(reader, humanArray);

    // Read in pets
    readPet(reader, petArray);

    // assign human preference to each pet
    assignHumanPreference(reader, petArray);

    // close the scanner 
    reader.close();

    // get and print out the results (the stable match)
    String[] results = findMatch(humanArray, petArray);
    for (String s : results) {
      System.out.println(s);
    }
  }

  /**
   * Method to compute the stable match
   * Time Complexity : O(n^2)
   * 
   * @param humanArray : the human array that contains all human object
   * @param petArray   : the pet array that contains all pet object
   * @return a String array which contains all matches
   */
  public static String[] findMatch(Human[] humanArray, Pet[] petArray) {
    // initial the results array
    String[] results = new String[humanArray.length];

    // initial the first unmatchedHuman(index)
    int unmatchedHuman = 0;

    // loop until all people are matched
    while (unmatchedHuman != -1) {
      Human human = humanArray[unmatchedHuman];
      int petIndex = human.getFirstPet();
      Pet pet = petArray[petIndex];
      String link = human.getName() + " / " + pet.getName();
      if (!pet.isMatched()) {
        results[unmatchedHuman] = link;
        // update the matching status for both human and pet
        human.updateMatch();
        pet.updateMatch();
        pet.setTargetHuman(unmatchedHuman);
        // move to next unmatched human
        unmatchedHuman = findFirstHuman(humanArray);
      } else if (pet.prefered(unmatchedHuman)) {
        results[unmatchedHuman] = link;
        // change the old target human to unmatch
        int oldHuman = pet.getTargetHuman();
        humanArray[oldHuman].updateMatch();
        // change the current human's matching status
        human.updateMatch();
        pet.setTargetHuman(unmatchedHuman);
        // move to the next unmatched human
        unmatchedHuman = findFirstHuman(humanArray);
      }
      // else just use the same human
    }
    return results;
  }

  /**
   * Find first unmatched human in human array
   * 
   * @param humanArray : the human array that contains all human
   * @return the first unmatched human's index;
   *         -1 if all humans are matched
   */
  public static int findFirstHuman(Human[] humanArray) {
    for (int i = 0; i < humanArray.length; i++) {
      if (!humanArray[i].isMatched())
        return i;
    }
    return -1;
  }

  /**
   * Method to read in human
   * pre : file must be opened by scanner, and humanArray is
   *       allocated memory.
   * post : the humanArray will be filled in.
   * 
   * @param reader     : the scanner for file
   * @param humanArray : the array to store human object
   * 
   */
  public static void readHuman(Scanner reader, Human[] humanArray) {
    int size = humanArray.length;
    for (int i = 0; i < size; i++) {
      String humanName = reader.nextLine();
      humanArray[i] = new Human(humanName, size);
    }
  }

  /**
   * Method to assign pet preference to each human in humanArray
   * pre : file must be opened by scanner, and readHuman() is
   *       called before calling this method
   * post : the pet preference is added
   * @param reader     : the scanner for file
   * @param humanArray : the array to store human object

   */
  public static void assignPetPreference(Scanner reader, Human[] humanArray) {
    for (int i = 0; i < humanArray.length; i++) {
      String petPreference = reader.nextLine();
      humanArray[i].addPet(petPreference);
    }
  }

  /**
   * Method to read in pets
   * pre : file must be opened by scanner, and petArray is
   *       allocated memory.
   * post : the petArray will be filled in.
   * 
   * @param reader     : the scanner for file
   * @param humanArray : the array to store pet object
   * 
   */
  public static void readPet(Scanner reader, Pet[] petArray) {
    int size = petArray.length;
    for (int i = 0; i < size; i++) {
      String petName = reader.nextLine();
      petArray[i] = new Pet(petName, size);
    }
  }

  /**
   * Method to assign pet preference to each human in humanArray
   * pre : file must be opened by scanner, and readPet() is
   *       called before calling this method
   * post : the human preference is added
   * 
   * @param reader   : the scanner for file
   * @param petArray : the array to store pet object
   */
  public static void assignHumanPreference(Scanner reader, Pet[] petArray) {
    for (int i = 0; i < petArray.length; i++) {
      String humanPreference = reader.nextLine();
      petArray[i].addHuman(humanPreference);
    }
  }
}