package de.noah.infoha.color;

import java.awt.*;

public class ColorAlgo {

    private Color color;


    public ColorAlgo() {

    }

    public Color darker(float percent) {
        this.color = new Color(color.getRed()*percent, color.getGreen()*percent, color.getBlue()*percent);
        return color;
    }

    public Color lighter(float percent) {
        this.color = new Color(color.getRed()*(1+percent), color.getGreen()*(1+percent), color.getBlue()*(1+percent));
        return color;
    }

    public Color hexToRgb(String hex) {
        color = Color.decode(hex);
        return color;
    }

    public String toHex() {
        return Integer.toHexString(color.getRGB());
    }

    public static void main(String[] args) {

    }


}
