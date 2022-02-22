package model;

public class CommandDeleteAllProducts {
	private Store store;

	public CommandDeleteAllProducts(Store store) {
		this.store = store;
	}
	
	
	public void execute() {
		store.removeAllProducts();
	}
	
}
