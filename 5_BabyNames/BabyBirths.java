
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of class HottestHourInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    // возвращает имена с рейтингом не больше 100
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                                    " Gender " + rec.get(1) +
                                    " Num Born " + rec.get(2));
                                }
        }
    }
    // возвращает общее количество детей, количество мальчиков и девочек
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numBirths = 0;
        int numBoys = 0;
        int numGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths = totalBirths + numBorn;
            numBirths = numBirths + 1;
            if (rec.get(1).equals("M")) {
                totalBoys = totalBoys + numBorn;
                numBoys = numBoys + 1;
            }
            else {
                totalGirls = totalGirls+ numBorn;
                numGirls = numGirls + 1;
            }
        }
        System.out.println("total births = " + totalBirths + " количество имен = " + numBirths); 
        System.out.println("total boys = " + totalBoys + " количество мужских имен = " + numBoys); 
        System.out.println("total girls = " + totalGirls + " количество женских имен = " + numGirls);        
    }
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths (fr);
    }
    // возвращает рейтинг имени
    public int getRank (int year, String name, String gender) {
        int Rank = 0;
        int FlagName = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)) {
                Rank = Rank + 1;
                if (rec.get(0).equals(name)){
                    FlagName = 1;
                    break;
                }                        
            }            
        }
        if (FlagName != 1){
         Rank = -1;
        }
        System.out.println(name + " " + gender + " = " + Rank);
        return Rank;
    }
    public void testGetRank () {
        getRank (2014, "Frank", "M");
    }
    // возвращает имя для указанного ранга
    public String getName (int year, int rank, String gender) {
        int currentRank = 0;
        int FlagName = 0;
        String newName = "NO NAME";
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)) {
                currentRank = currentRank + 1;
                if (currentRank == rank){
                    FlagName = 1;
                    newName = rec.get(0);
                    System.out.println(rank + " " + gender + " " + newName);
                    break;
                }                        
            }            
        }
        return newName;
    }
        public void testGetName () {
        String newName = getName (2014, 450, "M");
        System.out.println(newName);
    }
    // определяет имя с тем же рангом в заданном году
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        int Rank = 0;
        Rank = getRank (year, name, gender);
        String newName = getName (newYear, Rank, gender);
        System.out.println(newName); 
    }
    public void testWhatIsNameInYear () {
        whatIsNameInYear ("Owen", 2072, 2014, "M");
    }
    // возвращает год с высшим рейтингом имени
    public void yearOfHighestRank (String name, String gender) {
        int Rank = 0;
        int Year = 0;
        DirectoryResource dr = new DirectoryResource ();
        for (File f : dr.selectedFiles()){
         String fileName = f.getName();
         int currentYear = Integer.parseInt(fileName.substring(3, 7));
         int currentRank = 0;
         int FlagName = 0;
         FileResource fr = new FileResource(f);
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)) {
                currentRank = currentRank + 1;
                if (rec.get(0).equals(name)){
                    FlagName = 1;
                    break;
                }                        
            }            
        }
        if (FlagName != 1){
         currentRank = 999999999;
         currentYear = -1;
        }
        if (Rank > currentRank || Rank == 0){ 
        Rank = currentRank;
        Year = currentYear;
        }
       } 
       System.out.println(Year + " год в котором " + name + " имел рейтинг " + Rank);        
    }
    public void testYearOfHighestRank () {
        yearOfHighestRank("Genevieve", "F");
    }
    // определяент средний ранг за выбранный период для имени
    public double getAverageRank (String name, String gender) {
       double averageRank = 0.0;
       double sumRank = 0.0;
       double sumFile = 0.0;
       DirectoryResource dr = new DirectoryResource ();
       for (File f : dr.selectedFiles()){
         int currentRank = 0;
         int FlagName = 0;
         FileResource fr = new FileResource(f);
         for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)) {
                currentRank = currentRank + 1;
                if (rec.get(0).equals(name)){
                    FlagName = 1;
                    break;
                }                        
            }            
         }
         if (FlagName != 1){
             currentRank = -1;
         }
         sumRank = sumRank + (double)currentRank;
         sumFile = sumFile +1.0;
       }
        averageRank = sumRank / sumFile;
        return averageRank;
    }
    public void testGetAverageRank () {
        double averageRank = getAverageRank ("Robert", "M");
        System.out.println(averageRank + " - средний рейтинг для Robert");  
    }
    // общее число рожденных с рейтингом выше чем у заданного имени
    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
         int currentRank = 0;
         int summRank = 0;
         int FlagName = 0;
         FileResource fr = new FileResource();
         for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)) {
                currentRank = currentRank + 1;
                summRank = summRank + Integer.parseInt(rec.get(2));
                if (rec.get(0).equals(name)){
                    FlagName = 1;
                    summRank = summRank - Integer.parseInt(rec.get(2));
                    break;
                }                        
            }            
         }
         if (FlagName != 1){
             currentRank = -1;
         }
         return summRank;
    }
    public void testGetTotalBirthsRankedHigher () {
        int summRank = getTotalBirthsRankedHigher (2012, "Drew", "M");
        System.out.println(summRank + " - количество детей с больши рейтингом чем Drew");  
    }
}
