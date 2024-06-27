package Ventanas;

import Dominio.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class VentanaEquipo extends JFrame {
    private JComboBox<Team> comboBoxTeams;
    private JTextField textFieldRanking;
    private JLabel imageViewFlag;
    private List<Team> equipos;

    public VentanaEquipo() throws IOException {
        equipos = Team.cargarEquipos("src/main/java/Datos/teams.txt", "src/main/java/Datos/");

        setTitle("Teams");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        comboBoxTeams = new JComboBox<>();
        textFieldRanking = new JTextField(10);
        textFieldRanking.setEditable(false);
        imageViewFlag = new JLabel();

        JButton buttonPlayers = new JButton("Players");
        JButton buttonExit = new JButton("Exit");

        for (Team team : equipos) {
            comboBoxTeams.addItem(team);
        }
        comboBoxTeams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetallesEquipo((Team) comboBoxTeams.getSelectedItem());
            }
        });

        buttonPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Team selectedTeam = (Team) comboBoxTeams.getSelectedItem();
                if (selectedTeam != null) {
                    new VentanaJugador(selectedTeam).setVisible(true);
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Choose team:"), gbc);
        gbc.gridx = 1;
        add(comboBoxTeams, gbc);

        gbc.gridx = 2;
        gbc.gridheight = 3;
        add(imageViewFlag, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Ranking FIFA:"), gbc);
        gbc.gridx = 1;
        add(textFieldRanking, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        add(buttonPlayers, gbc);
        gbc.gridx = 1;
        add(buttonExit, gbc);
    }

    private void mostrarDetallesEquipo(Team team) {
        if (team != null) {
            textFieldRanking.setText(String.valueOf(team.getRanking()));
            ImageIcon imageIcon = new ImageIcon("src/main/java/Datos/" + team.getRutaBandera());
            imageViewFlag.setIcon(imageIcon);
        } else {
            textFieldRanking.setText("");
            imageViewFlag.setIcon(null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new VentanaEquipo().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
