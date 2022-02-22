package model;

public class Product {

	private String name;
	private String SN;
	private int buyPrice;
	private int sellPrice;
	private Customer buyer;
	
	public Product(String SN,String name, int buyPrice, int sellPrice, Customer buyer) {
		this.SN = SN;
		this.name = name;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.buyer = buyer;
	}
	public Product(String SN,String name, String buyPrice, String sellPrice, String customerName ,String customerPhoneNumber, boolean isNotification) {
		this.SN = SN;
		this.name = name;
		this.buyPrice = Integer.parseInt(buyPrice);
		this.sellPrice = Integer.parseInt(sellPrice);
		this.buyer = new Customer(customerName, customerPhoneNumber, isNotification);
	}
	
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	public String getBuyPriceString() {
		return String.valueOf(buyPrice);
	}
	public int getBuyPrice() {
		return this.buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getSellPriceString() {
		return String.valueOf(sellPrice);
	}
	public int getSellPrice() {
		return this.sellPrice;
	}
	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Customer getBuyer() {
		return buyer;
	}
	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("\nSerial Number: " + SN + "\nProduct: "+ name +"\nCost: " + buyPrice + "\nPrice: " + sellPrice);
		if(buyer==null)
			sb.append("\nIn Inventory\n");
		else 
			sb.append("\nSold To: "+buyer.toString()+"\n");
		return sb.toString();
	}
}
