package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping ("employee")
@RestController

public class EmployeeController {
    private List<Employee> employeeList = new ArrayList<>();

//http://localhost:8080/employee/employee1
    @GetMapping("employee1")
    public Employee employee1() {
        Employee employee1 = new Employee();
        employee1.setName("Toomas");
        employee1.setAddress("Talle 1");
        employee1.setPhone(123456);

        return employee1;
    }
//http://localhost:8080/employee/employee2?name=Tim&address=kase%202&phone=456789
    @GetMapping("employee2")
    public Employee employee2(@RequestParam("name") String name,
        @RequestParam("address") String address,
        @RequestParam("phone") int phone){

        Employee employee2 = new Employee();
        employee2.setName(name);
        employee2.setAddress(address);
        employee2.setPhone(phone);

        return employee2;
       }

        //http://localhost:8080/employee/employee2
        @PostMapping("employee2")
        public void employee3(@RequestBody Employee employee2){
            System.out.println(employee2.getName());
        }
        //http://localhost:8080/employee/insertEmployee
        @PostMapping("insertEmployee")
        public void addEmployee(@RequestBody Employee employee) {
            employeeList.add(employee);
        }
        //http://localhost:8080/employee/findEmployeesList
        @GetMapping("findEmployeesList")
        public List<Employee> getEmployees() {
            return employeeList;
        }
        //http://localhost:8080/employee/findSingleEmployee/2
        @GetMapping("findSingleEmployee/{id}")
        public Employee getSingleEmployee(@PathVariable int id){
            return employeeList.get(id);
        }
        //http://localhost:8080/employee/replaceEmployee/2
        @PutMapping("replaceEmployee/{id}")
        public void replaceEmployee(@RequestBody Employee employee ,@PathVariable int id){
            employeeList.set(id, employee);
        }
        //http://localhost:8080/employee/deleteEmployee/2
        @DeleteMapping("deleteEmployee/{id}")
        public void deleteEmployee(@PathVariable int id) {
            employeeList.remove(id);
        }
}
