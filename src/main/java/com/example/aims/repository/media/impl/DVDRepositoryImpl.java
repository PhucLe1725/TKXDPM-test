package com.example.aims.repository.media.impl;

import com.example.aims.entity.media.DVD;
import com.example.aims.repository.AIMSDB;
import com.example.aims.repository.media.DVDRepository;

import javax.persistence.EntityManager;

public class DVDRepositoryImpl implements DVDRepository {
    private final EntityManager em;

    public DVDRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public DVD getById(int id) {
        return em.find(DVD.class, id);
    }
}
