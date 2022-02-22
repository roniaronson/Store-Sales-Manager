package storeController;

import java.util.InputMismatchException;
import java.util.TreeMap;

import listeners.StoreListener;
import listeners.ViewListeners;
import model.Customer;
import model.Product;
import model.CommandStore;
import storeView.View;

public class StoreController implements StoreListener,ViewListeners{
	private CommandStore storeCmd;
	private View view;
	
	
	public StoreController(CommandStore storeCmd, View view) {
		this.storeCmd = storeCmd;
		this.view = view;
		
		storeCmd.getStore().registerListener(this);
		view.registerListener(this);
		
	}


	@Override
	public void fireUpdatedProduct() {
		view.messegeToUser("Product Already Exists,Product Detail Has Been Updated", true,0);
		
	}


	@Override
	public void fireProductAdded() {
		view.messegeToUser("Product Added To Inventory", true,0);
		view.enableBtnUndo(true);
	}


	@Override
	public void fireSubscriberAdded(Customer customer) {
		view.messegeToUser(customer.getName()+" Subscribed!", true, 3);
		
	}
	
	@Override
	public void fireInvalidInput() {
		view.messegeToUser("Some Of The Fields Has Invalid input", false,0);

	}
	
	public void fireSearchFailed() {
		view.messegeToUser("Product Does Not Exists", false,1);
		
	}
	

	


	@Override
	public boolean addProductToModel(String SN, String name, String cost, String price,
											String customerName,String customerPhone,boolean messages) {
		try {
			return storeCmd.addProduct(SN,name, cost,price,customerName,customerPhone,messages);
		} catch (InputMismatchException e) {
			view.messegeToUser("Customer Phone Not Valid", false, 0);
			
		}
		 catch (Exception e) {
			view.messegeToUser(e.getMessage(), false, 0);
		 
		 }
		return false;
		
		
		
	}



	@Override
	public boolean findProductInModel(String SN, int window) {
		Product tmp = storeCmd.findProduct(SN);
		
		if (tmp!=null&&window == 1) {// search window
			view.setLblNameFound(tmp.getName());
			view.setLblCostFound(String.valueOf(tmp.getBuyPrice()));
			view.setLblPriceFound(String.valueOf(tmp.getSellPrice()));
			view.setLblSoldTo(tmp.getBuyer().toString());
			return true;
		}
		
		return false;
	}

	
	
	@Override
	public String getProductsFromModel() {
		
			String allproduct = storeCmd.showProducts();
			if(allproduct.isEmpty()) {
				view.messegeToUser("There are no products", false, 2);
				return "There are no products";	
			}
			view.messegeToUser(allproduct, true, 2);
			return allproduct;	
	}


	@Override
	public void undoModel() {
		storeCmd.undo();
		view.messegeToUser("Last Product Deleted", true, 6);
	}


	@Override
	public void fireInvalidField(String msg) {
		view.messegeToUser(msg, false, 0);

	}


	@Override
	public void showProfitInModel() {
		String allproduct = storeCmd.showProfit();
		if(allproduct.isEmpty()) 
			view.messegeToUser("There are no products", false, 4);	
		else {
			view.messegeToUser(allproduct, true, 4);
		}
			
	
	}


	@Override
	public String sendMessageToCustomers(String msg) {
		String messages = storeCmd.sendMessageToCustomers(msg);
		if(messages.isEmpty())
			view.messegeToUser("There are no customers that subscribe", false, 5);
		return messages;
	}


	@Override
	public void deleteAllModel() {
		storeCmd.deleteAllProducts();
		view.messegeToUser("All Products Have Been Deleted", true, 6);
	}


	@Override
	public void deleteBySN(String SN) {
		boolean ok = storeCmd.deleteBySN(SN);
		if(ok) {
			view.messegeToUser("Product Removed", true, 6);
		}
		else {
			view.messegeToUser("Product Not Found", false, 6);
		}
	}


	@Override
	public void setSortToModel(TreeMap<String, Product> treeMap,String selected) {
		storeCmd.setSort(treeMap,selected);
	}



	


	
	
	
		
}
