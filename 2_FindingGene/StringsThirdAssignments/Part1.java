
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part1 {
  public int findStopCodon(String dnaStr,
                             int startIndex,
                             String stopCodon){                  //поиск гена с заданным stopCodon
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1){
           int diff = currIndex-startIndex;
           if (diff % 3 == 0){
               return currIndex;                        //возвращает индекс stopCodon если ген найден
           }
           else{
            currIndex = dnaStr.indexOf(stopCodon,currIndex+1);
           }
        }
    return dnaStr.length();                                   //возвращает длину цепочки если нет гена
  }
  public String findGene(String dna, int where) {               //поиск гена в цепочке
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
  public void testFindeStopCodon() {                      //тест поиска гена с заданным stopCodon
        String dna = "ATGeeeeTAAxxTAAx";
        int dex = findStopCodon (dna,0,"TAA");
        System.out.println("Stop Codon Index is " + dex);
        dna = "ATGxxxeeeTAP";
        dex = findStopCodon (dna,0,"TAA");
        System.out.println("No Stop Codon " + dex);
  }
  public void testFindGane() {                             //тест поиска гена в цепочке
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
  public void printAllGenes() {                      //печать всех генов
        String dna = "AATGCTAACTAGCTGACTAAT";
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
  public StorageResource GetAllGenes(String dna) {                  //сохранение всех генов
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
                while (true){
        String currentGene = findGene(dna, startIndex);
        if (currentGene.isEmpty()){
            break;
        }
        geneList.add(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex)+currentGene.length();
        }
        return geneList;
  }
  public void testOn(String dna) {                             //тест сохранения всех генов
        System.out.println("Test getAllGenes on " + dna);
        StorageResource genes = GetAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
  }
  public void test() {                                        //тест сохранения всех генов
        testOn("ATGxxxeeeTAAxxxyyyATGTAG");
        testOn("ATGxxxeeeTAAxxxyyyATGTGA");
        testOn("ATGxxxTeeeTAAxxxyyyATGTAG");
  }
  public float countX(String dna, String X){                  //счет X в dna
        int XIndex = 0;
        float Xcount = 0; 
        while (true){
           int xx = dna.indexOf(X,XIndex);           //находим индекс X в dna
           if (xx != -1){
           Xcount = Xcount + 1;                     //считаем количество X если индекс найден
           XIndex = xx+1;
        }
           if (xx == dna.length()||xx<0){   
           break;                                   //прерываем если индекс X последний или не найден
        }
           
    }
        return Xcount;
  }
  public float cgRatio(String dna){                  //поиск соотношеиния С + G к длинне dna
        float Ccount = countX (dna, "C");
        //System.out.println(Ccount);
        float Gcount = countX (dna, "G");
        //System.out.println(Gcount);
        return (Ccount+Gcount)/dna.length();
  }
  public void testcgRatio() {                  //Тест поиска соотношеиния С к G в dna
       String dna =  "CATG";
       System.out.println(dna);
       float cg = cgRatio (dna);
       System.out.println(cg);
       dna =  "GATGxxxCCCeeeTAAxxxGGGyyyATGTAG";
       System.out.println(dna);
       cg = cgRatio (dna);
       System.out.println(cg);
  }
  public float countCTG(String dna){                  //подсчет CTG в dna
        float CTGcount = countX (dna, "CTG");
        return CTGcount;
  }
  public void testcountCTG() {                  //Тест подсчет CTG в dna
       String dna = "CTG";
       System.out.println(dna);
       float c = countCTG (dna);
       System.out.println(c);
       dna =  "GACTGxxxCCCeeeTAAxxxGGGyyyCTGCTG";
       System.out.println(dna);
       c = countCTG (dna);
       System.out.println(c);
  }
  public void ProcessGenes(StorageResource sr) {       
      int gCount = 0;  
      for (String g: sr.data()) {
         if (g.length() > 60){
           System.out.println("Genes > 60 " + g);         
           gCount = gCount+1;
        }
      }
      System.out.println("Number gene > 60 = " + gCount);
      int gcCount = 0;
      for (String gc: sr.data()) {
      float Ccount = countX (gc, "C");
      float Gcount = countX (gc, "G");
      if (cgRatio(gc) > 0.35){
           System.out.println("Genes (C-G)/Gene > 0.35 " + gc);         
           gcCount = gcCount+1;
       }
      }
      System.out.println("Number Genes (C-G)/Gene > 0.35 = " + gcCount);
      int maxGane = 0;
      for (String maxL: sr.data()) {
         if ( maxL.length() > maxGane){
           maxGane = maxL.length();           
        }
      }
      System.out.println("Max Gane length = " + maxGane);
  }
  public void testProcessGenes() {       
      //String dna =  "GATGxxxCCCeeeTAAxATGxxxGGGCCCTGACTGATGyyyTGA";
      FileResource fr = new FileResource("GRch38dnapart.fa");
      String dna = fr.asString();
      dna = dna.toUpperCase();
      System.out.println("Test getAllGenes on " + dna);
      StorageResource genes = GetAllGenes(dna);
      ProcessGenes (genes);
      //dna =  "ATGxxxTAAxATGxxxGGGTGACTGATGyyyTGA";
      //System.out.println("Test getAllGenes on " + dna);
      //genes = GetAllGenes(dna);
      //ProcessGenes (genes);
  }
}