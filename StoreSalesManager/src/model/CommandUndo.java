package model;

public class CommandUndo {
	private Store store;

	public CommandUndo(Store store) {
		this.store = store;
	}
	
	
	public void execute() {
		store.loadMemento();
	}
	
}
