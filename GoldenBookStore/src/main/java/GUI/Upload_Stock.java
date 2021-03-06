package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DB_Methods;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Upload_Stock extends JFrame { // this frame will be generated by pressing button: Add

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Upload_Stock frame = new Upload_Stock();
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
	public Upload_Stock() {
		setTitle("Add");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 250, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblUploadTheSelected = new JLabel("Upload the selected stock");
			lblUploadTheSelected.setHorizontalAlignment(SwingConstants.LEFT);
			lblUploadTheSelected.setBounds(10, 11, 148, 14);
			contentPane.add(lblUploadTheSelected);
		
		JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
			spinner.setBounds(168, 7, 60, 23);
			contentPane.add(spinner);
		
		JButton btnSubmit = new JButton("Submit");
			btnSubmit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DB_Methods.Upload_Stock(Integer.parseInt(spinner.getValue().toString()));
					System.out.println(Integer.parseInt(spinner.getValue().toString()));
					dispose();
				}
			});
			btnSubmit.setBounds(69, 36, 89, 23);
			contentPane.add(btnSubmit);
	}
}
