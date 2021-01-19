package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.Lesson3Randomgame;
import ee.bcs.valiit.tasks.solution.Lesson3Randomgame;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution")
@RestController

public class RandomController {

    //http://localhost:8080/solution/Lessum?x=1,2,3 ok
    @GetMapping("Random")
    public int sum(@RequestParam int[] x) {
        return Lesson3Randomgame.sum(x);
    }


}
