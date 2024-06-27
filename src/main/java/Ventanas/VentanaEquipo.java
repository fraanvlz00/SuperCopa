package Ventanas;

import Dominio.Team;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VentanaEquipo extends Application {

    private ComboBox<Team> comboBoxTeams;
    private TextField textFieldRanking;
    private ImageView imageViewFlag;
    private List<Team> equipos;

    @Override
    public void start(Stage primaryStage) throws IOException {
        equipos = Team.cargarEquipos("src/main/java/Datos/teams.txt", "src/main/java/Datos/");

        primaryStage.setTitle("Teams");

        comboBoxTeams = new ComboBox<>();
        textFieldRanking = new TextField();
        textFieldRanking.setEditable(false);
        imageViewFlag = new ImageView();

        Button buttonPlayers = new Button("Players");
        Button buttonExit = new Button("Exit");

        comboBoxTeams.getItems().addAll(equipos);
        comboBoxTeams.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostrarDetallesEquipo(newValue));

        buttonPlayers.setOnAction(event -> {
            Team selectedTeam = comboBoxTeams.getSelectionModel().getSelectedItem();
            if (selectedTeam != null) {
                try {
                    new VentanaJugador(selectedTeam).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonExit.setOnAction(event -> primaryStage.close());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Choose team:"), 0, 0);
        gridPane.add(comboBoxTeams, 1, 0);
        gridPane.add(imageViewFlag, 2, 0, 1, 3);
        gridPane.add(new Label("Ranking FIFA:"), 0, 1);
        gridPane.add(textFieldRanking, 1, 1);
        gridPane.add(buttonPlayers, 0, 2);
        gridPane.add(buttonExit, 1, 2);

        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarDetallesEquipo(Team team) {
        if (team != null) {
            textFieldRanking.setText(String.valueOf(team.getRanking()));
            Image image = new Image("file:src/main/java/Datos/" + team.getRutaBandera());
            imageViewFlag.setImage(image);
        } else {
            textFieldRanking.clear();
            imageViewFlag.setImage(null);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
