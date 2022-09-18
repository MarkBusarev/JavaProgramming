
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;


public class Test {
   public void runYoutube() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
                for (String word : ur.words()){
                System.out.println(word);
        }
        
        }
   }

