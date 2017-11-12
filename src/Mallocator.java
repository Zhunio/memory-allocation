package src;

import src.algorithm.*;
import src.parser.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 11/11/17.
 *
 * @author Richard I. Zhunio
 */
public class Mallocator {
	public static void main(String[] args) throws Exception{
		String Minput = "Minput.data";
		String Pinput = "Pinput.data";

		// Create parsers for parsing Minput.data and Pinput.data
		MemorySlotParser mParser = new MemorySlotParser(Minput);
		ProcessParser pParser = new ProcessParser(Pinput);

		// Parse input data and
		LinkedList<MemorySlot> listOfSlots = mParser.parse();
		LinkedList<Process> listOfProcesses = pParser.parse();

		// Perform first fit algorithm
		FF firstFit = new FF(copyProcesses(listOfProcesses), copySlots(listOfSlots));
		List<String> log = firstFit.run();

		// Perform best fit algorithm
		BF bestFit = new BF(copyProcesses(listOfProcesses), copySlots(listOfSlots));
		List<String> log1 = bestFit.run();

		// Perform worst fit algorithm
		WF worstFit = new WF(copyProcesses(listOfProcesses), copySlots(listOfSlots));
		List<String> log2 = worstFit.run();

		System.out.println(log);
		System.out.println(log1);
		System.out.println(log2);
	}

	private static LinkedList<MemorySlot> copySlots(LinkedList<MemorySlot> listOfSlots)
		throws CloneNotSupportedException {
		LinkedList<MemorySlot> temp = new LinkedList<>();
		for (MemorySlot slot: listOfSlots) {
			temp.add((MemorySlot) slot.clone());
		}

		return temp;
	}

	private static LinkedList<Process> copyProcesses(LinkedList<Process> listOfProcesses)
		throws CloneNotSupportedException {
		LinkedList<Process> temp = new LinkedList<>();
		for (Process process: listOfProcesses) {
			temp.add((Process) process.clone());
		}

		return temp;
	}


}
