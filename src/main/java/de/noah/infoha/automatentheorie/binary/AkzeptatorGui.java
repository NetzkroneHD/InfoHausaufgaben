package de.noah.infoha.automatentheorie.binary;


import de.noah.infoha.automatentheorie.TextAreaPrintStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AkzeptatorGui extends JFrame {

    private final JPanel contentPane;

    private final TextArea consoleOutPut;


    public AkzeptatorGui() {
        setExtendedState(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setBounds(100, 100, 776, 491);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        consoleOutPut = new TextArea();
        consoleOutPut.setBounds(30, 50, 600, 414);
        consoleOutPut.setEditable(false);
        contentPane.add(consoleOutPut);


        System.setOut(new TextAreaPrintStream(consoleOutPut, System.out));
        System.setErr(new TextAreaPrintStream(consoleOutPut, System.err));

    }

}
