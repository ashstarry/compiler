
public class Symbol {
	private int id;
	private String symbolName;
	private boolean isTerminal;
	
	public Symbol(int id,String symbolName,boolean isTerminal) {
		this.id=id;
		this.symbolName=symbolName;
		this.isTerminal=isTerminal;
		
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public boolean isterminal() {
		// TODO Auto-generated method stub
		if(isTerminal)
			return true;
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
