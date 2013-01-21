/*
 User: Laptop
 Date: 03/01/13
 Time: 01:28
 */
package library.services;

import library.model.enums.EnumDepartments;
import library.model.enums.enumsRandom.EnumFirstName;
import library.model.enums.enumsRandom.EnumLastName;
import library.model.enums.enumsRandom.EnumRandomItemName;
import library.model.enums.EnumItems;
import library.model.Item;
import library.model.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class RandomInputHelper implements InputHelper {

	@Override
	public void inputUserDetails(User user) {

		user.setFirstName(inputFirstName().name());
		user.setLastName(inputLastName().name());
		Random randomInteger = new Random();
		user.setUserId(String.valueOf(randomInteger.nextInt(55555555)));
		user.setAddress("Tel-Aviv ," + user.getLastName() + user.getFirstName() + " St." + String.valueOf(randomInteger.nextInt()));
		user.setCredit("4580" + String.valueOf(randomInteger.nextInt()) + String.valueOf(randomInteger.nextInt()));
		user.setBalance(0);
	}

	@Override
	public int inputYear() {
		return new Random().nextInt(20) + 1990;
	}

	@Override
	public EnumDepartments inputMegama() {
		Random randomEnum = new Random();
		int i = randomEnum.nextInt(EnumDepartments.values().length);
		return EnumDepartments.values()[i];
	}

	@Override
	public EnumItems inputItem() {
		Random randomEnum = new Random();
		int i = randomEnum.nextInt(EnumItems.values().length);
		return EnumItems.values()[i];
	}

	@Override
	public Collection<EnumDepartments> inputMegamot() {
		Collection<EnumDepartments> megamot = new HashSet<EnumDepartments>();
		for (EnumDepartments megama : EnumDepartments.values()) {
			if (new Random().nextBoolean()) {
				megamot.add(megama);
			}
		}
		return megamot;
	}

	public EnumFirstName inputFirstName() {
		Random randomEnum = new Random();
		int i = randomEnum.nextInt(EnumFirstName.values().length);
		return EnumFirstName.values()[i];
	}

	public EnumLastName inputLastName() {
		Random randomEnum = new Random();
		int i = randomEnum.nextInt(EnumLastName.values().length);
		return EnumLastName.values()[i];
	}

	public EnumRandomItemName inputItemName() {
		Random randomEnum = new Random();
		int i = randomEnum.nextInt(EnumRandomItemName.values().length);
		return EnumRandomItemName.values()[i];
	}



	@Override
	public void inputItemDetails(Item item) {
		Random randomInteger = new Random();
		item.setItemId(String.valueOf(randomInteger.nextInt(55555555)));
		item.setItemName(inputItemName().name());
		item.setItemDescription("this is about " + item.getItemName());
		item.setLibraryItemCopys(randomInteger.nextInt(10)+1);
	}
}
