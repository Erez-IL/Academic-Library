package library.model.enums.enumsRandom;

public enum EnumFirstName {
	Filip(1), Erez(2), Oren(3), Hila(4), Tamar(5), Kobi(6), Haimon(7), Roslan(8),
	Tomer(9), Haim(10), Ronen(11), Nitzan(12), Sharon(13), Bentzi(14), Tal(15),
	Yevgeni(16), Olga(17), Anastasya(18), Vladimire(19), David(20), Yossi(21);

	private int mId;

	private EnumFirstName(int id) {
		this.mId = id;
	}

	public int getId(EnumFirstName item) {
		return item.mId;
	}

}


