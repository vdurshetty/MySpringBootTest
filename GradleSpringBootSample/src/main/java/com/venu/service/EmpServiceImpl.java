package com.venu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.venu.model.Address;
import com.venu.model.Emp;

@Service("empServiceImpl")
public class EmpServiceImpl implements EmpService{
	
	
	private List<Emp> empList;
	
	@Autowired
	private Environment env;

	public EmpServiceImpl() {
	
		populateEmpList();
	
	}
	
	
	private void populateEmpList() {
		empList = new ArrayList<Emp>();
		
		Address address1 = new Address();
		address1.setDoorNo("10"); address1.setStreet("Pendle way"); address1.setSuburb("Pendle Hill"); address1.setPin("2145");
		Address address2 = new Address();
		address2.setDoorNo("20"); address2.setStreet("Wenty Street"); address2.setSuburb("Wentworthwille"); address2.setPin("2146");
		
		Emp emp1 = new Emp();
		emp1.setEid(273l); emp1.setEname("Venugopal"); emp1.setSal(12.34f);emp1.setAddress(address1);
		Emp emp2 = new Emp();
		emp2.setEid(243l); emp2.setEname("Durshetty"); emp2.setSal(122.334f);emp2.setAddress(address2);
		
		empList.add(emp1);
		empList.add(emp2);
	}
	
	@Override
	public boolean addEmp(Emp emp) throws Exception {
		empList.add(emp);
		return true;
	}

	@Override
	public Emp getEmp(long eid) throws Exception {
	     for (Emp emp : empList) {
	            if (emp.getEid()== eid) {
	                return emp;
	           }
	        }
	        return null;
	}


	@Override
	public List<Emp> getAllEmps() throws Exception {
		System.out.println("Applicaiton Properties log file value ;" + env.getProperty("logfile"));
		System.out.println("Job1 Properties lastname :" + env.getProperty("lastname"));
		return empList;
	}
	
	
	
	

}
