package library.model;

import library.model.enums.EnumDepartments;

public class Student extends User {

	public int year;
	public EnumDepartments department;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public EnumDepartments getDepartment() {
		return department;
	}

	public void setDepartment(EnumDepartments department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [year=" + year + ", department=" + department + ", userId=" + getUserId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", address=" + getAddress() + ", credit=" + getCredit()  +", Balance="+getBalance() +"]";
	}
}