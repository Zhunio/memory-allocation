package src;

// User libraries
import src.algorithm.BF;
import src.algorithm.FF;
import src.algorithm.WF;
import src.memory.MemoryAllocator;

// Java SDK Libraries
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p>The <code>Mallocator</code> class represents the entry point to the memory
 * allocation project.
 *
 * <p>The {@code #main} method receives the following arguments:
 * <blockquote><pre>{@code
 *		args[0] = File path to Minput.data
 *		args[1] = File path to Pinput.data
 *		args[2] = First memory allocation method such as FF, BF, WF
 *		args[3] = Second memory allocation method
 *		args[n] = N memory allocation method.
 * }</pre></blockquote>
 *
 * <p>The memory allocation project consists of implementing three memory
 * allocation methods: First Fit (FF), Best Fit(BF), and Worst Fit (WF). The
 * {@code #main} method accepts the following arguments as valid memory allocation
 * methods: FF, BF, and WF.
 * @author Richard I. Zhunio
 */
public class Mallocator {
	public static void main(String[] args) throws Exception {
		//Check for min number of arguments
		if (args.length < 3) {
			System.err.println("Wrong number of cmd arguments.");
			System.exit(1);
		}

		// Retrieve memory input file and process input file arguments
		String mInput = args[0];
		String pInput = args[1];

		// Will hold memory allocator algorithms
		String[] mAlgorithms = new String[args.length - 2];

		// Represents memory allocator index from cmd arguments
		int algorithmIndex = 2;

		// Retrieve memory allocator algorithms
		while (algorithmIndex < args.length) {
			mAlgorithms[algorithmIndex - 2] = args[algorithmIndex++];
		}

		// Perform the memory allocator algorithms
		for (String mAlgorithm: mAlgorithms) {
			// Create new Memory allocator algorithm
			MemoryAllocator mAllocator =
				Mallocator.generate(mAlgorithm, mInput, pInput);

			// Run memory allocator algorithm
			List<String> log = mAllocator.run();

			// Generate output file path
			File outputFile = getOutputFile(mAlgorithm);

			// Save the log into a file
			PrintWriter writer = new PrintWriter(outputFile);
			log.forEach(writer::println);
			writer.close();
		}

	}

	public static MemoryAllocator generate(String memoryAlgorithm,
										   String memoryInput, String processInput)
		throws Exception {
		switch (memoryAlgorithm) {
			case "FF":
				return new FF(memoryInput, processInput);
			case "BF":
				return new BF(memoryInput, processInput);
			case "WF":
				return new WF(memoryInput, processInput);
			default:
				throw new Exception("Not supported memory allocator "
					+ "algorithm: " + memoryAlgorithm);
		}
	}


	/**
	 * Generates the output file from the input file. It assures to place the
	 * output file in the same directory as the input file and also renames
	 * the generated file to output.[ext]
	 *
	 * @param file the input file
	 * @return the generated output file
	 */
	private static File getOutputFile(String file) {
		// Get parent directory
		String parentDir = new File(file).getParent();

		// Test if parent dir is null
		parentDir = parentDir == null ? "" : parentDir + "/";

		// Get output name
		String output = file + "output.data";

		return new File(parentDir + output);
	}

}
