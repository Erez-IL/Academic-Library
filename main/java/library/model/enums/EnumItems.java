package library.model.enums;

public enum EnumItems {
	Disk(1), Book(2), CourseBook(3), Journal(4);

	private int mId;

	private EnumItems(int id) {
		this.mId = id;
	}

	public int getId(EnumItems item) {
		return item.mId;
	}
}


