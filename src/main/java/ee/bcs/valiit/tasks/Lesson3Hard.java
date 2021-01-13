package ee.bcs.valiit.tasks;

import java.util.*;

public class Lesson3Hard {

    public static void main(String[] args) {
        System.out.println("Les 3.1.1");
        //System.out.println(evenFibonacci(9));
        System.out.println("Les 3.1.2");
        //randomGame();
        System.out.println("Les 3.1.3");
        //System.out.println(morseCode("SOS"));
        System.out.println("Les Kata6 camelCase");
        //System.out.println(toCamelCase("the_Stealth-warrior"));
        System.out.println("Les Kata6 deletePhoto");
        int[] elements = {1,1,3,3,7,2,2,2,2};
        int maxOccurrences =2;
        System.out.println(deleteNth(elements, maxOccurrences));

}
    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        int i = 0;
        int fib = 0;
        int fib1 = 1;
        int output;
        int total=0;
        while (i < x - 1) {
            output = fib + fib1;
            if(output%2==0)
                total+=output;
            fib1 = fib;
            fib = output;
            i++;
        }
        return total;
    }
    public static void randomGame(){
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        System.out.print("Its a guess game. Computer picks a number from 0-100 and you should guess it.");
        Random random = new Random();
        Scanner userInput = new Scanner(System.in);
        int i = random.nextInt(100);
        System.out.println(i);
        int userGuess=-1;
        int userGuessCount=0;
        while (userGuess!=i){
            System.out.print("Enter a number from 0-100: ");
            userGuess=userInput.nextInt();
            userGuessCount++;
            if (userGuess==i){
                System.out.println("You did it! You had "+userGuessCount+" guesses.");
                break;
            }else if (userGuess>i){
                System.out.println("Try smaller.");
            }else{
                System.out.println("Try bigger.");
            }
        }
    }

    public static String morseCode(String text){
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        String editedText = text.toLowerCase();
        String outcome="";
        HashMap<String, String> morseIndex = new HashMap<>();
        morseIndex.put("s" , "...");
        morseIndex.put("o" , "---");
        morseIndex.put("h" , "....");
        morseIndex.put("e" , ".");
        morseIndex.put("l" , ".-..");
        morseIndex.put("p" , ".--.");
        for (int i=0; i<editedText.length();i++) {
            outcome=outcome+" "+morseIndex.get(editedText.substring(i, i+1));
        }
        return outcome;
    }
    //toCamelCase("the-stealth-warrior"); // returns "theStealthWarrior"
    //toCamelCase("The_Stealth-warrior"); // returns "TheStealthWarrior"
    public static String toCamelCase(String text){
        String modifiedText=text.replace("-", "_");
        String[] textSplitted = modifiedText.split("_");
        String finalString = textSplitted[0];
        String capLetter="";
        for(int i=1; i<textSplitted.length; i++) {
            capLetter = textSplitted[i].substring(0, 1).toUpperCase();
            finalString=finalString+capLetter+textSplitted[i].substring(1);
        }
        return finalString;
    }
/*
CODEWAR SOLUTION!!!
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.StringBuilder;

class Solution{

  static String toCamelCase(String s){
    Matcher m = Pattern.compile("[_|-](\\w)").matcher(s);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        m.appendReplacement(sb, m.group(1).toUpperCase());
    }
    return m.appendTail(sb).toString();
  }
}
 */

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        //int[] elements = {1,1,3,3,7,2,2,2,2};
        //int maxOccurrences =2;
        List<String> newElements = new ArrayList<>();
        int arv = elements[0];
        for(int i=0; i<elements.length; i++ ) {
            int elementCount =1;
            int elementCountNew=0;
            for (int j = 1; j < elements.length; j++){
                if (elements[i] == elements[j]);
                   elementCount++;
                   if (newElements.contains(i))
                   while (elementCount<=maxOccurrences){

                   }
    }}

    int[] newElement

        return null;
    }
}
