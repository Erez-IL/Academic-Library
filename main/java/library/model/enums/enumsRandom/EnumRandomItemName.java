package library.model.enums.enumsRandom;

public enum EnumRandomItemName {

	learnC(1), LearnJAVA(2), LearnCpp(3), LearnCss(4), LearnCSharp(5),
	LearnVBdotNet(6), LearnVB6(7), LearnJavaScript(8), Statistics(9), MedicalInformation(10),
	PCManagement(11), LearnObjectiveC(12), HowToDrawYourSelf(13);

	private int mId;

	private EnumRandomItemName(int id) {
		this.mId = id;
	}

	public int getId(EnumRandomItemName item) {
		return item.mId;
	}

}

