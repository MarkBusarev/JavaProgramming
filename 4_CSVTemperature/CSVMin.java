
/**
 * Write a description of class HottestHourInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    //Поиск самой низкой температуры в файле
    public CSVRecord СoldestHouerInFile(CSVParser parser) {
     CSVRecord coldestSoFar = null;
     for (CSVRecord currentRow : parser) {
       if (Double.parseDouble(currentRow.get("TemperatureF")) != -9999){
         coldestSoFar = getcoldestOfTwo(currentRow, coldestSoFar, "TemperatureF");
        }
     }
     return coldestSoFar;
    }
    public void testColdestHouerInFile (){
    FileResource fr = new FileResource ();
    CSVRecord coldest = СoldestHouerInFile(fr.getCSVParser());
    System.out.print("Самая холодная температура была " + coldest.get("TemperatureF") + 
                        " в " + coldest.get("DateUTC"));    
    }
    //Поиск самой низкой температуры за период
    public CSVRecord ColdestInManyDays() {
      CSVRecord coldestSoFar = null;
      DirectoryResource dr = new DirectoryResource ();
        for (File f : dr.selectedFiles()){
         FileResource fr = new FileResource (f);
         CSVRecord currentRow = СoldestHouerInFile(fr.getCSVParser());
         coldestSoFar = getcoldestOfTwo(currentRow, coldestSoFar,"TemperatureF");
        }
      return coldestSoFar;
    }
    public void testColdestInManyDays (){
        CSVRecord coldest = ColdestInManyDays();
        System.out.print("Самая холодная температура была " + coldest.get("TemperatureF") + 
                        " дата: " + coldest.get("DateUTC"));
    
    }
    //Выбор меньшей температуры из двух
    public CSVRecord getcoldestOfTwo(CSVRecord currentRow, 
                                     CSVRecord coldestSoFar, 
                                     String Parameter) {
        if (coldestSoFar == null&&(currentRow.get(Parameter)).equals("N/A") == false) {
            coldestSoFar = currentRow;
        }
        else {if((currentRow.get(Parameter)).equals("N/A") == false){
            Double currentTemp = Double.parseDouble(currentRow.get(Parameter));
            Double coldestTemp = Double.parseDouble(coldestSoFar.get(Parameter));
            if (currentTemp < coldestTemp){
            coldestSoFar = currentRow;
            }
        }
    }
        return coldestSoFar;
    } 
    //Поиск файла данных с самой низкой температурой за период
    public String fileWithColdestTemperature() {
      CSVRecord coldestSoFar = null;
      String fileName = null;
      DirectoryResource dr = new DirectoryResource ();
        for (File f : dr.selectedFiles()){
         FileResource fr = new FileResource (f);
         CSVRecord currentRow = СoldestHouerInFile(fr.getCSVParser());
         if (coldestSoFar == null) {
            coldestSoFar = currentRow;
            fileName = f.getName();
        }
        else {
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currentTemp < coldestTemp){
            coldestSoFar = currentRow;
            fileName = f.getName();
            }
        }
        }
      return fileName;
    }
    public void testFileWithColdestTemperature (){
        String fColdest = fileWithColdestTemperature();
        System.out.println("Имя файла с самой холодной температурой " + fColdest);
        FileResource fr = new FileResource ("nc_weather/2014/" + fColdest);
        CSVRecord coldest = СoldestHouerInFile(fr.getCSVParser());
        System.out.println("Самая холодная температура была " + coldest.get("TemperatureF") + 
                        " в " + coldest.get("TimeEST"));
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC")+ " "+ currentRow.get("TemperatureF"));
        }
    }
    //Поиск самой низкой влажности в файле
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
      CSVRecord coldestSoFar = null;
       for (CSVRecord currentRow : parser) {
            coldestSoFar = getcoldestOfTwo(currentRow, coldestSoFar, "Humidity");
       }
      return coldestSoFar;
    } 
    public void testlowestHumidityInFile (){
    FileResource fr = new FileResource ();
    CSVRecord parser = lowestHumidityInFile(fr.getCSVParser());
    System.out.print("Самая низкая влажность была " + parser.get("Humidity") + 
                        " в " + parser.get("DateUTC"));
    }
    //Поиск самой низкой влажности за период
    public CSVRecord lowestHumidityInManyFiles() {
      CSVRecord coldestSoFar = null;
      DirectoryResource dr = new DirectoryResource ();
        for (File f : dr.selectedFiles()){
         FileResource fr = new FileResource (f);
         CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
         coldestSoFar = getcoldestOfTwo(currentRow, coldestSoFar,"Humidity");
        }
      return coldestSoFar;
    }
    public void testLowestHumidityInManyFiles (){
        CSVRecord coldest = lowestHumidityInManyFiles();
        System.out.print("Самая низкая влажность была " + coldest.get("Humidity") + 
                        " дата: " + coldest.get("DateUTC"));
    }
    //Поиск средней температуры в файле
    public Double averageTemperatureInFile(CSVParser parser) {
     CSVRecord tempSumm = null;
     Double currentTemp = 0.0;
     Double summTemp = 0.0;
     Double countRow = 0.0;
     Double averageTemp = 0.0;
     for (CSVRecord currentRow : parser) {
         if ((currentRow.get("TemperatureF")).equals("N/A") == false) {   
         currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
         summTemp = summTemp + currentTemp;
         countRow = countRow + 1.0;
            }
     }
     return averageTemp = summTemp/countRow;
    }
    public void testAverageTemperatureInFile (){
    FileResource fr = new FileResource ();
    Double average = averageTemperatureInFile(fr.getCSVParser());
    System.out.println("Средняя температура " + average);    
    }
    //Поиск средней температуры при заданной влажности
    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
     CSVRecord tempSumm = null;
     Double currentTemp = 0.0;
     Double summTemp = 0.0;
     Double countRow = 0.0;
     Double averageTemp = 0.0;
     for (CSVRecord currentRow : parser) {
         if (((currentRow.get("TemperatureF")).equals("N/A") == false)
              && (Integer.parseInt(currentRow.get("Humidity")) >= value)) {
         currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
         summTemp = summTemp + currentTemp;
         countRow = countRow + 1.0;
         }
     }
     if (countRow == 0){
         return averageTemp = 0.0;
        }
       else {
         return averageTemp = summTemp/countRow;
       }
    }
    public void testAverageTemperatureWithHighHumidityInFile (){
       FileResource fr = new FileResource ();
       Double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
       if(average == 0.0){
       System.out.println("Нет значений температуры при заданной влажности");
       }
       else {
       System.out.println("Средняя температура " + average);    
       }
    }
}

     

