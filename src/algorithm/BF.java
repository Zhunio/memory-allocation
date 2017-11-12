package src.algorithm;

import src.memory.MemoryAllocator;
import src.memory.MemorySlot;
import src.memory.Process;

/**
 * Created on 11/11/17.
 *
 * @author Richard I. Zhunio
 */
public class BF extends MemoryAllocator {
	public BF(String memoryInput, String processInput) throws Exception {
		super(memoryInput, processInput);
	}

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

	private MemorySlot getSmallest(MemorySlot bestFit, MemorySlot slot) {
		return slot.spaceAvailable() < bestFit.spaceAvailable()? slot: bestFit;
	}
}
