package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution/lesson")
@RestController
public class GMTestController {

    @GetMapping("test")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("Lesmin/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1MathUtil.min(a, b);
    }

    @GetMapping("Lesmax/{a}/{b}")
    public int max(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1MathUtil.max(a, b);
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
