package console;

public class OrderItem {
	enum Status {NEW_ORDER, READY_TO_COLLECT, CANCELLED, COMPLETED}
	//private static int nextOrderID = 1;
	private int orderID;
	private long timeout;
	private Status orderStatus;
	private String branchName;
	
	public OrderItem() {
		//this.orderID = orderID; // Assign and increment the unique order ID
		this.orderStatus = Status.NEW_ORDER;
	}
	
	public OrderItem(int orderID, Status orderStatus, String branchName) {
		this.timeout = 0;
		this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.branchName = branchName;

        //nextOrderID = Math.max(orderID + 1, nextOrderID);
		//System.out.println("Add new order with id: " + this.orderID);
	}
	
	public OrderItem(int orderID) {
		this.timeout = 0;
		this.orderID = orderID;
        this.orderStatus = Status.NEW_ORDER;
        //nextOrderID = Math.max(orderID + 1, nextOrderID);
		//System.out.println("Add new order with id: " + this.orderID);
	}
	
	
	public int getOrderID() {
		return orderID;
	}

//	public void setOrderID(int orderID) {
//		this.orderID = orderID;
//	}

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
	public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
   	 }

	@Override
	public String toString() {
		return "orderID = " + orderID + ",     orderStatus=" + orderStatus + "\n";
	}	
}