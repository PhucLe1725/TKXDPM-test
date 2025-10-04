package com.example.aims.repository.media;

import com.example.aims.entity.media.Book;

public interface BookRepository {
    Book getById(int id);
}
