//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtSearch;
	public JTextField txtInsert;
	public JTextField txtDelete;
	public JButton btnSearch;
	public JButton btnInsert;
	public JButton btnDelete;
	public JButton btnBack;
	public JButton btnRead;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("M-Search Tree");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search key value");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(101, 164, 171, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insert key value");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(101, 93, 128, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Delete key value");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(101, 251, 128, 33);
		contentPane.add(lblNewLabel_2);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSearch.setBounds(337, 169, 96, 35);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		txtInsert = new JTextField();
		txtInsert.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtInsert.setBounds(337, 89, 96, 35);
		contentPane.add(txtInsert);
		txtInsert.setColumns(10);
		
		txtDelete = new JTextField();
		txtDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtDelete.setBounds(337, 250, 96, 35);
		contentPane.add(txtDelete);
		txtDelete.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSearch.setBounds(541, 164, 85, 45);
		contentPane.add(btnSearch);
		
		btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnInsert.setBounds(541, 84, 85, 45);
		contentPane.add(btnInsert);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(541, 245, 85, 45);
		contentPane.add(btnDelete);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(236, 336, 109, 45);
		contentPane.add(btnBack);
		
		btnRead = new JButton("Show tree structure");
		btnRead.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRead.setBounds(436, 336, 190, 45);
		contentPane.add(btnRead);
	}

}
