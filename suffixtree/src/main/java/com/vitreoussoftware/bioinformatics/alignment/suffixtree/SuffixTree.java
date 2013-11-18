package com.vitreoussoftware.bioinformatics.alignment.suffixtree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import com.vitreoussoftware.bioinformatics.sequence.*;



/**
 * Suffix Tree implementation for Sequence data
 * @author John
 *
 */
public interface SuffixTree {
		/**
	 * Does the tree contain the specified substring?
	 * @param sequence the substring to search for
	 * @return if the substring exists in the tree
	 */
	public boolean contains(Sequence sequence);
	
	/**
	 * Returns the depth of the suffix tree.
	 * @return the depth
	 */
	public int depth();

	/**
	 * Find the set of parents for the sequence of interest
	 * @param sequence the sequence to find parents for
	 * @return the set of parents, or empty list if no parents
	 */
	public Collection<Sequence> getParents(Sequence sequence);
	
	/**
	 * Adds a new sequence to the suffix tree
	 * @param sequence the sequence to add
	 */
	public void addSequence(final Sequence sequence);	
}