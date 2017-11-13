package src.memory;

// User libraries

import src.parser.InvalidNumberException;
import src.parser.MemorySlotParser;
import src.parser.ProcessParser;

// Java SDK Libraries
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>The <code>MemoryAllocator</code> class serves as a skeleton for implementing
 * memory allocation methods such as First Fit (FF), Best Fit (BF),
 * and Worst Fit (WF).
 *
 * <p>The <code>MemoryAllocator</code> class SHOULD NOT be instantiated. Instead,
 * it should be extended. The class that extends the <code>MemoryAllocator</code>
 * should obey the signature of the {@link #MemoryAllocator} constructor. The
 * {@link #run} method contains a valid implementation and must not be overwritten.
 * However, the {@link #run} method should be the only method called after
 * instantiating this class. The {@link #allocateSlot} method contains no implementation
 * and must be implemented by the extending class. If extending class fails to
 * implement the {@link #allocateSlot} method, the run method will throw a
 * {@link UnsupportedOperationException}.
 *
 * <p>Because a memory allocating method algorithm depends heavily on the part
 * when a memory slot is allocated to a process, the {@link #allocateSlot} method
 * is provided for the extending class to implement. The {@link #allocateSlot}
 * method should allocate a {@link MemorySlot} to the current {@link Process}
 * and then return the {@link MemorySlot} that was allocated to the process.
 * If the {@link #allocateSlot} method is not able to allocate a process, then
 * null is returned. However, this is handle properly by the {@link #run} method.
 *
 * @author Richard I. Zhunio
 */
public class MemoryAllocator {
	/** List of memory slots */
	protected LinkedList<MemorySlot> mList;

	/** List of processes */
	private LinkedList<Process> pList;

	/**
	 * Creates a new <code>MemoryAllocator</code>.
	 *
	 * @param memoryInput  the filepath to the Minput.data file.
	 * @param processInput the filepath to the Pinput.data file.
	 * @throws FileNotFoundException  if the <code>memoryInput</code> or
	 *                                <code>processInput</code>does not contain a
	 *                                valid filepath.
	 * @throws InvalidNumberException if the Minput.data or Pinput.data
	 *                                contain invalid numbers.
	 */
	protected MemoryAllocator(String memoryInput, String processInput)
		throws FileNotFoundException, InvalidNumberException {
		mList = new MemorySlotParser(memoryInput).parse();
		pList = new ProcessParser(processInput).parse();
	}

	/**
	 * Runs the memory allocation method, and returns a list containing
	 * the the memory slots allocated and their corresponding processes.
	 * @return a list of memory slots and their corresponding allocated process.
	 */
	public List<String> run() {
		// Contains the steps of this memory allocation method
		List<String> logger = new LinkedList<>();

		// Retrieve list iterator from process list
		ListIterator<Process> iter = pList.listIterator();

		// For each process in process list
		while (iter.hasNext()) {
			// Retrieve process
			Process process = iter.next();

			// Allocate memory slot to process
			// Allocation of slot depends on specific algorithm
			// Implement allocateSlot method when extending this class
			MemorySlot slot = allocateSlot(process);

			if (slot != null) {
				// Log algorithm used only if slot is not null
				logger.add(slot.startMarker + " " + slot.endMarker + " " + process.id);

				// Remove current process from process list
				iter.remove();
			}
		}

		// If some processes have not been allocated
		if (!pList.isEmpty()) {
			// Log any processes
			for (Process process : pList)
				logger.add("-" + process.id);
		}
		// If all processes have been allocated
		else logger.add("-0");

		return logger;
	}

	/**
	 * Allocates a {@code Process} to a {@code MemorySlot} if the
	 * {@code MemorySlot} has enough space available for the {@code Process}.
	 * If the {@code MemorySlot} does not have enough space available for the
	 * {@code Process}, the {@code MemorySlot} returned is null. Otherwise,
	 * the returned value contains the {@code MemorySlot} that allocates the
	 * {@code Process}.
	 *
	 * @param process the {@code Process} to allocate.
	 * @return the {@code MemorySlot} that allocates the {@code process}, otherwise
	 * null is returned.
	 */
	protected MemorySlot allocateSlot(Process process) {
		throw new UnsupportedOperationException(
			"This method must be overwritten when extending MemoryAllocator class.");
	}
}
