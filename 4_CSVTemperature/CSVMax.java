
/**
 * Write a description of class HottestHourInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    //Поиск самой высокой температуры за день
    public CSVRecord hottestHouerInFile(CSVParser parser) {
     CSVRecord largestSoFar = null;
     for (CSVRecord currentRow : parser) {
        largestSoFar = getlargestOfTwo(currentRow, largestSoFar);
     }
     return largestSoFar;
    }
    public void testHottestHouerInFile (){
    FileResource fr = new FileResource ("nc_weather/2015/weather-2015-01-02.csv");
    CSVRecord largest = hottestHouerInFile(fr.getCSVParser());
    System.out.print("Самая жаркая температура была " + largest.get("TemperatureF") + 
                        " в " + largest.get("TimeEST"));
    
    }
    //Поиск самой высокой температуры за период
    public CSVRecord hottestInManyDays() {
      CSVRecord largestSoFar = null;
      DirectoryResource dr = new DirectoryResource ();
        for (File f : dr.selectedFiles()){
         FileResource fr = new FileResource (f);
         CSVRecord currentRow = hottestHouerInFile(fr.getCSVParser());
         largestSoFar = getlargestOfTwo(currentRow, largestSoFar);
        }
      return largestSoFar;
    }
    public void testHottestInManyDays (){
        CSVRecord largest = hottestInManyDays();
        System.out.print("Самая жаркая температура была " + largest.get("TemperatureF") + 
                        " в " + largest.get("DateUTC"));
    
    }
    //Выбор большей температуры из двух
    public CSVRecord getlargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp){
            largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }     
}

     

