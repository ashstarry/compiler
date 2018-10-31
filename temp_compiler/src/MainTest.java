/**
 * Analyzes a .txt file (input.txt) and outputs its component tokens to the console
 * contact info: Jou9934@gmail.com
 * @author Kenneth Jun
 *
 */
public class MainTest {
	public static void main(String[] args) {
		//create tokenizer and do I/O import
		tokenizer testLexer = new tokenizer("input.txt");
		testLexer.analyse();
		testLexer.close();
	}
}
