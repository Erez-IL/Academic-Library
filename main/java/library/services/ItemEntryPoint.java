package library.services;

import library.model.enums.EnumDepartments;
import library.model.Item;

import java.util.Collection;

public class ItemEntryPoint {

	private final ItemsRepository itemsRepository;
	private final SwingInputHelper inputHelper;
	private final ReportEntryPoint reportEntryPoint;

	public ItemEntryPoint(ItemsRepository itemsRepository, SwingInputHelper inputHelper, ReportEntryPoint reportEntryPoint) {
		this.itemsRepository = itemsRepository;
		this.inputHelper = inputHelper;
		this.reportEntryPoint=reportEntryPoint;

	}

	//Item Menu
	public void showItemMenu() {
		switch (inputHelper.inputInteger("Item Menu\n" +
				"1. Add Item\n" + "2. Remove Item\n" + "3. Edit Item\n" +
				"4. print Item\n" + "5. Exit")) {
			case 1:
				addItem(inputHelper);
				break;
			case 2:
				removeItem();
				break;
			case 3:
				editItem();
				break;
			case 4:
				reportEntryPoint.printerMenuForItems(itemsRepository);
				break;
			case 5:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-5");
				break;
		}
	}


	//Edit Item By ID
	private void editItem() {
		String itemId = inputHelper.inputField("Enter a itemId number you want to edit");
		Item item = itemsRepository.getItem(itemId);
		if (item != null) {
			inputHelper.inputItemDetails(item);
			Collection<EnumDepartments> departments = inputHelper.inputMegamot();
			for (EnumDepartments department : departments) {
				item.addDepartment(department);
			}
			System.out.println("Edit Finish For: " + item.toString());
		}
	}

	//remove Item By ID
	private void removeItem() {
		String itemId = inputHelper.inputField("Enter ID You Wish To DELETE");
		Item removed = itemsRepository.removeItem(itemId);
		if (removed == null) {
			System.out.println("No Item With This ID: " + itemId);
			showItemMenu();
		}
	}

	public void addItem(InputHelper inputHelper) {
		Item item = new Item();
		inputHelper.inputItemDetails(item);
		item.setLibraryItemDescription(inputHelper.inputItem());
		Collection<EnumDepartments> departments = inputHelper.inputMegamot();
		for (EnumDepartments department : departments) {
			item.addDepartment(department);
		}
		item.setItemCopysAtUsers(0);
		item.setIsAvailable(true);
		if (itemsRepository.getItem(item.getItemId()) == null) { itemsRepository.addItem(item); } else {
			System.out.println("Already in DataBase");
		}
	}
}



	


