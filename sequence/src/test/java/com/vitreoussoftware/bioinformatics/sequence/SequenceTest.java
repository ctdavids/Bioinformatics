package com.vitreoussoftware.bioinformatics.sequence;

import com.vitreoussoftware.bioinformatics.sequence.basic.BasicSequence;
import com.vitreoussoftware.bioinformatics.sequence.encoding.AcceptUnknownDnaEncodingScheme;
import com.vitreoussoftware.bioinformatics.sequence.fasta.FastaSequenceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Test the Sequence class
 * @author John
 *
 */
public class SequenceTest {

	private FastaSequenceFactory factory;

	/**
	 * Setup the test class
	 */
	@Before
	public void setup() {
		this.factory = new FastaSequenceFactory();
	}
	
	/**
	 * Test that we can create a new DnaSequence
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testCreation_nominal() throws InvalidDnaFormatException {
		final String basis = "AATT";
		
		Sequence seq = this.factory.fromString(basis).get();
		
		assertEquals(basis, seq.toString());
	}
	
	/**
	 * Test that we can handle the full range of valid input
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testCreation_full() throws InvalidDnaFormatException {
		final String basis = "AATTCCGGUU";
		
		Sequence seq = this.factory.fromString(basis).orElseThrow(() -> new InvalidDnaFormatException("TODO update this exception"));
		
		assertEquals(basis, seq.toString());
	}
	
	/**
	 * Test that some basic invalid input is rejected
	 * @throws InvalidDnaFormatException 
	 */
	@Test(expected=InvalidDnaFormatException.class)
	public void testCreation_badData() throws InvalidDnaFormatException {
		final String basis = "AATT132";
		
		// We expect an error here so don't do anything about it!
		this.factory.fromString(basis).orElseThrow(() -> new InvalidDnaFormatException("TODO update this exception"));
	}

    /**
     * Test that some basic invalid input is rejected
     * @throws InvalidDnaFormatException
     */
    @Test
    public void testCreation_optional() {
        final String basis = "AATT132";

        // We expect an error here so don't do anything about it!
        Optional<Sequence> sequenceOptional = this.factory.fromString(basis);
        assertNotNull("optional value was null", sequenceOptional);
        assertFalse("isPresent was true", sequenceOptional.isPresent());
    }
	
	
	/**
	 * Test that empty input is rejected
	 * @throws InvalidDnaFormatException 
	 */
	@Test(expected=InvalidDnaFormatException.class)
	public void testCreation_empty() throws InvalidDnaFormatException {
		final String basis = "";
		
		// We expect an error here so don't do anything about it!
		this.factory.fromString(basis);
	}
	
	/**
	 * Test that we can handle the full range of valid input
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testEquals_sameRef() throws InvalidDnaFormatException {
		final String basis = "AATTCCGGUU";
		
		Sequence seq = this.factory.fromString(basis).get();
		
		assertEquals(seq, seq);
	}
	
	/**
	 * Test that we can handle the full range of valid input
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testEquals_diffRef() throws InvalidDnaFormatException {
		final String basis = "AATTCCGGUU";
		
		Sequence seq1 = this.factory.fromString(basis).get();
		Sequence seq2 = this.factory.fromString(basis).get();
		
		assertEquals(seq1, seq2);
	}
	
	/**
	 * Test that we can handle the full range of valid input
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testHashCode_sameRef() throws InvalidDnaFormatException {
		final String basis = "AATTCCGGUU";
		
		Sequence seq = this.factory.fromString(basis).get();
		
		assertEquals(seq.hashCode(), seq.hashCode());
	}
	
	/**
	 * Test that we can handle the full range of valid input
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testHashCode_diffRef() throws InvalidDnaFormatException {
		final String basis = "AATTCCGGUU";
		
		Sequence seq1 = this.factory.fromString(basis).get();
		Sequence seq2 = this.factory.fromString(basis).get();
		
		assertEquals(seq1.hashCode(), seq2.hashCode());
	}

    @Test
    public void testIterator_basic() {
        final Sequence seq = this.factory.fromString("ATCGU").get();

        final Iterator<BasePair> iterator = seq.iterator();

        for (int i = 0; i < seq.length(); i++) {
            assertTrue("There was no element @" + i, iterator.hasNext());
            iterator.next();
        }

        assertFalse("We could iterate too far", iterator.hasNext());
    }

    @Test
    public void testIterator_forEachRemaining() {
        final Sequence seq = this.factory.fromString("ATCGU").get();

        for (int i = 0; i < seq.length(); i++) {
            final Iterator<BasePair> iterator = seq.iterator();
            for (int j = 0; j < i; j++) {
                assertTrue("There was no element @" + j, iterator.hasNext());
                iterator.next();
            }
            final AtomicInteger remainingCount = new AtomicInteger();
            iterator.forEachRemaining(x -> remainingCount.incrementAndGet());
            assertEquals("Number of remaining elements was wrong", seq.length()-i, remainingCount.get());
        }
    }
}
