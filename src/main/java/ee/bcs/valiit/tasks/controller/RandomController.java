package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.solution.Lesson3Randomgame;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("solution")
@RestController

public class RandomController {

    Random random = new Random();
    int i = random.nextInt(100);
    int userGuessCount = 0;
    int userGuessCountMax = 10;

    //http://localhost:8080/solution/Random?userGuess=50
    @GetMapping("Random")
    public String randomGameWeb(@RequestParam int userGuess) {
        String output="";
        userGuessCount++;
        if (userGuessCount==userGuessCountMax) {
            output = "You are out of guesses! You lost! New game starts.";
            userGuessCount = 0;
            i = random.nextInt(100);
        } else if (userGuess>i) {
            output = "Try smaller. You have had " + userGuessCount + " guesses out of 10.";
        } else if (userGuess<i) {
            output = "Try bigger. You have had " + userGuessCount + " guesses out of 10.";
        } else {//  (userGuess == i)
            output = "You got it! You had " + userGuessCount + " guesses out of 10. New game starts.";
            userGuessCount = 0;
            i = random.nextInt(100);
        }

        return output;
    }

}
