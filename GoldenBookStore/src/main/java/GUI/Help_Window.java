package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Help_Window extends JFrame { // this frame will be generated by pressing the button: Help

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help_Window frame = new Help_Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Help_Window() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
			contentPane.add(scrollPane);
		
		JTextPane txtpnFilter = new JTextPane();
			txtpnFilter.setEditable(false);
			txtpnFilter.setText("[ Filter ] - If the Text Field is empty, it'll qeries evrithing from the Database.\r\n- You can filter: ISBN, Author, Type and Title but you must write the exact same text!\r\n- Keep in mind, that this application uses an embedded database, so If you copy this app the copy's database will be independent!\r\n\r\n[ Clear ] - Empties the Text Field.\r\n\r\n[ Spinner  v ] - Each query is ordered by a Column (A - Z), the default is Title.\r\n\r\n[ New ] - It will add a new Record if you fill the properties properly.\r\n- ISBN, the first Text Field must contain 9 numeric characters & the second 1.\r\n- Title, must contain at least 3 characters.\r\n- Author, also must contain at least  3 characters.\r\n- Type, if It's not the default \"Drama\", choose other...\r\n\r\n[ Add ] - It will increase the number of books in stock of the Selected Record.\r\n\r\n[ Delete ] - It will remove the Selected Record from the Database.\r\n\r\n[ Sell ] - It will decrease the number of books in stock of the Selected Record.\r\n- Keep in mind: This app isn't perfect, it can be go below to zero!\r\n\r\n[ Help ] - You already read the Help Option.\r\n\r\n[ Quit ] - Will close the app, don't worry all records are safe and healty.\r\n\r\nApplication Made by the developer: \u00C9les Vazul Csaba  ( Steve Sharp  or \u77F3\u4E49\u5C71 )");
			scrollPane.setViewportView(txtpnFilter);
	}
}
