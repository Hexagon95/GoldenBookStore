package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.Add_DB;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class New_Record extends JFrame { // this frame will be generated by pressing button: new

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_Record frame = new New_Record();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public New_Record() {
		setVisible(true);
		setTitle("New");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 520, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN");
			lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
			lblIsbn.setBounds(10, 11, 151, 14);
			contentPane.add(lblIsbn);
		
		JLabel lblIsbn_1 = new JLabel("ISBN_");
			lblIsbn_1.setBounds(8, 36, 35, 14);
			contentPane.add(lblIsbn_1);
		
		textField = new JTextField();		
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					if(textField.getText().length() >= 9) arg0.consume();
				}
			});
			textField.setBounds(45, 33, 80, 20);			
			textField.setColumns(10);		
			contentPane.add(textField);
		
		JLabel label = new JLabel("-");
			label.setBounds(130, 36, 10, 14);
			contentPane.add(label);
		
		textField_1 = new JTextField();
			textField_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(textField_1.getText().length() >= 1) e.consume();
				}
			});
			textField_1.setBounds(140, 33, 21, 20);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
		
		textField_2 = new JTextField();
			textField_2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(textField_2.getText().length() >= 30) e.consume();
				}
			});
			textField_2.setBounds(185, 33, 86, 20);
			textField_2.setColumns(10);
			contentPane.add(textField_2);			
		
		JLabel lblTitle = new JLabel("Title");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setBounds(185, 11, 86, 14);
			contentPane.add(lblTitle);
		
		textField_3 = new JTextField();
			textField_3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(textField_3.getText().length() >= 30) e.consume();
				}
			});
			textField_3.setBounds(300, 33, 86, 20);
			textField_3.setColumns(10);
			contentPane.add(textField_3);			
		
		JLabel lblNewLabel = new JLabel("Author");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(300, 11, 86, 14);
			contentPane.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Drama", "Sci-fi", "Horror", "Comedy\t", "Thriller", "Science", "Education", "Natural", "Else"}));
			comboBox_1.setBounds(410, 33, 86, 20);
			contentPane.add(comboBox_1);
		
		JLabel lblType = new JLabel("Type");
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
			lblType.setBounds(410, 11, 86, 14);
			contentPane.add(lblType);
		
		JButton btn_Submit = new JButton("Submit");
			btn_Submit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					String Temporary_ISBN = "ISBN_" + textField.getText() + "-" + textField_1.getText();
					Add_DB ADb = new Add_DB(Temporary_ISBN, textField_2.getText(), textField_3.getText(), comboBox_1.getSelectedIndex());
					if (ADb.Success) dispose();
				}
			});
			btn_Submit.setBounds(210, 64, 89, 23);
			contentPane.add(btn_Submit);
	}
}