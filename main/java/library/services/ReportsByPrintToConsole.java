/*
 User: Laptop
 Date: 04/01/13
 Time: 08:46
 */
package library.services;

import library.model.Action;
import library.model.Item;
import library.model.User;
import library.model.enums.EnumItems;

import java.util.Collection;

public class ReportsByPrintToConsole {

	public void printUsersToConsole(Class<? extends User> type, UsersRepository repository) {
		int indexForPrinting = 0;
		Collection<? extends User> users = repository.getUsersByClassType(type);
		for (User user : users) {
			indexForPrinting += 1;
			System.out.println(indexForPrinting + ". " + user);
		}
		if (users.isEmpty()) System.out.println("\nNO library.services.User with " + type.getSimpleName() + " in memory");
	}

	public void printActionsToConsole(Class<? extends Action> type, ActionRepository repository) {
		int indexForPrinting = 0;
		Collection<? extends Action> actions = repository.getActionByClassType(type);
		for (Action action : actions) {
			indexForPrinting += 1;
			System.out.println(indexForPrinting + ". " + action);
		}
		if (actions.isEmpty()) System.out.println("\nNO library.services.Action with " + type.getSimpleName() + " in memory");
	}

	public void printItemsToConsole(EnumItems type, ItemsRepository repository) {
		int indexForPrinting = 0;
		Collection<Item> items = repository.getItems();
		for (Item item : items) {
			if (item.getLibraryItemDescription() == type) {
				indexForPrinting += 1;
				System.out.println(indexForPrinting + ". " + item);
			}
		}
		if (items.isEmpty()) System.out.println("\nNO library.services.Item with " + type.name() + " in memory");
	}

	public void printBalanceToConsole(UsersRepository repository) {
		int indexForPrinting = 0;
		Collection<? extends User> users = repository.getUsers();
		for (User user : users) {
			if (user.getBalance() != 0) {
				indexForPrinting += 1;
				System.out.println(indexForPrinting + ". " + "User Id: " + user.getUserId() + " Name: " + user.getFirstName() + " " + user.getLastName() + "Balance: " + user.getBalance());
			}
		}

		if (users.isEmpty()) { System.out.println("\nNO Users in memory"); } else if (indexForPrinting == 0) {
			System.out.println("\nAll Users in memory Have 0 Balance");
		}
	}

	public void printActionsByUserId(ActionRepository actionRepository, String userId) {
		int indexForPrinting = 0;
		Collection<? extends Action> actions = actionRepository.getActionsByUserId(userId);
		if (actions.isEmpty()) {System.out.println("This User Id : " + userId + " Dont Have Actions"); } else {
			for (Action action : actions) {
				indexForPrinting += 1;
				System.out.println(indexForPrinting + ". " + action);
			}
		}
	}
}
