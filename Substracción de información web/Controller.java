package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

public class Controller extends Application {

    Random rnd = new Random();

    @FXML
    Label lblTitulo;

    @FXML
    Hyperlink tituloLink;

    @FXML
    void cargarTitulo(ActionEvent event) throws IOException {
        Document doc = Jsoup.connect("http://www.sensacine.com/peliculas/mejores/nota-espectadores/").get();

        Elements listaPeliculas = doc.select("a[class=no_underline]");
        Element pelicula = listaPeliculas.get(rnd.nextInt(listaPeliculas.size()));
        String absHref = pelicula.attr("abs:href");

        tituloLink.setText(pelicula.text());
        tituloLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getHostServices().showDocument(absHref);
            }
        });
        lblTitulo.setVisible(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stop();
    }

}
