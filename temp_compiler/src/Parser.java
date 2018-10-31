import java.util.*;

public class Parser {
	private LLTable table;
	private SymbolList symbolList;
	//get token here,wait to compelete
	//private ArrayList<Token> tokens
	private Node root;
	Stack<Symbol> symbolStack;
	Queue<token> inputToken;
	
	
	public parser(ArrayList<Token> tokens) {
		this.tokens=tokens;
		parse();
	}
	
	//@return Node
	public Node parse() throws Exception{
		//havent add pst Node,use symbol to instead
		
		LLTable table=new LLTable();//realization
		SymbolList symbolList=new SymbolList();
		//init
		Node root=new Node();//havent do tree operation
		Stack<Symbol> symbolStack=new Stack();// the stack(terminals and non-terminals)
		Queue<token> inputToken=new LinkedList<>(tokens);//input from text
		
		//pre-operate for stack;
		Symbol endSymbol=symbolList.getSymbol("$");
		Symbol startSymbol=symbolList.getSymbol("Pgm");
		symbolStack.push(endSymbol);
		symbolStack.push(startSymbol);
		
		//loop part
		while(!symbolStack.isEmpty()) {
			Symbol currentSymbol = symbolStack.peek();
			Token currentToken = inputToken.peek();
			if(currentSymbol.isterminal()) {//terminal situation
				if(currentSymbol.getId()==currentToken.getID()) 
					m1();//if use this way,the id of non-terminal in symbollist should 
				//be the same as token id in lexer,this is a problem
				else
					m2();
			}
			else {//non-terminal situation
				try {
					List<Symbol> expandedSymbol = table.get(currentSymbol, currentToken);
	                m4(expandedSymbol);
				}
				catch(Exception e){
					m3(e);
				}
			}
			
		}
						
		return root;
				
	}





	private void m1() {
		// TODO Auto-generated method stub
		Symbol poppedSymbol = symbolStack.pop();
        Token removedToken = inputToken.poll();
        if(inputToken.getID()!=0) {
        	
        }
	}	

	private void m2() {
		// TODO Auto-generated method stub
		throw new Exception("Error during parsing. Symbol and token do not match.");
	}
	
	private void m3(Exception e) {
		// TODO Auto-generated method stub
		e.printStackTrace();
        throw new Exception("Table does not contain a rule for the given symbol and token.");

	}

	
	private void m4(List<Symbol> expandedSymbol) {
		// TODO Auto-generated method stub
		Collections.reverse(expandedSymbol);
		Symbol poppedSymbol = symbolStack.pop(); 
        for (Symbol s : expandedSymbol) 
        	symbolStack.push(s);
        //tree working(node) here, waiting to complete
        
	}


	
}
