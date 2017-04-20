import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class HashTableTest {
	SimpleList simpleList = new SimpleList();
	HashTable hashTable = new HashTable();
	File file;

	public void start() throws IOException {
		grabFilePath();

		//generateSimpleList();

		generateHashTable();
	}

	public void generateSimpleList() {

		try {
			Instant start = Instant.now();
			simpleListGenerator();
			Instant end = Instant.now();

			fileWriterForSimpleList();
		//	System.out.println(simpleList.toString());
			System.out.println("Time it took: ");
			System.out.println(Duration.between(start, end));
			
			System.out.println("Size of SimpleList: " + simpleList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void generateHashTable() throws IOException {
		Instant start = Instant.now();
		hashTableGenerator();
		Instant end = Instant.now();
		
		//System.out.println(hashTable.toString());
		System.out.println("Time it took: ");
		System.out.println(Duration.between(start, end));
		System.out.println("Size of HashTable: " + hashTable.size());
		
		fileWriteForHashTable();
		
	}

	private void grabFilePath() throws IOException {

		JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
	}
	
	private void hashTableGenerator() throws IOException{
		Scanner fReader = new Scanner(file);
		
		while(fReader.hasNext()){
			String word = fReader.next().toLowerCase();
			
			hashTable.add(new Entry(word));
			
		}
		fReader.close();
	}

	private void simpleListGenerator() throws IOException {
		Scanner fReader = new Scanner(file);

		while (fReader.hasNext()) {
			String word = fReader.next().toLowerCase();

			int indexForWord = simpleList.find(word);

			if (indexForWord == -1) {
				simpleList.add(new Entry(word));
			} else {
				simpleList.getEntry(indexForWord).incrementCount();
			}
		}
		fReader.close();
	}

	private void fileWriterForSimpleList() throws IOException {

		String fileLocation = JOptionPane.showInputDialog(null, "New file name: ");

		File outFile = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

		for (int i = 0; i < simpleList.size(); i++) {
			writer.write(String.valueOf(simpleList.getEntry(i)));
			writer.write("\n");
		}
		writer.close();
	}
	
	private void fileWriteForHashTable() throws IOException {
		String fileLocation = JOptionPane.showInputDialog(null, "New file name: ");

		File outFile = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

		for (int i = 0; i < hashTable.size(); i++) {
			writer.write(String.valueOf(hashTable.getEntry(i)));
			writer.write("\n");
		}
		writer.close();
	}

}
