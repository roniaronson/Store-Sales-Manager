package model;

import java.util.TreeMap;

public class CommandStore implements Command{
	private Store store;
	private CommandAddProduct addProduct;
	private CommandShowProducts showProducts;
	private CommandFindProduct findProduct;
	private CommandUndo undo;
	private CommandShowProfit profit;
	private CommandSendMessage sendMessage;
	private CommandDeleteAllProducts deleteAllProducts;
	private CommandDeleteBySN deleteBySN;
	private CommandSetSort setSort;
	
	public CommandStore(Store store) {
		this.store = store;
		this.addProduct = new CommandAddProduct(store);
		this.showProducts = new CommandShowProducts(store);
		this.findProduct = new CommandFindProduct(store);
		this.undo = new CommandUndo(store);
		this.profit = new CommandShowProfit(store);
		this.sendMessage = new CommandSendMessage(store);
		this.deleteAllProducts = new CommandDeleteAllProducts(store);
		this.deleteBySN = new CommandDeleteBySN(store);
		this.setSort = new CommandSetSort(store);
	}

	
	
	public Store getStore() {
		return store;
	}




	public boolean addProduct(String SN, String name,String buyPrice, String sellPrice,
											String customerName, String customerPhone, boolean messeges) throws Exception {
		return addProduct.execute(SN, name,buyPrice, sellPrice,customerName,customerPhone,messeges);
	}
	
	@Override
	public String showProducts() {
		return showProducts.execute();
	}
	
	@Override
	public Product findProduct(String SN) {
		return findProduct.execute(SN);
	}



	@Override
	public void undo() {
		undo.execute();
	}



	@Override
	public String showProfit() {
		return profit.execute();
		
	}



	@Override
	public String sendMessageToCustomers(String msg) {
		return sendMessage.execute(msg);
	}



	@Override
	public void deleteAllProducts() {
		deleteAllProducts.execute();
		
	}



	@Override
	public boolean deleteBySN(String SN) {
		return deleteBySN.execute(SN);
	}


	@Override
	public void setSort(TreeMap<String, Product> treeMap,String selected) {
		setSort.execute(treeMap,selected);
	}

	
	
}
