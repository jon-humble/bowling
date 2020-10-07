package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

class LineImpl implements Line {

    private final List<Frame> frames;

    public LineImpl(final Frame... fs) {
        final List<Frame> frames = Arrays.asList(fs);
        if (!valid(frames)) throw new IllegalArgumentException("Not a valid frame set");
        this.frames = frames;
    }

    public int score() {
        final Stream<Frame> fs = frames.stream();
        final List<Frame> successors = new ArrayList<>(frames.size() + 1);
        successors.addAll(frames);
        successors.add(Frame.gutter());
        final Stream<Frame> ss = successors.stream().skip(1);

        final Stream<Integer> scores = Streams.zip(fs, ss, (f1, f2) -> f1.frameScore(f2));
        return scores.reduce(0, (x, y) -> x + y);
    }

    private boolean valid(final List<Frame> frames) {
        // There must be ten frames
        final boolean tenFrames = frames.size() == 10;

        // There can be either zero or 1 bonus frames
        final long bonusCount = frames.stream().filter(Frame::isBonus).count();
        final boolean validBonus = bonusCount == 0 || bonusCount == 1;

        // If there is a bonus frame, it has to be the last one
        boolean bonusIsLastOrThereIsNotOne = true;
        if (tenFrames && bonusCount == 1) {
            bonusIsLastOrThereIsNotOne = frames.get(9).isBonus();
        }

        // If there is no bonus frame, the last throw cannot be a strike or a spare
        boolean lastRoundSimpleIfNoBonus = true;
        if (tenFrames && bonusCount == 0) {
            final Frame lastFrame = frames.get(9);
            lastRoundSimpleIfNoBonus = !(lastFrame.isStrike() || lastFrame.isSpare());
        }

        return tenFrames && validBonus && bonusIsLastOrThereIsNotOne && lastRoundSimpleIfNoBonus;
    }
}
