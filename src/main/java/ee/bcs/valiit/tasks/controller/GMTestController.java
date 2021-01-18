package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import ee.bcs.valiit.tasks.solution.SolutionLesson1MathUtil;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution")
@RestController
public class GMTestController {

    @GetMapping("test")
    public String getHelloWorld() {
        return "Hello World";
    }
    //http://localhost:8080/solution/Lesmin/3/9
    @GetMapping("Lesmin/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1MathUtil.min(a, b);
    }
    //http://localhost:8080/solution/Lesmax?a=3&b=9
    @GetMapping("Lesmax")
    public int max(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable) {
        return Lesson1MathUtil.max(aVariable, bVariable);
    }

    @GetMapping("LesisEven/{a}")
    public boolean isEven(@PathVariable("a") int a) {
        return Lesson1MathUtil.isEven(a);
    }

    @GetMapping("Lesmin3/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("b") int c) {
        return Lesson1MathUtil.min3(a,b,c);
    }


}
