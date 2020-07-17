package Database;

public class DB_Methods {	// small functions/features that aren't big enough/important to have their own classes...
	
	public static String Selected_ISBN = ""; // It represents the selected row's ISBN
	
	public static void Upload_Stock(int Quantity) { // It will increase the number of books in stock of the selected row
		try {					
			int ID = 0;
			
			String sqlp = "SELECT Book_ID FROM Book WHERE Book_ISBN = '" + Selected_ISBN + "'";
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {				
					ID = Integer.parseInt(Connector.rs.getString("Book_ID"));
				}
			sqlp = "SELECT Quantity FROM Book_has_Quantity WHERE Book_has_Quantity_ID = " + ID + "";
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {				
					Quantity += Integer.parseInt(Connector.rs.getString("Quantity"));
				}
			Connector.conn.createStatement().execute("UPDATE Book_has_Quantity "
													+ "SET Quantity = " + Quantity + " "
													+ "WHERE Book_has_Quantity_ID = " + ID + "");
		} catch (Exception e) {	
			e.getStackTrace();
		} finally {
			Selected_ISBN = "";
		}
	}	
	public static void Delete_Record() { // It will remove the proper record from the embedded database
		try {
			int ID = 0;
			
			String sqlp = "SELECT Book_ID FROM Book WHERE Book_ISBN = '" + Selected_ISBN + "'";
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {				
					ID = Integer.parseInt(Connector.rs.getString("Book_ID"));
				}
			Connector.conn.createStatement().execute("DELETE FROM Book WHERE Book_ID = " + ID + "");
			Connector.conn.createStatement().execute("DELETE FROM Book_has_Author WHERE Book_has_Author_ID = " + ID + "");
			Connector.conn.createStatement().execute("DELETE FROM Book_has_Type WHERE Book_has_Type_ID = " + ID + "");
			Connector.conn.createStatement().execute("DELETE FROM Book_has_Quantity WHERE Book_has_Quantity_ID = " + ID + "");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Selected_ISBN = "";
		}
	}
	public static void Sell_Stock(int Quantity) { // It will decrease the number of books in stock of the selected row
		try {
			Quantity *= -1;
			int ID = 0;
			
			String sqlp = "SELECT Book_ID FROM Book WHERE Book_ISBN = '" + Selected_ISBN + "'";
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {				
					ID = Integer.parseInt(Connector.rs.getString("Book_ID"));
				}
			sqlp = "SELECT Quantity FROM Book_has_Quantity WHERE Book_has_Quantity_ID = " + ID + "";
			Connector.s = Connector.conn.createStatement();
			Connector.rs = Connector.s.executeQuery(sqlp);
				while(Connector.rs.next()) {				
					Quantity += Integer.parseInt(Connector.rs.getString("Quantity"));
				}
			Connector.conn.createStatement().execute("UPDATE Book_has_Quantity "
													+ "SET Quantity = " + Quantity + " "
													+ "WHERE Book_has_Quantity_ID = " + ID + "");
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			Selected_ISBN = "";
		}
	}
}
