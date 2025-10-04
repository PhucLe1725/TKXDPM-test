package com.example.aims.view.media;

import com.example.aims.controller.CartController;
import com.example.aims.entity.cart.CartMedia;
import com.example.aims.entity.media.CD;
import com.example.aims.entity.media.Media;
import com.example.aims.repository.media.CDRepository;
import com.example.aims.repository.media.MediaRepository;
import com.example.aims.repository.media.MediaRepositoryFactory;
import com.example.aims.repository.media.impl.MediaRepositoryImpl;
import com.example.aims.utils.ImagePath;
import com.example.aims.view.BaseScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CDScreenController extends BaseScreenController implements AddToCart {
    @FXML
    private Text artist;

    @FXML
    private ImageView cdImage;

    @FXML
    private Text category;

    @FXML
    private Text musicType;

    @FXML
    private Text price;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Text recordLabel;

    @FXML
    private Text releaseDate;

    @FXML
    private Text title;

    @FXML
    public Text weight;
    private Media media;
    private MediaRepository mediaRepository;
    private CartController cartController;

    public CDScreenController() {
    }
    public CDScreenController(Scene currentScene, String currentScreenPath, int id, CartController cartController) {
        super(currentScene, currentScreenPath, id, cartController);
    }
    @Override
    public <T> void initData(T... data) {
        this.cartController = (CartController) data[1];
        CDRepository cdRepository = (CDRepository) MediaRepositoryFactory.getRepository("CD");
        CD cd = cdRepository.getById((Integer) data[0]);
        this.media = cd;
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory
                        .IntegerSpinnerValueFactory(1 , 10, 1);
        quantitySpinner.setValueFactory(valueFactory);
        artist.setText(cd.getArtist());
        category.setText(cd.getCategory());
        musicType.setText(cd.getMusicType());
        recordLabel.setText(cd.getRecordLabel());
        releaseDate.setText(String.valueOf(cd.getReleasedDate()));
        title.setText(cd.getTitle());
        price.setText(cd.getPrice() + " VND");
        cdImage.setImage(ImagePath.getMediaImage(media));
        weight.setText(cd.getWeight() + "kg");
    }



    @FXML
    void addToCart(ActionEvent event) {
        CartMedia cartMedia = new CartMedia(media, quantitySpinner.getValue(), media.getPrice());
        addToCart(cartController, cartMedia);
    }
}
