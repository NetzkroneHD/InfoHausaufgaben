package de.noah.infoha.sprachen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {

    private final Pattern pattern;

    public PatternMatching(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public Matcher matcher(String input) {
        return pattern.matcher(input);
    }

    public boolean find(String input) {
        return matcher(input).find();
    }

    public void test(String input) {
        System.out.println(input+" - "+find(input));
    }

    public Pattern getPattern() {
        return pattern;
    }


    public static void main(String[] args) {
        final PatternMatching pmZahlen = new PatternMatching("(1|2)(34)(5|6)");
        pmZahlen.test("12345");
        pmZahlen.test("1346");
        pmZahlen.test("2345");
        pmZahlen.test("2346");



    }

}
