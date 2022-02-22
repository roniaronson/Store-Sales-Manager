package observer;

//Observer
public interface Receiver {
	//String getSimNumber();
	String receiveMSG(Sender s, Message msg);
}
