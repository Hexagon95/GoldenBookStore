import Database.Connector;
import GUI.Temporary_GUI;
//import Database.Create_DB;

public class Main {

	public static void main(String[] args) {
		Connector.Register(); 
		Connector.Connect();	// These two method calls will make the application connect to it's own embedded database.		
		
		//Create_DB C_db = new Create_DB(); // It resets the embedded database to default if it's needed ( mostly in the developing progress )
		Temporary_GUI.main(null); // It start's the GUI Window.		
	}		
}
 