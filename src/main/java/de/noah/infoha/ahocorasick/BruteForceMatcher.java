package de.noah.infoha.ahocorasick;

import java.util.function.Consumer;

public class BruteForceMatcher extends AbstractMultipleExactStringMatcher {

    @Override
    public void match(final byte[] text, final int start, final int end, final Consumer<MatchingResult> target, byte[]... patterns) {
        patterns = uniquePatterns(patterns);
        try {
            for (int textIndex = start; textIndex < end; ++textIndex) {
                for (int patternIndex = 0; patternIndex < patterns.length; ++patternIndex) {
                    tryMatch(text, patterns[patternIndex], textIndex, patternIndex, target);
                }
            }
        } catch (final RunStopException e) {
            // deliberately left blank
        }
    }

    private void tryMatch(final byte[] text, final byte[] pattern, final int endIndex, final int patternIndex, final Consumer<MatchingResult> target) {
        final int patternLength = pattern.length;

        if (patternLength <= endIndex + 1) {

            int textCursor = endIndex;
            int patternCursor = patternLength - 1;

            while (patternCursor >= 0) {
                if (text[textCursor] != pattern[patternCursor]) {
                    return;
                }

                --textCursor;
                --patternCursor;
            }

            target.accept(new MatchingResult(endIndex + 1, pattern));
        }
    }
}