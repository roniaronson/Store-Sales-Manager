package model;

public class CommandAddProduct{

	private Store store;


	
	public CommandAddProduct(Store store) {
		this.store = store;
	}



	public boolean execute(String SN, String name, String buyPrice, String sellPrice,String customerName,String customerPhone,boolean messeges) throws Exception {
		return store.addProduct(SN,name,buyPrice,sellPrice,customerName,customerPhone,messeges);
		
	}




	
	
	
	
}
