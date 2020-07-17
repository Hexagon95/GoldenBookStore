package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import Database.Connector;
import Database.DB_Methods;
import Database.Query_DB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class Temporary_GUI { // the main GUI window, by the way it is not Temporary...

	private JFrame frame;
	private JTextField text_Input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temporary_GUI window = new Temporary_GUI();
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
	public Temporary_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {		
		frame = new JFrame("Golden Bookstore");
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		text_Input = new JTextField();
			text_Input.setBounds(10, 11, 565, 20);
			frame.getContentPane().add(text_Input);
			text_Input.setColumns(10);
		
		JTable table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"In Stock", "ISBN", "Title", "Author", "Type"
				}
			));
			table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
			table_1.getColumnModel().getColumn(0).setMaxWidth(50);
			table_1.getColumnModel().getColumn(4).setMaxWidth(100);
			table_1.setToolTipText("");
			table_1.setDefaultEditor(Object.class, null);
			table_1.getTableHeader().setReorderingAllowed(false);
		frame.getContentPane().add(table_1);
		
		JScrollPane Sp_Table = new JScrollPane(table_1);
			Sp_Table.setBounds(10, 42, 565, 608);
			frame.getContentPane().add(Sp_Table);
		
		JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Title", "Author", "Type", "ISBN"}));
			comboBox.setToolTipText("");
			comboBox.setBounds(585, 76, 89, 20);
			frame.getContentPane().add(comboBox);
		
		JButton btn_Filter = new JButton("Filter");
			btn_Filter.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					@SuppressWarnings("unused")
					Query_DB Qdb = new Query_DB(text_Input.getText(), "");					
						switch (comboBox.getSelectedIndex()) {
						case 0:
							@SuppressWarnings("unused") Query_DB Qdb1 = new Query_DB(text_Input.getText(), "Book_Title");
							table_1.setModel(new DefaultTableModel(Query_DB.list_Book,new String[] {"In Stock", "ISBN", "Title", "Author", "Type"}));
							table_1.getColumnModel().getColumn(0).setMaxWidth(50);
							table_1.getColumnModel().getColumn(4).setMaxWidth(100);
							break;
						case 1:
							@SuppressWarnings("unused") Query_DB Qdb2 = new Query_DB(text_Input.getText(), "Author_Name");
							table_1.setModel(new DefaultTableModel(Query_DB.list_Book,new String[] {"In Stock", "ISBN", "Title", "Author", "Type"}));
							table_1.getColumnModel().getColumn(0).setMaxWidth(50);
							table_1.getColumnModel().getColumn(4).setMaxWidth(100);
							break;
						case 2:
							@SuppressWarnings("unused") Query_DB Qdb3 = new Query_DB(text_Input.getText(), "Type_Name");
							table_1.setModel(new DefaultTableModel(Query_DB.list_Book,new String[] {"In Stock", "ISBN", "Title", "Author", "Type"}));
							table_1.getColumnModel().getColumn(0).setMaxWidth(50);
							table_1.getColumnModel().getColumn(4).setMaxWidth(100);
							break;
						case 3:
							@SuppressWarnings("unused") Query_DB Qdb4 = new Query_DB(text_Input.getText(), "Book_ISBN");
							table_1.setModel(new DefaultTableModel(Query_DB.list_Book,new String[] {"In Stock", "ISBN", "Title", "Author", "Type"}));
							table_1.getColumnModel().getColumn(0).setMaxWidth(50);
							table_1.getColumnModel().getColumn(4).setMaxWidth(100);
							break;	
						default:
							break;
					}
				}
			});
			btn_Filter.setBounds(585, 10, 89, 23);
			frame.getContentPane().add(btn_Filter);
			
		JButton btn_Clear = new JButton("Clear");
			btn_Clear.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					text_Input.setText("");		
				}
			});		
			btn_Clear.setBounds(585, 42, 89, 23);
			frame.getContentPane().add(btn_Clear);
			
		JButton btn_Sell = new JButton("Sell");		
			btn_Sell.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(table_1.getSelectedRow() != -1) {	
						DB_Methods.Selected_ISBN = table_1.getValueAt(table_1.getSelectedRow(), 1).toString();
						@SuppressWarnings("unused")
						Sell_Stock SS = new Sell_Stock();					
					}
				}
			});
			btn_Sell.setBounds(585, 525, 89, 23);
			frame.getContentPane().add(btn_Sell);
		
		JButton btn_Add = new JButton("Add");
			btn_Add.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(table_1.getSelectedRow() != -1) {	
						DB_Methods.Selected_ISBN = table_1.getValueAt(table_1.getSelectedRow(), 1).toString();
						@SuppressWarnings("unused")
						Upload_Stock US = new Upload_Stock();					
					}
				}
			});
			btn_Add.setBounds(585, 423, 89, 23);
			frame.getContentPane().add(btn_Add);
		
		JButton btn_Delete = new JButton("Delete");
			btn_Delete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(table_1.getSelectedRow() != -1) {	
						int Confirm_Delete = JOptionPane.showConfirmDialog(frame, "Are you sure to Delete?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						
						if(Confirm_Delete == JOptionPane.YES_OPTION) {
							DB_Methods.Selected_ISBN = table_1.getValueAt(table_1.getSelectedRow(), 1).toString();
							DB_Methods.Delete_Record();
						}
											
					}
				}
			});
			btn_Delete.setBounds(585, 457, 89, 23);
			frame.getContentPane().add(btn_Delete);
		
		JButton btn_Help = new JButton("Help");		
			btn_Help.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					@SuppressWarnings("unused")
					Help_Window HW = new Help_Window();
				}
			});
			btn_Help.setBounds(585, 593, 89, 23);
			frame.getContentPane().add(btn_Help);
		
		JButton btn_NewBook = new JButton("New");
			btn_NewBook.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					@SuppressWarnings("unused")
					New_Record NR = new New_Record();
				}
			});
			btn_NewBook.setBounds(585, 355, 89, 23);
			frame.getContentPane().add(btn_NewBook);
		
		JButton btn_Quit = new JButton("Quit");
			btn_Quit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int Confirm_Quit = JOptionPane.showConfirmDialog(frame, "Are you sure to Quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(Confirm_Quit == JOptionPane.YES_OPTION) {
						Connector.Disconnect();
						System.exit(0);
					}
				}
			});
			btn_Quit.setBounds(585, 627, 89, 23);
			frame.getContentPane().add(btn_Quit);
	}	
}
