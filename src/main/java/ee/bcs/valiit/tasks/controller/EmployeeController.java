package ee.bcs.valiit.tasks.controller;
import ee.bcs.valiit.tasks.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping ("employee")
@RestController

public class EmployeeController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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
                              @RequestParam("phone") int phone) {

        Employee employee2 = new Employee();
        employee2.setName(name);
        employee2.setAddress(address);
        employee2.setPhone(phone);

        return employee2;
    }

    //http://localhost:8080/employee/employee2
    @PostMapping("employee2")
    public void employee2(@RequestBody Employee employee2) {
        System.out.println(employee2.getName());
    }
    //http://localhost:8080/employee/employee3?name=Toomas&address=Paju_4&phone=55555
    @PostMapping("employee3")
    public void employee3(@RequestParam String name, @RequestParam String address, @RequestParam int phone){
    String sql = "INSERT INTO employee (name, address, phone) VALUES (:name, :address, :phone)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", name);
        paraMap.put("address", address);
        paraMap.put("phone", phone);
        jdbcTemplate.update(sql, paraMap);
    }

    //http://localhost:8080/employee/employee4
    @PostMapping("employee4")
    public void employee3(@RequestBody Employee employee1){
        String sql = "INSERT INTO employee (name, address) VALUES (:name, :address)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", employee1.getName());
        paraMap.put("address", employee1.getAddress());
        jdbcTemplate.update(sql, paraMap);
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
