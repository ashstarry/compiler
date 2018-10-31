
import java.lang.reflect.Field;
/**
 * Separates tokens for ease of processing
 * Contains logic to test inputs against the lexicon
 * contact info: dongyuans@hotmail.com
 * @author Shawn Dong 
 *
 */
public class TypeUtil {
	private final String keyWords[] = { "prog", "main", "fcn", "class",
			"float", "int", "string", "if", "elseif", "else", "while",
			"input", "print", "new", "return", "var"};
	private final char operators[] = { '+','^', '-', '*', '/','<','>','=','!'};
	private final char separators[] = { ',', ':', ';', '{', '}', '(', ')', '[', ']', '.', '"','\\'};
	
	/**
	 * Tests if input is a letter or underscore
	 * @param ch character to be tested
	 * @return true if input is letter or underscore, false otherwise
	 */
	public boolean isLetter(char ch) {
		return (Character.isLetter(ch) || ch == '_');
	}

	/**
	 * Tests if input is a digit
	 * @param ch character to be tested
	 * @return true if input is digit, false otherwise
	 */
	public boolean isDigit(char ch) {
		return Character.isDigit(ch);
	}

	/**
	 * Tests if input is a keyword
	 * @param s input string to test
	 * @return true if input is a keyword, false otherwise
	 */
	public boolean isKeyWord(String s) {
		for (int i = 0; i < keyWords.length; i++) {
			if (keyWords[i].equals(s))
				return true;
		}
		return false;
	}

	/**
	 * Tests if input is in the operator list
	 * @param ch character to be tested
	 * @return true if input in list, false otherwise
	 */
	public boolean isOperator(char ch) {
		for (int i = 0; i < operators.length; i++) {
			if (ch == operators[i])
				return true;
		}
		return false;
	}

	/**
	 * Tests if input is in the separators list
	 * @param ch character to be tested
	 * @return true if input in list, false otherwise
	 */
	public boolean isSeparators(char ch) {
		for (int i = 0; i < separators.length; i++) {
			if (ch == separators[i])
				return true;
		}
		return false;
	}

	/**
	 * Finds the id of a given token key
	 * @param args Token to find id for
	 * @return id of the given token
	 */
	public int getType(String args) {
		int type = -1;
		Field[] fields = KeyTypes.class.getDeclaredFields();//KeyTypes has all keys as fields holding their int id
		for (Field field : fields) {
			if (field.getName().equals(args)) {
				try {
					type = (Integer) field.get(new KeyTypes());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return type;
	}

}
