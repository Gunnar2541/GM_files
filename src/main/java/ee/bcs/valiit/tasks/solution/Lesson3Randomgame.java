package ee.bcs.valiit.tasks.solution;

import java.util.Random;
import java.util.Scanner;

public class Lesson3Randomgame {
    public static void main(String[] args) {
        randomGame();
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
}
