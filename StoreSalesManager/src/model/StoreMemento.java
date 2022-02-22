package model;

import java.util.TreeMap;

public class StoreMemento {
	
	TreeMap<String, Product> products;
	
	

	public StoreMemento(Store store) {
		
		this.products =  new TreeMap<String, Product>(store.getProducts());
	}



	public TreeMap<String, Product> getProducts() {
		return products;
	}



	public void setMemento(Store store) {
		this.products =  new TreeMap<String, Product>(store.getProducts());
	}
	
}
