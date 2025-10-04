package com.example.aims.repository.media.impl;

import com.example.aims.entity.media.CD;
import com.example.aims.repository.AIMSDB;
import com.example.aims.repository.media.CDRepository;

import javax.persistence.EntityManager;

public class CDRepositoryImpl implements CDRepository {
    private final EntityManager em;

    public CDRepositoryImpl(EntityManager em){
        this.em = em;
    }
    @Override
    public CD getById(int id) {
        return em.find(CD.class, id);
    }
}
