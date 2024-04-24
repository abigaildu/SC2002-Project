package models;

import controllers.CartController;

public class Order {
	public enum OrderStatus{
		NEW,
		READY,
		COMPLETED,
		CANCELLED
	}
	
	private CartController cart;
	private int orderId;
	private long timeout; 
	private OrderStatus orderStatus;
	
	public Order(CartController cart, int orderId){
		this.cart = cart;
		this.orderId = orderId;
		this.timeout = 0;
		this.orderStatus = OrderStatus.NEW;
	}

	public CartController getCart() {
		return cart;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	
}
