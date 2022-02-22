package model;

import storeController.StoreController;
import storeView.View;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage theStage) throws Exception {
		File file = new File ("product.txt");
		Store store = Store.getStore(file);
		CommandStore storeCmd = new CommandStore(store);
		View view = new View(theStage, (file.length() == 0));
		StoreController controller = new StoreController(storeCmd, view);
	}
}
