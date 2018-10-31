
public class SymbolList {
	private String symbolName; 
	private Symbol[] symbols;
	
	public SymbolList() {
		symbols[0]=new Symbol(0,"$",true);
		symbols[1]=new Symbol(1,"Pgm",false);
		//.....
		//my way to list all symbols
	}
	


	public Symbol getSymbol(String symbolName) {
		// TODO Auto-generated method stub
		for(int i=0;i<symbols.length;i++) {
			if(symbols[i].getSymbolName().equals(symbolName))
				return symbols[i];
		}
		System.out.println("cant find symbol");//turn to exception..
	}
