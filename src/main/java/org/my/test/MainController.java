package org.my.test;

import org.my.test.repository.SkillsRepository;
import org.my.test.repository.EmployeeRepository;
import org.my.test.domain.Skills;
import org.my.test.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.my.test.domain.Users;
import org.my.test.repository.UserRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.my.test.report.ReportWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Controller    
@RequestMapping(path="/demo")
public class MainController {
    
	@Autowired 
	private UserRepository      userRepository;
        
        @Autowired 
	private EmployeeRepository  emRep;
        
        @Autowired 
	private SkillsRepository     skRep;
        
	@PostMapping(path="/add_user") 
	public @ResponseBody String addNewUser 
            (@RequestParam String name, 
             @RequestParam String password,
             @RequestParam Integer employee_id
            ) 
        {
		Users u = userRepository.findByName(name);
                if(u != null) return "exists";
                u = new Users();
		u.setName(name);
		u.setPassword(password);
                u.setActive(1);
                u.setEmployee(emRep.findOne(employee_id));
		userRepository.save(u);
		return "Saved";
	}
        
        @GetMapping(path="/all_users")
	public @ResponseBody Iterable<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
        @PostMapping(path="/add_employee") 
	public @ResponseBody String addNewEmployee 
            (@RequestParam String full_name, 
             @RequestParam String short_name,
             @RequestParam Integer age,
             @RequestParam String city
            ) 
        {		
		Employee e = new Employee();
		e.setFull_name(full_name);
                e.setShort_name(short_name);
                e.setAge(age);
                e.setCity(city);
		emRep.save(e);
		return "Saved";
	}
        
        @GetMapping(path="/all_employee")
	@ResponseBody
        public Iterable<Employee> getAllEmployee(){
            return emRep.findAll();
        }
        
        @GetMapping(path="/all_employee_without_user")
	@ResponseBody
        public Iterable<Employee> getAllEmployeeWithoutUser(){
            return emRep.findByUserNotExists();
        }
        
        @PostMapping(path="/add_skill") 
	public @ResponseBody String addNewSkill
            (@RequestParam String name, 
             @RequestParam String description,
             @RequestParam Integer employee_id
            ) 
        {		
		Skills s = new Skills();
		s.setName(name);
		s.setDescription(description);
                s.setEmployee(emRep.findOne(employee_id));
		skRep.save(s);
		return "Saved";
	}
        
        @GetMapping(path="/exel")
	@ResponseBody
        public ResponseEntity<Resource> makeExel() throws FileNotFoundException, IOException {

            Resource r = new UrlResource(new ReportWorkbook().makeReport("report.xls", emRep));
            return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+r.getFilename()+"\"")
            .body(r);
	}
}
