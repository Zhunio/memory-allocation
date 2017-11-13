package src.algorithm;

// User defined libraries
import src.memory.MemoryAllocator;
import src.memory.MemorySlot;
import src.memory.Process;
import src.parser.InvalidNumberException;

// Java SDK libraries
import java.io.FileNotFoundException;

/**
 * <p>The <code>FF</code> class implements the first-fit memory allocation method.
 *
 * <p>The first-fit method allocates the first memory slot that is big enough
 * to the process that needs allocation. A search can start either at the
 * beginning of the list of memory slots or at the location where the previous
 * first-fit search ended. The search stops until a memory slot is large enough
 * to allocate the process.
 *
 * @author Richard I. Zhunio
 * @see MemoryAllocator
 * @see Process
 * @see MemorySlot
 * @see InvalidNumberException
 * @see FileNotFoundException
 */
public class FF extends MemoryAllocator {

	/**
	 * Creates a new first-fit allocator method given the file paths to the
	 * Minput.data and Pinput.data files.
	 * @param memoryInput the filepath to the Minput.data file.
	 * @param processInput the filepath to the Pinput.data file.
	 * @throws FileNotFoundException  if the <code>memoryInput</code> or
	 *                                <code>processInput</code>does not contain a
	 *                                valid filepath.
	 * @throws InvalidNumberException if the Minput.data or Pinput.data
	 *                                contain invalid numbers.
	 */
	public FF(String memoryInput, String processInput) throws Exception {
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
		// Represents the algorithm slot that has been allocated to the process
		MemorySlot slot = null;

		// Execute for each slot in algorithm list until we find one that fits
		for (MemorySlot memorySlot: mList) {

			// if process fits in algorithm slot, add it to the algorithm slot
			if (process.size <= memorySlot.spaceAvailable()) {
				memorySlot.add(process);
				slot = memorySlot;

				// Break out of the loop
				break;
			}
		}

		return slot;
	}
}
