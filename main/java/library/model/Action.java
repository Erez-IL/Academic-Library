package library.model;

import org.joda.time.DateTime;

public abstract class Action {

	private Item actionItem;
	private User actionUser;
	private String actionId;
	private DateTime actionTime;
	private boolean isActiveAction;

	public void setActiveAction(boolean activeAction) {
		isActiveAction = activeAction;
	}

	public boolean isActiveAction() {

		return isActiveAction;
	}



	public User getActionUser() {
		return actionUser;
	}

	public Item getActionItem() {
		return actionItem;
	}

	public void setActionItem(Item actionItem) {
		this.actionItem = actionItem;
	}

	public void setActionUser(User actionUser) {
		this.actionUser = actionUser;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {

		this.actionId = actionId;
	}

	public void setActionTime(DateTime actionTime) {
		this.actionTime = actionTime;
	}

	public DateTime getActionTime() {
		return actionTime;
	}


}
