package src.algorithm;

import src.memory.MemoryAllocator;
import src.memory.MemorySlot;
import src.memory.Process;

/**
 * Created on 11/11/17.
 *
 * @author Richard I. Zhunio
 */
public class WF extends MemoryAllocator {
	public WF(String memoryInput, String processInput) throws Exception {
		super(memoryInput, processInput);
	}

	@Override
	public MemorySlot allocateSlot(Process process) {
		// Represents the algorithm slot that worst fits the process
		MemorySlot worstFit = null;

		// For each algorithm slot, find the worst fit
		for (MemorySlot slot: mList) {

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
