package com.example.aims.view.payment;

import com.example.aims.controller.PayOrderController;
import com.example.aims.entity.order.Invoice;
import com.example.aims.entity.payment.PaymentTransaction;
import com.example.aims.mail.IMailer;
import com.example.aims.mail.MailInfo;
import com.example.aims.mail.gmail.GMailer;
import com.example.aims.repository.order.OrderRepository;
import com.example.aims.repository.order.OrderRepositoryImpl;
import com.example.aims.repository.payment.PaymentRepository;
import com.example.aims.repository.payment.PaymentRepositoryImpl;
import com.example.aims.subsystem.IPaymentSubsystem;
import com.example.aims.subsystem.PaymentSubsystem;
import com.example.aims.subsystem.vnpay.VNPaySubsystemController;
import com.example.aims.utils.AppConfig;
import com.example.aims.utils.PathConfig;
import com.example.aims.utils.Utils;
import com.example.aims.view.BaseScreenController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class PaymentScreenController extends BaseScreenController {
    @FXML
    private WebView webView;

    private WebEngine engine;
    private PayOrderController payOrderController;
    private Invoice invoice;
    private RuntimeException exception;

    public <T> PaymentScreenController(Scene currentScene, String currentScreenPath, T... data) {
        super(currentScene, currentScreenPath, data);
    }

    public PaymentScreenController() {
    }

    @Override
    public <T> void initData(T... data) {
        this.engine = webView.getEngine();
        this.invoice = (Invoice) data[0];
//        OrderRepository orderRepository = new OrderRepositoryImpl();
//        PaymentRepository paymentRepository = new PaymentRepositoryImpl();
//        IPaymentSubsystem vnPay = new PaymentSubsystem(new VNPaySubsystemController());
//        IMailer mailer = new GMailer();
//        payOrderController = new PayOrderController(orderRepository, paymentRepository, vnPay, mailer);
        payOrderController = AppConfig.getPayOrderController();
        engine.locationProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains("http://aismtest.com/vnpay_jsp/vnpay_return.jsp?")) {
                completePaymentTransaction(newValue);
            }
        });
        try {
            engine.load(payOrderController.generateURL(invoice.getTotalAmount(), "Payment Transaction"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void completePaymentTransaction(String newValue) {
        try {
            URI uri = new URI(newValue);
            String query = uri.getQuery();

            Map<String, String> params = Utils.parseQueryString(query);

            PaymentTransaction transaction = payOrderController.makePayment(params);

            payOrderController.saveOrder(transaction, invoice.getOrder());
            MailInfo mailBody = payOrderController.prepareMailBody(invoice, transaction);
            payOrderController.sendMail(mailBody);

            showResult(transaction);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            exception = e;
            showResult(e);
            e.printStackTrace();
        }
    }

    void showResult(PaymentTransaction paymentTransaction) {
        payOrderController.emptyCart();
        new ResultScreenController(currentScene, PathConfig.RESULT_SCREEN_PATH, paymentTransaction, invoice);
    }

    void showResult(RuntimeException e) {
        new ResultScreenController(currentScene, PathConfig.RESULT_SCREEN_PATH, e);
    }
}
