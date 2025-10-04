package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.utils.PathConfig;
import com.example.aims.view.BaseScreenController;
import javafx.scene.Scene;

public class ScreenControllerFactory {
    public static BaseScreenController getController(Scene currentScene, String mediaType, int mediaId, CartController cartController) {
        return switch (mediaType.toUpperCase()) {
            case "BOOK" -> new BookScreenController(currentScene, PathConfig.BOOK_PATH, mediaId, cartController);
            case "CD" -> new CDScreenController(currentScene, PathConfig.CD_PATH, mediaId, cartController);
            case "DVD" -> new DVDScreenController(currentScene, PathConfig.DVD_PATH, mediaId, cartController);
            default -> throw new IllegalArgumentException("Unsupported media type: " + mediaType);
        };
    }
}
