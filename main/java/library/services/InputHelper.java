/*
 User: Laptop
 Date: 03/01/13
 Time: 01:26
 */
package library.services;

import library.model.enums.EnumDepartments;
import library.model.enums.EnumItems;
import library.model.Item;
import library.model.User;

import java.util.Collection;

public interface InputHelper {

	void inputUserDetails(User user);

	int inputYear();

	EnumDepartments inputMegama();

	Collection<EnumDepartments> inputMegamot();

	void inputItemDetails(Item item);

	EnumItems inputItem();



}
