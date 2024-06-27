package Ventanas;

import Dominio.Jugador;
import Dominio.Team;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaJugador extends Stage {

    private Team team;
    private TableView<Jugador> tableView;
    private TextField textFieldNombre;
    private TextField textFieldNumero;
    private ComboBox<String> comboBoxPosicion;
    private Button buttonGuardar;

    public VentanaJugador(Team team) {
        this.team = team;
        setTitle("Players");

        tableView = new TableView<>();
        TableColumn<Jugador, Integer> columnNumero = new TableColumn<>("Number");
        columnNumero.setCellValueFactory(cellData -> cellData.getValue().numeroProperty().asObject());
        TableColumn<Jugador, String> columnNombre = new TableColumn<>("Name");
        columnNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        TableColumn<Jugador, String> columnPosicion = new TableColumn<>("Position");
        columnPosicion.setCellValueFactory(cellData -> cellData.getValue().posicionProperty());

        tableView.getColumns().addAll(columnNumero, columnNombre, columnPosicion);
        tableView.getItems().addAll(team.getJugadores());

        textFieldNombre = new TextField();
        textFieldNumero = new TextField();
        comboBoxPosicion = new ComboBox<>();
        comboBoxPosicion.getItems().addAll("GK", "DF", "MF", "FW");

        buttonGuardar = new Button("Save changes");
        buttonGuardar.setOnAction(event -> guardarCambiosJugador());

        Button buttonEdit = new Button("Edit player");
        buttonEdit.setOnAction(event -> mostrarDetallesJugador());

        Button buttonBack = new Button("Back");
        buttonBack.setOnAction(event -> close());

        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.add(new Label("Name:"), 0, 0);
        formPane.add(textFieldNombre, 1, 0);
        formPane.add(new Label("Number:"), 0, 1);
        formPane.add(textFieldNumero, 1, 1);
        formPane.add(new Label("Position:"), 0, 2);
        formPane.add(comboBoxPosicion, 1, 2);

        VBox vBox = new VBox(10, new Label("Official formation"), tableView, formPane, new HBox(10, buttonEdit, buttonGuardar, buttonBack));
        vBox.setPadding(new Insets(10));

        Scene scene = new Scene(vBox, 600, 400);
        setScene(scene);
    }

    private void mostrarDetallesJugador() {
        Jugador jugadorSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado != null) {
            textFieldNombre.setText(jugadorSeleccionado.getNombre());
            textFieldNumero.setText(String.valueOf(jugadorSeleccionado.getNumero()));
            comboBoxPosicion.setValue(jugadorSeleccionado.getPosicion().name());
        }
    }

    private void guardarCambiosJugador() {
        Jugador jugadorSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionado != null) {
            jugadorSeleccionado.setNombre(textFieldNombre.getText());
            jugadorSeleccionado.setNumero(Integer.parseInt(textFieldNumero.getText()));
            jugadorSeleccionado.setPosicion(Dominio.Posicion.valueOf(comboBoxPosicion.getValue()));
            team.actualizarJugador(jugadorSeleccionado);
            tableView.refresh();
        }
    }
}

