package com.vitreoussoftware.bioinformatics.alignment.suffixtree.basic;

import java.io.IOException;

import com.vitreoussoftware.bioinformatics.alignment.suffixtree.SuffixTree;
import com.vitreoussoftware.bioinformatics.alignment.suffixtree.SuffixTreeFactory;
import com.vitreoussoftware.bioinformatics.sequence.InvalidDnaFormatException;
import com.vitreoussoftware.bioinformatics.sequence.Sequence;
import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollection;
import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollectionFactory;
import com.vitreoussoftware.bioinformatics.sequence.collection.basic.SequenceListFactory;
import com.vitreoussoftware.bioinformatics.sequence.reader.SequenceStreamReader;

/**
 * Create suffix trees with bounded size
 * @author John
 *
 */
public class BoundedSuffixTreeFactory implements SuffixTreeFactory {

	private SequenceCollectionFactory factory;
	private int maxLength;
	private int minLength;

	/**
	 * Create a BasicSuffixTreeFactory with a custom SequenceCollectionFactory
	 * @param factory the SequenceCollectionFactory to use with the SuffixTree
	 * @param minlength the minimum length of a suffix contained in the tree.
	 * @param maxLength the maximum length of a suffix contained in the tree.
	 */
	public BoundedSuffixTreeFactory(SequenceCollectionFactory factory, int minLength, int maxLength) {
		this.factory = factory;
		this.minLength = minLength;
		this.maxLength = maxLength;
	}
	
	/**
	 * Create a BasicSuffixTreeFactory with default configuration
	 * @param minlength the minimum length of a suffix contained in the tree.
	 * @param maxLength the maximum length of a suffix contained in the tree.
	 */
	public BoundedSuffixTreeFactory(int minlength, int maxLength) {
		this(new SequenceListFactory(), minlength, maxLength);
	}

    public SuffixTree create() {
        return new BoundedSuffixTree(this.factory, this.minLength, this.maxLength);
    }

	@Override
	public SuffixTree create(Sequence sequence) {
		SuffixTree tree = create();
		tree.addSequence(sequence);
		return tree;
	}

	@Override
	public SuffixTree create(SequenceCollection sequenceCollection) {
		SuffixTree tree = create();
		for (Sequence s : sequenceCollection)
			tree.addSequence(s);
		return tree;
	}

	@Override
	public SuffixTree create(SequenceStreamReader sequenceReader) throws IOException, InvalidDnaFormatException {
		SuffixTree tree = create();
		
		while (sequenceReader.hasNext())
			tree.addSequence(sequenceReader.next().orElseThrow(() -> new RuntimeException("TODO update this exception")));
		
		return tree;
	}

}