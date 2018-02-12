import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * @author Day
 *
 */
/**
 * @author Day
 *
 */
public class tf {

	private JFrame frame;
	private JTextField searchBar;
	private QuoteList list=new QuoteList();
	private Quote quote=new Quote();
	private TextArea randQArea; //variable for the text box for the random quote
	private JRadioButton bothRButt,quoteRButt, authorRButt;//the 3 radio buttons (author, quotes, both)
	private String userSearch[];
	private TextArea quoteSResults;//variable for the text field for the quotes found in the search 
	private TextArea recentSearch;
	private final String filename="quotes.XML";
	private JTextField addQuoteField;
	private JTextField addAuthorField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tf window = new tf();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tf() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		list=readQuotesXML(list);//reads quote form XML file
		userSearch=new String[5];
		
		frame = new JFrame();
		frame.setBounds(100, 100, 781, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label1 = new JLabel("GMU Quote Generator");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label1.setBounds(121, 0, 187, 22);
		frame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Random Quote of the Day:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label2.setBounds(15, 25, 161, 15);
		frame.getContentPane().add(label2);
	
		JButton genQButton = new JButton("Generate Random Quote");
		genQButton.setBounds(15, 117, 177, 22);
		frame.getContentPane().add(genQButton);
		genQButton.addActionListener(new genRandQuote());
		
		
		JLabel label3 = new JLabel("Search:");
		label3.setBounds(15, 172, 50, 14);
		frame.getContentPane().add(label3);
		
		searchBar = new JTextField();
		searchBar.setBounds(66, 169, 220, 20);
		frame.getContentPane().add(searchBar);
		searchBar.setColumns(10);
		
		JLabel label4 = new JLabel("Scope:");
		label4.setBounds(25, 198, 46, 14);
		frame.getContentPane().add(label4);

		//set up the radio buttons
		quoteRButt = new JRadioButton("Quote");
		quoteRButt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		quoteRButt.setBounds(61, 196, 53, 18);
		frame.getContentPane().add(quoteRButt);
		
		authorRButt = new JRadioButton("Author");
		authorRButt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		authorRButt.setBounds(117, 194, 59, 22);
		frame.getContentPane().add(authorRButt);
		
		bothRButt = new JRadioButton("Both");
		bothRButt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bothRButt.setBounds(178, 194, 50, 22);
		frame.getContentPane().add(bothRButt);
		
		JButton searchButt = new JButton("search");
		searchButt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		searchButt.setBounds(61, 231, 65, 22);
		frame.getContentPane().add(searchButt);
		searchButt.addActionListener(new searchQuote());
		
		JButton resetButt = new JButton("reset");
		resetButt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		resetButt.setBounds(139, 231, 65, 22);
		frame.getContentPane().add(resetButt);
		resetButt.addActionListener(new resetSearchBar());
		
		//Groups Radio Buttons together		
		radioButtGroup(bothRButt,quoteRButt, authorRButt);		
		
		JLabel label5 = new JLabel("User Searches");
		label5.setBounds(430, 125, 88, 14);
		frame.getContentPane().add(label5);
		
		
		recentSearch = new TextArea("");
		recentSearch.setBounds(418, 150, 100, 101);
		frame.getContentPane().add(recentSearch);
		recentSearch.setPreferredSize(new Dimension(100, 50));
		recentSearch.setEditable(false);
		
		quoteSResults=new TextArea("");
		quoteSResults.setBounds(15, 266, 503, 101);
		frame.getContentPane().add(quoteSResults);
		quoteSResults.setPreferredSize(new Dimension(500, 75));
		quoteSResults.setEditable(false);
		quoteSResults.setMaximumSize(new Dimension(100, 100));
		
		randQArea= new TextArea(quote.getQuoteText()+"\n"+quote.getAuthor());
		randQArea.setBounds(15, 53, 484, 58);
		frame.getContentPane().add(randQArea);
		randQArea.setEditable(false);		
		
		JLabel lblNewLabel = new JLabel("Add New Quote");
		lblNewLabel.setBounds(612, 53, 88, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblQuote = new JLabel("Quote:");
		lblQuote.setBounds(555, 83, 46, 14);
		frame.getContentPane().add(lblQuote);
		
		addQuoteField = new JTextField();
		addQuoteField.setBounds(600, 79, 122, 20);
		frame.getContentPane().add(addQuoteField);
		addQuoteField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Author:");
		lblNewLabel_1.setBounds(555, 121, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		addAuthorField = new JTextField();
		addAuthorField.setBounds(599, 122, 123, 20);
		frame.getContentPane().add(addAuthorField);
		addAuthorField.setColumns(10);
		
		JLabel addSuccessFail = new JLabel("");//Add Quote Success/Fail Label- tells if add quote was successful or not
		addSuccessFail.setBounds(612, 184, 110, 15);
		frame.getContentPane().add(addSuccessFail);
		
		
		JButton addQuoteButton = new JButton("Add");
		addQuoteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message=addQuote();
				System.out.println(message);
				addSuccessFail.setText(message);
				}	
		});
		addQuoteButton.setBounds(612, 150, 89, 23);
		frame.getContentPane().add(addQuoteButton);
		
		
	}
	/*if quote meets qualifications (isGoodQuote==true) it will return an add successful message for
	 * the addSuccessFail JLabel so the user can see it, else will return add fail message*/
	
	private String addQuote()
	{
		//if passes tests then add to list, else do nothing
		if(textFieldNotNull()) 
		{
			if(isGoodQuote())
			{
				Quote temp= new Quote(addAuthorField.getText(),addQuoteField.getText());
				list.setQuote(temp);//adds the temporary quote(temp) to the quote list
				writeQuotesXML();//writes new quote to list
				return "Add Successful!"; 
			}
			addAuthorField.setText(null);//user's entered text is erased after add button pressed
			addQuoteField.setText(null);
			return "Add Failed.";
		}
		return null;
	}
	private boolean isGoodQuote()//series of boolean test to ensure good quotes entered
	{
		if(isValidAuthor()&&notQuoteMax()&&isCapital(addAuthorField)&&isCapital(addQuoteField))
			return true;
		return false;
	}
	private boolean textFieldNotNull()//returns false if author or quote is left blank in textField  
	{
		if(addAuthorField.getText()!=null||addQuoteField.getText()!=null)
			return true;
		return false;
	}
	
	private boolean isValidAuthor()//returns true if ascii char is lowercase/uppercase letter or space
	{
		char [] temp=addAuthorField.getText().toCharArray();
		System.out.println(temp.length);
		for(int i=0;i<temp.length;i++)
		{
			if(	!Character.isLowerCase(temp[i]))
					if(temp[i]!=32)
					{
						if(!Character.isUpperCase(temp[i]))
							return false;
					}
		}
		
		return true;
	}
	
	private boolean notQuoteMax()//returns true if quote list size is 40 or less
	{
		if(list.getSize()<=40)
			return true;
		return false;
	}
	private boolean isCapital(JTextField t)//makes sure Capital letter is the only 1st char allowed
	{
		char[] temp=t.getText().toCharArray();
		if(temp[0]>64&&temp[0]<91)
			return true;
		return false;
	}
	
//Groups the radio search buttons (quote,author,both) together, prevents multiple radio buttons from being selected	
	public void radioButtGroup(JRadioButton a,JRadioButton b, JRadioButton c)
	{
		ButtonGroup radio=new ButtonGroup();
		radio.add(a);
		radio.add(b);
		radio.add(c);
	}
	
	//reads and returns list 
	public QuoteList readQuotesXML(QuoteList list) {
	
		QuoteSaxParser qList =new QuoteSaxParser(filename);
		return qList.getQuoteList();
	}
	 

///////////////////////////////////////////////////////////////////////
	public void writeQuotesXML()
	{   
	        try {
	        	DocumentBuilderFactory quoteDoc = DocumentBuilderFactory.newInstance();
	        	DocumentBuilder quoteBuilder = quoteDoc.newDocumentBuilder();
	            Document doc = quoteBuilder.newDocument();
	            Element mainRootElement = doc.createElementNS("w.XML", "quote-list");
	            doc.appendChild(mainRootElement);
	 
	            //add quote instances to the root 
	            for(int i=0;i<list.getSize();i++)
	            {
	            	mainRootElement.appendChild(quoteToDoc(doc,list.getQuote(i).getQuoteText(), list.getQuote(i).getAuthor()));
	            }
	           
	            createXML(doc);
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	
	}
	
	
	 private Node quoteToDoc(Document doc, String quoteText, String author) {
	        Element q = doc.createElement("quote");
	        q.appendChild(createNode(doc, q, "quote-text", quoteText));
	        q.appendChild(createNode(doc, q, "author", author));
	     
	        return q;
	    }
	 
	 private Node createNode(Document doc, Element element, String type, String value) {
	        Element node = doc.createElement(type);
	        node.appendChild(doc.createTextNode(value));
	        return node;
	    }

	 //puts doc file to XML file 
	 private void createXML(Document doc)
	 {     
		 Source s = new DOMSource(doc);
		 Transformer newXML=null;
		 File file = new File(filename);
		 Result result = new StreamResult(file);

		 try {
			newXML = TransformerFactory.newInstance().newTransformer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			newXML.transform(s, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	
/////////////////////////////////////////////////////////////////////////	
	
	
	
	public class genRandQuote implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			quote=list.getRandomQuote();
			randQArea.setText(quote.getQuoteText()+"\n"+quote.getAuthor());

		}
		 
	}
	
	public class searchQuote implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(searchBar.getText()!=null)
			{
				addToUserSearch(searchBar.getText());
				recentSearch.setText(userSearchToString());
				String searches=search(searchBar.getText());
				quoteSResults.setText(searches);	
			}
		}
	}
	
	public class resetSearchBar implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			searchBar.setText(null);
		}
	}
	
	public String search(String searchString)
	{
		QuoteList foundQuotes=null;//will hold all the found QuoteList objects from the search
		String searches="";//a String variable for all of the found Quotes, put into one String
		if(authorRButt.isSelected())
		{
			searches=authorSearch(foundQuotes, searchString);
		}
		
		else if(quoteRButt.isSelected())
		{
			searches=quoteSearch(foundQuotes, searchString);
		}
		
		else if(bothRButt.isSelected())
		{
			searches=authorSearch(foundQuotes, searchString) +quoteSearch(foundQuotes, searchString);
		}
		
		return searches;
	}
	
	public String searchListToString(QuoteList searchList) {
		String search="";
		for(int i=0;i<searchList.getSize();i++) {
			search=searchList.getQuote(i).getQuoteText()+"\n\t-"+searchList.getQuote(i).getAuthor()+"\n\n"+search;
		}
		
		return search;
	}
	
	public String authorSearch(QuoteList foundQuotes, String searchString)
	{
		String searchesAuthor="";//search List for the author
		foundQuotes=list.search(searchString, 0);//0 will do a search for just the author
		if(foundQuotes!=null)//if the search is unsuccessful, then the searchListToString method will not be used
			//prevents an error with the null pointer
		{
			searchesAuthor=searchListToString(foundQuotes);
		}

		return searchesAuthor;
	}
	
	public String quoteSearch(QuoteList foundQuotes, String searchString)
	{
		String searchesQuote="";//search List for the quote
		foundQuotes=list.search(searchString, 1);//1 will do a search for just the author
		if(foundQuotes!=null)//if the search is unsuccessful, then the searchListToString method will not be used
			//prevents an error with the null pointer
		{
			searchesQuote=searchListToString(foundQuotes);
		}

		return searchesQuote;
	}
	
	
	//adds the searched item to the array
	public void addToUserSearch(String text)
	{
		for(int i=0;i<5;i++)
		{
			if(userSearch[i]==null)//inserts text into available indexes 
			{
				userSearch[i]=text;
				break;
			}
		}
		
		if(userSearch[4]!=null&&userSearch[4]!=text)//all spaces on the recent Search list are full
		{
			rearrangeRecentList(text);//removes the oldest search in the list and adds the newest search
		}
	}
	
	//creates a String from the array of user searches, for the TextArea
	public String userSearchToString()
	{
		String Search="";
		for(int i=4;i>-1;i--)
		{
			Search=(i+1)+"."+userSearch[i]+"\n"+Search;
		}
		
		return Search;
	}
	
	//rearranges the recent search list when it is full
	public void rearrangeRecentList(String text)
	{
		String temp[]=new String[5];
		for(int i=0;i<4;i++)
		{
			temp[i]=userSearch[i+1];
		}
		temp[4]=text;
		userSearch=temp;
	}
}

