
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna){
        String result = "";
        String preresult = dna.toUpperCase();
        int startCodon;
        int stopCodon;
        int startIndex = preresult.indexOf("ATG");
        if (startIndex == -1)
        {
            return "no ATG";
        }
        int stopIndex = preresult.indexOf("TAA", startIndex+3);
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
        String dna = "ATGGGTTAAGTC";
        System.out.println("DNA strand is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);

        dna = "gatgctataat";
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
