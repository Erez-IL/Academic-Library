package library.services;

import library.model.Lend;
import library.model.Return;
import library.model.*;
import library.model.enums.EnumDepartments;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class ActionEntryPoint {

	private final ActionRepository actionRepository;
	private final SwingInputHelper inputHelper;
	private final ReportEntryPoint reportEntryPoint;

	private final UsersRepository usersRepository;
	private final ItemsRepository itemsRepository;

	private AtomicInteger actionCounter = new AtomicInteger();

	public ActionEntryPoint(ActionRepository actionRepository,
							SwingInputHelper inputHelper,
							ReportEntryPoint reportEntryPoint,
							UsersRepository usersRepository,
							ItemsRepository itemsRepository) {
		this.actionRepository = actionRepository;
		this.inputHelper = inputHelper;
		this.reportEntryPoint = reportEntryPoint;

		this.usersRepository = usersRepository;

		this.itemsRepository = itemsRepository;
	}

	//Action Menu
	public void showActionMenu() {
		switch (inputHelper.inputInteger("Action Menu\n" +
				"1. Lend Item\n" + "2. Return Item\n" + "3. Order Item\n" +
				"4. print Menu\n" + "5. Exit")) {
			case 1:
				lendItem();
				break;
			case 2:
				returnItem();
				break;
			case 3:
				editUser();
				break;
			case 4:
				reportEntryPoint.printerMenuForActions(actionRepository);
				break;
			case 5:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-5");
				showActionMenu();
				break;
		}
	}

	//	//method to lend Items (no rules)
	public void lendItem() {
		User tempUser = usersRepository.getUser(inputHelper.inputField("Enter a User ID number"));
		Item tempItem = itemsRepository.getItem(inputHelper.inputField("Enter a Item ID number"));

		if ((tempUser == null) && (tempItem == null)) { inputHelper.messageBox("Error! user id or item id is incorrect"); } else {
			int count = 0;
			if (tempItem.getLibraryItemCopys() >= 2) {

				for (Action action : actionRepository.getActions()) {
					if (action.getActionUser() == tempUser) count += 1;
				}

				if (tempUser instanceof Student) {
					if (count < 2) {
						createLendActionDetails(tempUser, tempItem);
					} else { inputHelper.messageBox("Error! this Student all ready lend 2 items"); }
				} else if (tempUser instanceof Lecturer) {
					if (count < ((Lecturer) tempUser).getDepartments().size() * 2) {
						createLendActionDetails(tempUser, tempItem);
					} else {
						inputHelper.messageBox("Error! this Lecturer all ready lend " + ((Lecturer) tempUser).getDepartments().size() * 2 + " items");
					}
				} else if (tempUser instanceof HeadDepartment) {
					if (count < 6) {
						createLendActionDetails(tempUser, tempItem);
					} else { inputHelper.messageBox("Error! this HeadDepartment all ready lend 6 items"); }
				}
			} else { inputHelper.messageBox("This Item Have " + tempItem.getLibraryItemCopys() + "at the library"); }
		}
	}

	public void setAndGetActionCounterAfterIncrement() {
		actionCounter.incrementAndGet();
	}

	public int getActionCounter() {

		return Integer.valueOf(actionCounter.get());
	}

	public void createLendActionDetails(User user, Item item) {
		Lend action = new Lend();
		action.setActionItem(item);
		action.setActionUser(user);
		action.setActionTime(new DateTime().now());
		action.setExpireData(action.getActionTime().plusDays(10));
		action.setActionId(String.valueOf(actionCounter.incrementAndGet()));
		item.setLibraryItemCopys(item.getLibraryItemCopys() - 1);
		item.setItemCopysAtUsers(item.getItemCopysAtUsers() + 1);
		action.setActiveAction(true);
		actionRepository.addAction(action);
	}

	public void returnItem() {
		int indexForPrinting = 0;
		Collection<? extends Action> actions = actionRepository.getActionsByUserId(inputHelper.inputField("Enter a User ID number"));
		if (actions.isEmpty()) { inputHelper.messageBox("This User Dont Have Actions"); } else {
			for (Action action : actions) {
				indexForPrinting += 1;
				System.out.println(indexForPrinting + ". " + action);
			}
			String actionId = new String(inputHelper.inputField("Which Of Action ID In the console you like to return ?"));
			Action temp = actionRepository.getAction(actionId);
			temp.getActionItem().setItemCopysAtUsers(temp.getActionItem().getItemCopysAtUsers() - 1);
			temp.getActionItem().setLibraryItemCopys(temp.getActionItem().getLibraryItemCopys() + 1);
			temp.setActiveAction(false);

			int days = 0;
			while (DateTime.now().isAfter(temp.getActionTime().plusDays(days))) { days += 1; }
			if (temp.getActionUser() instanceof Student && days > 5) {
				temp.getActionUser().setBalance(temp.getActionUser().getBalance() - ((days - 5) * 10));
			}
			if (temp.getActionUser() instanceof Lecturer && days > 13) {
				temp.getActionUser().setBalance(temp.getActionUser().getBalance() - ((days - 13) * 5));
			}
//			if(temp.getActionUser() instanceof HeadDepartment)
			Return returnAction = new Return();
			returnAction.setActionItem(temp.getActionItem());
			returnAction.setActionUser(temp.getActionUser());
			returnAction.setActionTime(temp.getActionTime());
			returnAction.setActionId(temp.getActionId());
			returnAction.setReturnDate(DateTime.now());

			actionRepository.removeAction(temp.getActionId());
			actionRepository.addAction(returnAction);
		}
	}

	//Edit User By ID
	private void editUser() {
		String userId = inputHelper.inputField("Enter a userId number you want to edit");
		User user = usersRepository.getUser(userId);
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
}



	


