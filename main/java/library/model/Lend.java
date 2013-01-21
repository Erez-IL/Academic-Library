package library.model;

import org.joda.time.DateTime;

public class Lend extends Action {

	private DateTime expireData;

	public void setExpireData(DateTime expireData) {
		this.expireData = expireData;
	}

	public DateTime getExpireData() {

		return expireData;
	}

	@Override
	public String toString() {
		return "Lend{ActionID: " + this.getActionId() +
				", actionItem=" + this.getActionItem().getItemName() + ", Type: " + this.getActionItem().getLibraryItemDescription() + ", UserID: " + this.getActionUser().getUserId() +
				", actionUser=" + this.getActionUser().getFirstName() + " " + this.getActionUser().getLastName() +
				", actionTime=" + this.getActionTime().toString() +
				'}';
	}
}