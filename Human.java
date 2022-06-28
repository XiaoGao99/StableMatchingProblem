import java.util.Scanner;

/**
 * Class to represent a human.
 * The human is represented by name and stores an array of
 * pet preference
 * Only necessaray getters and setters are provided 
 * 
 * Functionality includes find the first unproposed pet
 * @author Xiao Gao
 */

public class Human {
  // name of this person
  private String name;
  // preference list of this person
  private int[] pets;
  // current index on pets array
  private int index;
  // matching status
  private boolean matched;

  /**
   * Constructor
   * pre : none
   * post : instance variables are initialized
   * 
   * @param name : name of this human
   * @param size : size of the pets array
   */
  public Human(String name, int size) {
    this.name = name;
    pets = new int[size];
    index = 0;
    matched = false;
  }

  /**
   * To add a pet to the pets array
   * pre : pets array has been allocated memory
   * post : pet is been added to the correct index
   * 
   * @param line : the line that represent the pet preference of this human
   */
  public void addPet(String line) {
    Scanner reader = new Scanner(line);
    while (reader.hasNextInt()) {
      // assign to the array index directly (start with 0)
      this.pets[index] = reader.nextInt() - 1;
      index += 1;
    }
    reader.close();
    // reset index to 0, for getFirstPet() to work
    index = 0;
  }

  /**
   * Getter method for name
   * pre : name is initialized
   * post : return name variable
   * 
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter method for matched
   * pre : the human object is created
   * post : return the matched var
   * 
   * @return matched
   */
  public boolean isMatched() {
    return this.matched;
  }

  /**
   * Update the matched to the reverse status
   * pre : the human object is created
   * post : the matched variable is set reversely
   */
  public void updateMatch() {
    this.matched = !this.matched;
  }

  /**
   * Get the first pet in pets array that not proposed 
   * pre : the human object is created and addPet() is called
   * post : return the first unproposed pet
   * @return the first unproposed pet
   */
  public int getFirstPet() {
    index += 1;
    return pets[index - 1];
  }


}
