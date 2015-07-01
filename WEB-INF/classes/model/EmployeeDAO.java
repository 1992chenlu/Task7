/*
 * Team 1
 * Task 7
 * 01/15/2015
 */
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.Employee;

public class EmployeeDAO extends GenericDAO<Employee> {
	public EmployeeDAO(ConnectionPool cp, String tableName) throws DAOException, RollbackException {
		super(Employee.class, tableName, cp);
		//Write Initialize later!
		
		Employee[] list = match();
		if (list.length == 0) {
			init();
		}
	}
	
	public void setPassword(String username, String password) throws RollbackException {
        	Employee employee = read(username);
			
			if (employee == null) {
				throw new RollbackException("User registered with \" "+username+" \" not exists");
			}
			
			employee.setPassword(password);
			
			update(employee);
	}
	
	public boolean isExisted(String username) throws RollbackException {
		Employee[] employees = match(MatchArg.equals("username", username));
		if (employees.length > 0) {
			return true;
		}
		return false;
	}
	
	public void init() throws RollbackException {
		Employee employee1 = new Employee();
		employee1.setFirstname("Li");
		employee1.setLastname("Kong");
		employee1.setUsername("employee1");
		employee1.setPassword("123");
		create(employee1);
		
		Employee employee2 = new Employee();
		employee2.setFirstname("Da");
		employee2.setLastname("Ming");
		employee2.setUsername("employee2");
		employee2.setPassword("123");
		create(employee2);
	}
	
	
}
