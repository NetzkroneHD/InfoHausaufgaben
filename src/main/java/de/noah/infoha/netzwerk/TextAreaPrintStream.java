package de.noah.infoha.netzwerk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextAreaPrintStream extends PrintStream {

    private final TextArea textArea;
    private final SimpleDateFormat formatter;
    public TextAreaPrintStream(TextArea textArea, @NotNull OutputStream out) {
        super(out);
        this.textArea = textArea;
        formatter = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void println(@Nullable String x) {
        textArea.append("["+formatter.format(new Date(System.currentTimeMillis()))+"]: "+x+"\n");
    }
    public void print(String string) {
        textArea.append("["+formatter.format(new Date(System.currentTimeMillis()))+"]: "+string);
    }
    @Override
    public void print(@Nullable Object obj) {
        textArea.append("["+formatter.format(new Date(System.currentTimeMillis()))+"]: "+obj);
    }
    @Override
    public void println(@Nullable Object obj) {
        textArea.append("["+formatter.format(new Date(System.currentTimeMillis()))+"]: "+obj+"\n");
    }
}
