package console;

public class OrderItem {
	enum Status {NEW_ORDER, ORDER_PROCESSING, READY_FOR_PICKUP, COMPLETED, CANCELLED}
	private int orderID;
	private long timeout;
	private Status orderStatus;
	
	public OrderItem(int orderID) {
		this.timeout = 0;
		this.orderID = orderID;
		this.orderStatus = Status.NEW_ORDER;
		System.out.println("Add new order with id: " + this.orderID);
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public Status getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Status orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "orderID = " + orderID + ",     orderStatus=" + orderStatus + "\n";
	}	
}
