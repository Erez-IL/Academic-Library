/*
 User: Laptop
 Date: 07/01/13
 Time: 17:59
 */
package library.services;

import library.model.Lend;
import library.model.Order;
import library.model.Return;
import library.model.*;
import library.model.enums.EnumItems;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.Collection;

public class GraphsJpanel {

	private final UsersRepository usersRepository;
	private final ItemsRepository itemsRepository;
	private final ActionRepository actionRepository;
	private final SwingInputHelper inputHelper;
	private DefaultPieDataset pieDataSet;
	private DefaultCategoryDataset barDataSet;

	public GraphsJpanel(UsersRepository usersRepository,
						ItemsRepository itemsRepository,
						ActionRepository actionRepository,
						SwingInputHelper inputHelper) {
		this.usersRepository = usersRepository;
		this.itemsRepository = itemsRepository;
		this.actionRepository = actionRepository;
		this.inputHelper = inputHelper;

	}

	public void showGraphMenu() {
	Boolean runningMenu = true;

	while (runningMenu) {
		try {

			switch (inputHelper.inputInteger("Graphs Menu\n" +
											"1. Users By Type Graphs\n" + "2. Items By Type Graphs\n" +
											"3. Action By Type Graphs\n" + "4. Lends By Month Graphs\n" +
											"5. Lends Per User\n" +"6. Lends Per Item\n"+ "7. Back To Menu")){
				case 1:
					createUserByTypeDataSet();
					showGraph("Users By Type");
					break;
				case 2:
					createItemByTypeDataSet();
					showGraph("Items By Type");
					break;
				case 3:
					createActionByTypeDataSet();
					showGraph("Actions By Type");
					break;
				case 4:
					createLendActionByMonthDataSet();
					showGraph("Lends By Month");
					break;
				case 5:
					createLandActionByUserTypeByMonthDataSet();
					showGraph("Lends Per User");
					break;
				case 6:
					createLendActionByItemTypeDataSet();
					showGraph("Lends Per Item");
					break;
				case 7:
					runningMenu = false;
					break;

				default:
					inputHelper.messageBox("Error! enter only numbers between 1-7");
					break;
			}
		} catch (NumberFormatException e) {
			inputHelper.messageBox("Error! enter only number");
		}
	}
}

	private void showGraph(String message){
	try{
		switch (inputHelper.inputInteger("Graphs Menu\n" +
								"1. Bar Graphs\n" + "2. Pie Graphs\n" + "3.Back To Menu")) {
			case 1:
				showBarGraph(message);
				break;
			case 2:
				showPieGraph(message);
				break;
			case 3:
				break;
			default:
				inputHelper.messageBox("Error! enter only numbers between 1-3");
				break;
		}
	}
	catch (NumberFormatException e) {
				inputHelper.messageBox("Error! enter only number");
	}
}

	private void showPieGraph(String message) {
		JFreeChart chart = ChartFactory.createPieChart("Pie Chart for " + message, pieDataSet, false, true, false);

		ChartFrame frame1 = new ChartFrame("Pie Chart for " + message, chart);
		frame1.setVisible(true);
		frame1.setSize(500, 500);

		while (frame1.isShowing()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
	}

	private void showBarGraph(String message) {

		JFreeChart chart = ChartFactory.createBarChart("Bar Chart for " + message, message + " Type", "Amount Of " + message, barDataSet, PlotOrientation.VERTICAL, false, true, false);
		chart.setBackgroundPaint(Color.gray);
		chart.getTitle().setPaint(Color.black);
		CategoryPlot p = chart.getCategoryPlot();
		p.setRangeGridlinePaint(Color.red);
		ChartFrame frame1 = new ChartFrame("Bar Chart for " + message, chart);
		frame1.setVisible(true);
		frame1.setSize(500, 500);

		while (frame1.isShowing()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
	}

	private void createUserByTypeDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();
		pieDataSet.setValue("Students", usersRepository.getUsersByClassType(Student.class).size());
		pieDataSet.setValue("Lecturer", usersRepository.getUsersByClassType(Lecturer.class).size());
		pieDataSet.setValue("HeadDepartment", usersRepository.getUsersByClassType(HeadDepartment.class).size());
		barDataSet.setValue(usersRepository.getUsersByClassType(Student.class).size(), "Amount Of Users", "Students");
		barDataSet.setValue(usersRepository.getUsersByClassType(Lecturer.class).size(), "Amount Of Users", "Lecturer");
		barDataSet.setValue(usersRepository.getUsersByClassType(HeadDepartment.class).size(), "Amount Of Users", "HeadDepartment");
	}

	private void createItemByTypeDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();

		for (int i = 0; i < EnumItems.values().length; i++) {
			Integer indexForPrinting = 0;
			Collection<Item> items = itemsRepository.getItems();
			for (Item item : items) {
				if (item.getLibraryItemDescription() == EnumItems.values()[i]) indexForPrinting += 1;
			}
			pieDataSet.setValue(EnumItems.values()[i].name(), indexForPrinting);
			barDataSet.setValue(indexForPrinting, "Amount Of Items", EnumItems.values()[i].name());
		}
	}

	private void createActionByTypeDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();
		pieDataSet.setValue("Lend", actionRepository.getActionByClassType(Lend.class).size());
		pieDataSet.setValue("Order", actionRepository.getActionByClassType(Order.class).size());
		pieDataSet.setValue("Return", actionRepository.getActionByClassType(Return.class).size());
		barDataSet.setValue(actionRepository.getActionByClassType(Lend.class).size(), "Amount Of Actions", "Lend");
		barDataSet.setValue(actionRepository.getActionByClassType(Order.class).size(), "Amount Of Actions", "Order");
		barDataSet.setValue(actionRepository.getActionByClassType(Return.class).size(), "Amount Of Actions", "Return");
	}

	private void createLendActionByMonthDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();
		int year=inputHelper.inputInteger("Please Enter A Year");
		int[] indexForPrinting = new int[12];
		Collection<Action> actions = actionRepository.getActions();
		for (Action action : actions) {
			if(action.getActionTime().getYear()==year && action instanceof Lend){
				int i = action.getActionTime().getMonthOfYear();
				indexForPrinting[i - 1] += 1;
			}
		}
		for (int i = 0; i < 12; i++) { pieDataSet.setValue(String.valueOf(i + 1), indexForPrinting[i]); }
		for (int i = 0; i < 12; i++) { barDataSet.setValue(indexForPrinting[i], "Action By Month", String.valueOf(i + 1));	}
	}

	private void createLandActionByUserTypeByMonthDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();
		int[] indexForPrinting = new int[3];
//		Collection<Action> actions = actionRepository.getActions();
		Collection<Lend> lends = actionRepository.getActionByClassType(Lend.class);
		for (Lend lend : lends) {
			if (lend.getActionUser() instanceof Student) indexForPrinting[0] += 1;
			if (lend.getActionUser() instanceof Lecturer) indexForPrinting[1] += 1;
			if (lend.getActionUser() instanceof HeadDepartment) indexForPrinting[2] += 1;
		}
		pieDataSet.setValue("Student", indexForPrinting[0]);
		pieDataSet.setValue("Lecturer", indexForPrinting[1]);
		pieDataSet.setValue("HeadDepartment", indexForPrinting[2]);
		barDataSet.setValue(indexForPrinting[0], "Action By User Type", "Student");
		barDataSet.setValue(indexForPrinting[1], "Action By User Type", "Lecturer");
		barDataSet.setValue(indexForPrinting[2], "Action By User Type", "HeadDepartment");
	}

	private void createLendActionByItemTypeDataSet() {
		pieDataSet = new DefaultPieDataset();
		barDataSet = new DefaultCategoryDataset();
		for (int i = 0; i < EnumItems.values().length; i++) {
			Integer indexForPrinting = 0;
			Collection<Lend> lends = actionRepository.getActionByClassType(Lend.class);
			for (Lend lend : lends) {
				if (lend.getActionItem().getLibraryItemDescription() == EnumItems.values()[i]) indexForPrinting += 1;
			}
			pieDataSet.setValue(EnumItems.values()[i].name(), indexForPrinting);
			barDataSet.setValue(indexForPrinting,"Action Per Item",EnumItems.values()[i].name());
		}
	}

}

