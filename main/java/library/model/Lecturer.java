package library.model;

import library.model.enums.EnumDepartments;

import java.util.HashSet;
import java.util.Set;

public class Lecturer extends User {

	public final Set<EnumDepartments> departments = new HashSet<EnumDepartments>();

	public Set<EnumDepartments> getDepartments() {
		return departments;
	}

	@Deprecated
	public void setDepartments(Set<EnumDepartments> departments) {
		this.departments.clear();
		this.departments.addAll(departments);
	}

	public void addDepartment(EnumDepartments department) {
		departments.add(department);
	}

	@Override
	public String toString() {
		return "Lecturer [department=" + departments + ", userId=" + getUserId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", address=" + getAddress() + ", credit=" + getCredit() + ", Balance="+getBalance() +"]";
	}

}

	
	
	

