package test;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
 
import javax.swing.JOptionPane;

import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AlgoAgrawalFaster94;
import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRule;
import com.ahmedehab.patternmining.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import com.ahmedehab.patternmining.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import com.ahmedehab.patternmining.spmf.patterns.itemset_array_integers_with_count.Itemsets;

public class Test {

    List<List<String>> transactions = new ArrayList<List<String>>();

    public static void main(String[] args) throws IOException {
	Test ts = new Test();
	ts.runTest();
    } 

    public void runTest() throws IOException {
	try {

	    FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    String file = dialog.getDirectory() + "\\" + dialog.getFile();
	    String delimiter = JOptionPane.showInputDialog(null,
		    "Write your delimiter to specify boundaries of your data");
	    this.readyTransactions(file, delimiter);
	} catch (IOException e) {
	    System.out.println("Error in opening the file to ready transactions");
	    e.printStackTrace();
	} 
 
	AlgoFPGrowth algo = new AlgoFPGrowth();
	// SequentialPatterns sps = algo.runAlgorithm(this.transactions,0.1,
	// null);
	Itemsets items = algo.runAlgorithm(this.transactions, null, 0.1);
	int databaseSize = algo.getDatabaseSize();
//	System.out.println("=====Sequential Patterns By FPGrowth=====\n");
//	for (List<Itemset> level : items.getLevels()) {
//	    for (Itemset item : level) {
//		if (realLength(item.getItems().toString().length()) >= 2) {
//		    System.out.println("Pattern Length:" + realLength(item.getItems().toString().length()));
//		    System.out.println(item + " Support: " + item.getAbsoluteSupport());
//		}
//	    }
//	}
//	
	// STEP 2: Generating all rules from the set of frequent itemsets (based
	// on Agrawal & Srikant, 94)
	double minconf = 0.60;
	AlgoAgrawalFaster94 algoAgrawal = new AlgoAgrawalFaster94();
	// the next line run the algorithm.
	// Note: we pass null as output file path, because we don't want
	// to save the result to a file, but keep it into memory.
	AssocRules assocRules = algoAgrawal.runAlgorithm(items, null, databaseSize, minconf);
	System.out.println(" ------- Association rules -------");
	  
	for(AssocRule rule : assocRules.rules){
		System.out.println(rule.toString()+"\t"+"support :  " + rule.getRelativeSupport(databaseSize) 
		+ " (" + rule.getAbsoluteSupport() + "/" + 
		databaseSize + ") confidence : " + rule.getConfidence());
	}

    }

    public void readyTransactions(String path, String delimiter) throws IOException {
	File file = new File(path);
	FileReader fileReader = new FileReader(file);
	BufferedReader bufferedReader = new BufferedReader(fileReader);
	StringBuffer stringBuffer = new StringBuffer();
	String line;
	while ((line = bufferedReader.readLine()) != null) {
	    this.transactions.add(Arrays.asList(line.split(delimiter)));
	    stringBuffer.append(line);
	    stringBuffer.append("\n");

	}

	fileReader.close();
    }

    public static int realLength(int stringLength) {
	return (stringLength / 2) - 1;
    }

}
