import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
/**
 * Takes input from a file and outputs formatted tokens to the console.

 * contact info: Jou9934@gmail.com, dongyuans@hotmail.com, tristansmn@gmail.com
 * @author Kenneth Jun, Shawn Dong, Tristan Simon
 * 
 */
public class tokenizer extends TypeUtil{
	private static final char eol = System.lineSeparator().charAt(0);//system-dependent eol character
	private BufferedReader inStream; //input stream, BufferedReader necessary for getNext() logic
	private PrintStream outStream; //output stream, writes to "output.txt"
	private char ch; // holds the current symbol being analyzed
	private char next; //holds the next symbol in stream
	private String strToken; // holds multi-character tokens
	private int lineCount = 1; //counter for line number
	
	/**
	 * Constructor.  Opens the file being read
	 * @param fileSrc String holding path to input file
	 */
	public tokenizer(String fileSrc) {
		try {
			inStream = new BufferedReader(new FileReader(fileSrc));
			
			outStream = new PrintStream(new FileOutputStream("output.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
			writeFile("error", "");
		}
	}

	/**
	 * Reads and analyzes input stream.  Calls writeFile when token is processed and loops back to enclosing while loop
	 */
	public void analyse() {
		strToken = ""; // initialize multi-character holder to empty
		int temp;//holds int value passed in by BufferedReader.read()
		try {
			while ((temp = inStream.read()) != -1) { //check if we hit the end of the file and read in next character
				ch = (char) temp;
				getBC(); //remove all whitespace in the stream
				if (Character.isWhitespace(ch)) {break;} //rare case where last character in file is whitespace, prevents endless loop
				
				if (isLetter(ch)) { //=====================LETTERS============================
					concat(); //there are no single-character tokens that start with a letter other than ids, so this is safe
					getNext(); //use next for interior input tests to avoid dropping input with the exterior while loop
					while (isLetter(next) || isDigit(next)) {//test for alphanumeric char, runs until end of token
						getChar();
						concat();
						getNext();
					}
					
					if (isKeyWord(strToken)) {
						writeFile("k"+strToken,strToken);//token is keyword.  k needed to match keyword to KeyType field
					} else { 
						writeFile("id",strToken);//token is identifier
					}
					strToken = "";
					
					
				} else if (isDigit(ch)) {//=======================DIGITS=============================
					concat();
					getNext();
					while (isDigit(next)) {//get all initial digits from token
						getChar();
						concat();
						getNext();
					}
					if (next == '.') {//it's a float because there's a dot, see if there are more numbers
						getChar();
						concat();//process the dot
						getNext();
						while (isDigit(next)) {//there might not be any digits after the dot.  if there are, get them all
							getChar();
							concat();
							getNext();
						}
						writeFile("afloat",strToken);
						strToken = "";
					}
					else {//must be int, there's no dot
						writeFile("aint",strToken);
						strToken = "";
					}
					
					
				} else if (isOperator(ch)) { //====================OPERATORS==========================
					getNext();
					if (ch == '!') {//test for "!="
						if (next == '=') {
							concat();
							getChar();
							concat();
							writeFile("opne",strToken);
							strToken = "";
						}
						else {//'!' without '=' is an invalid token
							writeFile("error",ch+"");
						}
					}
					
					else if(ch == '/'){//test for /, /*, and //
						if(next == '*') {//for comment with/*
							getChar();
							while(true){//keep going until you hit the */
								if (ch == eol) {//because it can span multiple lines, linecount increments might be necessary
									lineCount++;
								}
								getChar();
								if(ch == '*'){// might end
									getChar();
									if(ch == '/') {//end of comment found
										break;
									}
								}
							}
						}
						else if(next == '/'){//for comment with //
							while(ch != eol){//stop at end of line
								getChar();
							}
							lineCount++;
						}
						else {
							writeFile("slash",ch+"");//must be '/'
						}
					}
					
					else if(ch == '=') {//test for =, and ==
						if(next == '=') {// == test
							concat();
							getChar();
							concat();
							writeFile("opeq",strToken);
							strToken = "";
						}
						else {
							writeFile("equal",ch+"");
						}
					}
					else if(ch == '-') { //test for -int/float, ->, and -
						if (isDigit(next) || next == '.') {//is it an int/float?
							concat();
							getChar();
							while (isDigit(ch)){//get all digits pre-dot
								concat();
								getNext();
								if (!(isDigit(next))) {
									break;
								}
								getChar();
							}
							getNext();
							if (next == '.') {//needed if coming from previous while loop
								getChar();
							}
							if (ch == '.') {
								concat();
								getNext();
								if (isDigit(next)) {//get all digits post-dot
									getChar();
									while (isDigit(ch)) {
										concat();
										getNext();
										if (!(isDigit(next))) {
											break;
										}
										getChar();
									}
								}
								writeFile("afloat",strToken);//dot found: float token
								strToken = "";
							}
							if (!strToken.equals("")) { //was it written as a float?  if not,
								writeFile("aint",strToken); //write it as an int
								strToken = "";
							}
						}
						
						
						else if(next == '>') {// test for ->
							concat();
							getChar();
							concat();
							writeFile("oparrow",strToken);
							strToken = "";
						}
						else {
							writeFile("minus",ch+"");// must be '-'
						}
					}
					
					
					else if(ch == '<') {// test for < , <= , <<
						if (next == '=') {// <= logic
							concat();
							getChar();
							concat();
							writeFile("ople",strToken);
							strToken = "";
						}
						else if (next == '<') {// << logic
							concat();
							getChar();
							concat();
							writeFile("opshl",strToken);
							strToken = "";
						}
						else {
							writeFile("angle1",ch+"");// must be <
						}
					}
					
					
					else if(ch == '>') {//test for > , >= , >>
						if (next == '=') {// >= logic
							concat();
							getChar();
							concat();
							writeFile("opge",strToken);
							strToken = "";
						}
						else if (next == '>') {// >> logic
							concat();
							getChar();
							concat();
							writeFile("opshr",strToken);
							strToken = "";
						}
						else {
							writeFile("angle2",ch+"");// must be >
						}
					}
					else {//must be single-character
						switch (ch) {
						case '+': writeFile("plus",ch+"");break;
						case '*': writeFile("aster",ch+"");break;
						case '^': writeFile("caret",ch+"");break;
						default: writeFile("error",ch+"");break;//this should not be possible
						}
					}
					

				} else if (isSeparators(ch)) { //==========================SEPARATORS==============================
					getNext();
					if (ch == '.') {//could be a float, test for digits
						if (isDigit(next)) {
							concat();
							getChar();
							while (isDigit(ch)) {//get all digits
								concat();
								getNext();
								if (!(isDigit(next))) {
									break;
								}
								getChar();
							}
							writeFile("afloat",strToken);
							strToken = "";
						}
						else {//must be non-float
							writeFile("dot",ch+"");
						}
						
					}
					else if (ch == '"') {//we found a string
						concat();
						getNext();
						while(next != '"') {//go until you hit another ".  No strings with " in them are legal
							getChar();
							concat();
							getNext();
						}
						getChar();
						concat();
						writeFile("astring",strToken);
						strToken = "";
					}
					else {//single-character tokens
						switch (ch) {
						case '{': writeFile("brace1",ch+""); break;
						case '}': writeFile("brace2",ch+""); break;
						case '[': writeFile("bracket1",ch+""); break;
						case ']': writeFile("bracket2",ch+""); break;
						case '(': writeFile("parens1",ch+""); break;
						case ')': writeFile("parens2",ch+""); break;
						case ',': writeFile("comma",ch+""); break;
						case ';': writeFile("semi",ch+""); break;
						case ':': writeFile("colon",ch+"");break;
						default:  writeFile("error",ch+"");break;//this should never run
						}
					}
				} else writeFile("error",ch+"");//anything not found isn't in the grammar, or there's a bug. Either way it's an error
			}
			writeFile("eof","");
		} catch (IOException e) {
			writeFile("error","");
			e.printStackTrace();
		}
	}

	/**
	 * Advances the input stream 1 character and stores the value to ch
	 */
	public void getChar() {
		int temp;
		try {
			if ((temp = inStream.read()) == -1) {//prevents errors if we hit the end of file early
				ch = eol;
			}
			else {
				ch = (char) temp;
			}
		} catch (IOException e) {
			writeFile("error","");
			e.printStackTrace();
		}
	}
	
	/**
	 * look at next character in stream without changing position of pointer and store it in 'next'
	 */
	public void getNext() {
		int bufSize = 3;
		try {
			inStream.mark(bufSize);
			int temp;
			temp = inStream.read();
			inStream.reset();
			if (temp == -1) {//prevent errors from eof
				next = eol;
			}
			else {
				next = (char) temp;
			}
		} catch (IOException e) {
			writeFile("error","");
			e.printStackTrace();
		}
		
	}
	/**
	 * Reads stream until non-whitespace character is found.  Increments lineCount every time an eol character is found.
	 */
	public void getBC() {

		while (Character.isWhitespace(ch)) {
			if (ch == eol) { //eol counts as whitespace, so the check has to go here
				lineCount++;
			}
			getChar();
			int bufSize = 2;
			try {
				inStream.mark(bufSize);
				if (inStream.read() == -1) { //stop if you're about to hit the end of the file
					inStream.reset();
					break;
				}
				inStream.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Appends character in ch to strToken
	 */
	public void concat() {
		strToken += ch;
	}
	
	/**
	 * Output a token to the console and output.txt
	 * @param file key value of the token.  Used to find id
	 * @param s actual text input of the token
	 */
	public void writeFile(String file,String s) {
		int id = getType(file);
		if ((id <= 5) && (id >= 3)) {
			if (id == 3) {
				System.out.printf("(Tok:%3d line=%2d str= \"%s\" int= %s)%n",id,lineCount,s,s);
				outStream.printf("(Tok:%3d line=%2d str= \"%s\" int= %s)%n",id,lineCount,s,s);
			}
			else if (id == 4){
				System.out.printf("(Tok:%3d line=%2d str= \"%s\" float= %s)%n",id,lineCount,s,s);
				outStream.printf("(Tok:%3d line=%2d str= \"%s\" float= %s)%n",id,lineCount,s,s);
			}
			else {
				s = s.substring(1, s.length()-1);
				System.out.printf("(Tok:%3d line=%2d str= \"%s\")%n",id,lineCount,s);
				outStream.printf("(Tok:%3d line=%2d str= \"%s\")%n",id,lineCount,s);
			}
		}
		else {
			System.out.printf("(Tok:%3d line=%2d str= \"%s\")%n",id,lineCount,s);
			outStream.printf("(Tok:%3d line=%2d str= \"%s\")%n",id,lineCount,s);
		}
	}
	
	/**
	 * Closes input and output streams
	 */
	public void close() {
		outStream.close();
		try {
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}