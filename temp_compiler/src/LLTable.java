import java.util.List;

public class LLTable {
	
}

public List<Symbol> get(Symbol currentSymbol, Token currentToken) throws Exception{
	List<Symbol> expandedSymbol;
	RuleList ruleList;
	ruleList=new RuleList();
	int rowHeader;
	int columHeader;
	int index;//decide which rule 
	
	//finish the logic only 
	rowHeader=currentSymbol.getId();
	columHeader=currentToken.getId();
	index=getRuleId(rowHeader,columHeader);
	
	Rule rule=ruleList.getRule(index);
	expandedSymbol=rule.getSymbols();
	return expandedSymbol;
}

private int getRuleId(int rowHeader, int columHeader) {
	// TODO Auto-generated method stub
	
	return 0;
}

