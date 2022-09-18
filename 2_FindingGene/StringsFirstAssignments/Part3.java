
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public String twoOccurrences(String stringa, String stringb){
        int firstIndex = stringa.indexOf(stringb);
        if (firstIndex == -1)
        {
            return "FALSE";
        }
        int secondIndex = stringa.indexOf(stringb, firstIndex+stringb.length());
        if (secondIndex == -1)
        {
            return "FALSE";
        }
        return "TRUE";
    }
    
    public String lastPart(String stringa, String stringb){
        int firstIndex = stringa.indexOf(stringb);
        if (firstIndex == -1)
        {
            return stringb;
        }
        
        return stringa.substring(firstIndex+stringb.length());
    }
    
    public void testing() {
        String stringa = "ctgtatgta";
        System.out.println(stringa);
        String stringb = "atg";
        System.out.println(stringb);
        String pre = twoOccurrences(stringa, stringb);
        System.out.println("Результат" + pre);
        
        stringa = "A story by Abby Long";
        System.out.println(stringa);
        stringb = "by";
        System.out.println(stringb);
        pre = twoOccurrences(stringa, stringb);
        System.out.println(pre);
        
        stringa = "banana";
        System.out.println(stringa);
        stringb = "a";
        System.out.println(stringb);
        pre = twoOccurrences(stringa, stringb);
        System.out.println(pre);
        
    }
    
    public void test2() {
        String stringa = "ctgtaatagta";
        System.out.println(stringa);
        String stringb = "atg";
        System.out.println(stringb);
        String pre = lastPart(stringa, stringb);
        System.out.println("Результат: " + pre);
        
        stringa = "A story by Abby Long";
        System.out.println(stringa);
        stringb = "by";
        System.out.println(stringb);
        pre = lastPart(stringa, stringb);
        System.out.println("Результат: " + pre);
        
        stringa = "banana";
        System.out.println(stringa);
        stringb = "a";
        System.out.println(stringb);
        pre = lastPart(stringa, stringb);
        System.out.println("Результат: " + pre);
        
    }
}
