package library.services;

import library.model.*;
import library.model.enums.EnumDepartments;

import java.util.Collection;

public class UserEntryPoint {

	private final UsersRepository userRepository;
	private final SwingInputHelper inputHelper;
	private final ReportEntryPoint reportEntryPoint;

	public UserEntryPoint(UsersRepository userRepository, SwingInputHelper inputHelper, ReportEntryPoint reportEntryPoint) {
		this.userRepository = userRepository;
		this.inputHelper = inputHelper;
		this.reportEntryPoint = reportEntryPoint;
	}

	//User Menu
	public void showUsersMenu() {

		switch (inputHelper.inputInteger("library.services Menu\n" +
				"1. Add user\n" + "2. Remove user\n" + "3. Edit user\n" +
				"4. print user\n" + "5. Exit")) {
			case 1:
				AddUsers();
				break;
			case 2:
				removeUser();
				break;
			case 3:
				editUser();
				break;
			case 4:
				reportEntryPoint.printerMenuForUsers(userRepository);
				break;
			case 5:

				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-5");
				break;
		}
	}

	//Edit User By ID
	private void editUser() {
		String userId = inputHelper.inputField("Enter a userId number you want to edit");
		User user = userRepository.getUser(userId);
		if (user != null) {
			inputHelper.inputUserDetails(user);
			//if user == Student
			if (user instanceof Student) {
				((Student) user).setYear(inputHelper.inputYear());
				((Student) user).setDepartment(inputHelper.inputMegama());
			}

			// if user == Lecturer
			if (user instanceof Lecturer) {
				Collection<EnumDepartments> departments = inputHelper.inputMegamot();
				for (EnumDepartments department : departments) {
					((Lecturer) user).addDepartment(department);
				}
			}
			//if User == HeadDepartment
			if (user instanceof HeadDepartment) {
				EnumDepartments department = inputHelper.inputMegama();
				((HeadDepartment) user).setDepartment(department);
			}
			System.out.println("Edit Finish For: " + user.toString());
		}
	}

	//remove User By ID
	private void removeUser() {
		String userId = inputHelper.inputField("Enter ID You Wish To DELETE");
		User removed = userRepository.removeUser(userId);
		if (removed == null) {
			System.out.println("No User With This ID: " + userId);
			showUsersMenu();
		}
	}

	//this methods create users by specifications from UI
	private void AddUsers() {
		try {
			switch (inputHelper.inputInteger("enter\n 1-  Student\n2-  Lecturer\n3-  Head Department\n")) {
				case 1:
					addStudent(inputHelper);
					break;
				case 2:
					addLecturer(inputHelper);
					break;
				case 3:
					addHeadDepartment(inputHelper);
					break;
				default:
					inputHelper.messageBox("Error! enter only numbers between 1-3");
					break;
			}
		} catch (NumberFormatException e) {
			inputHelper.messageBox("Error! enter only number");
		}
	}

	void addStudent(InputHelper inputHelper) {
		Student user = new Student();
		inputHelper.inputUserDetails(user);
		user.setDepartment(inputHelper.inputMegama());
		user.setYear(inputHelper.inputYear());
		if (userRepository.getUser(user.getUserId()) == null) { userRepository.addUser(user); } else {
			System.out.println("Already in DataBase");
		}
	}

	public void addHeadDepartment(InputHelper inputHelper) {
		HeadDepartment user = new HeadDepartment();
		inputHelper.inputUserDetails(user);
		user.setDepartment(inputHelper.inputMegama());
		if (userRepository.getUser(user.getUserId()) == null) { userRepository.addUser(user); } else {
			System.out.println("Already in DataBase");
		}
	}

	public void addLecturer(InputHelper inputHelper) {
		Lecturer user = new Lecturer();
		inputHelper.inputUserDetails(user);
		Collection<EnumDepartments> departments = inputHelper.inputMegamot();
		for (EnumDepartments department : departments) {
			user.addDepartment(department);
		}
		if (userRepository.getUser(user.getUserId()) == null) { userRepository.addUser(user); } else {
			System.out.println("Already in DataBase");
		}
	}
}



	


