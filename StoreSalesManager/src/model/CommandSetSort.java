package model;

import java.util.TreeMap;

public class CommandSetSort {
private Store store;
	
	public CommandSetSort(Store store) {
		this.store = store;
	}
	public void execute(TreeMap<String, Product> treeMap,String sortMethod) {
		store.setSort(treeMap,sortMethod);
		
	}
}
