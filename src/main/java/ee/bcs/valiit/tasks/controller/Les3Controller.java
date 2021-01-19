package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution")
@RestController

public class Les3Controller {

    //http://localhost:8080/solution/Lessum?x=1,2,3 ok
    @GetMapping("Lessum")
    public int sum(@RequestParam int[] x) {
        return Lesson3.sum(x);
    }

    //http://localhost:8080/solution/Lesfactorial?x=8 ok
    @GetMapping("Lesfactorial")
    public int factorial(@RequestParam int x) {
        return Lesson3.factorial(x);
        }

    //http://localhost:8080/solution/Lessort?a=6,2,3 ok
    @GetMapping("Lessort")
    public int [] sort(@RequestParam int[] a) {
        return Lesson3.sort(a);
    }

    //http://localhost:8080/solution/LesreverseString?a=automaat läks katki
    //http://localhost:8080/solution/LesreverseString?a=automaat%20l%C3%A4ks%20katki
    @GetMapping("LesreverseString")
    public String reverseString(@RequestParam String a) {
        return Lesson3.reverseString(a);
    }

    // http://localhost:8080/solution/LesisPrime/8 ok
    @GetMapping("LesisPrime/{z}")
    public boolean isPrime(@PathVariable("z") int z){
            return Lesson3.isPrime(z);
        }

    //http://localhost:8080/solution/LesreverseString?a=automaat%20l%C3%A4ks%20katki
    //@GetMapping("LesreverseString")
    //public String reverseString(@RequestParam String a) {
    //    return Lesson3.reverseString(a);



}