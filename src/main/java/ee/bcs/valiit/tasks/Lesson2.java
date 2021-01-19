package ee.bcs.valiit.tasks;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {
        System.out.println("Ex 1");
        int [] arrayItems = {1, 2, 3, 5, 6, 7};
        System.out.println(exercise1(arrayItems));
        //System.out.println("\nEx 2");
        //exercise2(5);
        //System.out.println("\nEx 3");
        //exercise3(3,3);
        //System.out.println("\nEx 4");
        //System.out.println(exercise4(6));
        //System.out.println("\nEx 5");
        //System.out.println(exercise5(27));
        //System.out.println("\nEx 6");
        //System.out.println(exercise6(20, 30));
        //System.out.println("\nCodew 8");
        //System.out.println(codewars8());
        //System.out.println("\nCodew 7");
        //codewarsKata7();

    }

    // TODO loo 10 elemendile täisarvude massiv
    // TODO loe sisse konsoolist 10 täisarvu
    // TODO trüki arvud välja vastupidises järiekorras
    public static int [] exercise1(int [] arrayItems) {
        //Scanner userNumber = new Scanner(System.in);
        //ArrayList<String> nrs = new ArrayList<>();
        //for (int u = 0; u < arrayItems.length; u++) {
            //System.out.print("Enter " + arrayItems + " numbers, one number at a time. You have entered " + u + " numbers so far: ");
            //String userNumberIn = userNumber.next();
            //nrs.add(u, userNumberIn);
        //}
        //System.out.println("Array as entered: " + nrs);

        int [] nrsReversed = new int[arrayItems.length];
        for (int i = arrayItems.length; i > 0; i--) {
            nrsReversed[arrayItems.length - i]= arrayItems[i-1];
        }
        //System.out.println("Array reversed: " + nrsReversed);
        return nrsReversed;
    }

    // TODO prindi välja x esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static void exercise2(int x) {
        for (int i = 1; i <= x; i++) {
            System.out.println(i * 2);
        }
    }
    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee
    public static void exercise3(int x, int y) {
        //int[][] intMatrix = new int[x][y];
        System.out.println("Array elements are: ");
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                //intMatrix[i][j] = (i + 1) * (j + 1);
                System.out.print(i*j + " ");
            }
            System.out.println();
        }
    }
       // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element
    public static int exercise4(int n) {
        int i = 0;
        int fib = 0;
        int fib1 = 1;
        int output = 0;
        while (i < n - 1) {
            output = fib + fib1;
            fib1 = fib;
            fib = output;
            i++;
        }
        return output;
    }

    public static int exercise5(int n) {

        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        // TODO 1 (tee alamfunktsioon) mis leiab 3n+1 sequenci pikkuse
        //int n = 24;
        int counter = 0;
        while (n != 1) {
            counter++;
            //System.out.println("Check " + counter + " result " + n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = (3 * n) + 1;
            }
        }
        //System.out.println("Check " + (counter + 1) + " result " + n);
        return (counter + 1);
    }
            /*
            1. input n
            2. print n
            3. if n = 1 then STOP
            4. if n is odd then n ←− 3n + 1
            5. else n ←− n/2
            6. GOTO 2
                         */

    // kui on paaris / 2 kui on paaritu *3+1
    // TODO 2 tee tsükkel mis leiab i -> j kõige suurema tsükkli pikkuse
    public static int exercise6(int g, int c) {
        int outcome = 0;
        for (int i = g; i <= c; i++) {
            if (exercise5(i) > outcome)
                outcome = i;
        }
        return outcome;
    }
    /*
    You are given an array with positive numbers and a non-negative number N.
    You should find the N-th power of the element in the array with the index N.
    If N is outside of the array, then return -1. Don't forget that the first element has the index 0.
    Let's look at a few examples:
    array = [1, 2, 3, 4] and N = 2, then the result is 3^2 == 9;
    array = [1, 2, 3] and N = 3, but N is outside of the array, so the result is -1.
    */
    public static int codewars8 () {
        int[] nrs = {1,2,3,4};
        int indx =3;
        if (nrs.length<=indx){
            return -1;
        }else {
            return ((int) Math.pow(nrs[indx], indx));
        }
    }
    //Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
    //Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"
    public static void codewarsKata7 () {
        String words = "How can mirrors be real if our eyes aren't real";
        String[] newWords = words.split(" ");
        ArrayList<String> newString = new ArrayList<>();
        for (int i = 0; i < newWords.length; i++) {
            //System.out.println(newWords[i]);
            String s1 = newWords[i].substring(0, 1).toUpperCase();
            String nameCapitalized = s1 + newWords[i].substring(1);
            newString.add(nameCapitalized);
        }
        //System.out.println(newString);
        String convertedString = String.join(" ", newString);
        System.out.println(convertedString);
        }

    }


