package src.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import src.memory.Process;

public class ProcessParser {
	/* Represents the scanner that will read the contents of the disk file */
	private Scanner reader;

	public ProcessParser(String file) throws FileNotFoundException {
		reader = new Scanner(new File(file));
	}

	/**
	 * Parse a Pinput.data file.
	 * The format is as follows:
	 * 3		- Number of processes
	 * 1 190	- Process ID followed by process size.
	 * 2 210	- Same as above
	 * 3 205
	 * @return A list of processes.
	 * @throws Exception if error occurs
	 */
	@SuppressWarnings("unused")
	public LinkedList<Process> parse() throws Exception {
		// Represents list of processes that will be read from file disk
		LinkedList<Process> listOfProcesses = new LinkedList<>();

		// Read process no
		int processNo = readProcessNo();

		// Read list of processes from file disk
		readProcesses(listOfProcesses);

		// Return list of processes
		return listOfProcesses;
	}
	/**
	 * Reads the number of processes in the disk file. The first line is
	 * read, and we expect one int value.
	 *
	 * @return the number of processes in the disk file.
	 * @throws Exception if there is an invalid number of processes.
	 */
	private int readProcessNo() throws Exception {

		// if invalid number of processes
		int processNo = -1;

		// Read the first line from the disk file
		if (reader.hasNextLine()) {

			// Read the very first line on the disk file
			processNo = Integer.parseInt(reader.nextLine());

			// Error occurs if an invalid number is entered
			if (processNo < 0)
				throw new Exception("No valid number of processes: "
					+ processNo);
		}

		return processNo;
	}
	/**
	 * Read processes from Pinput.data. Format is specified above in parse method.
	 * @param listOfProcesses list to hold list o processes.
	 */
	private void readProcesses(LinkedList<Process> listOfProcesses) {

		// Read the next line containing process
		while (reader.hasNextLine()) {

			// Retrieve next line to parse
			String line = reader.nextLine();
			Scanner parser = new Scanner(line);

			// Parse the line to obtain process id and size
			int processID = parser.nextInt();
			int processSize = parser.nextInt();

			// Add new process to the ready queue
			listOfProcesses.add( new Process(processID, processSize) );
		}
	}
}