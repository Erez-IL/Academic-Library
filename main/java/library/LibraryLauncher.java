package library;

import library.services.*;

public class LibraryLauncher {

	public static void main(String[] args) {

		UsersRepository usersRepository = new UsersRepository();
		ItemsRepository itemsRepository = new ItemsRepository();
		SwingInputHelper inputHelper = new SwingInputHelper();
		ReportEntryPoint reportEntryPoint = new ReportEntryPoint(inputHelper);
		UserEntryPoint userEntryPoint = new UserEntryPoint(usersRepository, inputHelper, reportEntryPoint);
		ItemEntryPoint itemEntryPoint = new ItemEntryPoint(itemsRepository, inputHelper, reportEntryPoint);
		ActionRepository actionRepository = new ActionRepository();
		ActionEntryPoint actionEntryPoint = new ActionEntryPoint(actionRepository, inputHelper, reportEntryPoint, usersRepository, itemsRepository);

		new RandomUsersGenerator(userEntryPoint).createListOfRandomUsers(50);
		new RandomItemsGenerator(itemEntryPoint).createListOfRandomItems(50);
		new RandomActionsGenerator(actionRepository, actionEntryPoint, usersRepository, itemsRepository).createListOfRandomActions(50);

		Boolean runinClass = true;

		while (runinClass) {
			try {

				switch (inputHelper.inputInteger("Main Menu\n" +
						"1. library Users screen\n" + "2. library Items\n" +
						"3. library Action\n" + "4. library Reports screen\n" +
						"5. library Graphs screen\n" + "6. Exit\n")) {
					case 1:
						userEntryPoint.showUsersMenu();
						break;
					case 2:
						itemEntryPoint.showItemMenu();
						break;
					case 3:
						actionEntryPoint.showActionMenu();
						break;
					case 4:
						reportEntryPoint.printerMainMenu(usersRepository, itemsRepository, actionRepository);
						break;
					case 5:
						new GraphsJpanel(usersRepository, itemsRepository, actionRepository, inputHelper).showGraphMenu();

						break;
					case 6:
						runinClass = false;
						break;
					default:
						inputHelper.messageBox("Error! enter only numbers between 1-6");
						break;
				}
			} catch (NumberFormatException e) {
				inputHelper.messageBox("Error! enter only number");
			}
		}
	}
}
