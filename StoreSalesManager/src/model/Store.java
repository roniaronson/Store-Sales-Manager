package model;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.RandomAccessFile;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import listeners.StoreListener;

import observer.Message;

import observer.Sender;


public class Store extends Thread implements Sender, Iterable<Entry<String, Product>> {

	private File file;

	private static Store store = null;
	private static ArrayList<StoreListener> allListeners = new ArrayList<StoreListener>();
	private TreeMap<String, Product> products = new TreeMap<String, Product>();
	private StoreMemento memento;
	private boolean ableToUndo = false;
	private String sortType = "";

	// ----------------------------- constractur -----------------------------

	private Store(File file) {
		this.file = file;
		if(file.length()!=0)
			loadFromFile();
	}

	public static Store getStore(File file) {
		if (store == null) 
			store = new Store(file);
		return store;
	}

	// ----------------------------- getters -----------------------------

	public String getSortType() {
		return this.sortType;
	}

	public String getHowManyProducts() {
		return String.valueOf(this.products.size());
	}

	public TreeMap<String, Product> getProducts() {
		return products;
	}

	// ----------------------------- setters -----------------------------

	public TreeMap<String, Product> setSort(TreeMap<String, Product> treeMap, String sortMethod) {
		if(treeMap== null) {
			treeMap = new TreeMap<String, Product>();
		}
			
		switch (sortMethod) {
		case "A"://DESC
			treeMap = new TreeMap<String, Product>(new ProductCompareDESC());
			sortType = "A";
			break;

		case "B"://ASC
			treeMap = new TreeMap<String, Product>();
			sortType = "B";
			break;

		case "C":// adding order
			treeMap = new TreeMap<String, Product>(new ProductCompareAddingOrder());
			sortType = "C";
			break;
		}
		this.products = treeMap;
		return treeMap;
	}

	// ----------------------------- Add Product -----------------------------

	public boolean addProduct(String SN, String name, String buyPrice, String sellPrice, String customerName,
			String customerPhone, boolean messages) throws Exception {
		int cost, price;

		price = checkValidCoin(sellPrice);
		cost = checkValidCoin(buyPrice);
		checkValidCustomer(customerName, customerPhone);

		if (IsExists(SN))
			updateProduct(SN, name, cost, price, customerName, customerPhone, messages);

		else {
			Customer customer = new Customer(customerName, customerPhone, messages);
			Product add = new Product(SN, name, cost, price, customer);
			if (products != null)
				saveMemento();
			products.put(SN, add);

			saveToFile(this.file);

			allListeners.get(0).fireProductAdded();
		}
		return true;
	}

	public void addProduct(Product newProduct) {
		products.put(newProduct.getSN(), newProduct);
	}

	private int checkValidCoin(String price) throws Exception {
		int tmp = 0;
		String str = "";
		if (price.equalsIgnoreCase(str) || price == null)
			tmp = 0;

		else
			try {
				tmp = Integer.parseInt(price);
			} catch (Exception e) {
				throw new Exception("Cost/Price Not Valid");
			}

		if (tmp < 0)
			throw new Exception("Cost/Price Not Valid");
		return tmp;
	}

	// ----------------------------- Update Product -----------------------------

	private void updateProduct(String SN, String name, int buyPrice, int sellPrice, String customerName,
			String customerPhone, boolean messeges) {
		Product update = find(SN);
		update.setName(name);
		update.setBuyPrice(buyPrice);
		update.setSellPrice(sellPrice);

		Customer customer = new Customer(customerName, customerPhone, messeges);
		update.setBuyer(customer);

		saveToFile(this.file);

		allListeners.get(0).fireUpdatedProduct();
	}

	// ----------------------------- Check -----------------------------

	
	public boolean IsExists(String SN) {
		if (products == null || products.get(SN) == null)
			return false;
		return true;
	}

	private void checkValidCustomer(String customerName, String customerPhone) throws Exception {

		if (customerPhone != "" && customerPhone.length() != 10)
			throw new Exception("Customer Phone Not Valid");

		else
			try {
				long num = Long.parseLong(customerPhone);
			} catch (Exception e) {
				throw new Exception("Customer Phone Not Valid");
			}

		char[] arr = customerName.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (Character.isDigit(arr[i]))
				throw new Exception("Customer Name Not Valid");
		}
	}

	// ----------------------------- Find Product -----------------------------

	public Product find(String SN) {

		Product tmp = products.get(SN);
		if (tmp == null) {
			allListeners.get(0).fireSearchFailed();
			return null;
		}
		return tmp;
	}

	// ----------------------------- Show Products -----------------------------

	public String showProducts() {
		if (products == null || products.isEmpty()) 
			return "";
		
			
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Product> entry : products.entrySet())
			sb.append(entry.getValue().toString());

		return sb.toString();

	}

	// ----------------------------- File + Iterator -----------------------------

	@Override
	public Iterator<Entry<String, Product>> iterator() {
		return new fileIterator(this.file);
	}

	public void saveToFile(File file) {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			String sortType = getSortType();
			raf.setLength(0); // clean file
			raf.writeUTF(sortType); // sortType : A - DESC B- ASC C- ADDING ORDER
			for (Map.Entry<String, Product> entry : products.entrySet()) {
				raf.writeUTF(entry.getValue().getSN()); // SN									
				raf.writeUTF(entry.getValue().getSN()); // product
				raf.writeUTF(entry.getValue().getName());
				raf.writeUTF(entry.getValue().getBuyPriceString());
				raf.writeUTF(entry.getValue().getSellPriceString());
				raf.writeUTF(entry.getValue().getBuyer().getName()); // customer
				raf.writeUTF(entry.getValue().getBuyer().getPhoneNumber());
				raf.writeBoolean(entry.getValue().getBuyer().isNotification());
			}
			raf.close();
		} catch (Exception e) {
		}
	}

	public void loadFromFile() {
		TreeMap<String, Product> newProducts = new TreeMap<String, Product>();
		Entry<String, Product> tmp;
		Iterator<Entry<String, Product>> fIterator = iterator();
		if (fIterator.hasNext()) {
			tmp = fIterator.next();
			newProducts = this.setSort(newProducts,tmp.getKey());
		}
		while (fIterator.hasNext()) {
			tmp = fIterator.next();
			newProducts.put(tmp.getKey(), tmp.getValue());
		}
		this.products = newProducts;
	}

	public void removeAllProducts() {

		Iterator<Entry<String, Product>> fIterator = iterator();
		if (fIterator.hasNext())
			fIterator.next();

		while (fIterator.hasNext()) {
			fIterator.next();
			fIterator.remove();
		}
		loadFromFile();
	}

	public boolean removeBySN(String SN) {

		Entry<String, Product> tmp;
		boolean flag = true;
		Iterator<Entry<String, Product>> fIterator = iterator();
		if (fIterator.hasNext())
			fIterator.next();

		while (fIterator.hasNext() && flag) {
			tmp = fIterator.next();
			if (tmp.getKey().equalsIgnoreCase(SN)) {
				fIterator.remove();
				flag = false;
			}
		}
		if(!flag) {
			loadFromFile();
			return true;
		}
		return false;
	}

	// ----------------------------- Memento -----------------------------

	public void saveMemento() {
		if (memento == null)
			memento = new StoreMemento(this);
		else
			memento.setMemento(this);
		ableToUndo = true;
	}

	public void loadMemento() {
		if (ableToUndo)
			products = memento.getProducts();
		ableToUndo = false;
		saveToFile(file);
	}

	// ----------------------------- Register Listener -----------------------------

	public void registerListener(StoreListener newListener) {
		allListeners.add(newListener);
	}

	// ----------------------------- Show Profit -----------------------------

	public String showProfit() {
		int totalProfit = 0;
		int entryProfit = 0;
		if (products == null || products.isEmpty()) {
			return "";
		}
			
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Product> entry : products.entrySet()) {
			entryProfit = entry.getValue().getSellPrice() - entry.getValue().getBuyPrice();
			totalProfit += entryProfit;
			sb.append(entry.getValue().getSN() + ": " + entryProfit + "$\n");

		}
		sb.append("------------------------------\nTotal Profit: " + totalProfit + "$");
		return sb.toString();
	}

	// ----------------------------- Send Message -----------------------------

	@Override
	public String sendMSG(Message msg) {
		StringBuffer sb = new StringBuffer();

		for (Map.Entry<String, Product> entry : products.entrySet()) {
			if (entry.getValue().getBuyer().isNotification()) {
				sb.append(entry.getValue().getBuyer().receiveMSG(this, msg));
			}
		}
		return sb.toString();
	}

	public String sendMessage(String msg) {
		Message newMSG = new Message(msg);
		return sendMSG(newMSG);
	}

	// ----------------------------- fileIterator -----------------------------

	public class fileIterator implements Iterator<Entry<String, Product>>  {

		private File file;
		private long pos = 0;
		private long last = -1;

		public fileIterator(File file) {
			this.file = file;
		}

		@Override
		public boolean hasNext() {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				boolean hasNext = (raf.length() - pos) > 0;
				raf.close();
				return hasNext;
			} catch (IOException e) {
				return false;
			}
		}

		@Override
		public Entry<String, Product> next() {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				Entry<String, Product> res = null;
				if (pos == 0) {
					res = new SimpleEntry<>(raf.readUTF(), null);
					last = pos;
					pos = raf.getFilePointer();	
				} else {	
					raf.seek(pos);	
					res = new SimpleEntry<>(raf.readUTF(), new Product(raf.readUTF(), raf.readUTF(), raf.readUTF(),
							raf.readUTF(), raf.readUTF(), raf.readUTF(), raf.readBoolean()));
					last = pos;
					pos = raf.getFilePointer();
				}
				raf.close();
				return res;
			} catch (Exception e) {
				return null;
			}
		}

		public void remove() {
			if (last == -1)
				throw new IllegalStateException();
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				byte[] data = new byte[(int) (raf.length() - pos)];
				raf.seek(pos);
				raf.read(data);
				raf.setLength(last);
				raf.seek(last);
				raf.write(data);
				raf.close();
				pos = last;
				last = -1;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

		public void update(String SN, String name, int buyPrice, int sellPrice, String customerName,
				String customerPhone, boolean messages) {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.read();
				raf.read();
				while (raf.getFilePointer() < raf.length()) {
					String fileSN = raf.readUTF();
					long pos = raf.getFilePointer();
					if (fileSN.equalsIgnoreCase(SN)) {
						raf.seek(pos);
						raf.writeUTF(name);
						raf.write(buyPrice);
						raf.write(sellPrice);
						raf.writeUTF(customerName);
						raf.writeUTF(customerPhone);
						raf.writeBoolean(messages);
					}
				}
				raf.close();
			} catch (IOException e) {
			}
		}

	}


}
