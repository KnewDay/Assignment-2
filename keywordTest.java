import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class keywordTest {
	QuoteList q;
	Keyword k;
	
	@BeforeEach
	void setUp() throws Exception {
		QuoteSaxParser qList =new QuoteSaxParser("quotes.XML");
		q=qList.getQuoteList();
		k=new Keyword();
		k.addKeyword(q,0 ,"Bubbie");
	}

	@Test
	public void set_GetKeywordTest()
	{
		Quote key=new Quote("Day","Live life");
		key.setKeyword("motto");
		assertEquals("motto",key.getKeyword());
	}
	
	@Test
	public void addKeywordTest()
	{
		assertEquals("Bubbie",q.getQuote(0).getKeyword());
	}
	
	@Test
	public void write_ReadKeywordTest()
	{
		q=k.addKeyword(q, 1, "Snuggs");
		assertEquals("Snuggs",q.getQuote(1).getKeyword());
	}
	
	@Test
	void searchKeywordTest() {
		k.addKeyword(q, 0, "Snuggs");
		QuoteList list=q.search("Snuggs", 4);
		assertEquals("Snuggs",list.getQuote(0).getKeyword());
		assertEquals("Snuggs",list.getQuote(1).getKeyword());
		assertEquals("Eschew obfuscation!",list.getQuote(1).getQuoteText());
	}


}
