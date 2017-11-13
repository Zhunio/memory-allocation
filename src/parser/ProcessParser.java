package src.parser;

// User defined libraries

import src.memory.Process;

// Java SDK libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The <code>ProcessParser</code> class is responsible for parsing the
 * Pinput.data file. The Pinput.data file constains a list of processes.
 * The format of the Pinput.data is as follows:
 * <blockquote><pre>{@code
 * 	3        = number of processes
 * 	1 190    = process id and size
 * 	... ...
 * 	... ...
 * }</pre></blockquote>
 *
 * @author Richard I. Zhunio
 */
public class ProcessParser {
	/* Scanner that will read the contents of the disk file */
	private Scanner reader;

	/**
	 * Creates a new <code>ProcessParser</code>.
	 *
	 * @param file the filepath to the Pinput.data
	 * @throws FileNotFoundException if <code>file</code> is not a correct
	 *                               path to the Pinput.data
	 */
	public ProcessParser(String file) throws FileNotFoundException {
		reader = new Scanner(new File(file));
	}

	/**
	 * Parses a Pinput.data file.
	 *
	 * @return list of {@link src.memory.Process}s read from the Pinput.data file
	 * @throws InvalidNumberException if invalid number processes are provided.
	 */
	@SuppressWarnings("unused")
	public LinkedList<Process> parse() throws InvalidNumberException {
		// Represents a list of processes
		LinkedList<Process> listOfProcesses = new LinkedList<>();

		// Read number of processes
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
	 * @throws InvalidNumberException if there is an invalid number of processes.
	 */
	private int readProcessNo() throws InvalidNumberException {

		// if invalid number of processes
		int processNo = -1;

		// Read the first line from the disk file
		if (reader.hasNextLine()) {

			// Read the very first line on the disk file
			processNo = Integer.parseInt(reader.nextLine());

			// Error occurs if an invalid number is entered
			if (processNo < 0)
				throw new InvalidNumberException("No valid number of processes: "
					+ processNo);
		}

		return processNo;
	}

	/**
	 * Reads processes from Pinput.data and adds them to a list.
	 *
	 * @param listOfProcesses list to hold {@link src.memory.Process}s.
	 */
	private void readProcesses(LinkedList<Process> listOfProcesses) {

		// Read the next line containing a process
		while (reader.hasNextLine()) {

			// Retrieve next line to parse
			String line = reader.nextLine();
			Scanner parser = new Scanner(line);

			// Parse the line to obtain process id and size
			int processID = parser.nextInt();
			int processSize = parser.nextInt();

			// Add new process to the list
			listOfProcesses.add(new Process(processID, processSize));
		}
	}
}