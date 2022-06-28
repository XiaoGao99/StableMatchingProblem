
import java.util.Scanner;

/** 
 * Class to represent a pet.
 * The human is represented by name and stores an array of 
 * human preference
 * Only necessaray getters and setters are provided 
 * 
 * Functionality includes check the prefered adopter.
 * @author Xiao Gao
 */
public class Pet {
  // name of the pet
  private String name;    
  // the preference list of this pet
  private int[] humans;   
  // the matching status 
  private boolean matched;
  // the value of the matching person
  private int targetHuman;

  /**
   * Constructor
   * pre : none 
   * post : instance variables are initialized 
   */
  public Pet (String name, int size)
  {
    this.name = name;
    humans = new int[size];
    matched = false;
    targetHuman = -1;
  }

  /**
   * To this pet's human preference to the humans array.
   * pre : humans array has been allocated memory 
   * post : human is been added to the correct position
   * @param line : the line that represents the human preference for this pet 
   */
  public void addHuman (String line)
  {
    Scanner reader = new Scanner(line);
    int index = 0;
    while (reader.hasNextInt()) {
      // assign to the array index directly (start with 0)
      this.humans[index] = reader.nextInt() - 1;
      index += 1;
    }
    reader.close();
  }

  /**
   * getter method for name
   * pre : name has been initialized 
   * post : return the name 
   * @return name 
   */
  public String getName () {
    return this.name;
  }

    /**
   * Getter method for matched 
   * pre : the pet object is created 
   * post : return the matched var
   * @return matched
   */
  public boolean isMatched () {
    return this.matched;
  }

  /**
   * Update the matched to the reverse status
   * pre : the pet object is created 
   * post : the matched variable is set reversely
   */
  public void updateMatch () {
    this.matched = !this.matched;
  }

  /**
   * getter for target human
   * pre : the pet object is created 
   * post : return target human
   * @return the target human
   */
  public int getTargetHuman() {
    return this.targetHuman;
  }

  /**
   * setter for target human
   * pre : the pet object is created 
   * post : target is setted to the new value
   * @param human : the current target human for this pet
   */
  public void setTargetHuman(int human) {
    this.targetHuman = human;
  }

  /**
   * Method to check if this pet prefer the old matched human or not
   * pre : this pet is matched 
   * post : the prefered human is determined
   * @param human
   * @return true if this pet prefered the new proposed human;
   *         false if this pet prefered the old human.
   */
  public boolean prefered(int human) {
    // check which one is encountered first
    for (int i = 0; i < humans.length; i++) {
      if (humans[i] == human) {
        return true;
      } else if (humans[i] == targetHuman) {
        return false;
      }
    }
    // which means this pet is not matched yet and the input human is invalid.
    // Therefore, sysout a warning message and return false 
    System.out.println("This pet's is not matched yet, please match first!");
    System.out.println("Also, your input human is not found!");
    return false;
  }
}
