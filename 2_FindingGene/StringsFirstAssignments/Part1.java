
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
        {
            return "no ATG";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1)
        {
            return "no TAA";
        }
        result= dna.substring(startIndex, stopIndex+3);
        if (result.length() % 3 == 0)
        {
            return result;
        }      
        return "no DNA";
    }
    
    public void testSimpleGene() {
        String dna = "AAGGCGTAATATTGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "AATGCGTATATATTGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATAGCTATTATATTGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCGTAGTTTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCGTAGTTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    }
}
