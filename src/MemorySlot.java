package src;

import java.util.LinkedList;
/**
 * Represents a algorithm slot with a starting and ending address. It is capable of
 * holding processList and calculating how much space is available.
 *
 * @author Richard I. Zhunio
 * Created on 11/11/17.
 */
public class MemorySlot implements Cloneable {
	/* The starting address of this algorithm slot */
	private int start;

	/* The ending address of this algorithm slot */
	private int end;

	public int startMarker;
	public int endMarker;

	/* List of processList inside this algorithm slot */
	private LinkedList<Process> processList;

	/**
	 * Creates a new empty algorithm slot with a starting and ending address.
	 * @param start the starting address.
	 * @param end the ending address.
	 */
	public MemorySlot(int start, int end) {
		this.start = start;
		this.end = end;
		this.startMarker = this.endMarker = start;
		this.processList = new LinkedList<>();
	}

	/**
	 * Retrieves the space available in this algorithm slot.
	 * @return space avalable in this algorithm slot.
	 */
	public int spaceAvailable() {
		return end - endMarker;
	}

	@Override
	public String toString() {
		return start + " " + end;
	}

	/**
	 * Add process to this algorithm slot.
	 * @param process the process to add to the algorithm slot.
	 */
	public void add(Process process) {
		// Update markers
		startMarker = endMarker;
		endMarker += process.size;

		// Add process
		processList.add(process);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object clone() throws CloneNotSupportedException {
		MemorySlot newMemorySlot = (MemorySlot) super.clone();
		newMemorySlot.start = start;
		newMemorySlot.end = end;
		newMemorySlot.startMarker = startMarker;
		newMemorySlot.endMarker = endMarker;
		newMemorySlot.processList = (LinkedList<Process>) processList.clone();
		return newMemorySlot;
	}
}
