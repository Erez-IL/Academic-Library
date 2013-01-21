/*
 User: Laptop
 Date: 03/01/13
 Time: 00:04
 */
package library.services;

public class RandomItemsGenerator {

	private final ItemEntryPoint entryPoint;
	private RandomInputHelper inputHelper = new RandomInputHelper();

	public RandomItemsGenerator(ItemEntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}

	//this Methods Create The Random list of users
	public void createListOfRandomItems(int Length) {
		for (; Length > 0; Length--)
					entryPoint.addItem(inputHelper);
	}
}
