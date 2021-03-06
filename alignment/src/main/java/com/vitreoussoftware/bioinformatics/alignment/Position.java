package com.vitreoussoftware.bioinformatics.alignment;

import com.vitreoussoftware.bioinformatics.sequence.Sequence;

/**
 * Created by John on 8/31/14.
 */
public class Position {
    final private Sequence sequence;
    final private int position;

    private Position(Sequence text, int position) {
        this.sequence = text;
        this.position = position;
    }


    public static Position with(Sequence sequence, int position) {
        return new Position(sequence, position);
    }


    public Sequence getText() {
        return this.sequence;
    }

    public int getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", this.position, this.sequence.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        if (position != position1.position) return false;
        if (sequence != null ? !sequence.equals(position1.sequence) : position1.sequence != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sequence != null ? sequence.hashCode() : 0;
        result = 31 * result + position;
        return result;
    }
}
