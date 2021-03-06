package com.vitreoussoftware.bioinformatics.sequence;

import com.vitreoussoftware.bioinformatics.sequence.encoding.EncodingScheme;

/**
 * Represents a Nucleotide Base Pair
 * @author John
 *
 */
public class BasePair {
	private final byte nucleotide;
	private EncodingScheme scheme;
	
	/**
	 * Create a new base pair from the given nucleotide
	 * @param encodingScheme the character representation 
	 * @param value the nucleotide identifier
	 * @throws InvalidDnaFormatException The given nucleotide was not valid
	 */
    protected BasePair(byte value, EncodingScheme encodingScheme) {
		this.nucleotide = value;		
		this.scheme = encodingScheme;
	}
	
	/**
	 * Create a new base pair from the given nucleotide
	 * @param nucleotide the nucleotide identifier
	 * @return the base pair representation
	 * @throws InvalidDnaFormatException The given nucleotide was not valid
	 */
	public static BasePair create(char nucleotide, EncodingScheme encodingScheme) throws InvalidDnaFormatException
	{	
		byte value = encodingScheme.getValue(nucleotide);
		return new BasePair(value, encodingScheme);
	}

    public int distance(BasePair bp) {
        if (this.equals(bp))
            return 0;
        else
            return 1;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nucleotide;
		return result;
	}

	@Override
	public boolean equals(Object basepair) {
		if (this == basepair)
			return true;
		if (basepair == null)
			return false;
		if (getClass() == basepair.getClass())
		{
			BasePair other = (BasePair) basepair;
			return this.equals(other.nucleotide);
		}
		else if (basepair.getClass() == Byte.class)
		{
			return this.equals((byte)basepair);
		}
		
		return false;
	}
	
	/**
	 * Determines if the given byte representation is equal to this basepair  
	 * @param nucleotide the byte representation
	 * @return the result
	 */
	public boolean equals(byte nucleotide) {
		return nucleotide == this.nucleotide;
	}

	
	public String toString() {
		try {
			return this.scheme.toString(this.nucleotide);
		} catch (InvalidDnaFormatException e) {
			// this should never fail since the encoding came from the encapsulated BasePair
			e.printStackTrace();
			throw new RuntimeException("We hit an unknown basepair encoding converting to string\n");
		}
	}

	public char toChar() {
		try {
			return this.scheme.toChar(this.nucleotide);	
		} catch (InvalidDnaFormatException e) {
			// this should never fail since the encoding came from the encapsulated BasePair
			e.printStackTrace();
			throw new RuntimeException("We hit an unknown basepair encoding converting to string\n");
		}
		
	}	
}
