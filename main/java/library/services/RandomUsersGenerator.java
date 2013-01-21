/*
 User: Laptop
 Date: 03/01/13
 Time: 00:04
 */
package library.services;

import java.util.Random;

public class RandomUsersGenerator {

	private final UserEntryPoint entryPoint;
	private RandomInputHelper inputHelper = new RandomInputHelper();

	public RandomUsersGenerator(UserEntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}

	//this Methods Create The Random list of users
	public void createListOfRandomUsers(int Length) {
		Random randomNumber = new Random();
		for (; Length > 0; Length--) {
			int i = randomNumber.nextInt(3) + 1;
			switch (i) {
				case 1:
					entryPoint.addStudent(inputHelper);
					break;
				case 2:
					entryPoint.addLecturer(inputHelper);
					break;
				case 3:
					entryPoint.addHeadDepartment(inputHelper);
					break;
			}
		}
	}
}
