package listeners;

import java.util.TreeMap;

import model.Product;

public interface ViewListeners {
	boolean addProductToModel(String SN,String name,String cost,String price,String customerName,String customerPhone,boolean messeges) throws Exception;
	boolean findProductInModel(String SN,int window);
	String getProductsFromModel();
	void undoModel();
	void showProfitInModel();
	String sendMessageToCustomers(String text);
	void deleteAllModel();
	void deleteBySN(String text);
	void setSortToModel(TreeMap<String, Product> treeMap,String selected);

	
}
