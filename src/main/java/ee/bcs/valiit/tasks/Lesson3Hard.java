package ee.bcs.valiit.tasks;

import java.util.*;

public class Lesson3Hard {
    static HashMap<String, String> store = new HashMap<>();

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
        int[] elements = {7,1,2,1,2,1,21,2,1,2,7,7,7};
        int maxOccurrences =2;
        //System.out.println(Arrays.toString(deleteNth(elements, maxOccurrences))); //Kata 6
        for(int i : (deleteNth(elements, maxOccurrences))){
            System.out.print(i+", ");}
        //System.out.println(howOld()); //Kata 8
        int n=12;
        //System.out.println(toBinary(n)); //Kata 8
        int[] arr = {2, 1, 1, 10, 3, 15};
        //System.out.println(sumOfDifferences(arr)); //Kata 7
        int knight = 0;
        long pawn = 7;
        //redKnight(knight, pawn); //Kata 7
        int[] arrg={15,11,10,7,12,2,3};
        //System.out.println("Final"+Arrays.toString(solve(arr))); //Kata 7
        long start = 10000;
        long limit = 100000;
        System.out.println(buddy(start, limit)); //Kata 6 - see lahendus CW ajapiiri sisse ei mahu....

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
        ArrayList<Integer> convListA = new ArrayList<>();
        int elementPosition = -1;
        while (elementPosition + 1 < elements.length) {
            elementPosition++;
            int elementCounter = 0;
            for (int i = 0; i < convListA.size(); i++) {
                if (elements[elementPosition] == convListA.get(i)) {
                    elementCounter++;
                }
            }
            if (elementCounter < maxOccurrences)
                convListA.add(elements[elementPosition]);
        }
        int[] convList = new int[convListA.size()];
        for (int j = 0; j < convListA.size(); j++) {
            convList[j] = convListA.get(j);
        }
        return convList;
    }
    public static int howOld() {
        final String herOld = "2 She is";
        String newString = herOld.substring(0, 1);
        int result = Integer.parseInt(newString);

        return result;
    }
    public static int toBinary(int n) {
        double multiplier = 0.1;
        int sum = 0;
        for (int i = n; n >= 1; n = n / 2) {
            multiplier *= 10;
            if (n % 2 != 0)
                sum += (int) multiplier;
        }
        return sum;
    }
    public static int sumOfDifferences(int[] arr) {
        int temp;
        int arrLength= arr.length;
        int result=0;
        if(arr.length<1)
            return 0;
        while (arrLength>0) {
            arrLength--;
            for (int i = arr.length - 1; i > 0; i--) {
                if (arr[i] >= arr[i - 1]) {
                    temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                }
            }
            //System.out.println(Arrays.toString(arr));
        }
        for (int i =0; i < arr.length - 1; i++) {
            result=result+(arr[i]-arr[i+1]);
        }
        return result;
    }

/*
    public static int sumOfDifferences(int[] arr) {
        Arrays.sort(arr);
        return arr.length <= 1 ? 0 : arr[arr.length-1] - arr[0];
    }
 */
public static void redKnight(int knight, long pawn) {

    long knightDistance = 0;
    String color = "Black";
    int knightPosition = 0;
    int pawnStep = 1;
    int knightStep = 2;
    knightDistance = pawn*2;
        /*while (knightDistance < pawn) {
            knightDistance += knightStep;
            pawn += pawnStep;
        }*/
    if (knight == 0 && knightDistance % 4 == 0 || knight == 1 && knightDistance % 4 == 2) {
        color = "White";
        knightPosition = 0;
    }
    System.out.println(knightDistance);
    System.out.println("Knight final color is: " + color);
    System.out.println("Knight final position is: " + knightPosition);
}
    public static int[] solve(int[] arrg){
        ArrayList<Integer> uusArr = new ArrayList<>();
        for (int i=0; i< arrg.length; i++){
            uusArr.add(arrg[i]);
        }
        uusArr.sort(null);
        Collections.reverse(uusArr);
        for (int i=1; i< uusArr.size()-1; i+=2){
            uusArr.add(i, uusArr.get(uusArr.size()-1));
            uusArr.remove(uusArr.size()-1);
        }
        for (int i=0; i< arrg.length; i++)
            arrg[i]= uusArr.get(i);

        return arrg;
    }/*
    import java.util.Arrays;
    class Solution{
        public static int[] solve (int[] arr){
            //..
            Arrays.sort(arr);
            int[] resOrdered = new int[arr.length];
            for (int i = 0; i < arr.length; i++){
                resOrdered[i] = arr[i % 2 == 0 ? arr.length-1-i/2 : i / 2];
            }            return resOrdered;
        }    }
    class Solution{
    public static int[] solve (int[] arr){
        List<Integer> temp = new ArrayList<Integer>();
        Arrays.sort(arr);
        for (int i = 0, j = arr.length - 1; i <= j; ++i, --j) {
            if (i != j) temp.add(arr[j]);
            temp.add(arr[i]);
        }
        return temp.stream().mapToInt(i -> i).toArray();
    }}*/

    public static String buddy(long start, long limit) {
        HashMap<Long, Long> store = new HashMap<>();
        String option = "Nothing";
        long biggest = limit;
        for (long j = start; j <= biggest; j++) {
            if(j>=limit)
                break;
            long value = sumOfDiv(j);
            if (j <= limit && biggest < value) {
                biggest = value;
            }
            try {
                if (store.get(value).equals(j)&&value<j) {
                    return ("[" + (value) + "," + (j) + "]");
                }
            } catch (NullPointerException e) {
            }
            store.put(j, value);
        }
        return option;
    }

    public static long sumOfDiv (long hashKey){
        long hashVal = 0;
        for (long i = 1; i <= hashKey / 2; i++) {
            if (hashKey % i == 0)
                hashVal += i;
        }
        return hashVal - 1;
    }
}

