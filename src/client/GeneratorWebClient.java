package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.io.BufferedReader;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

import javax.swing.JButton;
//import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.restlet.resource.ClientResource;

import service.GeneratorResource;

import common.ErrorLogs;
import common.FileUtilities;
import common.Generator;
import common.RuleSet;
import common.Timer;
import common.TimerLogs;
import common.TransactionSet;


public class GeneratorWebClient extends JFrame {

	//Variables for web client
	private static final long serialVersionUID = 1L;
	private String inputFilePath;
	private String outputErrorFilePath;
	private String outputRuleFilePath;
	private double msl;
	private double mcl;
	private Thread thread;
	private JPanel contentPane;
	private JTextField mslTextField;
	private JTextField mclTextField;
	private JTextField inputText;
	private JTextField outputRuleText;
	private boolean isRunning;
	private JTextField outputErrorText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {						
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneratorWebClient frame = new GeneratorWebClient();
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
	public GeneratorWebClient() {		
		
		isRunning = false;	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		mslTextField.setText("0.0");
		mslTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mslTextField.setBounds(270, 23, 86, 31);
		panel.add(mslTextField);
		mslTextField.setColumns(10);
		
		mclTextField = new JTextField();
		mclTextField.setText("0.0");
		mclTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mclTextField.setBounds(270, 72, 86, 29);
		panel.add(mclTextField);
		mclTextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 296, 370, 245);
		panel.add(scrollPane);
		
		final JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(394, 296, 370, 245);
		panel.add(scrollPane_1);
		
		final JTextPane textErrorPane = new JTextPane();
		scrollPane_1.setViewportView(textErrorPane);
		textErrorPane.setEditable(false);
		
		JLabel lblInputFile = new JLabel("Input Transaction File");
		lblInputFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInputFile.setBounds(10, 109, 202, 36);
		panel.add(lblInputFile);
		
		JLabel lblOutputRuleFile = new JLabel("Output Rule File");
		lblOutputRuleFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputRuleFile.setBounds(10, 151, 157, 32);
		panel.add(lblOutputRuleFile);
		
		inputText = new JTextField();
		lblInputFile.setLabelFor(inputText);
		inputText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputText.setBounds(270, 112, 327, 33);
		panel.add(inputText);
		inputText.setColumns(10);
		
		outputRuleText = new JTextField();
		lblOutputRuleFile.setLabelFor(outputRuleText);
		outputRuleText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputRuleText.setBounds(270, 154, 327, 29);
		panel.add(outputRuleText);
		outputRuleText.setColumns(10);
		
		JButton btnPressMe = new JButton("Perform Apriori");
		JButton btnReset = new JButton("Reset");
		
		btnReset.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				inputText.setText("");
				outputRuleText.setText("");
				outputErrorText.setText("");
				mslTextField.setText("0.0");
				mclTextField.setText("0.0");
				textPane.setText("");
				textErrorPane.setText("");
				textPane.repaint();
				textErrorPane.repaint();
				//outputDialog(textPane);
				//outputErrorDialog(textErrorPane);
				
			}
		});
		
		
		
		btnPressMe.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				inputFilePath = inputText.getText();
				outputErrorFilePath = outputErrorText.getText();
				outputRuleFilePath = outputRuleText.getText();
				msl = Double.parseDouble(mslTextField.getText());
				mcl = Double.parseDouble(mclTextField.getText());				
						
				//ClientResource clientResource = new ClientResource("http://localhost:8111/");
		        
				//TransactionSetResource proxy = clientResource.wrap(TransactionSetResource.class);

				///proxy.store(transSet);
				//newTransSet = proxy.retrieve();
				
				
				if(isRunning == false){
					isRunning = true;
					
					thread = new Thread() {
					    public void run() {
					    	//Function for what happens after mouse click
					    	
					    	if(formatFilePaths()){
					    		ClientResource clientResource = new ClientResource("http://localhost:8111/");
						        
								GeneratorResource proxy = clientResource.wrap(GeneratorResource.class);
					    		System.out.println("IN IF of format file paths");
					    		System.out.println(inputFilePath);
					    		System.out.println(outputErrorFilePath);
					    		System.out.println(outputRuleFilePath);
					    		runTest(inputFilePath, outputRuleFilePath, msl, mcl, clientResource, proxy);
						    	outputDialog(textPane);
						    	//outputRuleDialog(textPane);
						    	outputErrorDialog(textErrorPane);
								isRunning = false;
					    	}
							
					    }
					};
					
					thread.start();
					
				}
							
			}
		});
		
		btnPressMe.setBounds(100, 251, 139, 33);
		btnReset.setBounds(500, 251, 139, 33);
		panel.add(btnPressMe);
		panel.add(btnReset);
		
		JLabel lblOutputErrorFile = new JLabel("Output Error File");
		lblOutputErrorFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutputErrorFile.setBounds(10, 197, 157, 32);
		panel.add(lblOutputErrorFile);
		
		outputErrorText = new JTextField();
		lblOutputErrorFile.setLabelFor(outputErrorText);
		outputErrorText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputErrorText.setColumns(10);
		outputErrorText.setBounds(270, 200, 327, 29);
		panel.add(outputErrorText);
		
		
	}

	
	/* Method to run various tests with different parameters quickly" */
	public void runTest(String fileInputName, String fileOutputName, double minSupportLevel, double minConfidenceLevel,
			ClientResource clientResource, GeneratorResource proxy) {
		String supMsg = checkLevels(minSupportLevel);
		String confMsg = checkLevels(minConfidenceLevel);
		ErrorLogs errorLogs = new ErrorLogs();
		RuleSet ruleSet = new RuleSet();
		Timer timer = new Timer();
		TimerLogs tlogs = new TimerLogs();
		
		/*Client and Server Resource Variables*/
		
		
		errorLogs = parameterLogs(supMsg, confMsg);

		if (supMsg.equals("") && confMsg.equals("")) {// no errors
			System.out.println("Min. support level is" + supMsg);
			System.out.println("Min. confidence level is" + confMsg);
			Generator generator = new Generator(minSupportLevel, minConfidenceLevel, inputFilePath);
			//Timer timer = new Timer();
			timer.startTimer();
			TransactionSet transactionSet = new TransactionSet();
			System.out.println("Starting Reading File..." + inputFilePath);
			Timer tRead = new Timer();
			tRead.startTimer();
			transactionSet = FileUtilities.readFile(inputFilePath);
			tRead.stopTimer();
			String readTime = ("FileUtilties.readFile in msec. = " + tRead.getTotal());
			tlogs.getTimerLogs().add(readTime);
			errorLogs.add(transactionSet.getErrorLogs());
			System.out.println("FILE READ ERRORS IN TRANSACTION SET" + transactionSet.getErrorLogs().getErrorCount());
			if (transactionSet.getErrorLogs().getErrorCount()==0) {// while I have transactionSet
				System.out.println("No error(s) found");
				errorLogs.getErrorMsgs().add("No error(s) found in Input Format");
				/*Start RESTful Server Controls*/
				proxy.store(generator);
				ruleSet = proxy.retrieve();
				
			}
			else {
				errorLogs.getErrorMsgs().add("Format Error: Transaction Set is not well-formed");
			}
		}
		System.out.println("Starting Writing File(s): " + outputRuleFilePath + " and " + outputErrorFilePath);
		FileUtilities.writeFile(ruleSet, outputRuleFilePath, errorLogs, outputErrorFilePath);
		System.out.println("Finished Writing File(s):  " + outputRuleFilePath +" and " + outputErrorFilePath);

	}

	/*
	 * method to determine acceptable levels minSupportLevel and
	 * minConfidenceLevel
	 */
	public static String checkLevels(double level) {
		String message = "";
		if (level < 0.0) {
			message = " below acceptable range.";
		}
		if (level > 1.0) {

			message = " above acceptable range.";
		}

		return message;
	}
	
	public static ErrorLogs parameterLogs(String supMsg, String confMsg){
		ErrorLogs logs = new ErrorLogs();
		String parameterError = "Parameter Input Error: ";
		String parameterRange = " Must be between 0.0 and 1.0, inclusively";
		if (!supMsg.equals("")) {
			logs.getErrorMsgs().add(
					parameterError + " Min Support Level is " + supMsg
							+ parameterRange);
		}
		if (!confMsg.equals("")) {
			logs.getErrorMsgs().add(
					parameterError + " Min Confidence Level is " + confMsg
							+ parameterRange);
		}
		return logs;
		
	}
	
	
	private void writeOutput(JTextPane pane, String text){
		pane.setText(pane.getText() + text + "\n");
		pane.setCaretPosition(pane.getText().length());
	}
	
	private void outputDialog(JTextPane pane){
		Scanner fileScanner;
		System.out.println("Default Output Rule File Path: " + outputRuleFilePath);
		
		
		//Input File
		/*
		try {
			if(!inputFilePath.contains("src/")){
				inputFilePath = "src/" + inputFilePath;
			}
			fileScanner = new Scanner(new FileInputStream(inputFilePath));
			
			writeOutput(pane, "Input File : " + inputFilePath);
			writeOutput(pane, "_______________");
			//int i = 0;
			while(fileScanner.hasNextLine()){		
				writeOutput(pane, fileScanner.nextLine());
				//i++;
			}
		
			fileScanner.close();				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		*/
		try {
			//System.out.println("Printing rules");
			if(!outputRuleFilePath.contains("src/")){
				outputRuleFilePath = "src/" + outputRuleFilePath;
			}
			fileScanner = new Scanner(new FileInputStream(outputRuleFilePath));

			writeOutput(pane, "\nOutput Rule File : " + outputRuleFilePath);
			writeOutput(pane, "_______________");
			int rules = 0;
			while(fileScanner.hasNext()){
				//System.out.println("Printing rules");
				writeOutput(pane, fileScanner.nextLine());
				rules++;
			}
			writeOutput(pane, ("\n"+rules+" Rules Generated"));
		
			fileScanner.close();				
			System.out.println("finished Printing rules");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private void outputErrorDialog(JTextPane pane){
		Scanner fileScanner;
		System.out.println("Default Output Error File Path: " + outputErrorFilePath);
		try {
			if(!outputErrorFilePath.contains("src/")){
				outputErrorFilePath = "src/" + outputErrorFilePath;
			}
			fileScanner = new Scanner(new FileInputStream(outputErrorFilePath));

			writeOutput(pane, "\nOutput Error File : " + outputErrorFilePath);
			writeOutput(pane, "_______________");
			
			while(fileScanner.hasNextLine()){					
				writeOutput(pane, fileScanner.nextLine());
			}
		
			fileScanner.close();				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private boolean formatFilePaths(){
		
		//If the inputFilePath is empty turn result false			
		boolean result = true;
		
		if(inputFilePath.equals("")){
			result = false;
		} else {


			String findExtension = "(.*)\\.txt$";
			//String findSRC  ="src\\/";
			//String findFile = "(src\\/)?(.*)\\.txt$";
			
			Pattern inputFilePattern = Pattern.compile(findExtension);
			Matcher inputFileMatcher = inputFilePattern.matcher(inputFilePath);
			Matcher outputRuleFileMatcher = inputFilePattern.matcher(outputRuleFilePath);
			Matcher outputErrorFileMatcher = inputFilePattern.matcher(outputErrorFilePath);

			if(inputFileMatcher.find()){
				//System.out.println("GROUPED INPUT FILE NAME: " + inputFileMatcher.group(0));
				inputFilePath = inputFileMatcher.group(0);
			}else{
				System.out.println("No input extension is found");
				inputFilePath += ".txt";
			}
			
			
			
			
			if(outputRuleFileMatcher.find()){
				//System.out.println("GROUPED OUTPUT RULE FILE NAME: " + outputRuleFileMatcher.group(0));
				this.outputRuleFilePath =  outputRuleFileMatcher.group(0);
			}else if(outputRuleFilePath.equals("")){
				//System.out.println("In this block");
				this.outputRuleFilePath = "rules_of_" + this.inputFilePath;		
			}else{
				System.out.println("No output rule extension is found");
				this.outputRuleFilePath += ".txt";	
			}
			
			
			
			if(outputErrorFileMatcher.find()){
				//System.out.println("GROUPED OUTPUT ERROR FILE NAME: " + outputErrorFileMatcher.group(0));
				this.outputErrorFilePath =  outputErrorFileMatcher.group(0);
			
			}else if(outputErrorFilePath.equals("")){
					System.out.println("In this block");
					this.outputErrorFilePath = "errors_of_" + this.inputFilePath;			
			}else{
				System.out.println("No output error extension is found");
				this.outputErrorFilePath += ".txt";
			}
			
			}
		return result;
	}
	
		

}
