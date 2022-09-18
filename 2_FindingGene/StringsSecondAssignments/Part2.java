
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part2 {
  public int howMany(String stringa, String stringb){
     int counter = 0;
     int currIndex = stringb.indexOf(stringa);
        while (currIndex != -1){
           counter = counter + 1;
           currIndex = stringb.indexOf(stringa,currIndex+stringa.length());
           }
   return counter;
  }
  public void testCounter() {
        int test = howMany ("GAA","ATGAACGAATTGAATC");
        System.out.println(test); 
        test = howMany ("AA","ATAAAA");
        System.out.println(test);
    }
}
