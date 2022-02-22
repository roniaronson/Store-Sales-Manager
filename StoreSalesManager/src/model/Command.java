package model;

import java.util.TreeMap;

public interface Command {
	
	
	boolean addProduct(String SN,String name, String buyPrice, String sellPrice,String customerName,String customerPhone,boolean messeges) throws Exception;
	Product findProduct(String SN);
	String showProducts();
	String showProfit();
	void undo();
	String sendMessageToCustomers(String msg);
	void deleteAllProducts();
	boolean deleteBySN(String SN);
	void setSort(TreeMap<String, Product> treeMap,String selected);
}
