package controllers;

import models.PaymentMethod;

public class PaymentControllerCustomer {
	private PaymentMethod paymentMethod;

	public PaymentControllerCustomer(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public boolean pay(CartController cart, OrderControllerCustomer orderControllerCustomer) {
		return paymentMethod.run(cart, orderControllerCustomer);
	}
}
