package de.noah.infoha.ahocorasick;

import java.util.*;
import java.util.function.Consumer;

public class AhoCorasickMatcher extends AbstractMultipleExactStringMatcher {

    @Override
    public void match(final byte[] text, final int start, final int end, final Consumer<MatchingResult> target, byte[]... patterns) {
        try {
            if (patterns.length > 0) {
                patterns = uniquePatterns(patterns);
                final Automaton automaton = constructACAutomaton(patterns);
                TrieNode state = automaton.root;
                for (int textIndex = start; textIndex < end; ++textIndex) {
                    while (state.getChild(text[textIndex]) == null) {
                        state = automaton.fail.get(state);
                    }

                    state = state.getChild(text[textIndex]);
                    for (final int patternIndex : automaton.patterns.get(state)) {
                        target.accept(new MatchingResult(textIndex + 1, patterns[patternIndex]));
                    }
                }
            }
        } catch (final RunStopException e) {
            //
        }
    }

    private static final class TrieNode {

        private final TrieNode[] children = new TrieNode[0x100];

        void setChild(final byte character, final TrieNode child) {
            children[Byte.toUnsignedInt(character)] = child;
        }

        TrieNode getChild(final byte character) {
            return children[Byte.toUnsignedInt(character)];
        }
    }

    private Automaton constructACAutomaton(final byte[]... patterns) {
        final Automaton ret = new Automaton();
        constructTrie(ret, patterns);
        computeFailureFunction(ret);
        return ret;
    }

    private void constructTrie(final Automaton automaton, final byte[]... patterns) {
        final TrieNode root = new TrieNode();
        final int k = patterns.length;

        for (int patternIndex = 0; patternIndex < k; ++patternIndex) {
            TrieNode node = root;
            int charIndex = 0;
            final int patternLength = patterns[patternIndex].length;

            while (charIndex < patternLength && node.getChild(patterns[patternIndex][charIndex]) != null) {
                node = node.getChild(patterns[patternIndex][charIndex]);
                ++charIndex;
            }

            while (charIndex < patternLength) {
                final TrieNode u = new TrieNode();
                node.setChild(patterns[patternIndex][charIndex], u);
                node = u;
                ++charIndex;
            }

            automaton.patterns.put(node, new int[] { patternIndex });
        }

        automaton.patterns.put(root, new int[0]);
        automaton.root = root;
    }

    private void computeFailureFunction(final Automaton automaton) {
        final TrieNode fallbackNode = new TrieNode();

        for (int c = 0; c < 0x100; ++c) {
            final byte b = toUnsignedByte(c);
            fallbackNode.setChild(b, automaton.root);
        }

        automaton.fail.put(automaton.root, fallbackNode);
        final Deque<TrieNode> queue = new ArrayDeque<>();
        queue.addLast(automaton.root);

        while (!queue.isEmpty()) {
            final TrieNode head = queue.removeFirst();

            for (int c = 0; c < 0x100; ++c) {
                final byte character = toUnsignedByte(c);

                if (head.getChild(character) != null) {

                    final TrieNode child = head.getChild(character);
                    TrieNode w = automaton.fail.get(head);

                    while (w.getChild(character) == null) {
                        w = automaton.fail.get(w);
                    }

                    automaton.fail.put(child, w.getChild(character));

                    final int[] failTargets = automaton.patterns.get(automaton.fail.get(child));

                    final int[] existingList = automaton.patterns.get(child);
                    if (existingList == null) {
                        automaton.patterns.put(child, failTargets);
                    } else {
                        final int[] extendedList = Arrays.copyOf(existingList, existingList.length + failTargets.length);
                        System.arraycopy(failTargets, 0, extendedList, existingList.length, failTargets.length);
                        automaton.patterns.put(child, extendedList);
                    }
                    queue.addLast(child);
                }
            }
        }

        automaton.patterns.put(automaton.root, new int[0]);
    }

    private static byte toUnsignedByte(final int value) {
        return (byte) (0xFF & value);
    }

    private static final class Automaton {
        TrieNode root;
        Map<TrieNode, TrieNode> fail = new HashMap<>();
        Map<TrieNode, int[]> patterns = new HashMap<>();
    }
}
