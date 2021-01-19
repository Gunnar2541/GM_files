package ee.bcs.valiit.tasks;

import java.util.*;

public class Lesson3 {
    public static void main(String[] args) {
        //System.out.println("Les 3.1");
        //int[] x = {1, 2, 3};
        //System.out.println(sum(x));
        //System.out.println("\nLes 3.2");
        //System.out.println(factorial(8));
        //System.out.println("\nLes 3.3");
        //int[] a = {6, 2, 3};
        //System.out.println(Arrays.toString(sort(a)));
        //System.out.println("\nLes 3.3Reino");
        //int[] b = {4, 100, 29, 3, 6, 9, 77};
        //System.out.println(sortReino(b));
        //System.out.println("\nLes 3.4");
        //System.out.println(reverseString("mina olin siin"));
        //System.out.println("\nLes 3.5");
        //System.out.println(isPrime(17));
        //System.out.println(isPrime(25));
        //System.out.println(isPrime(1));

        /*
        int c = 4;
        int d = 5;
        System.out.println(c);
        System.out.println(d);
         */
    }

    public static int sum(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i = 0; i < x.length; i++)
            sum +=x[i];
        return sum;
    }

    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 5*4*3*2*1 = 120
        int multipl = x;
        for (int i = x - 1; i > 0; i--) {
            x = x - 1;
            multipl = multipl * (x);
        }
        return multipl;
    }

    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi.
        // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
        // int[] a = {4, 100, 29, 3};
        List<Integer> newList = new ArrayList<>();
        int replacementInA = Integer.MIN_VALUE;
        while (newList.size() < a.length) {
            int x = replacementInA;
            for (int i = 0; i < a.length; i++) {
                if (x <= a[i]) {
                    x = a[i];
                }}
                newList.add(x);
                for (int j = 0; j < a.length; j++){
                    if (a[j] == x)
                        a[j] = replacementInA;
                }
            }
        int [] finalList=new int[a.length];
        for (int z=0; z<a.length; z++)
            finalList[z]=newList.get(z);

        return finalList;

    }

    public static int[] sortReino(int[] b) {
        // TODO sorteeri massiiv suuruse järgi.
        // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
        int count = 0;
        for (int i= 1; i<b.length; i++){
            if (b[i]>b[i-1]){
                int vahepealne = b[i-1];
                b[i-1] = b[i];
                b[i]= vahepealne;
                i=0;
                count =0;
            }
            count ++;
            if (count == b.length){
                break;
            }
        }
        return b;
    }


    public static String reverseString (String a){
            // TODO tagasta string tagurpidi
            String[] words = a.split(" ");
            String newString ="";
            for (int i=words.length-1; i>=0; i-- )
                newString += words[i]+" ";

            return newString;
        }

        public static boolean isPrime ( int x){
            // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
            boolean isPrime = true;
            for (int i=x-1; i>1; i-- ) {
                if (x % i == 0)
                    isPrime = false;
            }
            return isPrime;

        }

}