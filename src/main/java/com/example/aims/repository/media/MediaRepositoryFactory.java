package com.example.aims.repository.media;

import com.example.aims.repository.media.impl.BookRepositoryImpl;
import com.example.aims.repository.media.impl.CDRepositoryImpl;
import com.example.aims.repository.media.impl.DVDRepositoryImpl;
import com.example.aims.repository.media.impl.LPRepositoryImpl;
import com.example.aims.utils.AppConfig;

public class MediaRepositoryFactory {
    public static Object getRepository(String mediaType) {
        return switch (mediaType) {
            case "BOOK" -> AppConfig.getBookRepository();
            case "CD" -> AppConfig.getCDRepository();
            case "DVD" -> AppConfig.getDVDRepository();
            case "LP" -> AppConfig.getLPRepository();
            default -> throw new IllegalArgumentException("Unsupported media type.");
        };
    }
}
