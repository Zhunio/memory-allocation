package src.algorithm;

import src.MemorySlot;
import src.Process;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class MemoryAllocator {
	LinkedList<Process> processList;
	LinkedList<MemorySlot> memoryList;

	public MemoryAllocator(LinkedList<Process> processList, LinkedList<MemorySlot> memoryList) {
		this.processList = processList;
		this.memoryList = memoryList;
	}

	public List<String> run() {
		// Contains the steps of this algorithm
		List<String> logger = new LinkedList<>();

		// Retrieve list iterator from process list
		ListIterator<Process> iter = processList.listIterator();

		// For each process in process list
		while (iter.hasNext()) {
			// Retrieve process
			Process process = iter.next();

			// Allocate algorithm slot to process
			MemorySlot slot = allocateSlot(process);

			if (slot != null) {
				// Log algorithm used only if slot is not null
				logger.add(slot.startMarker + " " + slot.endMarker + " " + process.id);

				// Remove current process from process list
				iter.remove();
			}
		}

		// If some processes have not been allocated
		if (!processList.isEmpty()) {
			// Log any processes
			for (Process process : processList)
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
	 * @param process the {@code Process} to allocate.
	 * @return the {@code MemorySlot} that allocates the {@code process}, otherwise
	 * null is returned.
	 */
	public abstract MemorySlot allocateSlot(Process process);
}
