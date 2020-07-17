package Database;

public class Create_DB { // this class will reset the embedded database of the application
	
	public Create_DB() {
	Initialise_DB();
	}
	
	private void Initialise_DB(){
		try {
			Connector.conn.createStatement().execute("DROP TABLE Book");				
			Connector.conn.createStatement().execute("DROP TABLE Author");
			Connector.conn.createStatement().execute("DROP TABLE Type");
			Connector.conn.createStatement().execute("DROP TABLE Book_has_Author");
			Connector.conn.createStatement().execute("DROP TABLE Book_has_Type");
			Connector.conn.createStatement().execute("DROP TABLE Book_has_Quantity");
			Connector.conn.createStatement().execute("CREATE TABLE Book (\r\n" + 
					"  Book_ID INTEGER NOT NULL,\r\n" + 
					"  Book_ISBN CHAR(16),\r\n" + 
					"  Book_Title CHAR(30),\r\n" + 
					"  PRIMARY KEY (Book_ID))\r\n");					
			Connector.conn.createStatement().execute("CREATE TABLE Author (\r\n" + 
					"  Author_ID INTEGER NOT NULL,\r\n" + 
					"  Author_Name CHAR(30),\r\n" + 
					"  PRIMARY KEY (Author_ID))\r\n");
			Connector.conn.createStatement().execute("CREATE TABLE Type (\r\n" + 
					"  Type_ID INTEGER NOT NULL,\r\n" + 
					"  Type_Name CHAR(30),\r\n" + 
					"  PRIMARY KEY (Type_ID))\r\n");
			Connector.conn.createStatement().execute("CREATE TABLE Book_has_Author (\r\n" + 
					"  Book_has_Author_ID INTEGER NOT NULL,\r\n" + 
					"  Book_has_Author_Author_ID INT NOT NULL,\r\n" + 
					"  PRIMARY KEY (Book_has_Author_ID))\r\n");
			Connector.conn.createStatement().execute("CREATE TABLE Book_has_Type (\r\n" + 
					"  Book_has_Type_ID INTEGER NOT NULL,\r\n" + 
					"  Book_has_Type_Type_ID INT NOT NULL,\r\n" + 
					"  PRIMARY KEY (Book_has_Type_ID))\r\n");
			Connector.conn.createStatement().execute("CREATE TABLE Book_has_Quantity (\r\n" + 
					"  Book_has_Quantity_ID INTEGER NOT NULL,\r\n" + 
					"  Quantity INT,\r\n" + 
					"  PRIMARY KEY (Book_has_Quantity_ID))\r\n");
			System.out.println("Database has created!");
			
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (1,'Kertész Imre')");		
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (2, 'Szabó Magda')");	
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (3,'Esterházy Péter')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (4,'Nádas Péter')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (5,'Spiró György')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (6,'Grecsó Krisztián')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (7,'Vavyan Fable')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (8,'Háy János')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (9,'Rakovszky Zsuzsa')");
			Connector.conn.createStatement().execute("INSERT INTO Author VALUES (10,'Bartis Attila')");
			System.out.println("Table Author has Updated!");
			
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (1,'Drama')");
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (2,'Sci-fi')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (3,'Horror')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (4,'Comedy')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (5,'Thriller')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (6,'Science')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (7,'Education')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (8,'Natural')");	
			Connector.conn.createStatement().execute("INSERT INTO Type VALUES (9,'Else')");
			System.out.println("Table Type has Updated!");
			
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (1,'ISBN_817525766-0', 'Planet of Monkeys')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (2,'ISBN_817125736-2', 'The Hedgehog')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (3,'ISBN_814445766-3', 'Burqua')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (4,'ISBN_888885766-2', 'The Therapy')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (5,'ISBN_111125766-0', 'Sausages')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (6,'ISBN_617525766-0', 'Dorks')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (7,'ISBN_817555766-0', 'Blackholse')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (8,'ISBN_848525766-2', 'Individual Identities')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (9,'ISBN_117525766-0', 'Planet Crush')");
			Connector.conn.createStatement().execute("INSERT INTO Book VALUES (10, 'ISBN_217525466-8', 'AIDS')");
			
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (1,4)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (2,1)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (3,4)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (4,1)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (5,8)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (6,6)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (7,7)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (8,10)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (9,9)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Author VALUES (10,4)");
			
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (1,2)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (2,8)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (3,6)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (4,6)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (5,6)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (6,1)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (7,2)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (8,3)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (9,3)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Type VALUES (10,1)");
			
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (1,125)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (2,88)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (3,62)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (4,11)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (5,12)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (6,20)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (7,55)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (8,45)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (9,80)");
			Connector.conn.createStatement().execute("INSERT INTO Book_has_Quantity VALUES (10,76)");
			System.out.println("Table Book has Updated!");
		
		} catch (Exception e) {	
			e.printStackTrace();		
		}
	}
}
