package com.example.aims.controller;

import com.example.aims.entity.cart.Cart;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.order.Order;
import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.exception.payment.PaymentException;
import com.example.aims.exception.UnrecognizedException;
import com.example.aims.mail.IMailer;
import com.example.aims.mail.MailBody;
import com.example.aims.mail.MailInfo;
import com.example.aims.repository.order.OrderRepository;
import com.example.aims.repository.payment.PaymentRepository;
import com.example.aims.subsystem.IPaymentSubsystem;

import java.io.IOException;
import java.util.Map;

/*
* SRP: Vi phạm
  * saveOrder(), emptyCart()
  * Thực hiện nhiều chức năng: thanh toán, tạo đơn hàng, xóa giỏ hàng
  * Nên tách thành các module nhỏ hơn xử lý từng chức năng
* OCP: Tuân thủ
* LSP: Tuân thủ
* ISP: Ko áp dụng
* DIP: Vi phạm một phần
  * emptyCart()
  * Phụ thuộc vào singleton Cart
  * Thay thế singleton bằng service thông qua DI
 */
public class PayOrderController {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private IPaymentSubsystem vnPay;
    private IMailer mailer;

    public PayOrderController(OrderRepository orderRepository, PaymentRepository paymentRepository, IPaymentSubsystem vnPay, IMailer mailer){
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.vnPay = vnPay;
        this.mailer = mailer;
    }

    /*
    * Data Coupling
    * Functional Cohension
     */
    public PaymentTransaction makePayment(Map<String, String> res)
            throws PaymentException, UnrecognizedException {

        PaymentTransaction transaction = vnPay.getPaymentTransaction(res);
        return transaction;
    }

    /*
    * Data Coupling
    * Procedural Cohesion
      * Thực hiện các bước tạo và lưu đơn hàng theo quy trình
      * Không có giải pháp đề xuất
     */
    public void saveOrder(PaymentTransaction transaction, Order order)
    {
        Order savedOrder = orderRepository.createOrder(order);
        transaction.setOrder(savedOrder);
        paymentRepository.saveTransaction(transaction);
    }

    /*
    * Data Coupling
    * Functional Cohesion
     */
    public String generateURL(int amount, String content) throws IOException {
        return vnPay.generateURL(amount, content);
    }

    /*
    * Functional Cohesion
     */
    public void emptyCart(){
        Cart.getCart().getListCartMedia().clear();
    }

    /*
    * Data Coupling
    * Functional Cohesion
     */
    public void sendMail(MailInfo mailBody) {
        this.mailer.sendMail(mailBody);
    }

    /*
    * Data Coupling
    * Functional Cohesion
     */
    public MailInfo prepareMailBody(Invoice invoice, PaymentTransaction transaction)
    {
        String body = MailBody.createMailBody(invoice, transaction);
        return new MailInfo(invoice.getOrder().getDeliveryInfo().getEmail(), body);
    }
}
