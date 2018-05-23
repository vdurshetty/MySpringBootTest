package com.venu.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.venu.model.Emp;

@Component
public interface EmpService {
	
	public boolean addEmp(Emp emp) throws Exception;
	public Emp getEmp(long eid) throws Exception;
	public List<Emp> getAllEmps() throws Exception;

}
