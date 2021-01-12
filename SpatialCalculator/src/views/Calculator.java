package views;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import common.Compartment;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.Window.Type;

/**
 * 
 */public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ctpMain;
	private JLabel lblComptName;
	private JButton btnCalc;
	private JLabel iblOccupancy;
	private JComboBox cbOccupancy;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private JLabel lblUPOarea;
	private JTextField txtUPOarea;
	private JLabel lblLD;
	private JTextField txtLD;
	private JTextField txtComptName;
	private JLabel lblPermOpns;
	private JTextField txtActOpns;
	private JTextField txtPermOpns;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton btnYes;
	private JRadioButton btnNo;
	private JLabel iblSprnk;
	private JTextField txtFRR;
	private JTextField txtConstruction;
	private JTextField txtCladding;

	// component models
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JButton btnClear;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
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
	public Calculator() {
		initiComponents();
		createEvents();		
	}
	
	/**
	 * 
	 * 
	 */
	private void initiComponents() { //this method contains all the code for creating and initializing components
		
		setTitle("Spatial Calculator");
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("/resources/building512.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 403);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		mnFile.add(mntmExit);
		
		mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Dialog", Font.PLAIN, 12));
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		
		mnHelp.add(mntmAbout);
		ctpMain = new JPanel();
		ctpMain.setForeground(Color.WHITE);
		ctpMain.setBackground(Color.WHITE);
		setContentPane(ctpMain);
		
		lblComptName = new JLabel("Compartment Name");
		lblComptName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnCalc = new JButton("Calculate");
		
		btnYes = new JRadioButton("Yes");
		btnYes.setBackground(Color.WHITE);
		btnYes.setSelected(true);
		buttonGroup.add(btnYes);
		
		cbOccupancy = new JComboBox();
		cbOccupancy.setBackground(Color.WHITE);
		cbOccupancy.setModel(new DefaultComboBoxModel(new String[] {"", "A-1", "A-2", "A-3", "A-4", "B", "C", "D", "E", "F-1", "F-2", "F-3"}));
		cbOccupancy.setSelectedIndex(0);
		
		iblOccupancy = new JLabel("Occupancy");
		iblOccupancy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtHeight = new JTextField();
		txtHeight.setBackground(Color.WHITE);
		txtHeight.setColumns(10);
		
		lblWidth = new JLabel("Width");
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtWidth = new JTextField();
		txtWidth.setBackground(Color.WHITE);
		txtWidth.setColumns(10);
		
		lblUPOarea = new JLabel("Area of Openings");
		lblUPOarea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtUPOarea = new JTextField();
		txtUPOarea.setBackground(Color.WHITE);
		txtUPOarea.setColumns(10);
		
		lblLD = new JLabel("Limiting Distance");
		lblLD.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtLD = new JTextField();
		txtLD.setBackground(Color.WHITE);
		txtLD.setColumns(10);
		
		txtComptName = new JTextField();
		txtComptName.setBackground(Color.WHITE);
		txtComptName.setColumns(10);
		
		lblPermOpns = new JLabel("Permitted Openings");
		lblPermOpns.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtActOpns = new JTextField();
		txtActOpns.setBackground(new Color(255, 255, 255));
		txtActOpns.setForeground(Color.BLACK);
		txtActOpns.setEnabled(false);
		txtActOpns.setEditable(false);
		txtActOpns.setColumns(10);	
		
		txtPermOpns = new JTextField();
		txtPermOpns.setBackground(new Color(255, 255, 255));
		txtPermOpns.setForeground(Color.BLACK);
		txtPermOpns.setEditable(false);
		txtPermOpns.setEnabled(false);
		txtPermOpns.setColumns(10);
		
		btnNo = new JRadioButton("No");
		btnNo.setBackground(Color.WHITE);
		buttonGroup.add(btnNo);
		
		iblSprnk = new JLabel("Sprinklered");
		iblSprnk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtFRR = new JTextField();
		txtFRR.setBackground(new Color(255, 255, 255));
		txtFRR.setForeground(Color.BLACK);
		txtFRR.setEditable(false);
		txtFRR.setEnabled(false);
		txtFRR.setColumns(10);
		
		JLabel lblFRR = new JLabel("Fire Rating");
		lblFRR.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblConstruction = new JLabel("Construction");
		lblConstruction.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtConstruction = new JTextField();
		txtConstruction.setBackground(new Color(255, 255, 255));
		txtConstruction.setForeground(Color.BLACK);
		txtConstruction.setEditable(false);
		txtConstruction.setEnabled(false);
		txtConstruction.setColumns(10);
		
		txtCladding = new JTextField();
		txtCladding.setBackground(new Color(255, 255, 255));
		txtCladding.setForeground(Color.BLACK);
		txtCladding.setEditable(false);
		txtCladding.setEnabled(false);
		txtCladding.setColumns(10);
		
		JLabel lblCladding = new JLabel("Cladding");
		lblCladding.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JSeparator separator = new JSeparator();
		
		btnClear = new JButton("Clear");

		JLabel lblActOpns = new JLabel("Actual Openings");
		lblActOpns.setForeground(Color.BLACK);
		lblActOpns.setFont(new Font("Tahoma", Font.PLAIN, 11));

		GroupLayout gl_ctpMain = new GroupLayout(ctpMain);
		gl_ctpMain.setHorizontalGroup(
			gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addComponent(lblComptName)
							.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
							.addComponent(txtComptName, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addComponent(iblSprnk, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(btnYes)
							.addGap(18)
							.addComponent(btnNo, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
								.addComponent(iblOccupancy)
								.addComponent(lblHeight, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWidth)
								.addComponent(lblUPOarea)
								.addComponent(lblLD))
							.addGap(43)
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtLD, Alignment.LEADING)
								.addComponent(txtUPOarea, Alignment.LEADING)
								.addComponent(txtWidth, Alignment.LEADING)
								.addComponent(txtHeight, Alignment.LEADING)
								.addComponent(cbOccupancy, Alignment.LEADING, 0, 114, Short.MAX_VALUE))))
					.addGap(3)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(71)
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPermOpns)
								.addComponent(lblCladding)
								.addComponent(lblActOpns)
								.addComponent(lblFRR)
								.addComponent(lblConstruction))
							.addGap(37)
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
								.addComponent(txtConstruction, 112, 112, 112)
								.addComponent(txtFRR, 112, 112, 112)
								.addComponent(txtCladding, 112, 112, 112)
								.addComponent(txtPermOpns, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtActOpns, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(50)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(47, Short.MAX_VALUE))
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addGap(154)
					.addComponent(btnCalc)
					.addGap(260)
					.addComponent(btnClear)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_ctpMain.setVerticalGroup(
			gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ctpMain.createSequentialGroup()
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(20)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ctpMain.createSequentialGroup()
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(14)
											.addComponent(lblComptName))
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(12)
											.addComponent(txtComptName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(4)
											.addComponent(iblSprnk))
										.addComponent(btnYes)
										.addComponent(btnNo))
									.addGap(8)
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(4)
											.addComponent(iblOccupancy))
										.addComponent(cbOccupancy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(20)
											.addComponent(lblHeight))
										.addGroup(gl_ctpMain.createSequentialGroup()
											.addGap(18)
											.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_ctpMain.createSequentialGroup()
									.addGap(84)
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPermOpns)
										.addComponent(txtPermOpns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtActOpns, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblActOpns))))
							.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ctpMain.createSequentialGroup()
									.addGap(14)
									.addComponent(lblWidth))
								.addGroup(gl_ctpMain.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFRR)
										.addComponent(txtFRR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(12)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ctpMain.createSequentialGroup()
							.addGap(2)
							.addComponent(lblUPOarea))
						.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtUPOarea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConstruction)
							.addComponent(txtConstruction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLD)
						.addComponent(txtLD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCladding)
						.addComponent(txtCladding, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCalc)
						.addComponent(btnClear))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		ctpMain.setLayout(gl_ctpMain);
		
	}
	
	private void createEvents() {	//this method contains all the code for creating events
		
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Compartment.name = txtComptName.getText();
					Compartment.h = Float.parseFloat(txtHeight.getText());
					Compartment.w = Float.parseFloat(txtWidth.getText());
					Compartment.LD = Float.parseFloat(txtLD.getText());
					Compartment.group = cbOccupancy.getSelectedItem().toString();
					if (txtUPOarea.getText().isEmpty())
						Compartment.actOpns = 0;
					else
						Compartment.actOpns = Float.parseFloat(txtUPOarea.getText());
					
					if (btnYes.isSelected())
						Compartment.sprk = true;
					else
						Compartment.sprk = false;	
				
					try {
						Compartment.calculate();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
						
					txtPermOpns.setText(String.valueOf(new DecimalFormat("#0.0").format(Compartment.uOpns))+'%');
					txtActOpns.setText(String.valueOf(new DecimalFormat("#0.0").format(Compartment.actOpns))+'%');
					if (Compartment.actOpns > Compartment.uOpns) {
						txtActOpns.setFont(new Font("Tahoma", Font.BOLD, 11));
						if (Compartment.actOpns > 100)
							JOptionPane.showMessageDialog(null, "Area of openings cannot be greater than area of compartment");
						else
							JOptionPane.showMessageDialog(null, "Area of permitted openings exceeded");
					}
					else 
						txtActOpns.setFont(new Font("Tahoma", Font.PLAIN, 11));
					
					txtConstruction.setText(Compartment.constr);
					txtFRR.setText(Compartment.frr);
					txtCladding.setText(Compartment.clad);
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Enter valid numbers for height, width and limiting distance");
				}		
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
				if (ret == JOptionPane.YES_OPTION)
						System.exit(0);
						// FOR FUTURE - make save work automatically
				}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComptName.setText(null);
				txtHeight.setText(null);
				txtWidth.setText(null);
				cbOccupancy.setSelectedIndex(0);
				txtPermOpns.setText(null);
				txtLD.setText(null);	
				txtActOpns.setText(null);
				txtUPOarea.setText(null);
				txtConstruction.setText(null);
				txtFRR.setText(null);
				txtCladding.setText(null);
			}
		});
		
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setModal(true);
				about.setVisible(true);
			}
		});
		
	}
}
