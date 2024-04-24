package models;

import controllers.CartController;

public class OrderItem {
	public enum OrderStatus {NEW_ORDER, READY_TO_COLLECT, COLLECTED, CANCELLED}
	private CartController cart;
	private int orderId;
	private OrderStatus orderStatus;
	private long timeout;
	
	public OrderItem(CartController cart, int orderId) {
		this.cart = cart;
		this.orderId = orderId;
		this.orderStatus = OrderStatus.NEW_ORDER;
		this.timeout = 0;
	}
	
	public OrderItem(CartController cart, int orderId, OrderStatus orderStatus) {
		this.cart = cart;
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.timeout = 0;
	}

	public CartController getCart() {
		return cart;
	}

	public void setCart(CartController cart) {
		this.cart = cart;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}