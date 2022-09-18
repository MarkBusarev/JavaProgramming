
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;


public class Part4 {
   public void runYoutube() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String find = "youtube.com";
        for (String word : ur.words()){
                String loword = word.toLowerCase();
                int idIndex = loword.indexOf(find);
                    if (idIndex != -1)
                    {
                     int startIndex = word.lastIndexOf("\"", idIndex);
                     int stopIndex = word.indexOf("\"", idIndex);
                     String result= word.substring(startIndex+1, stopIndex);
                     System.out.println(result);
                    }
        
        }
   }
}
