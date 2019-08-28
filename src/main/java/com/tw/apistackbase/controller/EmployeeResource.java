package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	List<Employee> employeeList = new ArrayList();

	@GetMapping(path = "/")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees(){
		employeeList.add(new Employee("001","小红",20,"female"));
		employeeList.add(new Employee("002","小明",22,"male"));
		return employeeList;

	}
	@RequestMapping(path = "/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Employee> insertEmployee(@RequestBody Employee employee){
		employeeList.add(employee);
		
		return employeeList;
		
	}
	
	@RequestMapping(path = "/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String id){
		for(Employee employee:employeeList){
			if(employee.getId().equals(id)){
				return ResponseEntity.ok(employee);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable String id,@RequestBody Employee employee){
		for(int i = 0;i<employeeList.size();i++){
			if(id.equals(employeeList.get(i).getId())){
				employeeList.set(i, employee);
			}
		}
		return ResponseEntity.ok(employee);
	}
	@DeleteMapping(path = "/{id}")
	public void deleteEmployee(@PathVariable String id){
		for(Employee employee : employeeList){
			if(employee.getId().equals(id)){
				employeeList.remove(employee);
			}
		}
	}
}
