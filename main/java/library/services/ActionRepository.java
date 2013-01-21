/*
 User: Laptop
 Date: 02/01/13
 Time: 22:32
 */
package library.services;

import library.model.Action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ActionRepository {

	private final Map<String, Action> actions = new HashMap<String, Action>();

	public <T extends Action> Collection<T> getActionByClassType(Class<T> type) {
		Collection<T> result = new ArrayList<T>();
		for (Action action : actions.values()) {
			if (type.isInstance(action)) {
				result.add((T) action);
			}
		}
		return result;
	}

	public Collection<Action> getActions() {
		return actions.values();
	}

	public Action getAction(String actionId) {
		return actions.get(actionId);
	}

	public <T extends Action> Collection<T> getActionsByUserId(String userId) {
		Collection<T> result = new ArrayList<T>();
		for (Action action : actions.values()) {
			if (action.getActionUser().getUserId().equals(userId)) result.add((T) action);
		}
		return result;
	}

	public void addAction(Action action) {
		actions.put(action.getActionId(), action);
	}

	public Action removeAction(String actionId) {
		Action removed = actions.remove(actionId);
		if (removed != null) {
			System.out.println("The Action With ID: " + actionId + " Was Removed");
		}
		return removed;
	}
}
