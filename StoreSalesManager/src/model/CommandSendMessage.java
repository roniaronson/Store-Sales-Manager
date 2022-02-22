package model;

public class CommandSendMessage {

	private Store store;

	public CommandSendMessage(Store store) {
		this.store = store;
	}

	public String execute(String msg) {
		return store.sendMessage(msg);
	}
}
