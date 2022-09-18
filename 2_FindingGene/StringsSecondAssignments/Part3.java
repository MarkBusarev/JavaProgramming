
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part3 {
    public int findStopCodon(String dnaStr,
                             int startIndex,
                             String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1){
           int diff = currIndex-startIndex;
           if (diff % 3 == 0){
               return currIndex;
           }
           else{
            currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
           }
        }
    return dnaStr.length();
  }
  public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon (dna,startIndex,"TAA");
        int tagIndex = findStopCodon (dna,startIndex,"TAG");
        int tgaIndex = findStopCodon (dna,startIndex,"TGA");
        int temp = Math.min (taaIndex,tagIndex);
        int minIndex = Math.min (temp,tgaIndex);
        if (minIndex == dna.length()){
        return "";
        }
        return dna.substring(startIndex,minIndex + 3);
  }
  public int countAllGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true){
        String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
            break;
        }
        count = count + 1;
        startIndex = dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
        return count;
}
  public void testFindeStopCodon() {
        String dna = "ATGeeeeTAAxxTAAx";
        int dex = findStopCodon (dna,0,"TAA");
        System.out.println("Stop Codon Index is " + dex);
        dna = "ATGxxxeeeTAP";
        dex = findStopCodon (dna,0,"TAA");
        System.out.println("No Stop Codon " + dex);
  }
  public void testFindGane() {
        String dna = "ATAxxxeeeTAAxxxyyyTAAxxx";
        System.out.println(dna);
        String gane = findGene(dna, 0);
        System.out.println("No ATG " + gane);
        dna = "xxATGxxxeeeTGAxxxyyyTAAxxxTAG";
        System.out.println(dna);
        gane = findGene(dna, 0);
        System.out.println("Gane is " + gane);
        dna = "xxATGxxxeeeTAGxxxyyyTGAxxxTAA";
        System.out.println(dna);
        gane = findGene(dna, 0);
        System.out.println("Gane is " + gane);
        dna = "xxATGxxxeeeTAAxxxyyyTGAxxxTAG";
        System.out.println(dna);
        gane = findGene(dna, 0);
        System.out.println("Gane is " + gane);
        dna = "ATGxxxFeeeTAxAxxxyyyTGxAxxxTAxG";
        System.out.println(dna);
        gane = findGene(dna, 0);
        System.out.println("Gane is " + gane);
  }
  public void printAllGenes() {
        String dna = "jgkyngkygukyATGgkgTAAnbATGTAGsssuuuppppppATGxxxTGA";
        int startIndex = 0;
        System.out.println("DNA" + dna);
        while (true){
        String currentGene = findGene(dna, startIndex);
        if (currentGene.isEmpty()){
            break;
        }
        System.out.println(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
  }
   public void TestcountAllGenes() {
        String dna = "ATGgggTAAnbATGTAGsssuuuppppppATGxxxTGA";
        System.out.println("Gene:" + dna);
        int test = countAllGenes(dna);
        System.out.println(test);
        dna = "ATGggggTAAnbATGTAG";
        System.out.println("Gene:" + dna);
        test = countAllGenes(dna);
        System.out.println(test);
        dna = "1ATGgggTAAnbaaaATGTAGaaaATGTAGaaaATGTAG";
        System.out.println("Gene:" + dna);
        test = countAllGenes(dna);
        System.out.println(test);
        }
  }