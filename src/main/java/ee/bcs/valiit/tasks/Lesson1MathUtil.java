package ee.bcs.valiit.tasks;

public class Lesson1MathUtil {
    private String test;

    // TODO kirjuta ise (if/else) ära kasuta java.lang.Math
    public Lesson1MathUtil(String test) {
        this.test = test;
    }

    public static void main(String[] args) {
        System.out.println(min(3,10));
        System.out.println(max(3,10));
        System.out.println(abs(-18));
        System.out.println(isEven(5));
        System.out.println(isEven(4));
        System.out.println(min3(3,3, 6));
        System.out.println(max33(5,8,22));

    }

    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a<b)
            return a;
        else
            return b;
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a>b)
            return a;
        else
            return b;
    }

    // TODO tagasta a absoluut arv ok
    public static int abs(int a) {
        if(a>=0)
            return a;
        else
            return (a*-1);
    }

    // TODO tagasta true, kui a on paaris arv ok
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        if(a%2==0)
            return true;
        else
            return false;
    }

    // TODO tagasta kolmest arvust kõige väiksem ok
    public static int min3(int a, int b, int c) {
        if (b<=c&&b<=a)
            return b;
        if (a<=c&&a<=b)
            return a;
        else
            return c;
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max33(int a, int b, int c) {
        if (b>=c&&b>=a)
            return b;
        if (a>=c&&a>=b)
            return a;
        else
            return c;
    }
}
