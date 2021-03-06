package com.vitreoussoftware.bioinformatics.alignment.suffixtree.basic;


import java.util.Iterator;

import com.vitreoussoftware.bioinformatics.sequence.BasePair;
import com.vitreoussoftware.bioinformatics.sequence.Sequence;
import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollectionFactory;



/**
 * Suffix Tree implementation for Sequence data where the substrings fall within a specified range of lengths.
 * @author John
 *
 */
public class BoundedSuffixTree extends BasicSuffixTree {
	private int minLength;
	private int maxLength;


	/**
	 * Create the suffix tree
	 * @param factory the factory for creating SequenceCollections 
	 * @param minLength the minimum length of a suffix contained in the tree.
	 * @param maxLength the maximum length of a suffix contained in the tree.
	 */
	BoundedSuffixTree(SequenceCollectionFactory factory, int minLength, int maxLength) {
		super(factory);
		this.minLength = minLength;
		this.maxLength = maxLength;
	}


	/**
	 * Adds a new sequence to the suffix tree
	 * @param text the sequence to add
	 */
	public void addText(final Sequence text) {
		if (text == null) throw new IllegalArgumentException("Sequence cannot be null");
		Iterator<BasePair> suffixIter = text.iterator();

		int startPos = 0;
		final int length = text.length();
		while (suffixIter.hasNext() && startPos + this.minLength < length)
		{
			SuffixTreeNode current = root;
			// up to max length create or iterate the nodes and add parents.
			for (int offset = 0; offset < this.maxLength && offset + startPos < text.length(); offset++) {
                current = current.getOrCreate(text.get(startPos + offset));

                // Only add the starting position, not the position of the current node.
				if (offset >= this.minLength)
                    current.addPosition(text, startPos);
			}
			
			// iterate forward and update the position
			suffixIter.next();
			startPos++;
		}
	}	
}
