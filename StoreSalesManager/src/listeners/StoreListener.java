package listeners;

import model.Customer;

public interface StoreListener {

	public void fireProductAdded();
	public void fireUpdatedProduct();
	public void fireSubscriberAdded(Customer customer);
	public void fireInvalidInput();
	public void fireSearchFailed();
	public void fireInvalidField(String msg);


	
}
