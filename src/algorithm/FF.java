package src.algorithm;

import src.MemorySlot;
import src.Process;
import java.util.LinkedList;

/**
 * Created on 11/11/17.
 *
 * @author Richard I. Zhunio
 */
public class FF extends MemoryAllocator {

	public FF(LinkedList<Process> processList, LinkedList<MemorySlot> memoryList) {
		super(processList, memoryList);
	}

	/**
	 * Allocates a process to a free algorithm slot, or returns null otherwise.
	 * @param process the process to allocate the free algorithm slot.
	 * @return the free algorithm slot to allocate the process or null if process was
	 * not allocated.
	 */
	@Override
	public MemorySlot allocateSlot(Process process) {
		// Represents the algorithm slot that has been allocated to the process
		MemorySlot slot = null;

		// Execute for each slot in algorithm list until we find one that fits
		for (MemorySlot memorySlot: memoryList) {

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
