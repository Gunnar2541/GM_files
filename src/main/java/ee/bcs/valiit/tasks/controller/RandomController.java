package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.solution.Lesson3Randomgame;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution")
@RestController

public class RandomController {

    //http://localhost:8080/solution/Random?x=1
    @GetMapping("Random")
    public String randomGameBegin(@RequestParam int x) {
        return Lesson3Randomgame.randomGameBegin(x);
    }

}
