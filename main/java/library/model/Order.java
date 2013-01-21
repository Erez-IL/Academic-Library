package library.model;

import library.model.Action;
import org.joda.time.DateTime;

public class Order extends Action {

	private DateTime expireData;

	public void setExpireData(DateTime expireData) {
		this.expireData = expireData;
	}

	public DateTime getExpireData() {

		return expireData;
	}
	@Override
	public String toString() {
		return "Order{ActionID: " + this.getActionId() +
				", actionItem=" + this.getActionItem().getItemName() +", Type: "+this.getActionItem().getLibraryItemDescription()+", UserID: "+this.getActionUser().getUserId()+
				", actionUser=" + this.getActionUser().getFirstName() + " " + this.getActionUser().getLastName() +
				", actionTime=" + this.getActionTime().toString() +
				'}';
	}
}
