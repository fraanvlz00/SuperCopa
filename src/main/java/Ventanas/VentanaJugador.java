package Ventanas;

import Dominio.Team;
import Dominio.Jugador;
import Dominio.Posicion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaJugador extends JFrame {
    private Team team;
    private JTable table;
    private DefaultTableModel tableModel;

    public VentanaJugador(Team team) {
        this.team = team;
        setTitle("Players");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Number", "Name", "Position"};
        Object[][] data = new Object[team.getJugadores().size()][3];
        for (int i = 0; i < team.getJugadores().size(); i++) {
            Jugador jugador = team.getJugadores().get(i);
            data[i][0] = jugador.getNumero();
            data[i][1] = jugador.getNombre();
            data[i][2] = jugador.getPosicion().name();
        }
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton buttonEdit = new JButton("Edit player");
        buttonEdit.addActionListener(e -> {
            editarJugador();
        });

        JButton buttonEliminar = new JButton("Delete player");
        buttonEliminar.addActionListener(e -> {
            eliminarJugador();
        });

        JButton buttonBack = new JButton("Back");
        buttonBack.addActionListener(e -> dispose());

        JPanel buttonPane = new JPanel();
        buttonPane.add(buttonEdit);
        buttonPane.add(buttonEliminar);
        buttonPane.add(buttonBack);

        setLayout(new BorderLayout());
        add(new JLabel("Official formation"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    private void editarJugador() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int numero = (int) table.getValueAt(selectedRow, 0);
            Jugador jugador = team.buscarJugador(numero);

            String nuevoNombre = JOptionPane.showInputDialog(this, "Enter new name:", jugador.getNombre());
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                String nuevaPosicion = (String) JOptionPane.showInputDialog(this, "Select new position:",
                        "Position", JOptionPane.QUESTION_MESSAGE, null, new String[]{"GK", "DF", "MF", "FW"}, jugador.getPosicion().name());
                if (nuevaPosicion != null) {
                    team.actualizarJugador(numero, nuevoNombre, Posicion.valueOf(nuevaPosicion));
                    table.setValueAt(nuevoNombre, selectedRow, 1);
                    table.setValueAt(nuevaPosicion, selectedRow, 2);
                }
            }
        }
    }

    private void eliminarJugador() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int numero = (int) table.getValueAt(selectedRow, 0);
            team.eliminarJugador(numero);
            tableModel.removeRow(selectedRow);
        }
    }
}
