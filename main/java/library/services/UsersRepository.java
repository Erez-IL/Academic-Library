/*
 User: Laptop
 Date: 02/01/13
 Time: 22:32
 */
package library.services;

import library.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UsersRepository {

	private final Map<String, User> users = new HashMap<String, User>();

	public <T extends User> Collection<T> getUsersByClassType(Class<T> type) {
		Collection<T> result = new ArrayList<T>();
		for (User user : users.values()) {
			if (type.isInstance(user)) {
				result.add((T) user);
			}
		}
		return result;
	}

	public Collection<User> getUsers() {
		return users.values();
	}

	public User getUser(String userId) {
		User result = users.get(userId);
				if (result != null) {
					return result;
				}
		return null;

	}

	public void addUser(User user) {
		users.put(user.getUserId(), user);
	}

	public User removeUser(String userId) {
		User removed = users.remove(userId);
		if (removed != null) {
			System.out.println("The User With ID: " + userId + " Was DELETED");
		}
		return removed;
	}
}
