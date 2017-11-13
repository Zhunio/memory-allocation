package src.parser;

/**
 * A <code>InvalidNumberException</code> is thrown when a number that is
 * defined as invalid is detected.
 *
 * @author Richard I. Zhunio
 */
public class InvalidNumberException extends Exception {

	/**
	 * Creates a new <code>InvalidNumberException</code> with the given message.
	 *
	 * @param message additional information about the origin of this
	 *                <code>Exception</code>
	 */
	public InvalidNumberException(String message) {
		super(message);
	}
}
