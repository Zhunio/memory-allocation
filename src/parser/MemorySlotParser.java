package src.parser;

// User defined libraries
import src.memory.MemorySlot;

// Java SDK libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The <code>MemorySlotParser</code> class is responsible for parsing the
 * Minput.data file. The Minput.data file contains a list of memory slots.
 * The format of the Minput.data file is as follows:
 * <blockquote><pre>{@code
 * 	3        = number of free memory slots
 * 	100 400  = start and end address of free memory slot
 * 	... ...
 * 	... ...
 * }</pre></blockquote>
 *
 * @author Richard I. Zhunio
 */
public class MemorySlotParser {
	/* Scanner that will read the contents of the disk file */
	private Scanner reader;

	/**
	 * Creates a new <code>MemorySlotParser</code>.
	 *
	 * @param file the filepath to Minput.data
	 * @throws FileNotFoundException if <code>file</code> is not a correct
	 *                               path to the Minput.data.
	 */
	public MemorySlotParser(String file) throws FileNotFoundException {
		reader = new Scanner(new File(file));
	}

	/**
	 * Parses a Minput.data file.
	 *
	 * @return list of {@link src.memory.MemorySlot}s read the Minput.data file.
	 * @throws InvalidNumberException if an invalid number of memory slots is
	 *                                provided.
	 */
	@SuppressWarnings("unused")
	public LinkedList<MemorySlot> parse() throws InvalidNumberException {

		// Represents a list of memory slots
		LinkedList<MemorySlot> listOfSlots = new LinkedList<>();

		// Read number of memory slots
		int slotsNo = readSlotsNo();

		// Read list of memory slots from file disk
		readMemorySlots(listOfSlots);

		// Return list of memory slots
		return listOfSlots;
	}

	/**
	 * Reads the number of free memory slots in the disk file. The first line is
	 * read, and we expect one int value.
	 *
	 * @return the number of free memory slots in the disk file.
	 * @throws InvalidNumberException if there is an invalid number of free memory
	 *                                slots.
	 */
	private int readSlotsNo() throws InvalidNumberException {

		// if invalid number of processes
		int slotsNo = -1;

		// Read the first line from the disk file
		if (reader.hasNextLine()) {

			// Read the very first line on the disk file
			slotsNo = Integer.parseInt(reader.nextLine());

			// Error occurs if an invalid number is entered
			if (slotsNo < 0)
				throw new InvalidNumberException("No valid number of processes: "
					+ slotsNo);
		}

		return slotsNo;
	}

	/**
	 * Reads memory slots from Minput.data and adds them to a list.
	 *
	 * @param listOfSlots list to hold {@link src.memory.MemorySlot}s.
	 */
	private void readMemorySlots(LinkedList<MemorySlot> listOfSlots) {

		// Read the next line containing memory slot
		while (reader.hasNextLine()) {

			// Retrieve next line to parse
			String line = reader.nextLine();
			Scanner parser = new Scanner(line);

			// Parse the line to obtain memory slot starting and ending address
			int startAddr = parser.nextInt();
			int endAddr = parser.nextInt();

			// Add new free memory slot to the list
			listOfSlots.add(new MemorySlot(startAddr, endAddr));
		}
	}
}