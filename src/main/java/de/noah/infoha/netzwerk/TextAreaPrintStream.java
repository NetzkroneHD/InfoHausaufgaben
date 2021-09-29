package de.noah.infoha.netzwerk;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class TextAreaPrintStream extends PrintStream {

    private TextArea textArea;

    public TextAreaPrintStream(TextArea textArea, @NotNull OutputStream out) {
        super(out);
        this.textArea = textArea;
    }

    @Override
    public void println(@Nullable String x) {
        textArea.append(x+"\n");
    }
    public void print(String string) {
        textArea.append(string);
    }
    @Override
    public void print(@Nullable Object obj) {
        textArea.append(obj.toString());
    }
    @Override
    public void println(@Nullable Object obj) {
        textArea.append(obj.toString()+"\n");
    }
}
