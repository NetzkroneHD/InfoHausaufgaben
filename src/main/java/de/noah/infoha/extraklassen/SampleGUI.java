package de.noah.infoha.extraklassen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SampleGUI extends JFrame {

    private final JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SampleGUI frame = new SampleGUI();
                frame.setVisible(true);

                Vector2D.getSquare(new Vector2D(100, 100), new Vector2D(200, 264), vector2D -> {
                    JLabel label = new JLabel(".");
                    label.setBounds((int)vector2D.getX(), (int)vector2D.getY(), 5, 10);
                    frame.getContentPane().add(label);
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SampleGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 758, 464);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


    }


    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

}
