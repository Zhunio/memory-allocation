package src.algorithm;

import src.MemorySlot;
import src.Process;
import java.util.LinkedList;

/**
 * Created on 11/11/17.
 *
 * @author Richard I. Zhunio
 */
public class WF extends MemoryAllocator {
	public WF(LinkedList<Process> processList, LinkedList<MemorySlot> memoryList) {
		super(processList, memoryList);
	}

	@Override
	public MemorySlot allocateSlot(Process process) {
		// Represents the algorithm slot that worst fits the process
		MemorySlot worstFit = null;

		// For each algorithm slot, find the worst fit
		for (MemorySlot slot: memoryList) {

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
		return slot.spaceAvailable() > worstFit.spaceAvailable()? slot: worstFit;
	}
}
