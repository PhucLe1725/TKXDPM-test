package com.example.aims.utils;

import com.example.aims.controller.PayOrderController;
import com.example.aims.mail.IMailer;
import com.example.aims.mail.gmail.GMailer;
import com.example.aims.repository.AIMSDB;
import com.example.aims.repository.media.BookRepository;
import com.example.aims.repository.media.CDRepository;
import com.example.aims.repository.media.DVDRepository;
import com.example.aims.repository.media.LPRepository;
import com.example.aims.repository.media.impl.BookRepositoryImpl;
import com.example.aims.repository.media.impl.CDRepositoryImpl;
import com.example.aims.repository.media.impl.DVDRepositoryImpl;
import com.example.aims.repository.media.impl.LPRepositoryImpl;
import com.example.aims.repository.order.OrderRepository;
import com.example.aims.repository.order.OrderRepositoryImpl;
import com.example.aims.repository.payment.PaymentRepository;
import com.example.aims.repository.payment.PaymentRepositoryImpl;
import com.example.aims.subsystem.IPaymentSubsystem;
import com.example.aims.subsystem.PaymentSubsystem;
import com.example.aims.subsystem.vnpay.VNPaySubsystemController;

import javax.persistence.EntityManager;

public class AppConfig {

    public static PayOrderController getPayOrderController()
    {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        PaymentRepository paymentRepository = new PaymentRepositoryImpl();
        IPaymentSubsystem vnPay = new PaymentSubsystem(new VNPaySubsystemController());
        IMailer mailer = new GMailer();
        return new PayOrderController(orderRepository, paymentRepository, vnPay, mailer);
    }

    public static BookRepository getBookRepository() {
        EntityManager em = AIMSDB.getEntityManager();
        return new BookRepositoryImpl(em);
    }

    public static CDRepository getCDRepository() {
        EntityManager em = AIMSDB.getEntityManager();
        return new CDRepositoryImpl(em);
    }

    public static DVDRepository getDVDRepository() {
        EntityManager em = AIMSDB.getEntityManager();
        return new DVDRepositoryImpl(em);
    }

    public static LPRepository getLPRepository() {
        EntityManager em = AIMSDB.getEntityManager();
        return new LPRepositoryImpl(em);
    }
}
