package com.venu.service;

import java.util.List;

import com.venu.model.Emp;

public interface EmpService {
	
	public boolean addEmp(Emp emp) throws Exception;
	public Emp getEmp(long eid) throws Exception;
	public List<Emp> getAllEmps() throws Exception;

}
