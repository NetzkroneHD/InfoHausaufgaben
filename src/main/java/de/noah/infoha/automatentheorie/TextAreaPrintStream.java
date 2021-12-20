package de.noah.infoha.automatentheorie;

import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextAreaPrintStream extends PrintStream {

    private final TextArea textArea;
    public TextAreaPrintStream(TextArea textArea, OutputStream out) {
        super(out);
        this.textArea = textArea;
    }

    @Override
    public void println(String x) {
        textArea.append(x+"\n");
    }
    public void print(String string) {
        textArea.append(string);
    }
    @Override
    public void print(Object obj) {
        textArea.append(obj+"");
    }
    @Override
    public void println(Object obj) {
        textArea.append(obj+"\n");
    }
}
