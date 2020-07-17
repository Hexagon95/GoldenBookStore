package Database;

public class Query_DB {	// this class handles the queries of the [Filter] button
	// VARIABLES //
	
	private String text; // represents the given text
	private String order; // represents the column of the order
	public static String[][] list_Book; // a string matrix, that stores all of the query results
	
	// CONSTRUCTOR //
	
	public Query_DB(String text, String order) { // main mechanic is: first which column to order the results, default is title; next examines the given string which column it's according to. 
		this.text = text;	
		if(order.isEmpty()) this.order = "Book_Title";
		else this.order = order;
		
		if (this.text.isEmpty()) Select_All();
		else if (Text_ISBN()) Select_ISBN();
		else if (Text_Author()) Select_Author();
		else if (Text_Type()) Select_Type();
		else if (Text_Title()) Select_Title(); // the examinations for "what is the given string" and it's proper queries
		else Empty();
	}
	
	// EXAMINER METHODS //	
	private boolean Text_ISBN() { // if the given string's first 5 characters are: I S B N _ it will counts as an ISBN filter
		char[] x = this.text.toCharArray();
		if(x[0] == 'I' && x[1] == 'S' && x[2] == 'B' && x[3] == 'N' && x[4] == '_') return true; // ISBN_
		else return false;
	}
	private boolean Text_Author() { // this method will query all of the excising authors from the Table Author and examines is the given string one of it's elements
		try {			
			String sqlp = "SELECT Author_name FROM Author";	
			int x;
				
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					x = 0;
					for (int i = 0; i < this.text.length(); i++) {
						if(Connector.rs.getString("Author_Name").toCharArray()[i] == this.text.toCharArray()[i]) x++;
					}
					if(x == this.text.length()) return true;
				}		
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean Text_Type() { // this method will query all of the excising types from the Table Type and examines is the given string one of it's elements
		try {			
			String sqlp = "SELECT Type_Name FROM Type";	
			int x;
				
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {						
					x = 0;
					for (int i = 0; i < this.text.length(); i++) {
						if(Connector.rs.getString("Type_Name").toCharArray()[i] == this.text.toCharArray()[i]) x++;
					}
					if(x == this.text.length()) return true;
				}		
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean Text_Title() { // this method will query all of the excising titles from the Table Book and examines is the given string one of it's elements
		try {			
			String sqlp = "SELECT Book_Title FROM Book";	
			int x;
				
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {						
					x = 0;
					for (int i = 0; i < this.text.length(); i++) {
						if(Connector.rs.getString("Book_Title").toCharArray()[i] == this.text.toCharArray()[i]) x++;
					}
					if(x == this.text.length()) return true;
				}		
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// QUERY METHODS //
	private void Select_All() { // the method that queries everything from the embedded database
		try {	
			String sqlp = "SELECT Quantity, Book_ISBN, Book_Title, Author_Name, Type_Name FROM "
					+ "Book_has_Quantity INNER JOIN Book ON Book_has_Quantity_ID = Book_ID "
					+ "INNER JOIN Book_has_Author ON Book_has_Author_ID = Book_ID "
					+ "INNER JOIN Author ON Book_has_Author_Author_ID = Author_ID "
					+ "INNER JOIN Book_has_Type ON Book_has_Type_ID = Book_ID "
					+ "INNER JOIN Type ON Book_has_Type_Type_ID = Type_ID "
					+ "ORDER BY " + this.order;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);			
				int Query_Length = 0;
				while(Connector.rs.next()) {				
					Query_Length++;
				}					
				Query_DB.list_Book = new String[Query_Length][5];
				int i = 0;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					list_Book[i][0] = Connector.rs.getString("Quantity");
					list_Book[i][1] = Connector.rs.getString("Book_ISBN");
					list_Book[i][2] = Connector.rs.getString("Book_Title");
					list_Book[i][3] = Connector.rs.getString("Author_Name");
					list_Book[i][4] = Connector.rs.getString("Type_Name");	
					
					i++;
				}			
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	private void Select_ISBN() { // if the given string happened to be an ISBN code, this method will query it's results
		try {
			String sqlp = "SELECT Quantity, Book_ISBN, Book_Title, Author_Name, Type_Name FROM "
					+ "Book_has_Quantity INNER JOIN Book ON Book_has_Quantity_ID = Book_ID "
					+ "INNER JOIN Book_has_Author ON Book_has_Author_ID = Book_ID "
					+ "INNER JOIN Author ON Book_has_Author_Author_ID = Author_ID "
					+ "INNER JOIN Book_has_Type ON Book_has_Type_ID = Book_ID "
					+ "INNER JOIN Type ON Book_has_Type_Type_ID = Type_ID "
					+ "WHERE Book_ISBN = '" + this.text + "' "
					+ "ORDER BY " + this.order;
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);			
				int Query_Length = 0;
				while(Connector.rs.next()) {				
					Query_Length++;
				}					
				Query_DB.list_Book = new String[Query_Length][5];
				if(Query_Length == 0) throw new EXCEPTION_Query_Empty();
				int i = 0;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					list_Book[i][0] = Connector.rs.getString("Quantity");
					list_Book[i][1] = Connector.rs.getString("Book_ISBN");
					list_Book[i][2] = Connector.rs.getString("Book_Title");
					list_Book[i][3] = Connector.rs.getString("Author_Name");
					list_Book[i][4] = Connector.rs.getString("Type_Name");	
					
					i++;
				}		 
		}catch (EXCEPTION_Query_Empty e) {			
		}catch (Exception e) {		
			e.printStackTrace();
		}
	}
	private void Select_Author() { // if the given string happened to be a Name of an Author, this method will query it's results
		try {			
			String sqlp = "SELECT Quantity, Book_ISBN, Book_Title, Author_Name, Type_Name FROM "
					+ "Book_has_Quantity INNER JOIN Book ON Book_has_Quantity_ID = Book_ID "
					+ "INNER JOIN Book_has_Author ON Book_has_Author_ID = Book_ID "
					+ "INNER JOIN Author ON Book_has_Author_Author_ID = Author_ID "
					+ "INNER JOIN Book_has_Type ON Book_has_Type_ID = Book_ID "
					+ "INNER JOIN Type ON Book_has_Type_Type_ID = Type_ID "
					+ "WHERE Author_Name = '" + this.text + "' "
					+ "ORDER BY " + this.order;
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);			
				int Query_Length = 0;
				while(Connector.rs.next()) {				
					Query_Length++;
				}					
				Query_DB.list_Book = new String[Query_Length][5];
				if(Query_Length == 0) throw new EXCEPTION_Query_Empty();
				int i = 0;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					list_Book[i][0] = Connector.rs.getString("Quantity");
					list_Book[i][1] = Connector.rs.getString("Book_ISBN");
					list_Book[i][2] = Connector.rs.getString("Book_Title");
					list_Book[i][3] = Connector.rs.getString("Author_Name");
					list_Book[i][4] = Connector.rs.getString("Type_Name");	
					
					i++;
				}		 
		}catch (EXCEPTION_Query_Empty e) {			
		}catch (Exception e) {		
			e.printStackTrace();
		}
	}
	private void Select_Type() { // if the given string happened to be a Name of a Type, this method will query it's results
		try {			
			String sqlp = "SELECT Quantity, Book_ISBN, Book_Title, Author_Name, Type_Name FROM "
					+ "Book_has_Quantity INNER JOIN Book ON Book_has_Quantity_ID = Book_ID "
					+ "INNER JOIN Book_has_Author ON Book_has_Author_ID = Book_ID "
					+ "INNER JOIN Author ON Book_has_Author_Author_ID = Author_ID "
					+ "INNER JOIN Book_has_Type ON Book_has_Type_ID = Book_ID "
					+ "INNER JOIN Type ON Book_has_Type_Type_ID = Type_ID "
					+ "WHERE Type_Name = '" + this.text + "' "
					+ "ORDER BY " + this.order;
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);			
				int Query_Length = 0;
				while(Connector.rs.next()) {				
					Query_Length++;
				}					
				Query_DB.list_Book = new String[Query_Length][5];
				if(Query_Length == 0) throw new EXCEPTION_Query_Empty();
				int i = 0;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					list_Book[i][0] = Connector.rs.getString("Quantity");
					list_Book[i][1] = Connector.rs.getString("Book_ISBN");
					list_Book[i][2] = Connector.rs.getString("Book_Title");
					list_Book[i][3] = Connector.rs.getString("Author_Name");
					list_Book[i][4] = Connector.rs.getString("Type_Name");	
					
					i++;
				}		 
		}catch (EXCEPTION_Query_Empty e) {			
		}catch (Exception e) {		
			e.printStackTrace();
		}
	}
	private void Select_Title() { // if the given string happened to be a Title of a Book, this method will query it's results
		try {			
			String sqlp = "SELECT Quantity, Book_ISBN, Book_Title, Author_Name, Type_Name FROM "
					+ "Book_has_Quantity INNER JOIN Book ON Book_has_Quantity_ID = Book_ID "
					+ "INNER JOIN Book_has_Author ON Book_has_Author_ID = Book_ID "
					+ "INNER JOIN Author ON Book_has_Author_Author_ID = Author_ID "
					+ "INNER JOIN Book_has_Type ON Book_has_Type_ID = Book_ID "
					+ "INNER JOIN Type ON Book_has_Type_Type_ID = Type_ID "
					+ "WHERE Book_Title = '" + this.text + "' "
					+ "ORDER BY " + this.order;
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);			
				int Query_Length = 0;
				while(Connector.rs.next()) {				
					Query_Length++;
				}					
				Query_DB.list_Book = new String[Query_Length][5];
				if(Query_Length == 0) throw new EXCEPTION_Query_Empty();
				int i = 0;
			
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {					
					list_Book[i][0] = Connector.rs.getString("Quantity");
					list_Book[i][1] = Connector.rs.getString("Book_ISBN");
					list_Book[i][2] = Connector.rs.getString("Book_Title");
					list_Book[i][3] = Connector.rs.getString("Author_Name");
					list_Book[i][4] = Connector.rs.getString("Type_Name");	
					
					i++;
				}		 
		}catch (EXCEPTION_Query_Empty e) {			
		}catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
	// EMPTY //
	private void Empty() { // will set's the GUI table empty
		Query_DB.list_Book = new String[0][5];		
	}
}
