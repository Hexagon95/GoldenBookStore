package Database;

public class Add_DB { // This class inserts a new record into the embedded database
	// VARIABLES //	
	
	private String[] Authors; // a string array that stores the current authors
		private int Author_index; // an integer that represents the proper ID of the proper author in the embedded database author table
	
	public boolean Success = false; // a boolean that represents the success of the insertion ( mostly a GUI window use this, it closes if the insertion succeed and nothing happens after pressing the submit button and success if false... for user convenience )
	
	// CONSTRUCTOR //
	
	public Add_DB(String ISBN, String Title, String Author, int Type) { // the main mechanic is: examine the given parameters if everything is fine, attempt to insert
		Fill_Authors();
		
		if(Is_Correct(ISBN, Title, Author, Type)) Update(ISBN, Title, Author, Type);
		else Discard(); // does nothing much, ( mostly had a purpose during developing ) 
	}	
	
	// METHODS //
	
	private boolean Is_Correct(String ISBN, String Title, String Author, int Type) { // the examination will go on separately		
		if(Is_ISBN(ISBN) && Is_Title(Title) && Is_Author(Author) && Is_Type(Type)) return true;		
		else {
			System.out.println("Not Correct!"); // all of the system.out.println calls is for the developer, over all the users will only see the GUI so, I kept all of them
			return false;
		}
	}
		private boolean Is_ISBN(String ISBN) { // main mechanic is: are the given strings are numbers and are they contains 9 & 1 
			System.out.println("ISBN");
			if(ISBN.toCharArray().length == 16 && Character.isDigit(ISBN.toCharArray()[15]) && Middle(ISBN)) {
				System.out.println("ISBN OK");
				return true;
			}
			return false;
		}
			private boolean Middle(String ISBN) { // it exams the 9 character length numeric part ISBN_[THIS]-8
				int x = 0;
				for (int i = 5; i <= 13; i++) {
					if(Character.isDigit(ISBN.toCharArray()[i])) x++;
				}
				if(x == 9) return true;
				else return false;
			}
		private boolean Is_Title(String Title) { // if the given string as title not empty, than it is fine...
			System.out.println("Title");
			if(Title.toCharArray().length >= 1 && Title.toCharArray().length <= 30) return true;
			else return false;
		}
		private boolean Is_Author(String Author) { // main mechanic is: are the given string already an existing author if it is, we pick it's ID; if not but he given string not empty, it will add the given string to the database as a new Author
			System.out.println("Author");
			int x;
			
			for (int i = 0; i < Authors.length; i++) {
				x = 0;
				for (int j = 0; j < Author.toCharArray().length; j++) {
					if (Authors[i].toCharArray()[j] == Author.toCharArray()[j]) x++;
				}
				if(x == Author.toCharArray().length) {
					i++;
					this.Author_index = i;
					return true;
				}
			}
			if(Author.toCharArray().length >= 3 && Author.toCharArray().length <= 30) {
				Add_Author(Author);
				return true;
			}				
			else return false;
		}
			private void Add_Author(String Author) { //
				try {
					String sqlp = "SELECT MAX(Author_ID) FROM Author";						
						
					Connector.s = Connector.conn.createStatement();
					Connector.rs = Connector.s.executeQuery(sqlp);
						while(Connector.rs.next()) {					
						
							int Max = Connector.rs.getInt(1);
							Max++;
								
							Connector.conn.createStatement().execute("INSERT INTO Author VALUES (" + Max + ", '" + Author + "')");
							this.Author_index = Max;
						}
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}
		private boolean Is_Type(int Type) { // if user has chosen a type the variable will have a value that isnt's -1, -1 is a default for no chosen element
			System.out.println("Type");
			if(Type != -1) return true;
			else return false;
		}
	private void Update(String ISBN, String Title, String Author, int Type) { // the method that attempts to insert a record
		try {
			System.out.println("Update");
			String sqlp = "SELECT MAX(Book_ID) FROM Book";
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {
					int Max = Connector.rs.getInt(1);
					Max++;
					Type++;
					
					Connector.conn.createStatement().execute("INSERT INTO Book VALUES (" + Max + ",'" + ISBN + "', '" + Title + "')");
					Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (" + Max + "," + this.Author_index + ")");
					Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (" + Max + "," + Type + ")");
					Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (" + Max + ",0)");
				}
				Success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void Discard() {
		System.out.println("Discard");
	}
	
	// PUBLIC STATIC LIST FILLERS //
	private void Fill_Authors() {	// the method that fills the Authors string array
		try {				
			String sqlp = "SELECT Author_name FROM Author";	
			int Query_Length = 0;
				
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {
					Query_Length++;
				}
				this.Authors = new String[Query_Length];
				Query_Length = 0;
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {
					this.Authors[Query_Length] = Connector.rs.getString("Author_Name");
					Query_Length++;
				}
			System.out.println("Authors Filled!");
		}catch (Exception e) {
			e.printStackTrace();			
		}
	}	
}
