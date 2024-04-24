package controllers;

import java.util.ArrayList;
import java.util.List;

import models.PaymentMethod;
import models.Staff;

public class PaymentMethodController {
	List<PaymentMethod> paymentMethods;

	public PaymentMethodController() {
		this.paymentMethods = new ArrayList<PaymentMethod>();
	}
	
	public PaymentMethod getPaymentMethodByName(String name) {
		
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void addPaymentMethods(PaymentMethod paymentMethod) {
		paymentMethods.add(paymentMethod);
	}
	
	public void deletePaymentMethod(String name) {
		
	}
	
}
