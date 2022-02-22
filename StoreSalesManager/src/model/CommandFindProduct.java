package model;

public class CommandFindProduct {
	private Store store;

	public CommandFindProduct(Store store) {
		this.store = store;
	}
	
	public Product execute(String SN) {
		return store.find(SN);
		
	}
	
}
