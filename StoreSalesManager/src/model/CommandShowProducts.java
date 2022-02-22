package model;

public class CommandShowProducts {
	private Store store;
	
	public CommandShowProducts(Store store) {
		this.store = store;
	}
	public String execute() {
		return store.showProducts();
		
	}

}
