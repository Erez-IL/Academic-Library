/*
 User: Laptop
 Date: 02/01/13
 Time: 22:32
 */
package library.services;

import library.model.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemsRepository {


	private final Map<String, Item> items = new HashMap<String, Item>();



	public Collection<Item> getItems() {
		return items.values();
	}

	public Item getItem(String itemId) {
		Item result = items.get(itemId);
				if (result != null) {
					return result;
				}
		return null;
	}

	public void addItem(Item item) {
		items.put(item.getItemId(), item);
	}

	public Item removeItem(String itemId) {
		Item removed = items.remove(itemId);
		if (removed != null) {
			System.out.println("The User With ID: " + itemId + " Was DELETED");
		}
		return removed;
	}
}
