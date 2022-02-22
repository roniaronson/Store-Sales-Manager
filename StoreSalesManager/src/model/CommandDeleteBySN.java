package model;

public class CommandDeleteBySN {
	private Store store;

	public CommandDeleteBySN(Store store) {
		this.store = store;
	}
	
	
	public boolean execute(String SN) {
		return store.removeBySN(SN);
	}
	
}
