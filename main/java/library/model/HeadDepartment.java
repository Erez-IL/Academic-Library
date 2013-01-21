package library.model;

import library.model.enums.EnumDepartments;

public class HeadDepartment extends User {

	public EnumDepartments department;

	public EnumDepartments getDepartment() {
		return department;
	}

	public void setDepartment(EnumDepartments department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "HeadDepartment [department=" + department + ", userId=" + getUserId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", address=" + getAddress() + ", credit=" + getCredit() + "]";
	}
}
