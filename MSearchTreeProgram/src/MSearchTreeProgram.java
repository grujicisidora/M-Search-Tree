import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

public class MSearchTreeProgram {

	private JFrame frmMSearchTree;
	private JTextField txtM;
	public MSearchTree tree;
	public MNode current;
	public MNode back;
	public int M;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSearchTreeProgram window = new MSearchTreeProgram();
					window.frmMSearchTree.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MSearchTreeProgram() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMSearchTree = new JFrame();
		frmMSearchTree.setTitle("M-Search Tree");
		frmMSearchTree.setBounds(100, 100, 622, 353);
		frmMSearchTree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMSearchTree.getContentPane().setLayout(null);
		
		txtM = new JTextField();
		txtM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtM.setBounds(239, 123, 126, 49);
		frmMSearchTree.getContentPane().add(txtM);
		txtM.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter tree order");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(87, 120, 142, 49);
		frmMSearchTree.getContentPane().add(lblNewLabel);
		
		JButton EnterM = new JButton("Enter");
		EnterM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					M = Integer.parseInt(txtM.getText());
					if(M > 1) {
						tree = new MSearchTree(M);
						current = new MNode(M);
						back = current;
						MainFrame mainFrame = new MainFrame();
						mainFrame.setVisible(true);
						frmMSearchTree.dispose();
						mainFrame.btnSearch.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e1) {
								try {
									int a = Integer.parseInt(mainFrame.txtSearch.getText());
									int res1 = tree.search(a);
									if (res1 == 0) {
										JOptionPane.showMessageDialog(null, "This key value couldn't be found in your tree.");
									}
									else if (res1 == 1){
										JOptionPane.showMessageDialog(null, "The value was found in your tree.");	
									}
									else JOptionPane.showMessageDialog(null, "Your tree is empty!");
									mainFrame.txtSearch.setText(null);
								}
								catch(Exception exc1) {
									JOptionPane.showMessageDialog(null, "Please enter valid number.");
									mainFrame.txtSearch.setText(null);
								}
							}
						});
						mainFrame.btnInsert.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e2) {
								try {
									int b = Integer.parseInt(mainFrame.txtInsert.getText());
									int res2 = tree.insert(b);
									if (res2 == 1) {
										JOptionPane.showMessageDialog(null, "Key value successfully added.");	
									}
									mainFrame.txtInsert.setText(null);
								}
								catch(Exception exc2) {
									JOptionPane.showMessageDialog(null, "Please enter valid number.");
									mainFrame.txtInsert.setText(null);
								}
							}
						});
						mainFrame.btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e3) {
								try {
									int c = Integer.parseInt(mainFrame.txtDelete.getText());
									int res3 = tree.deleteKey(c);
									if (res3 == 1) {
										JOptionPane.showMessageDialog(null, "Key value successfully deleted.");
										mainFrame.txtDelete.setText(null);
									}
									mainFrame.txtDelete.setText(null);
								}
								catch(Exception exc3) {
									JOptionPane.showMessageDialog(null, "Please enter valid number.");
									mainFrame.txtDelete.setText(null);
								}
							}
						});
						mainFrame.btnBack.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e4) {
								try {
									MSearchTreeProgram window = new MSearchTreeProgram();
									window.frmMSearchTree.setVisible(true);
									mainFrame.dispose();
								} 
								catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						mainFrame.btnRead.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e5) {
								try {
									TreeFrame treeFrame = new TreeFrame();
									treeFrame.setVisible(true);
									mainFrame.dispose();
									current = tree.root;
									back = current;
									treeFrame.lblTree.setText(tree.readTree(tree.root));
									treeFrame.btnEdit.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e8) {
											try {
												mainFrame.setVisible(true);
												treeFrame.dispose();
											}
											catch(Exception e) {
												e.getStackTrace();
											}
										}
									});
									treeFrame.btnBackIndex.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e6) {
											try {
												current = back;
												treeFrame.lblTree.setText(tree.readTree(current));		
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
									});
									treeFrame.btnIndex.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e7) {
											try {
												int index = Integer.parseInt(treeFrame.textIndex.getText());
												if (index > tree.M - 1 || index < 0) JOptionPane.showMessageDialog(null, "Please enter a valid index.");
												else {
													back = current;
													current = current.children[index];
													if (current != null)
														treeFrame.lblTree.setText(tree.readTree(current));
													else 
														JOptionPane.showMessageDialog(null, "You can't read the tree further.");
												}
												treeFrame.textIndex.setText(null);
											}
											catch(Exception e) {
												e.printStackTrace();
											}
										}
									});
									treeFrame.btnDraw.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e8) {
											try {
												FileWriter file = new FileWriter("MSearchTree.dot");
												DotFile df = new DotFile();
												file.write(df.toDot(tree, current));
												file.close();
												JOptionPane.showMessageDialog(null, "You successfully made your dot file.");
											}
											catch(IOException e) {
												JOptionPane.showMessageDialog(null, "An error occurred.");
												e.printStackTrace();
											}
										}
									});
								}
								catch(Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
					else {
						JOptionPane.showMessageDialog(null, "The number you enter must be greater than 1.");
						txtM.setText(null);
					}	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Please enter valid number.");
					txtM.setText(null);
				}
			}
		});
		EnterM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		EnterM.setBounds(409, 123, 126, 49);
		frmMSearchTree.getContentPane().add(EnterM);
	}
}
