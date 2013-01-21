package library.model.enums.enumsRandom;

public enum EnumLastName {
	Dre(1), Cohen(2), Joy(4), Kaz(5), Sason(6), Yoli(7), Shan(8), Rock(9),
	Moomy(10), Naama(11), fore(12), Mama(13), Feldman(14), Fishenzon(15),
	Nili(16), Stein(17), Barak(18), Benet(19), Livni(20);

	private int mId;

	private EnumLastName(int id) {
		this.mId = id;
	}

	public int getId(EnumLastName item) {
		return item.mId;
	}

}



