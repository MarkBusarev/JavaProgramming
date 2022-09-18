
/**
 * Write a description of class WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport //выбор стран по экспортируемому товару
{
    public void listExporters(CSVParser parser, String exportOfInterest) {
       for (CSVRecord record : parser) {
        String export = record.get ("Exports");
        if (export.contains(exportOfInterest)) {
            String country = record.get("Country");
            System.out.println(country);
        }
       }
    }
    public void countryInfo(CSVParser parser, String Country) {//возвращает информацию о стране
       int number = 0;
        for (CSVRecord record : parser) {
        String country = record.get ("Country");
        if (country.contains(Country)) {
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            System.out.println(country + ":" + exports + ":" + value);
            number = number + 1;
        }              
      }
      if (number==0) {
            System.out.println(Country + ": NOT FOUND" );
        }
    }
    public void listExportersTwoProducts(CSVParser parser, 
                                         String exportItem1, 
                                         String exportItem2) {
      for (CSVRecord record : parser) {
        String exports = record.get ("Exports");
        if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
            String country = record.get("Country");
            System.out.println(country);
        }              
      }      
    }
    public void numberOfExporters(CSVParser parser,String exportItem) {
       int number = 0;
        for (CSVRecord record : parser) {
        String exports = record.get ("Exports");
        if (exports.contains(exportItem)) {
            number = number + 1;
        }              
      }
      System.out.println("Number exporter " + exportItem + " = " + number);
    }
    public void bigExporters(CSVParser parser,String amount) {
       int number = 0;
        for (CSVRecord record : parser) {
        String value = record.get ("Value (dollars)");
        if (value.length()>amount.length()) {
            String country = record.get("Country");
            System.out.println(country + " " + value);
        }              
      }
    }
    public void tester() {
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       //listExporters(parser, "coffee");
       countryInfo(parser, "Nauru");
       parser = fr.getCSVParser();
       listExportersTwoProducts(parser,"cotton", "flowers");
       parser = fr.getCSVParser();
       numberOfExporters(parser,"cocoa");
       parser = fr.getCSVParser();
       bigExporters(parser,"$999,999,999,999");
       }
}
