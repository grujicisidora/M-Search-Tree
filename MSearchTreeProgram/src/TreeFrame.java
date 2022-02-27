//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TreeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textIndex;
	public JLabel lblTree;
	public JButton btnBackIndex;
	public JButton btnIndex;
	public JButton btnEdit;
	public JButton btnDraw;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreeFrame frame = new TreeFrame();
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
	public TreeFrame() {
		setTitle("M-Search Tree");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTree = new JLabel("");
		lblTree.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTree.setBounds(86, 10, 554, 205);
		contentPane.add(lblTree);
		
		JLabel lblNewLabel = new JLabel("Next level index");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(157, 238, 114, 19);
		contentPane.add(lblNewLabel);
		
		textIndex = new JTextField();
		textIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textIndex.setBounds(281, 235, 96, 25);
		contentPane.add(textIndex);
		textIndex.setColumns(10);
		
		btnIndex = new JButton("Read");
		btnIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnIndex.setBounds(387, 225, 102, 44);
		contentPane.add(btnIndex);
		
		btnBackIndex = new JButton("Go back a level");
		btnBackIndex.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBackIndex.setBounds(499, 225, 141, 44);
		contentPane.add(btnBackIndex);
		
		btnEdit = new JButton("Edit tree");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(32, 282, 114, 37);
		contentPane.add(btnEdit);
		
		btnDraw = new JButton("Create a DOT file");
		btnDraw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDraw.setBounds(387, 282, 253, 37);
		contentPane.add(btnDraw);
	}
}
