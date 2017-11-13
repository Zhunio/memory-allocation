package src.memory;

import java.util.LinkedList;
/**
 * <p>The <code>MemorySlot</code> class represents a memory slot read from the
 * Minput.data file.
 *
 * <p>Each <code>MemorySlot</code> has a <code>start</code>, and
 * <code>end</code> address, a <code>startMarker</code> and <code>endMarker</code>,
 * and a list of processes. The <code>start</code> address is the beginning
 * of this memory slot. The <code>end</code> address is the end of this memory
 * slot. The <code>startMarker</code> gives the starting address of the last
 * process added to this memory slot. The <code>endMarker</code> gives the
 * ending address of the last process added to this memory slot. The list of
 * processes contains a list of processes allocated to this memory slot.
 *
 * @author Richard I. Zhunio
 */
public class MemorySlot implements Cloneable {
	/* The starting address of this memory slot */
	private int start;

	/* The ending address of this memory slot */
	private int end;

	/* The starting address of the last process added to this memory slot */
	public int startMarker;

	/* The ending address of the last process added to this memory slot */
	public int endMarker;

	/* List of processes inside this memory slot */
	public LinkedList<Process> processList;

	/**
	 * Creates a new empty memory slot with a starting and ending address.
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
	 * Retrieves the space available in this memory slot.
	 * @return space avalable in this memory slot.
	 */
	public int spaceAvailable() {
		return end - endMarker;
	}

	/**
	 * String representation of this <code>MemorySlot</code>.
	 * The <code>start</code>, and <code>end</code> of a <code>MemorySlot</code>
	 * are returned.
	 * @return the <code>start</code>, and <code>end</code> of a
	 * <code>MemorySlot</code>
	 */
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
