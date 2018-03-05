import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyword  {
			
	public QuoteList addKeyword(QuoteList list, int index,String key)
		{
			tf k=new tf();
			list.getQuote(index).setKeyword(key);
			k.writeQuotesXML(list);
			list=k.readQuotesXML(list);
				
			return list;	
		}

	 }
	