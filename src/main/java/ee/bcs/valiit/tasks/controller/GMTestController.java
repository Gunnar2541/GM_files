package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("solution")
@RestController
public class GMTestController {

    @GetMapping("test")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping("Lesmin")
    public int min(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson1MathUtil.min(a, b);
    }

    @GetMapping("Lesmax")
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson1MathUtil.max(a, b);
    }

    @GetMapping("LesisEven")
    public boolean isEven(@RequestParam("a") int a) {
        return Lesson1MathUtil.isEven(a);
    }

    @GetMapping("Lesmin3")
    public int min3(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("b") int c) {
        return Lesson1MathUtil.min3(a,b,c);
    }


}
