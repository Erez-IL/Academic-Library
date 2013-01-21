/*
 User: Laptop
 Date: 03/01/13
 Time: 00:04
 */
package library.services;

import library.model.Lend;
import library.model.*;
import org.joda.time.DateTime;

import java.util.Random;

public class RandomActionsGenerator {

	private final ActionEntryPoint actionEntryPoint;
	private final UsersRepository usersRepository;
	private final ItemsRepository itemsRepository;
	private final ActionRepository actionRepository;

	public RandomActionsGenerator(ActionRepository actionRepository,
								  ActionEntryPoint actionEntryPoint,
								  UsersRepository usersRepository,
								  ItemsRepository itemsRepository) {
		this.actionRepository = actionRepository;
		this.actionEntryPoint = actionEntryPoint;
		this.usersRepository = usersRepository;
		this.itemsRepository = itemsRepository;
	}

	//this Methods Create The Random list of Actions by Length
	public void createListOfRandomActions(int Length) {
		for (; Length > 0; Length--) {
			Lend temp = createLendActionDetails(inputUser(), inputItem());
			if (temp == null) { Length++; } else actionRepository.addAction(temp);
		}
	}

	public User inputUser() {

		int i = new Random().nextInt(usersRepository.getUsers().size());
		for (User user : usersRepository.getUsers()) {
			if (i == 0) return user;
			i -= 1;
		}
		return null;
	}

	public Item inputItem() {

		int i = new Random().nextInt(itemsRepository.getItems().size());
		for (Item item : itemsRepository.getItems()) {
			if (i == 0) return item;
			i -= 1;
		}
		return null;
	}

	public Lend createLendActionDetails(User user, Item item) {
		Lend action = new Lend();
		action.setActionItem(item);
		action.setActionUser(user);
		new DateTime();
		action.setActionTime(DateTime.now().minusWeeks(new Random().nextInt(50)));
		action.setExpireData(action.getActionTime().plusDays(10));
		item.setLibraryItemCopys(item.getLibraryItemCopys() - 1);
		item.setItemCopysAtUsers(item.getItemCopysAtUsers() + 1);
		action.setActiveAction(true);
		actionEntryPoint.setAndGetActionCounterAfterIncrement();
		action.setActionId(String.valueOf(actionEntryPoint.getActionCounter()));
		return action;
	}

	public Action lendItemRandomly() {

		User tempUser = inputUser();
		Item tempItem = inputItem();
		Lend tempLend;
		if ((tempUser == null) && (tempItem == null)) { return null; } else {
			int count = 0;
			if (tempItem.getLibraryItemCopys() >= 2) {

				for (Action action : actionRepository.getActions()) {
					if (action.getActionUser() == tempUser) count += 1;
				}

				if (tempUser instanceof Student) {
					if (count < 2) {
						tempLend = createLendActionDetails(tempUser, tempItem);
						return tempLend;
					} else { return null; }
				} else if (tempUser instanceof Lecturer) {
					if (count < ((Lecturer) tempUser).getDepartments().size() * 2) {
						tempLend = createLendActionDetails(tempUser, tempItem);
						return tempLend;
					} else {
						return null;
					}
				} else if (tempUser instanceof HeadDepartment) {
					if (count < 6) {
						tempLend = createLendActionDetails(tempUser, tempItem);
						return tempLend;
					} else { return null; }
				}
			} else { return null; }
		}
		return null;
	}
}
