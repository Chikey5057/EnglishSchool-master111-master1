package ru.sapteh.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Manufacture;
import ru.sapteh.model.Product;
import ru.sapteh.service.ManufactureService;
import ru.sapteh.service.ProductService;

;
import java.util.List;


public class ControllerMain {

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<Manufacture> comboBox;

    @FXML
    private FlowPane flowPane;

    @FXML
    private Button searchButton;

    Product product = new Product();
    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<Manufacture> manufactures = FXCollections.observableArrayList();

    public void initialize(){

//        initManufactureFilter();
        initData();
//        searchText();


        flowPane.setAlignment(Pos.TOP_LEFT);
        flowPane.setHgap(25);
        flowPane.setVgap(10);

        for (Product product : products) {
            flowPane.getChildren().add(getNode(product.getMainImagePath(),product.getTitle(),product.getCost(),product.getIsActive()));
        }
    }

    private void initData() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Product,Integer> productService = new ProductService(factory);

        List<Product> list = productService.readByAll();
        products.addAll(list);

    }

    private Node getNode(String url, String name, double cost,int active){
        AnchorPane pane = new AnchorPane();

        Image image = new Image(url);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(200);



        Label label = new Label();
        label.setText(String.format("%s \n %.0f рублей \n %s",name,cost, active == 1 ? "Активен" : " "));
        label.setLayoutY(300);
        label.setMaxWidth(150);
        label.setWrapText(true);

       EventHandler<MouseEvent> mouseOn = mouseEvent -> {
            imageView.setFitWidth(300);
            imageView.setFitHeight(500);
            label.setLayoutY(520);
       };
       EventHandler<MouseEvent> mouseOff = mouseEvent -> {
           imageView.setFitWidth(200);
           imageView.setFitHeight(300);
           label.setLayoutY(150);
       };

       imageView.setOnMouseEntered(mouseOn);
       imageView.setOnMouseExited(mouseOff);

        pane.getChildren().add(imageView);

        pane.getChildren().add(label);

        return pane;
    }
//    private void initManufactureFilter(){
//            comboBox.setItems(manufactures);
//            comboBox.valueProperty().addListener(
//                    (obj,oldValue,newValue)->{
//                        FilteredList<Product> filteredList = new FilteredList<>(products,
//                                s->newValue.equals(s.getManufacture().getName()));
//                        System.out.println(filteredList);
//                    }
//        );
//    }
//    private void searchText(){
//        searchButton.setOnAction(event -> {
//            for (Product product : products){
//                if (searchTextField.equals(product.getTitle())){
//                    flowPane.getChildren().add(getNode(product.getMainImagePath(),product.getTitle(),product.getCost(),product.getIsActive()));
//                }else {
//                    System.out.println("else");
//                }
//                System.out.println(product);
//            }
//
//        });
//    }


}

