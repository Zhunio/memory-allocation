package src.algorithm;

// User defined libraries
import src.memory.MemoryAllocator;
import src.memory.MemorySlot;
import src.memory.Process;
import src.parser.InvalidNumberException;

import java.io.FileNotFoundException;

/**
 * <p>The <code>BF</code> class implements the best-fit memory allocation method.
 *
 * <p>The best-fit method allocates the smallest memory slot that is big enough
 * to the process that needs allocation. A search through the entire list is
 * necessary for un-ordered list. For an ordered list, other searches such as
 * binary search will improve the performance.
 *
 * <p>The best-fit allocation method produces the smallest leftover memory slot.
 *
 * @author Richard I. Zhunio
 * @see MemoryAllocator
 * @see Process
 * @see MemorySlot
 * @see InvalidNumberException
 * @see FileNotFoundException
 */
public class BF extends MemoryAllocator {

	/**
	 * Creates a new best-fit allocator method given the file paths to the
	 * Minput.data and Pinput.data files.
	 * @param memoryInput the filepath to the Minput.data file.
	 * @param processInput the filepath to the Pinput.data file.
	 * @throws FileNotFoundException  if the <code>memoryInput</code> or
	 *                                <code>processInput</code>does not contain a
	 *                                valid filepath.
	 * @throws InvalidNumberException if the Minput.data or Pinput.data
	 *                                contain invalid numbers.
	 */
	public BF(String memoryInput, String processInput)
		throws FileNotFoundException, InvalidNumberException {
		super(memoryInput, processInput);
	}

	/**
	 * Allocates a <code>Process</code> to a <code>MemorySlot</code> if the
	 * <code>MemorySlot</code> has enough space available for the <code>Process</code>.
	 * If the <code>MemorySlot</code> does not have enough space available for the
	 * <code>Process</code>, the <code>MemorySlot</code> returned is null. Otherwise,
	 * the returned value contains the <code>MemorySlot</code> that allocates the
	 * <code>Process</code>.
	 *
	 * @param process the <code>Process</code> to allocate.
	 * @return the <code>MemorySlot</code> that allocates the <code>Process</code>, otherwise
	 * null is returned.
	 */
	@Override
	public MemorySlot allocateSlot(Process process) {
		// Represents the algorithm slot that best fits the process
		MemorySlot bestFit = null;

		// Execute for each algorithm slot we find one that best fits
		for (MemorySlot slot: mList) {

			// if process fits in algorithm slot
			if (process.size <= slot.spaceAvailable()) {

				// Get the smallest algorithm slot
				if (bestFit != null) bestFit = getSmallest(bestFit, slot);
				else bestFit = slot;
			}
		}

		// Add process to the best fit algorithm slot
		if (bestFit != null) bestFit.add(process);

		return bestFit;
	}

	/**
	 * Compares the two <code>MemorySlot</code>s provided as parameters and
	 * returns the smallest <code>MemorySlot</code>.
	 * @param bestFit a <code>MemorySlot}</code>.
	 * @param slot a <code>MemorySlot}</code>.
	 * @return the smallest <code>MemorySlot}</code>.
	 */
	private MemorySlot getSmallest(MemorySlot bestFit, MemorySlot slot) {
		return slot.spaceAvailable() < bestFit.spaceAvailable()? slot: bestFit;
	}
}
