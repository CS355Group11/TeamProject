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
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


public class WebClientUpdate extends JFrame {

	//Variables for web client
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
					WebClientUpdate frame = new WebClientUpdate();
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
	public WebClientUpdate() {		
		
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
		btnPressMe.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				inputFilePath = inputText.getText();
				outputErrorFilePath = outputErrorText.getText();
				outputRuleFilePath = outputRuleText.getText();
				msl = Double.parseDouble(mslTextField.getText());
				mcl = Double.parseDouble(mclTextField.getText());				
						
				
				if(isRunning == false){
					isRunning = true;
					
					thread = new Thread() {
					    public void run() {
					    	//Function for what happens after mouse click
					    	
					    	if(formatFilePaths()){
					    		System.out.println("IN IF of format file paths");
					    		System.out.println(inputFilePath);
					    		System.out.println(outputErrorFilePath);
					    		System.out.println(outputRuleFilePath);
					    		runTest(inputFilePath, outputRuleFilePath, msl, mcl);
						    	outputDialog(textPane);
						    	outputErrorDialog(textErrorPane);
								isRunning = false;
					    	}
							
					    }
					};
					
					thread.start();
					
				}
							
			}
		});
		
		btnPressMe.setBounds(86, 251, 139, 33);
		panel.add(btnPressMe);
		
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
	public void runTest(String fileInputName, String fileOutputName,
			double minSupportLevel, double minConfidenceLevel) {
		String supMsg = checkLevels(minSupportLevel);
		String confMsg = checkLevels(minConfidenceLevel);
		ErrorLogs errorLogs = new ErrorLogs();
		//ErrorLogs daoLogs = new ErrorLogs();
		RuleSet ruleSet = new RuleSet();
		Timer timer = new Timer();
		Timer timerDB = new Timer();
		TimerLogs tlogs = new TimerLogs();
		
		// System.out.println("supMsg: " + supMsg);
		// System.out.println("confMsg: " + confMsg);
		errorLogs = parameterLogs(supMsg, confMsg);

		if (supMsg.equals("") && confMsg.equals("")) {// no errors
			System.out.println("Min. support level is" + supMsg);
			System.out.println("Min. confidence level is" + confMsg);
			Generator generator = new Generator(minSupportLevel, minConfidenceLevel);
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
			if (transactionSet != null) {// while I have transactionSet

				System.out.println("Starting APriori");
				Timer tGen = new Timer();
				tGen.startTimer();
				TransactionSet input = Generator.DoApriori(
						transactionSet, minSupportLevel);
				tGen.stopTimer();
				String genTime = ("Generator.DoApriori in msec. = " + tGen.getTotal());
				tlogs.getTimerLogs().add(genTime);
				System.out.println("Finished APriori");
				System.out.println("Starting Generating Rules");
				Timer tRule = new Timer();
				tRule.startTimer();
				ruleSet = Generator.GenerateRuleSets(transactionSet,
						input, minConfidenceLevel);
				tRule.stopTimer();
				String ruleTime = ("Generator.GenerateRuleSets in msec. = " + tRule.getTotal());
				tlogs.getTimerLogs().add(ruleTime);
				System.out.println("Finished Generating Rules");
				timer.stopTimer();
				System.out
						.println("elapsed time in msec.: " + timer.getTotal());
				/* Inserting original transactionSet and generated rule set */
				timerDB.startTimer();
				//errorLogs = DAOController(generator, transactionSet, ruleSet);
				timerDB.stopTimer();
				System.out.println("DB elapsed time in msec.: " + timerDB.getTotal());
				System.out.println("Errors from DAO: " + errorLogs.getErrorCount());
			} else {
				errorLogs.getErrorMsgs().add(
						"Format Error: Transaction Set is not well-formed");
			}
		}

		int errorCount = errorLogs.getErrorCount();
		System.out.println(errorLogs.toString());
		if (errorCount != 0) {
			System.out.println(errorCount
					+ " error(s) found. No Rules are  Generated");
			errorLogs.getErrorMsgs().add(errorCount+ " error(s) found. No Rules are  Generated");
		}else{
		System.out
				.println("No error(s) found");
		errorLogs.getErrorMsgs().add("No error(s) found in Input Format");
		}
		System.out.println("Total Time elapsed time in msec.: " + (timer.getTotal() + timerDB.getTotal()));
		System.out.println("Starting Writing File(s): " + outputRuleFilePath + " and " + outputErrorFilePath);
		FileUtilities.writeFile(ruleSet, outputRuleFilePath, errorLogs, outputErrorFilePath);
		System.out.println("Finished Writing File(s):  " + outputRuleFilePath +" and " + outputErrorFilePath);
		FileUtilities.writeTimes(tlogs);
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
	

	/* DAO MAIN */
	public static ErrorLogs DAOController(Generator generator, TransactionSet transactionSet,RuleSet ruleSet) {
		System.out.println("Starting DAO Controller");
		ErrorLogs errorLogs = new ErrorLogs();
		VendorPersistenceController vpc = new VendorPersistenceController();
		TransactionPersistenceController tpc = new TransactionPersistenceController();
		RulePersistenceController rpc = new RulePersistenceController();
		TransactionSetPersistenceController tspc = new TransactionSetPersistenceController();
		RuleSetPersistenceController rspc = new RuleSetPersistenceController();
		GeneratorPersistenceController gpc = new GeneratorPersistenceController();
		int errorCount = 0;
		String daoString = "MySQL";
		/*
		InputStreamReader unbuffered = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(unbuffered);
		try {
			System.out.println("Use (Mock) DAO or (MySQL) DAO? Mock");
			daoString = keyboard.readLine();
		} catch (IOException error) {
			System.err.println("Error reading input");
		}
		*/
		gpc.setDAO(daoString);
		vpc.setDAO(daoString);
		tspc.setDAO(daoString);
		tpc.setDAO(daoString);
		rspc.setDAO(daoString);
		rpc.setDAO(daoString);
		
		System.out.println("Starting Persist Vendor");
		

		
		for (int i = 0; i < transactionSet.getVendorSet().size(); i++) {
			Vendor vendor = transactionSet.getVendorSet().get(i);
			vpc.persistVendor(vendor);
			System.out
					.println("vendor " + i + " is " + vendor.getVendor_name());
		}
		int vpc_errors = vpc.getErrorLogs().getErrorMsgs().size();
		System.out.println("Finished Persist Vendor");
		errorCount += vpc_errors;
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: VENDOR TABLE");
			errorLogs.add(vpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}

		// daoString = "MySQL";
		// iterate through tranactionset to get individual transactions
		int i = 0;
		
			System.out.println("Starting Persist TransactionSet");
			tspc.persistTransactionSet(transactionSet);
			int tspc_errors = tspc.getErrorLogs().getErrorMsgs().size();
			errorCount += tspc_errors;
			System.out.println("Finished Persist TransactionSet");
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: TRANSACTIONSET TABLE");
			errorLogs.add(tspc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
		}
		System.out.println("errorCount: " + errorCount);
	
			for (Transaction transaction : transactionSet.getTransactionSet()) {
				System.out.println("Size: "
						+ transactionSet.getTransactionSet().size());
				// for(int i = 0; i < transactionSet.getTransactionSet().size();
				// i++){
				System.out.println("Starting Persist Transaction: #" + i);
				// tpc.persistTransaction(transactionSet.getTransactionSet().get(i));
				tpc.persistTransaction(transaction);
				i++;
				System.out.println("Finished Persist Transaction: #" + i);
			}
			int tpc_errors = tpc.getErrorLogs().getErrorMsgs().size();
			errorCount += tpc_errors;
			System.out.println("errorCount: " + errorCount);
			
			if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: TRANSACTION TABLE");
			errorLogs.add(tpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
		
			System.out.println("Starting Persist Generator");
			gpc.persistGenerator(generator.getGenerator_minSupportLevel(), generator.getGenerator_minConfidenceLevel());
			System.out.println("Finished Persist Generator");
			int gpc_errors = gpc.getErrorLogs().getErrorMsgs().size();
			errorCount += gpc_errors;
			if(errorCount != 0){
				errorLogs.add("DATABASE ERROR: GENERATOR TABLE");
				errorLogs.add(gpc.getErrorLogs());
				return errorLogs;
			}
		
		
		// }
		int j = 0;
		
			// iterate through ruleset to get individual rules
			System.out.println("Starting Persist RuleSet");
			rspc.persistRuleSet(ruleSet);
			int rspc_errors = rspc.getErrorLogs().getErrorMsgs().size();
			errorCount += rspc_errors;
			System.out.println("errorCount: " + errorCount);
		if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: RULESET TABLE");
			errorLogs.add(rspc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
		
			System.out.println("Finished Persist RuleSet");
			for (Rule rule : ruleSet.getRuleSet()) {
				System.out.println("Starting Persist Rule: #" + j);
				rpc.persistRule(rule);
				System.out.println("Finished Persist Rule: #" + j);
			}
			int rpc_errors = rpc.getErrorLogs().getErrorMsgs().size();
			errorCount += rpc_errors;
			System.out.println("errorCount: " + errorCount);
			if(errorCount != 0){
			errorLogs.add("DATABASE ERROR: RULE TABLE");
			errorLogs.add(rpc.getErrorLogs());
			System.out.println("size: " + errorLogs.getErrorMsgs().size());
			return errorLogs;
			}
	
		System.out.println("Final errorCount: " + errorCount);
		System.out.println("Finished DAO Controller");
		System.out.println("size: " + errorLogs.getErrorMsgs().size());
		return errorLogs;
	}
	
	
	private void writeOutput(JTextPane pane, String text){
		pane.setText(pane.getText() + text + "\n");
		pane.setCaretPosition(pane.getText().length());
	}
	
	private void outputDialog(JTextPane pane){
		Scanner fileScanner;
		System.out.println("Default Output Rule File Path: " + outputRuleFilePath);
		
		
		//Input File
		try {
			if(!inputFilePath.contains("src/")){
				inputFilePath = "src/" + inputFilePath;
			}
			fileScanner = new Scanner(new FileInputStream(inputFilePath));
			
			writeOutput(pane, "Input File : " + inputFilePath);
			writeOutput(pane, "_______________");
			int i = 0;
			while(fileScanner.hasNextLine()){		
				writeOutput(pane, fileScanner.nextLine());
				i++;
			}
		
			fileScanner.close();				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
		
		try {
			if(!outputRuleFilePath.contains("src/")){
				outputRuleFilePath = "src/" + outputRuleFilePath;
			}
			fileScanner = new Scanner(new FileInputStream(outputRuleFilePath));

			writeOutput(pane, "\nOutput Rule File : " + outputRuleFilePath);
			writeOutput(pane, "_______________");
			
			while(fileScanner.hasNext()){					
				writeOutput(pane, fileScanner.nextLine());
			}
		
			fileScanner.close();				
			
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
			/*
			if(outputErrorFilePath.equals("")){
				System.out.println("In this block");
				outputErrorFilePath = "errorLogs_" + inputFilePath + "_output";			
			}
			
			if(outputRuleFilePath.equals("")){
				outputRuleFilePath = inputFilePath + "_output";	
			}
			
			*/
			
			
			
			String findExtension = "(.*)\\.txt$";
			String findSRC  ="src\\/";
			String findFile = "(src\\/)?(.*)\\.txt$";
			
			Pattern inputFilePattern = Pattern.compile(findExtension);
			Matcher inputFileMatcher = inputFilePattern.matcher(inputFilePath);
			Matcher outputRuleFileMatcher = inputFilePattern.matcher(outputRuleFilePath);
			Matcher outputErrorFileMatcher = inputFilePattern.matcher(outputErrorFilePath);
			
			
			
			System.out.println("New GUI: " + inputFilePath);
			System.out.println("New GUI: " + outputRuleFilePath);
			System.out.println("New GUI: " + outputErrorFilePath);
			
			
			
			if(inputFileMatcher.find()){
				System.out.println("GROUPED INPUT FILE NAME: " + inputFileMatcher.group(0));
				inputFilePath = inputFileMatcher.group(0);
			}else{
				System.out.println("No input extension is found");
				inputFilePath += ".txt";
			}
			
			
			
			
			if(outputRuleFileMatcher.find()){
				System.out.println("GROUPED OUTPUT RULE FILE NAME: " + outputRuleFileMatcher.group(0));
				this.outputRuleFilePath =  outputRuleFileMatcher.group(0);
			}else if(outputRuleFilePath.equals("")){
				System.out.println("In this block");
				this.outputRuleFilePath = "rule_output_" + this.inputFilePath;		
			}else{
				System.out.println("No output rule extension is found");
				this.outputRuleFilePath += ".txt";	
			}
			
			
			
			if(outputErrorFileMatcher.find()){
				System.out.println("GROUPED OUTPUT ERROR FILE NAME: " + outputErrorFileMatcher.group(0));
				this.outputErrorFilePath =  outputErrorFileMatcher.group(0);
			
			}else if(outputErrorFilePath.equals("")){
					System.out.println("In this block");
					this.outputErrorFilePath = "errorLogs_" + this.inputFilePath;			
			}else{
			System.out.println("No output error extension is found");
				this.outputErrorFilePath += ".txt";
			}
			
			}
		
			
			
			System.out.println("Final GUI: " + inputFilePath);
			System.out.println("Final GUI: " + outputRuleFilePath);
			System.out.println("Final GUI: " + outputErrorFilePath);
			
		
		
		return result;
	}
	
		

}
