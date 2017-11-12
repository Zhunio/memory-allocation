package src;
/**
 * Represents a process with an id and a size.
 *
 * @author Richard I. Zhunio
 * Created on 11/11/17.
 */
public class Process implements Cloneable {
	/* Represents the id of this process */
	public int id;

	/* Represents the size of this process */
	public int size;

	/**
	 * Creates a new process given its id and size.
	 * @param processID the id of the process.
	 * @param processSize the size of the process.
	 */
	public Process(int processID, int processSize) {
		this.id = processID;
		this.size = processSize;
	}

	@Override
	public String toString() {
		return id + " " + size;
	}

	/**
	 * Creates a deep copy of this process.
	 * @return a deep copy of this process.
	 * @throws CloneNotSupportedException if clone operation is not supported.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Process newProcess = (Process) super.clone();
		newProcess.id = id;
		newProcess.size = size;
		return newProcess;
	}
}
