package library.model;

import library.model.Action;
import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: Laptop
 * Date: 01/01/13
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
public class Return extends Action {
	private DateTime returnData;

	public void setReturnDate(DateTime returnData) {
		this.returnData = returnData;
	}

	public DateTime getReturnData() {

		return returnData;
	}
	@Override
	public String toString() {
		return "Return{ActionID: " + this.getActionId() +
				", actionItem=" + this.getActionItem().getItemName() +", Type: "+this.getActionItem().getLibraryItemDescription()+", UserID: "+this.getActionUser().getUserId()+
				", actionUser=" + this.getActionUser().getFirstName() + " " + this.getActionUser().getLastName() +
				", actionTime=" + this.getActionTime().toString() +", actionReturnDate="+returnData.toString()+
				'}';
	}
}
