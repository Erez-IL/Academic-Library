/*
 User: Laptop
 Date: 03/01/13
 Time: 00:39
 */
package library.services;

import library.model.enums.EnumDepartments;
import library.model.enums.EnumItems;
import library.model.Item;
import library.model.User;

import javax.swing.*;
import java.util.Collection;
import java.util.HashSet;

public class SwingInputHelper implements InputHelper {

	@Override
	public void inputUserDetails(User user) {
		user.setUserId(inputField("Enter a userId number"));
		user.setFirstName(inputField("Enter your First Name"));
		user.setLastName(inputField("Enter your Last Name"));
		user.setAddress(inputField("Enter your Address"));
		user.setCredit(inputField("Enter your CreditCard"));
		user.setBalance(0);
	}


	@Override
	public EnumDepartments inputMegama() {
		return (EnumDepartments) JOptionPane.showInputDialog(null, "Choose now...", "Megama", JOptionPane.QUESTION_MESSAGE, null, EnumDepartments.values(), "");
	}

	@Override
	public int inputYear() {
		return inputInteger("Enter your Year");
	}

	public String inputField(String message) {
		return JOptionPane.showInputDialog(message);
	}

	public int inputInteger(String message) {
		return Integer.parseInt(inputField(message));
	}
	public void messageBox(String message){
		JOptionPane.showMessageDialog(null,message);
	}

	@Override
	public Collection<EnumDepartments> inputMegamot() {
		int amountOfMegamot = inputInteger(("Enter how many departments you have"));
		Collection<EnumDepartments> megamot = new HashSet<EnumDepartments>(amountOfMegamot);
		for (int i = 0; i < amountOfMegamot; i++) {
			megamot.add(inputMegama());
		}
		return megamot;
	}

	@Override
	public void inputItemDetails(Item item) {
		item.setItemId(inputField("Enter a ItemId number"));
		item.setItemName(inputField("Enter your First Name"));
		item.setItemDescription(inputField("Enter your Item Description"));
		item.setLibraryItemCopys(inputInteger("Item number of copys"));



	}
	@Override
	public EnumItems inputItem() {
		return (EnumItems) JOptionPane.showInputDialog(null, "Choose now...", "Item", JOptionPane.QUESTION_MESSAGE, null, EnumItems.values(), "");
	}


}
