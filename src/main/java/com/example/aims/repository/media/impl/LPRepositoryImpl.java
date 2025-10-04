package com.example.aims.repository.media.impl;

import com.example.aims.entity.media.DVD;
import com.example.aims.entity.media.LP;
import com.example.aims.repository.AIMSDB;
import com.example.aims.repository.media.LPRepository;

import javax.persistence.EntityManager;

public class LPRepositoryImpl implements LPRepository {
    private final EntityManager em;

    public LPRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public LP getById(int id) {
        return em.find(LP.class, id);
    }
}
