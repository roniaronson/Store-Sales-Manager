package storeView;


import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listeners.ViewListeners;

public class View extends Application {
	private static ArrayList<ViewListeners> allListeners = new ArrayList<ViewListeners>();
	static StackPane spRootMiddle = new StackPane();
	static StackPane spRootMiddleMessages = new StackPane();
	private Label lblAddMesseges = new Label();
	private Label lblAddMesseges2 = new Label();
	private Label lblSearchMesseges = new Label();
	private Label lblShowProducts = new Label();
	private Label lblShowProfit = new Label();
	private Label lblSendMessages = new Label();
	private Label lblDeleteOptions = new Label();
	private Label lblSetSortMessage = new Label();

	private Label lblNameFound = new Label();
	private Label lblCostFound = new Label();
	private Label lblPriceFound = new Label();
	private Label lblSoldTo = new Label();

	private Button btnUndo = new Button("Undo Last Purchase");

	GridPane gpSelectSort = new GridPane();

	public View(Stage theStage, Boolean isFile) {
		theStage.setTitle("Store - Version 2021");
		Label lblBottomNames = new Label("By - Tor Hanan & Roni Aronson");
		LocalDate date = LocalDate.now();
		Label lblDate = new Label(date.toString());

		Button btnAddProduct = new Button("Add Product		");
		Button btnSearchProduct = new Button("Search Product	");
		Button btnCheckOut = new Button("Check Out		");
		Button btnShowProducts = new Button("Show All Products	");
		Button btnShowProfit = new Button("Show Profit		");
		Button btnSendMessages = new Button("Send Messages	");
		Button btnDeleteOptions = new Button("Delete Options	");
		Button btnExit = new Button("Exit");

		BorderPane brRootBox = new BorderPane();
		brRootBox.setPadding(new Insets(10));

		spRootMiddle.setAlignment(Pos.CENTER);

		VBox vbRootLeft = new VBox();
		vbRootLeft.setSpacing(10);
		vbRootLeft.setPadding(new Insets(10));
		vbRootLeft.setAlignment(Pos.TOP_LEFT);

		HBox hbRootTop = new HBox();
		hbRootTop.setSpacing(500);
		hbRootTop.setPadding(new Insets(10));
		hbRootTop.setAlignment(Pos.CENTER_LEFT);

		HBox hbRootBottom = new HBox();
		hbRootBottom.setSpacing(400);
		hbRootBottom.setPadding(new Insets(10));
		hbRootBottom.setAlignment(Pos.CENTER_LEFT);

		// ---------------------------------select sort type pane---------------------------------

		ToggleGroup tglSelect = new ToggleGroup();
		RadioButton rdoDESC = new RadioButton("Descending");
		RadioButton rdoASC = new RadioButton("Ascending");
		RadioButton rdoAddingOrder = new RadioButton("Adding Order");
		Label lblSelectSort = new Label("Select sort type :");
		Button btnSelectSort = new Button("Update");

		rdoDESC.setToggleGroup(tglSelect);
		rdoASC.setToggleGroup(tglSelect);
		rdoAddingOrder.setToggleGroup(tglSelect);

		rdoDESC.setTextFill(Color.DEEPPINK);
		rdoASC.setTextFill(Color.ORCHID);
		rdoAddingOrder.setTextFill(Color.BLUEVIOLET);

		gpSelectSort.setPadding(new Insets(10));
		gpSelectSort.setHgap(10);
		gpSelectSort.setVgap(10);
		gpSelectSort.setAlignment(Pos.TOP_LEFT);
		gpSelectSort.setVisible(true);

		gpSelectSort.add(lblSelectSort, 0, 0);
		gpSelectSort.add(rdoDESC, 0, 1);
		gpSelectSort.add(rdoASC, 0, 2);
		gpSelectSort.add(rdoAddingOrder, 0, 3);
		gpSelectSort.add(btnSelectSort, 2, 4);
		gpSelectSort.add(lblSetSortMessage, 2, 6);

// ---------------------------------add product pane---------------------------------
		Label lblSN = new Label("Serial Number:");
		Label lblName = new Label("Product Name:");
		Label lblCost = new Label("Cost:");
		Label lblPrice = new Label("Price:");
		Label lblCustomerDetails = new Label("Fill the Following Details:");
		Label lblCustomerName = new Label("Customer Name:");
		Label lblCustomerPhone = new Label("Phone Number:");
		Label lblAstirix0 = new Label("*");
		Label lblAstirix1 = new Label("*");
		Label lblAstirix2 = new Label("*");
		Label[] astirixes = { lblAstirix0, lblAstirix1, lblAstirix2 };
		for (Label l : astirixes) {
			l.setTextFill(Color.RED);
		}

		TextField tfSN = new TextField();
		TextField tfName = new TextField();
		TextField tfCost = new TextField();
		TextField tfPrice = new TextField();
		TextField tfCustomerName = new TextField();
		TextField tfCustomerPhone = new TextField();

		CheckBox cbNotifications = new CheckBox("Would you like to recive Notificatons About Sales?");

		Button btnClear = new Button("Clear");
		Button btnOK = new Button("OK");

		TextField[] textFieldsAddProduct = { tfSN, tfCustomerPhone, tfCustomerName};

		GridPane gpAddProduct = new GridPane();
		gpAddProduct.setPadding(new Insets(10));
		gpAddProduct.setHgap(10);
		gpAddProduct.setVgap(10);
		gpAddProduct.setAlignment(Pos.TOP_LEFT);
		gpAddProduct.setVisible(false);

		gpAddProduct.add(lblSN, 0, 0);
		gpAddProduct.add(tfSN, 1, 0);
		gpAddProduct.add(lblAstirix0, 2, 0);

		gpAddProduct.add(lblName, 0, 1);
		gpAddProduct.add(tfName, 1, 1);
		gpAddProduct.add(lblCost, 0, 2);
		gpAddProduct.add(tfCost, 1, 2);
		gpAddProduct.add(lblPrice, 0, 3);
		gpAddProduct.add(tfPrice, 1, 3);

		gpAddProduct.add(lblCustomerDetails, 0, 5, 2, 1);
		gpAddProduct.add(lblCustomerName, 0, 6);
		gpAddProduct.add(tfCustomerName, 1, 6);
		gpAddProduct.add(lblAstirix1, 2, 6);
		gpAddProduct.add(lblCustomerPhone, 0, 7);
		gpAddProduct.add(tfCustomerPhone, 1, 7);
		gpAddProduct.add(lblAstirix2, 2, 7);

		gpAddProduct.add(cbNotifications, 0, 8, 3, 1);
		gpAddProduct.add(btnClear, 0, 10);
		gpAddProduct.add(btnOK, 1, 10);

		gpAddProduct.add(lblAddMesseges, 0, 11, 3, 1);
		gpAddProduct.add(lblAddMesseges2, 0, 12, 3, 1);

		// ---------------------------------find product pane---------------------------------

		Label lblSNFind = new Label("Serial Number:");
		Label lblNameFind = new Label("Product:");
		Label lblCostFind = new Label("Cost:");
		Label lblPriceFind = new Label("Price:");
		Label lblSold = new Label("Sold To:");

		TextField tfSNFind = new TextField();

		Button btnSearch = new Button("Search");

		GridPane gpFindProduct = new GridPane();
		gpFindProduct.setPadding(new Insets(10));
		gpFindProduct.setHgap(10);
		gpFindProduct.setVgap(10);
		gpFindProduct.setAlignment(Pos.TOP_LEFT);
		gpFindProduct.setVisible(false);

		gpFindProduct.add(lblSNFind, 0, 0);
		gpFindProduct.add(tfSNFind, 1, 0);
		gpFindProduct.add(lblNameFind, 0, 1);
		gpFindProduct.add(lblNameFound, 1, 1);
		gpFindProduct.add(lblCostFind, 0, 2);
		gpFindProduct.add(lblCostFound, 1, 2);
		gpFindProduct.add(lblPriceFind, 0, 3);
		gpFindProduct.add(lblPriceFound, 1, 3);
		gpFindProduct.add(lblSold, 0, 4);
		gpFindProduct.add(lblSoldTo, 1, 4);
		gpFindProduct.add(btnSearch, 2, 0);

		gpFindProduct.add(lblSearchMesseges, 0, 10, 3, 1);

		// ---------------------------------display all products---------------------------------

		ScrollPane scpShowProducts = new ScrollPane();
		scpShowProducts.setPadding(new Insets(10));
		scpShowProducts.setVmax(440);
		scpShowProducts.setVisible(false);

		scpShowProducts.setContent(lblShowProducts);

		// ---------------------------------show profit pane---------------------------------

		ScrollPane scpShowProfit = new ScrollPane();
		scpShowProfit.setPadding(new Insets(10));
		scpShowProfit.setVmax(440);
		scpShowProfit.setVisible(false);

		scpShowProfit.setContent(lblShowProfit);
		// ---------------------------------send messages pane---------------------------------

		GridPane gpSendMessages = new GridPane();
		gpSendMessages.setPadding(new Insets(10));
		gpSendMessages.setHgap(10);
		gpSendMessages.setVgap(10);
		gpSendMessages.setAlignment(Pos.TOP_LEFT);
		gpSendMessages.setVisible(false);

		Label lblWriteMessage = new Label("Write Message:");
		TextField tfWriteMessage = new TextField();
		Button btnSend = new Button("Send");

		ScrollPane scpSendMessage = new ScrollPane();
		scpSendMessage.setPadding(new Insets(10));
		scpSendMessage.setVmax(440);
		scpSendMessage.setVisible(false);

		scpSendMessage.setContent(lblSendMessages);

		gpSendMessages.add(lblWriteMessage, 0, 0);
		gpSendMessages.add(tfWriteMessage, 1, 0);
		gpSendMessages.add(btnSend, 2, 1);
		gpSendMessages.add(lblSendMessages, 1, 3);

		BorderPane brRootBoxMessages = new BorderPane();
		brRootBoxMessages.setPadding(new Insets(10));

		Button btnShowMassages = new Button("Show New Messages");
		Button btnExitMessage = new Button("Exit");
		spRootMiddleMessages.setAlignment(Pos.CENTER);

		spRootMiddleMessages.getChildren().addAll(scpSendMessage, btnShowMassages, btnExitMessage);
		brRootBoxMessages.setBottom(btnExitMessage);
		brRootBoxMessages.setTop(btnShowMassages);
		brRootBoxMessages.setCenter(spRootMiddleMessages);
		Scene sceneMessages = new Scene(brRootBoxMessages, 400, 400);
		Stage theStageMessages = new Stage();
		theStageMessages.setTitle("New Messages");
		theStageMessages.setScene(sceneMessages);

		// ---------------------------------delete options pane---------------------------------
		GridPane gpDeleteOptions = new GridPane();
		gpDeleteOptions.setPadding(new Insets(10));
		gpDeleteOptions.setHgap(10);
		gpDeleteOptions.setVgap(10);
		gpDeleteOptions.setAlignment(Pos.TOP_LEFT);
		gpDeleteOptions.setVisible(false);

		ToggleGroup tglSelectDelete = new ToggleGroup();
		RadioButton rdoUndo = new RadioButton("Delete Last (Undo)");
		RadioButton rdoDeleteAll = new RadioButton("Delete All Products");
		RadioButton rdoDeleteBySN = new RadioButton("Delete By Serial-Number");

		rdoUndo.setToggleGroup(tglSelectDelete);
		rdoDeleteAll.setToggleGroup(tglSelectDelete);
		rdoDeleteBySN.setToggleGroup(tglSelectDelete);

		rdoUndo.setTextFill(Color.DEEPPINK);
		rdoDeleteAll.setTextFill(Color.ORCHID);
		rdoDeleteBySN.setTextFill(Color.BLUEVIOLET);

		GridPane gpUndo = new GridPane();
		gpUndo.setPadding(new Insets(10));
		gpUndo.setHgap(10);
		gpUndo.setVgap(10);
		gpUndo.setAlignment(Pos.CENTER);
		gpUndo.setVisible(false);

		btnUndo.setDisable(true);
		gpUndo.add(btnUndo, 0, 0);

		GridPane gpDeleteAll = new GridPane();
		gpDeleteAll.setPadding(new Insets(10));
		gpDeleteAll.setHgap(10);
		gpDeleteAll.setVgap(10);
		gpDeleteAll.setAlignment(Pos.CENTER);
		gpDeleteAll.setVisible(false);

		Button btnDeleteAll = new Button("Delete All Products");
		gpDeleteAll.add(btnDeleteAll, 0, 0);

		GridPane gpDeleteBySN = new GridPane();
		gpDeleteBySN.setPadding(new Insets(10));
		gpDeleteBySN.setHgap(10);
		gpDeleteBySN.setVgap(10);
		gpDeleteBySN.setAlignment(Pos.CENTER);
		gpDeleteBySN.setVisible(false);

		Label lblSNDelete = new Label("Serial Number:");
		TextField tfSNDelete = new TextField();
		Button btnSNDelete = new Button("Delete");

		gpDeleteBySN.add(lblSNDelete, 0, 0);
		gpDeleteBySN.add(tfSNDelete, 1, 0);
		gpDeleteBySN.add(btnSNDelete, 2, 0);

		Label lblSelectDelete = new Label("Select Delete Option:");

		gpDeleteOptions.add(lblSelectDelete, 0, 0);
		gpDeleteOptions.add(rdoUndo, 0, 1);
		gpDeleteOptions.add(rdoDeleteAll, 0, 2);
		gpDeleteOptions.add(rdoDeleteBySN, 0, 3);
		gpDeleteOptions.add(gpUndo, 0, 4);
		gpDeleteOptions.add(gpDeleteAll, 0, 4);
		gpDeleteOptions.add(gpDeleteBySN, 0, 4);
		gpDeleteOptions.add(lblDeleteOptions, 0, 9, 1, 2);
		// ---------------------------------file options---------------------------------

		if (!isFile) {
			gpSelectSort.setVisible(false);
			btnAddProduct.setVisible(true);
			btnCheckOut.setVisible(true);
			btnSearchProduct.setVisible(true);
			btnShowProducts.setVisible(true);
			btnShowProfit.setVisible(true);
			btnSendMessages.setVisible(true);
			btnDeleteOptions.setVisible(true);
		} else {
			gpSelectSort.setVisible(true);
			btnAddProduct.setVisible(false);
			btnCheckOut.setVisible(false);
			btnSearchProduct.setVisible(false);
			btnShowProducts.setVisible(false);
			btnShowProfit.setVisible(false);
			btnSendMessages.setVisible(false);
			btnDeleteOptions.setVisible(false);
		}

		hbRootTop.getChildren().addAll(lblDate);
		hbRootBottom.getChildren().addAll(lblBottomNames, btnExit);
		vbRootLeft.getChildren().addAll(btnAddProduct, btnSearchProduct, btnShowProducts, btnDeleteOptions,
				btnShowProfit, btnSendMessages);
		spRootMiddle.getChildren().addAll(gpSelectSort, gpAddProduct, gpFindProduct, scpShowProducts, scpShowProfit,
				gpSendMessages, gpDeleteOptions);
		brRootBox.setLeft(vbRootLeft);
		brRootBox.setCenter(spRootMiddle);
		brRootBox.setTop(hbRootTop);
		brRootBox.setBottom(hbRootBottom);
		btnAddProduct.setTextFill(Color.DEEPPINK);
		btnSearchProduct.setTextFill(Color.ORCHID);
		btnShowProducts.setTextFill(Color.BLUEVIOLET);
		btnDeleteOptions.setTextFill(Color.BLUE);
		btnShowProfit.setTextFill(Color.GREEN);
		btnSendMessages.setTextFill(Color.PERU);
		Scene scene = new Scene(brRootBox, 700, 500);
		theStage.setScene(scene);
		theStage.show();

		// ---------------------------------all buttons actions---------------------------------

		// ---------------------------------select sort---------------------------------
		btnSelectSort.setOnAction(e -> {
			lblSetSortMessage.setVisible(false);
			boolean ok = false;
			if (rdoASC.isSelected()) {
				allListeners.get(0).setSortToModel(null, "B");
				ok = true;
			} else if (rdoDESC.isSelected()) {
				allListeners.get(0).setSortToModel(null, "A");
				ok = true;
			} else if (rdoAddingOrder.isSelected()) {
				allListeners.get(0).setSortToModel(null, "C");
				ok = true;
			} else {
				messegeToUser("Must Select A Sort Option", false, 7);
			}
			if (ok) {
				showSelectedPane(null);
				btnAddProduct.setVisible(true);
				btnCheckOut.setVisible(true);
				btnSearchProduct.setVisible(true);
				btnShowProducts.setVisible(true);
				btnDeleteOptions.setVisible(true);
				btnShowProfit.setVisible(true);
				btnSendMessages.setVisible(true);
			}

		});

		// ---------------------------------add Product---------------------------------
		btnAddProduct.setOnAction(e -> {
			showSelectedPane(gpAddProduct);
			lblAddMesseges.setVisible(false);

		});

		btnClear.setOnAction(e -> {
			lblAddMesseges.setVisible(false);
			tfSN.clear();
			tfName.clear();
			tfCost.clear();
			tfPrice.clear();
			tfCustomerName.clear();
			tfCustomerPhone.clear();
			cbNotifications.setSelected(false);
			lblAddMesseges.setVisible(false);
			lblAddMesseges2.setVisible(false);

		});

		btnOK.setOnAction(e -> {
			lblAddMesseges.setVisible(false);
			lblAddMesseges2.setVisible(false);
			if (checkTextFields(textFieldsAddProduct, lblAddMesseges2)) {
			try {
					if (allListeners.get(0).addProductToModel(tfSN.getText(), tfName.getText(), tfCost.getText(),
							tfPrice.getText(), tfCustomerName.getText(), tfCustomerPhone.getText(),
							cbNotifications.isSelected())) {
						tfSN.clear();
						tfName.clear();
						tfCost.clear();
						tfPrice.clear();
						btnUndo.setDisable(false);
						cbNotifications.setSelected(false);
					}
				} catch (Exception e1) {
				}

			}
		});
		

		// ---------------------------------search product---------------------------------

		btnSearchProduct.setOnAction(e -> {
			showSelectedPane(gpFindProduct);
			lblSearchMesseges.setVisible(false);
		});

		btnSearch.setOnAction(e -> {
			lblSearchMesseges.setVisible(false);
			lblNameFound.setText("");
			lblCostFound.setText("");
			lblPriceFound.setText("");
			lblSoldTo.setText("");
			allListeners.get(0).findProductInModel(tfSNFind.getText(), 1);
		});

		// ---------------------------------show products---------------------------------
		btnShowProducts.setOnAction(e -> {
			showSelectedPane(null);
			scpShowProducts.setVisible(true);

			lblShowProducts.setText(allListeners.get(0).getProductsFromModel());
			lblShowProducts.setVisible(true);

		});

		// ---------------------------------delete---------------------------------
		btnDeleteOptions.setOnAction(e -> {
			showSelectedPane(gpDeleteOptions);
		});
		rdoUndo.setOnAction(e -> {
			gpUndo.setVisible(true);
			gpDeleteAll.setVisible(false);
			gpDeleteBySN.setVisible(false);
			lblDeleteOptions.setVisible(false);
		});
		btnUndo.setOnAction(e -> {
			allListeners.get(0).undoModel();
			btnUndo.setDisable(true);
		});
		rdoDeleteAll.setOnAction(e -> {
			gpUndo.setVisible(false);
			gpDeleteAll.setVisible(true);
			gpDeleteBySN.setVisible(false);
			lblDeleteOptions.setVisible(false);
		});
		btnDeleteAll.setOnAction(e -> {
			allListeners.get(0).deleteAllModel();
		});
		rdoDeleteBySN.setOnAction(e -> {
			gpUndo.setVisible(false);
			gpDeleteAll.setVisible(false);
			gpDeleteBySN.setVisible(true);
			lblDeleteOptions.setVisible(false);
		});
		btnSNDelete.setOnAction(e -> {
			allListeners.get(0).deleteBySN(tfSNDelete.getText());
			tfSNDelete.clear();
		});

		// ---------------------------------show profit---------------------------------
		btnShowProfit.setOnAction(e -> {
			showSelectedPane(null);
			scpShowProfit.setVisible(true);
			allListeners.get(0).showProfitInModel();

		});

		// ---------------------------------send message---------------------------------
		btnSendMessages.setOnAction(e -> {
			showSelectedPane(gpSendMessages);
			btnSendMessages.setVisible(true);
		});

		btnSend.setOnAction(e -> {
			lblSendMessages.setVisible(false);
			if (tfWriteMessage.getText().equalsIgnoreCase("")) {
				messegeToUser("Can't Send Empty Message", false, 5);
			} else {
				theStageMessages.show();
				scpSendMessage.setVisible(false);
				btnShowMassages.setVisible(true);
				lblSendMessages.setVisible(true);
				String messages = allListeners.get(0).sendMessageToCustomers(tfWriteMessage.getText());
				String[] allMessages = messages.split("\n");

				int numOfLabels = allMessages.length;
				Label[] labels = new Label[numOfLabels];

				VBox vbAllLabels = new VBox();

				for (int i = 0; i < numOfLabels; i++) {
					labels[i] = new Label(allMessages[i]);
					labels[i].setVisible(false);
					vbAllLabels.getChildren().add(labels[i]);
				}
				scpSendMessage.setContent(vbAllLabels);

				btnShowMassages.setOnAction(k -> {
					scpSendMessage.setVisible(true);
					btnShowMassages.setVisible(false);
					Thread t = new Thread(() -> {
						try {
							for (Label l : labels) {
								Thread.sleep(2000);

								Platform.runLater(() -> {
									l.setVisible(true);
								});
							}
						} catch (Exception d) {
						}
					});
					t.start();
				});
				btnExitMessage.setOnAction(j -> theStageMessages.close());

			}

		});

		// ---------------------------------exit---------------------------------
		btnExit.setOnAction(e -> Platform.exit());

	}

	// ---------------------------------mathods---------------------------------

	public static void showSelectedPane(Pane tempPane) {
		for (int i = 0; i < spRootMiddle.getChildren().size(); i++)
			spRootMiddle.getChildren().get(i).setVisible(false);
		if (tempPane != null)
			tempPane.setVisible(true);
	}

	private boolean checkTextFields(TextField[] tfArr, Label lblError) {
		boolean ok = true;
		for (int i = 0; i < 3; i++) {
			if (tfArr[i].getText().equalsIgnoreCase(""))
				ok = false;
		}
		if (!ok)
			messegeToUser("Must Fill the marked Fields", false, 3);
		return ok;
	}

	public void registerListener(ViewListeners newListener) {
		allListeners.add(newListener);
	}

	public void messegeToUser(String msg, boolean greenMsg, int window) {

		Label tmp = null;
		switch (window) {
		case 0:
			tmp = lblAddMesseges;
			break;
		case 1:
			tmp = lblSearchMesseges;
			break;
		case 2:
			tmp = lblShowProducts;
			break;
		case 3:
			tmp = lblAddMesseges2;
			break;
		case 4:
			tmp = lblShowProfit;
			break;
		case 5:
			tmp = lblSendMessages;
			break;
		case 6:
			tmp = lblDeleteOptions;
			break;
		case 7:
			tmp = lblSetSortMessage;
			break;
		}
		tmp.setText(msg);
		if (greenMsg)
			tmp.setTextFill(Color.GREEN);

		else
			tmp.setTextFill(Color.RED);
		tmp.setVisible(true);
	}

	public void enableBtnUndo(boolean enable) {
		if (enable)
			btnUndo.setDisable(false);
	}

	public void setLblNameFound(String name) {
		this.lblNameFound.setText(name);
		;
	}

	public void setLblCostFound(String cost) {
		this.lblCostFound.setText(cost);
	}

	public void setLblPriceFound(String price) {
		this.lblPriceFound.setText(price);
	}

	public void setLblSoldTo(String customer) {
		this.lblSoldTo.setText(customer);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
