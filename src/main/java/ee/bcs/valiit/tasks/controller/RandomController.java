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
    int userGuessCountmax = 10;

    //http://localhost:8080/solution/Random?x=50
    @GetMapping("Random")
    public String randomGameWeb(@RequestParam int userGuess) {
        userGuessCount++;
        if  (userGuess == i)
            return "You got it";
        else if (userGuess>i)
            return "Try smaller.";
        else if (userGuess<i)
            return "Try bigger.";
        else if (userGuessCount==userGuessCountmax)
            return "You are out of guesses! You lost";

    }
}
