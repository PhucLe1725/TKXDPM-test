package com.example.aims.repository.media;

import com.example.aims.entity.media.Book;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.DVD;
import com.example.aims.response.MediaResponse;

public interface MediaRepository {
    MediaResponse getMediaListByFilter(String query, String priceFilter, int pageCount);
}
