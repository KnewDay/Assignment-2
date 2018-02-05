package tst;

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
		list=quotes(list);//adds 6 Quotes to the Quotes list
		userSearch=new String[5];
		
		frame = new JFrame();
		frame.setBounds(100, 100, 546, 416);
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
		quoteSResults.setBounds(15, 292, 503, 75);
		frame.getContentPane().add(quoteSResults);
		quoteSResults.setPreferredSize(new Dimension(500, 75));
		quoteSResults.setEditable(false);
		quoteSResults.setMaximumSize(new Dimension(100, 100));
		
		randQArea= new TextArea(quote.getQuoteText()+"\n"+quote.getAuthor());
		randQArea.setBounds(15, 53, 484, 58);
		frame.getContentPane().add(randQArea);
		randQArea.setEditable(false);		
	}
	
//Groups the radio search buttons (quote,author,both) together, prevents multiple radio buttons from being selected	
	public void radioButtGroup(JRadioButton a,JRadioButton b, JRadioButton c)
	{
		ButtonGroup radio=new ButtonGroup();
		radio.add(a);
		radio.add(b);
		radio.add(c);
	}
	
//Creates 6 instances of Quote and adds each one to the QuoteList, then returns the list
	public QuoteList quotes(QuoteList list) {
		Quote temp =new Quote("Don Cunningham","Eschew obfuscation!");
		list.setQuote(temp);
		
		temp=new Quote("H. L. Mencken","For every problem there is one solution which is simple, neat, and wrong.");
		list.setQuote(temp);
		
		temp=new Quote("Ramsey Clark","A right is not what someone gives you; it's what no one can take from you.</");
		list.setQuote(temp);
		
		temp=new Quote("Joseph Addison","Don't tell me how hard you work. Tell me how much you get done");
		list.setQuote(temp);
		
		temp=new Quote("John Keats","Nothing ever becomes real till it is experienced; even a proverb is no proverb to"
				+ " you till your life has illustrated it.");
		list.setQuote(temp);
		
		temp=new Quote("Bill Rogers","No one who works a forty hour week is ever going to beat me.");
		list.setQuote(temp);
		
		return list;
	}
	 
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

