/*
 User: Laptop
 Date: 10/01/13
 Time: 18:52
 */
package library.services;

import library.model.*;
import library.model.Lecturer;
import library.model.enums.EnumItems;

public class ReportEntryPoint {

	private final SwingInputHelper inputHelper;
	ReportsByPrintToConsole printer = new ReportsByPrintToConsole();

	public ReportEntryPoint(SwingInputHelper inputHelper) {
		this.inputHelper = inputHelper;
	}

	public void printerMainMenu(UsersRepository usersRepository,
								ItemsRepository itemsRepository,
								ActionRepository actionRepository) {
		switch (inputHelper.inputInteger("Printing User Menu\n" +
				"1. Users\n" + "2. Items\n" + "3. Actions\n" + "4. Back")) {
			case 1:
				printerMenuForUsers(usersRepository);
				break;
			case 2:
				printerMenuForItems(itemsRepository);
				break;
			case 3:
				printerMenuForActions(actionRepository);
				break;
			case 4:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-4");
				printerMenuForUsers(usersRepository);
		}
	}

	public void printerMenuForUsers(UsersRepository usersRepository) {
		switch (inputHelper.inputInteger("Printing User Menu\n" +
				"1. Students\n" + "2. Teachers\n" + "3. Rakazim\n" + "4. All\n" + "5. Balance\n" + "6. Back")) {
			case 1:
				printer.printUsersToConsole(Student.class, usersRepository);
				break;
			case 2:
				printer.printUsersToConsole(Lecturer.class, usersRepository);
				break;
			case 3:
				printer.printUsersToConsole(HeadDepartment.class, usersRepository);
				break;
			case 4:
				printer.printUsersToConsole(Student.class, usersRepository);
				printer.printUsersToConsole(Lecturer.class, usersRepository);
				printer.printUsersToConsole(HeadDepartment.class, usersRepository);
				break;
			case 5:
				printer.printBalanceToConsole(usersRepository);
				break;
			case 6:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-5");
				printerMenuForUsers(usersRepository);
		}
	}

	public void printerMenuForActions(ActionRepository actionRepository) {
		switch (inputHelper.inputInteger("Printing Action Menu\n" +
				"1. Lends\n" + "2. Orders\n" + "3. Returns\n" + "4. All\n"+ "5. Action By User ID\n" + "6. Back")) {
			case 1:
				printer.printActionsToConsole(Lend.class, actionRepository);
				break;
			case 2:
				printer.printActionsToConsole(Order.class, actionRepository);
				break;
			case 3:
				printer.printActionsToConsole(Return.class, actionRepository);
				break;
			case 4:
				printer.printActionsToConsole(Lend.class, actionRepository);
				printer.printActionsToConsole(Order.class, actionRepository);
				printer.printActionsToConsole(Return.class, actionRepository);
				break;
			case 5:
				printer.printActionsByUserId(actionRepository,inputHelper.inputField("Please Enter User Id"));
				break;
			case 6:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-5");
				printerMenuForActions(actionRepository);
		}
	}

	public void printerMenuForItems(ItemsRepository itemsRepository) {
		switch (inputHelper.inputItem()) {
			case Book:
				printer.printItemsToConsole(EnumItems.Book, itemsRepository);
				break;
			case CourseBook:
				printer.printItemsToConsole(EnumItems.CourseBook, itemsRepository);
				break;
			case Disk:
				printer.printItemsToConsole(EnumItems.Disk, itemsRepository);
				break;
			case Journal:
				printer.printItemsToConsole(EnumItems.Journal, itemsRepository);
				break;
		}
	}
}
