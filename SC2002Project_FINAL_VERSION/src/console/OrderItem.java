package console;

/**
Representing an OrderItem.
*/
public class OrderItem {
	enum Status {NEW_ORDER, READY_TO_COLLECT, CANCELLED, COMPLETED}
	/**
	* The id of this OrderItem.
	*/
	private int orderID;
	/**
	* The timeout of this OrderItem.
	*/
	private long timeout;
	/**
	* The order status of this OrderItem.
	*/
	private Status orderStatus;
	/**
	* The branch name of this OrderItem.
	*/
	private String branchName;
	
	/**
     * Creating a new OrderItem.
     */
	public OrderItem() {
		this.orderStatus = Status.NEW_ORDER;
	}
	
	/**
     * Creating a new OrderItem with the given information.
     * @param orderID This OrderItem's id.
     * @param orderStatus This OrderItem's status.
     * @param branchName This OrderItem's branch name.
     */
	public OrderItem(int orderID, Status orderStatus, String branchName) {
		this.timeout = 0;
		this.orderID = orderID;
        this.orderStatus = orderStatus;
        this.branchName = branchName;
	}
	
	/**
     * Creating a new OrderItem with order id.
     * @param orderID This OrderItem's id.
     */
	public OrderItem(int orderID) {
		this.timeout = 0;
		this.orderID = orderID;
        this.orderStatus = Status.NEW_ORDER;
	}
	
	/**
     * Getting the id of this OrderItem.
     * @return This OrderItem's id.
     */
	public int getOrderID() {
		return orderID;
	}

	/**
     * Getting the timeout of this OrderItem.
     * @return This OrderItem's timeout.
     */
	public long getTimeout() {
		return timeout;
	}

	/**
     * Changing the timeout of this OrderItem.
     * @param timeout This OrderItem's new timeout.
     */
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	/**
     * Getting the status of this OrderItem.
     * @return This OrderItem's status.
     */
	public Status getOrderStatus() {
		return orderStatus;
	}

	/**
     * Changing the status of this OrderItem.
     * @param orderStatus This OrderItem's new status.
     */
	public void setOrderStatus(Status orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
     * Getting the branch name of this OrderItem.
     * @return This OrderItem's branch name.
     */
	public String getBranchName() {
        return branchName;
    }

	/**
     * Changing the branch name of this OrderItem.
     * @param branchName This OrderItem's new branch name.
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
   	 }

    /**
     * Getting OrderItem details.
     * @return OrderItem details.
     */
	@Override
	public String toString() {
		return "orderID = " + orderID + ",     orderStatus=" + orderStatus + "\n";
	}	
}