package ee.bcs.valiit.tasks.controller;

import ee.bcs.valiit.tasks.Lesson1MathUtil;
import ee.bcs.valiit.tasks.Lesson2;
import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    //http://localhost:8080/solution/Lesmax?a=3&b=9   ! ! ! Siin @RequestParam ! ! !
    @GetMapping("Lesmax")
    public int max(@RequestParam("a") int aVariable, @RequestParam("b") int bVariable) {
        return Lesson1MathUtil.max(aVariable, bVariable);
    }
    //http://localhost:8080/solution/Lesabs/3 ok
    @GetMapping("Lesabs/{a}")
    public int min(@PathVariable("a") int a) {
        return Lesson1MathUtil.abs(a);
    }

    //http://localhost:8080/solution/LesisEven/8
    @GetMapping("LesisEven/{a}")
    public boolean isEven(@PathVariable("a") int a) {
        return Lesson1MathUtil.isEven(a);
    }
    //http://localhost:8080/solution/Lesmin3/5/8/22
    @GetMapping("Lesmin3/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1MathUtil.min3(a,b,c);
    }
    //http://localhost:8080/solution/Lesmaxer/33/10/55 ok
    @GetMapping("Lesmaxer/{a}/{b}/{c}")
    public int maxer(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1MathUtil.maxer(a,b,c);
    }
    //http://localhost:8080/solution/exercise1?arr=1,2,3,4,5,6 OK
    @GetMapping("exercise1")
    public int [] exercise1(@RequestParam int [] arr) {
        return Lesson2.exercise1(arr);
    }
    //http://localhost:8080/solution/exercise2?a=5 OK
    @GetMapping("exercise2")
    public List<Integer> exercise2(@RequestParam("a") int aVariable) {
        return Lesson2.exercise2(aVariable);
    }

    //http://localhost:8080/solution/exercise33/3/3 ! ! ! VALE TULEMUS, kuna proge alla vajab ümber tegemist
    @GetMapping("exercise33/{a}/{b}")
    public int [] exercise33(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson2.exercise33(a, b);
    }

    //http://localhost:8080/solution/Lesexercise4/8
    @GetMapping("Lesexercise4/{a}")
    public int exercise4(@PathVariable("a") int a) {
        return Lesson2.exercise4(a);
    }
    //http://localhost:8080/solution/Lesexercise5/40
    @GetMapping("Lesexercise5/{a}")
    public int exercise5(@PathVariable("a") int a) {
        return Lesson2.exercise5(a);
    }
    //http://localhost:8080/solution/Lesexercise6/15/23
    @GetMapping("Lesexercise6/{a}/{b}")
    public int exercise6(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson2.exercise6(a,b);
    }

}
