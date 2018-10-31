
/**
 * contains token identifiers and their associated int value
 * contact info: Jou9934@gmail.com
 * @author Kenneth Jun
 *
 */
public class KeyTypes {

	//user-defined
	public static final int id = 2;
	public static final int aint = 3;//leading a needed to avoid using a keyword as an id
	public static final int afloat = 4;
	public static final int astring = 5;
	
	//unpaired delimiters
	public static final int comma = 6;
	public static final int semi = 7;
	
	//utility
	public static final int error = 99;
	public static final int eof = 0;

	//keywords
	public static final int kprog = 10;
	public static final int kmain = 11;
	public static final int kfcn = 12;
	public static final int kclass = 13;
	public static final int kfloat = 14;
	public static final int kint = 15;
	public static final int kstring = 16;
	public static final int kif = 17;
	public static final int kelseif = 18;
	public static final int kelse = 19;
	public static final int kwhile = 20;
	public static final int kinput = 21;
	public static final int kprint = 22;
	public static final int knew = 23;
	public static final int kreturn = 24;
	public static final int kvar = 25;
	
	//paired delimiters
	public static final int angle1 = 31;
	public static final int angle2 = 32;
	public static final int brace1 = 33;
	public static final int brace2 = 34;
	public static final int bracket1 = 35;
	public static final int bracket2 = 36;
	public static final int parens1 = 37;
	public static final int parens2 = 38;
	
	//punctuation
	public static final int aster = 41;
	public static final int caret = 42;
	public static final int colon = 43;
	public static final int dot = 44;
	public static final int equal = 45;
	public static final int minus = 46;
	public static final int plus = 47;
	public static final int slash = 48;
	
	//multi-character operators
	public static final int oparrow = 51;
	public static final int opeq = 52;
	public static final int opne = 53;
	public static final int ople = 54;
	public static final int opge = 55;
	public static final int opshl = 56;
	public static final int opshr = 57;
}
