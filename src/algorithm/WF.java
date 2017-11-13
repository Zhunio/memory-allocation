package src.algorithm;

import src.memory.MemoryAllocator;
import src.memory.MemorySlot;
import src.memory.Process;
import src.parser.InvalidNumberException;

import java.io.FileNotFoundException;

/**
 * <p>The <code>WF</code> class implements the worst-fit memory allocation method.
 *
 * <p>The worst-fit method allocates the largest memory slot to the process that
 * needs allocation. We must search the entire list unless it is sorted by size.
 * This strategy produces the largest leftover memory slot which may be more \
 * useful than the smaller leftover memory slot from the best-fit approach.
 *
 * @author Richard I. Zhunio
 * @see MemoryAllocator
 * @see Process
 * @see MemorySlot
 * @see InvalidNumberException
 * @see FileNotFoundException
 */
public class WF extends MemoryAllocator {

	/**
	 * Creates a new worst-fit allocator method given the file paths to the
	 * Minput.data and Pinput.data files.
	 *
	 * @param memoryInput  the filepath to the Minput.data file.
	 * @param processInput the filepath to the Pinput.data file.
	 * @throws FileNotFoundException  if the <code>memoryInput</code> or
	 *                                <code>processInput</code>does not contain a
	 *                                valid filepath.
	 * @throws InvalidNumberException if the Minput.data or Pinput.data
	 *                                contain invalid numbers.
	 */
	public WF(String memoryInput, String processInput) throws Exception {
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
		// Represents the algorithm slot that worst fits the process
		MemorySlot worstFit = null;

		// For each algorithm slot, find the worst fit
		for (MemorySlot slot : mList) {

			// if process fits in algorithm slot
			if (process.size <= slot.spaceAvailable()) {

				// Get the largest algorithm slot
				if (worstFit != null) worstFit = getLargest(worstFit, slot);
				else worstFit = slot;
			}
		}

		// Add process to the worst fit algorithm slot
		if (worstFit != null) worstFit.add(process);

		return worstFit;
	}

	private MemorySlot getLargest(MemorySlot worstFit, MemorySlot slot) {
		return slot.spaceAvailable() > worstFit.spaceAvailable() ? slot : worstFit;
	}
}
