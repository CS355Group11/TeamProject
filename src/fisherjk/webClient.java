package fisherjk;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class webClient extends JFrame {

	//Variables for web client
	private String intputFilePath;
	private String outputFilePath;
	private double msl;
	private double mcl;
	
	private JPanel contentPane;
	private JTextField mslTextField;
	private JTextField mclTextField;
	private JTextField inputText;
	private JTextField outputText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					webClient frame = new webClient();
					frame.setVisible(true);
//					frame.setExtendedState(MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public webClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMinimumSupportLevel = new JLabel("Minimum Support Level");
		lblMinimumSupportLevel.setBounds(10, 11, 202, 43);
		lblMinimumSupportLevel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblMinimumSupportLevel);
		
		JLabel lblNewLabel = new JLabel("Minimum Confidence Level");
		lblNewLabel.setBounds(10, 60, 232, 41);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNewLabel);
		
		mslTextField = new JTextField();
		mslTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mslTextField.setBounds(270, 23, 86, 31);
		panel.add(mslTextField);
		mslTextField.setColumns(10);
		
		mclTextField = new JTextField();
		mclTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mclTextField.setBounds(270, 72, 86, 29);
		panel.add(mclTextField);
		mclTextField.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 238, 767, 245);
		panel.add(textPane);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInputFile.setBounds(10, 109, 86, 36);
		panel.add(lblInputFile);
		
		JLabel lblOutputFile = new JLabel("Output File");
		lblOutputFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputFile.setBounds(10, 151, 102, 32);
		panel.add(lblOutputFile);
		
		inputText = new JTextField();
		lblInputFile.setLabelFor(inputText);
		inputText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputText.setBounds(270, 112, 327, 33);
		panel.add(inputText);
		inputText.setColumns(10);
		
		outputText = new JTextField();
		lblOutputFile.setLabelFor(outputText);
		outputText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputText.setBounds(270, 154, 327, 29);
		panel.add(outputText);
		outputText.setColumns(10);
		
		JButton btnPressMe = new JButton("Press Me");
		btnPressMe.setBounds(123, 194, 102, 33);
		panel.add(btnPressMe);
		
		JButton button = new JButton("Press Me");
		button.setBounds(235, 194, 102, 33);
		panel.add(button);
	}
}
