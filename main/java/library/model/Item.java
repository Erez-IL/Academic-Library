package library.model;

import library.model.enums.EnumDepartments;
import library.model.enums.EnumItems;

import java.util.HashSet;
import java.util.Set;

public class Item {
	

	private String itemId;
    private String itemName;
    private String itemDescription;
    private int libraryItemCopys;
    private Boolean isAvailable;
    private int itemCopysAtUsers;
	private String categoryId;
	private EnumItems libraryItemDescription;
	private final Set<EnumDepartments> megamot = new HashSet<EnumDepartments>();




	public String getCategoriesId() {
		return categoryId;
	}

	public void setCategoriesId(String categoriesId) {
		this.categoryId = categoriesId;
	}

	public EnumItems getLibraryItemDescription() {
		return libraryItemDescription;
	}

	public void setLibraryItemDescription(EnumItems libraryItemDescription) {
		this.libraryItemDescription = libraryItemDescription;
		setCategoriesId(String.valueOf(libraryItemDescription.getId(libraryItemDescription)));
	}

	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getLibraryItemCopys() {
		return libraryItemCopys;
	}
	public void setLibraryItemCopys(int libraryItemCopys) {
		this.libraryItemCopys = libraryItemCopys;
	}


	public Boolean isAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getItemCopysAtUsers() {
		return itemCopysAtUsers;
	}
	public void setItemCopysAtUsers(int itemCopysAtUsers) {
		this.itemCopysAtUsers = itemCopysAtUsers;
	}
	
	@Override
	public String toString() {
		return libraryItemDescription+" [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemDescription=" + itemDescription
				+ ", libraryItemCopys=" + libraryItemCopys + ", departments="+megamot
				+ ", isAvailable=" + isAvailable
				+ ", itemCopysAtUsers=" + itemCopysAtUsers
				+ ", categoryId=" + categoryId + ", libraryItemDescription="
				+ libraryItemDescription + "]";
	}
	public Set<EnumDepartments> getMegamot() {
		return megamot;
	}
	@Deprecated
	public void setMegamot(Set<EnumDepartments> megamot) {
		this.megamot.clear();
		this.megamot.addAll(megamot);
	}

	public void addDepartment(EnumDepartments megama) {
		megamot.add(megama);
	}
	

	

	



	
	
	


}
