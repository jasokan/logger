package com.jagan.speedment;

import com.company.speedment.test.sql696688.Sql696688Application;
import com.company.speedment.test.sql696688.db0.sql696688.borrowed.Borrowed;
import com.company.speedment.test.sql696688.db0.sql696688.employee.Employee;
import com.company.speedment.test.sql696688.db0.sql696688.user.User;
import com.speedment.Manager;
import com.speedment.Speedment;

public class SpeedmentTest {

	public static void main(String... params) {
		Speedment speedment = new Sql696688Application().withPassword(
				"xH5%nS8%").build();
		Manager<User> users = speedment.managerOf(User.class);

		Manager<Borrowed> borrowed = speedment.managerOf(Borrowed.class);
		Manager<Employee> employees = speedment.managerOf(Employee.class);

		users.stream().map(User::toJson).forEach(System.out::println);

		/*try {
			Borrowed borrowedData = borrowed.newInstance().setBook("Test")
					.setEmployeeid((short) 1).setRef(new Integer(20)).persist();

		} catch (SpeedmentException se) {
			se.printStackTrace();
		}

		try {
			User user = users.newInstance().setAge(20).setName("Jagan")
					.setId(1L).persist();
			System.out.print("Hello, " + user.getName() + "!");
		} catch (SpeedmentException se) {
			System.out.print("Why are you so persistant?");
			se.printStackTrace();
		}*/
	}

}
