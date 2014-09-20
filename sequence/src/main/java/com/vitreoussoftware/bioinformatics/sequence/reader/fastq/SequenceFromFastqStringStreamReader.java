package com.vitreoussoftware.bioinformatics.sequence.reader.fastq;

import com.vitreoussoftware.bioinformatics.sequence.Sequence;
import com.vitreoussoftware.bioinformatics.sequence.fasta.FastaSequenceFactory;
import com.vitreoussoftware.bioinformatics.sequence.reader.SequenceStreamReader;
import com.vitreoussoftware.bioinformatics.sequence.reader.SequenceStringStreamReader;
import org.javatuples.Pair;

import java.util.Optional;

/**
 * Maps a SequenceStringStreamReader who's string return values are in FASTA format into encoded sequences
 * @author John
 *
 */
public class SequenceFromFastqStringStreamReader implements SequenceStreamReader {
	private final SequenceStringStreamReader reader;
	private FastaSequenceFactory factory;
	
	/**
	 * Process Sequence Strings into encoded sequences
	 * @param reader The SequenceStringStreamReader to process sequence strings from
	 */
	public SequenceFromFastqStringStreamReader(SequenceStringStreamReader reader)
	{
		this.reader = reader;
		this.factory = new FastaSequenceFactory();
	}
	
	@Override
	public void close() throws Exception {
		reader.close();
	}

	@Override
    public boolean hasNext() {
		return reader.hasNext();
	}

	@Override
    public Optional<Sequence> next() {
        final Pair<String,String> next = this.reader.next();
        return this.factory.fromString(next.getValue0(), next.getValue1());
	}
}