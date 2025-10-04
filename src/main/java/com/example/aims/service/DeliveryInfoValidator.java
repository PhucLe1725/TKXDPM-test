package com.example.aims.service;



public interface DeliveryInfoValidator {
    void validateInfo(String name, String email, String address, String phone) throws RuntimeException;
}