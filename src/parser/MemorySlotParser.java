package src.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import src.MemorySlot;

public class MemorySlotParser {
	/* Represents the scanner that will read the contents of the disk file */
	private Scanner reader;

	public MemorySlotParser(String file) throws FileNotFoundException {
		reader = new Scanner(new File(file));
	}

	/**
	 * Parse a Minput.data file.
	 * The format is as follows:
	 * 3		- Number of free algorithm slots.
	 * 100 400	- Memory slot starting and ending address.
	 * 600 800	- Same as above
	 *
	 * @return A list of free algorithm slots.
	 * @throws Exception a exception
	 */
	@SuppressWarnings("unused")
	public LinkedList<MemorySlot> parse() throws Exception {

		// Represents a list of free algorithm slots
		LinkedList<MemorySlot> listOfSlots = new LinkedList<>();

		// Read number of free algorithm slots
		int slotsNo = readSlotsNo();

		// Read list of free algorithm slots from file disk
		readMemorySlots(listOfSlots);

		// Return list of free algorithm slots
		return listOfSlots;
	}
	/**
	 * Reads the number of free algorithm slots in the disk file. The first line is
	 * read, and we expect one int value.
	 *
	 * @return the number of free algorithm slots in the disk file.
	 * @throws Exception if there is an invalid number of free algorithm slots.
	 */
	private int readSlotsNo() throws Exception {

		// if invalid number of processes
		int slotsNo = -1;

		// Read the first line from the disk file
		if (reader.hasNextLine()) {

			// Read the very first line on the disk file
			slotsNo = Integer.parseInt(reader.nextLine());

			// Error occurs if an invalid number is entered
			if (slotsNo < 0)
				throw new Exception("No valid number of processes: "
					+ slotsNo);
		}

		return slotsNo;
	}
	/**
	 * Read free algorithm slots from Minput.data. Format is specified above in parse
	 * method.
	 * @param listOfSlots list to hold free algorithm slots.
	 */
	private void readMemorySlots(LinkedList<MemorySlot> listOfSlots) {

		// Read the next line containing free algorithm slot
		while (reader.hasNextLine()) {

			// Retrieve next line to parse
			String line = reader.nextLine();
			Scanner parser = new Scanner(line);

			// Parse the line to obtain free algorithm slot starting and ending address
			int startAddr = parser.nextInt();
			int endAddr = parser.nextInt();

			// Add new free algorithm slot to the list of slots
			listOfSlots.add( new MemorySlot(startAddr, endAddr) );
		}
	}
}