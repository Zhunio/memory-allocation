package src.memory;

// User libraries
import src.algorithm.BF;
import src.algorithm.FF;
import src.algorithm.WF;
import src.parser.MemorySlotParser;
import src.parser.ProcessParser;

// Java SDK Libraries
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Richard I. Zhunio
 */
public class MemoryAllocator {
	protected LinkedList<MemorySlot> mList;
	private LinkedList<Process> pList;

	protected MemoryAllocator(String memoryInput, String processInput)
		throws Exception {
		mList = new MemorySlotParser(memoryInput).parse();
		pList = new ProcessParser(processInput).parse();
	}

	public static MemoryAllocator generate(String memoryAlgorithm,
										   String memoryInput, String processInput)
		throws Exception {
		switch (memoryAlgorithm) {
			case "FF":
				return new FF(memoryInput, processInput);
			case "BF":
				return new BF(memoryInput, processInput);
			case "WF":
				return new WF(memoryInput, processInput);
			default:
				throw new Exception("Not supported memory allocator "
					+ "algorithm: " + memoryAlgorithm);
		}
	}

	public List<String> run() {
		// Contains the steps of this algorithm
		List<String> logger = new LinkedList<>();

		// Retrieve list iterator from process list
		ListIterator<Process> iter = pList.listIterator();

		// For each process in process list
		while (iter.hasNext()) {
			// Retrieve process
			Process process = iter.next();

			// Allocate algorithm slot to process
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
