package com.example.aims.repository.media.impl;

import com.example.aims.entity.media.Book;
import com.example.aims.repository.AIMSDB;
import com.example.aims.repository.media.BookRepository;

import javax.persistence.EntityManager;

public class BookRepositoryImpl implements BookRepository {
    private final EntityManager em;

    public BookRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }
}