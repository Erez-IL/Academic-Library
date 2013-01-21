package library.model.enums;

public enum EnumDepartments {
	Electricity(1), MedicalEquipment(2), Electronics(3), IndustrialManagement(4), Machinery(5), Software(6);

	private int mId;

	private EnumDepartments(int id) {
		this.mId = id;
	}

	public int getId(EnumDepartments item) {
		return item.mId;
	}

}
