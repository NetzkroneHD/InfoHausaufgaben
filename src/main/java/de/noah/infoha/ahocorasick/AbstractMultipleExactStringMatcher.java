package de.noah.infoha.ahocorasick;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class AbstractMultipleExactStringMatcher {

    public static class RunStopException extends RuntimeException {

    }

    public final void match(final byte[] text, final Consumer<MatchingResult> target, final byte[]... patterns) {
        match(text, 0, text.length, target, patterns);
    }

    public abstract void match(byte[] text, int start, int end, final Consumer<MatchingResult> target, byte[]... patterns);

    protected byte[][] uniquePatterns(final byte[][] patterns) {
        final Set<byte[]> filter = new HashSet<>(Arrays.asList(patterns));
        return filter.toArray(new byte[filter.size()][0]);
    }

    /**
     * This class represents a match.
     */
    public static final class MatchingResult {

        public final int startIndex;

        public final int endIndex;

        public final int matchLength;

        public final byte[] pattern;

        public MatchingResult(final int endIndex, final byte[] pattern) {
            startIndex = endIndex - pattern.length;
            this.endIndex = endIndex;
            matchLength = pattern.length;
            this.pattern = pattern;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(pattern);
            result = prime * result + startIndex;
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MatchingResult other = (MatchingResult) obj;
            if (!Arrays.equals(pattern, other.pattern)) {
                return false;
            }
            return startIndex == other.startIndex;
        }

        @Override
        public String toString() {
            return "MatchingResult [startIndex=" + startIndex + ", endIndex=" + endIndex + ", pattern=" + new String(pattern) + "]";
        }

    }
}