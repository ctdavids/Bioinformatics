package com.vitreoussoftware.bioinformatics.sequence.collection.basic;

import static org.junit.Assert.*;

import java.io.IOException;

import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollectionTest;
import com.vitreoussoftware.bioinformatics.sequence.io.FastaData;
import com.vitreoussoftware.bioinformatics.sequence.io.reader.fasta.FastaSequenceStreamReader;
import com.vitreoussoftware.bioinformatics.sequence.io.reader.fasta.FastaStringFileStreamReaderTest;
import org.junit.Test;

import com.vitreoussoftware.bioinformatics.sequence.InvalidDnaFormatException;
import com.vitreoussoftware.bioinformatics.sequence.Sequence;
import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollection;
import com.vitreoussoftware.bioinformatics.sequence.collection.SequenceCollectionFactory;
import com.vitreoussoftware.bioinformatics.sequence.io.reader.SequenceStreamReader;

/**
 * Test the SequenceList basic collection so we have a baseline
 * @author John
 *
 */
public class SequenceListTest extends SequenceCollectionTest {

    @Override
    protected SequenceCollectionFactory getFactory() {
        return new SequenceListFactory();
    }
	
	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testCreation() throws InvalidDnaFormatException {
		SequenceCollection sc = this.getFactory().getSequenceCollection();
		
		assertNotNull(sc);
	}
	
	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testAdd() throws InvalidDnaFormatException {
		SequenceCollection sc = this.getFactory().getSequenceCollection();
		
		assertNotNull(sc);
		sc.add(sequenceFactory.fromString("AATTCCGGUU").get());
		assertEquals(1,  sc.size());
	}
	
	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testContains_sameRef() throws InvalidDnaFormatException {
		SequenceCollection sc = this.getFactory().getSequenceCollection();
		Sequence seq = sequenceFactory.fromString("AATTCCGGUU").get();
		sc.add(seq);
		assertEquals(1,  sc.size());
		assertTrue(sc.contains(seq));
	}
	
	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 */
	@Test
	public void testContains_diffRef() throws InvalidDnaFormatException {
		SequenceCollection sc = this.getFactory().getSequenceCollection();
		sc.add(sequenceFactory.fromString("AATTCCGGUU").get());
		assertEquals(1,  sc.size());
		assertTrue(sc.contains(sequenceFactory.fromString("AATTCCGGUU").get()));
	}

	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 * @throws IOException 
	 */
	@Test
	public void testStreamSource_single() throws InvalidDnaFormatException, IOException {
		SequenceStreamReader reader = new FastaSequenceStreamReader(FastaStringFileStreamReaderTest.getSimpleFastaReader());
		SequenceCollection sc = this.getFactory().getSequenceCollection(reader);
		
		assertNotNull(sc);
		assertEquals(1,  sc.size());
		assertEquals(sequenceFactory.fromString(FastaData.getRecordSimple()).get(), sc.iterator().next());
		assertTrue(sc.contains(sequenceFactory.fromString(FastaData.getRecordSimple()).get()));
	}
	
	/**
	 * Can it be created?
	 * @throws InvalidDnaFormatException 
	 * @throws IOException 
	 */
	@Test
	public void testStreamSource_multiple() throws InvalidDnaFormatException, IOException {
        SequenceStreamReader reader = new FastaSequenceStreamReader(FastaStringFileStreamReaderTest.getExampleFastaReader());
		SequenceCollection sc = this.getFactory().getSequenceCollection(reader);

		assertNotNull(sc);
		assertEquals(3,  sc.size());
		assertTrue(sc.contains(sequenceFactory.fromString(FastaData.getRecord1()).get()));
		assertTrue(sc.contains(sequenceFactory.fromString(FastaData.getRecord2()).get()));
		assertTrue(sc.contains(sequenceFactory.fromString(FastaData.getRecord3()).get()));
	}
}
