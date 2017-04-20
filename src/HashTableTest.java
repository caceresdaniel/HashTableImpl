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
	static SimpleList simpleList = new SimpleList();
	static HashTable hashTable = new HashTable();
	static File file;

	/****************************************************************************/
	/****************************************************************************/
	public void start() throws IOException {
		grabFilePath();

		// generateSimpleList();

		generateHashTable();
	}

	/****************************************************************************/
	/****************************************************************************/
	public static SimpleList generateSimpleList() throws IOException {

		Instant start = Instant.now();
		simpleListGenerator();
		Instant end = Instant.now();

		System.out.println("Time it took for SimpleList: ");
		System.out.println(Duration.between(start, end));

		System.out.println("Size of SimpleList: " + simpleList.size());

		// sort method

		fileWriterForSimpleList(simpleList);

		return simpleList;
	}

	/****************************************************************************/
	/****************************************************************************/
	public static HashTable generateHashTable() throws IOException {
		Instant start = Instant.now();
		hashTableGenerator();
		Instant end = Instant.now();

		System.out.println("Time it took for HashTable: ");
		System.out.println(Duration.between(start, end));

		System.out.println("Size of HashTable: " + hashTable.size());


		SimpleList cleanHash = hashTableCleanUp();
		cleanHash = insertionSort(cleanHash);
		
		fileWriterForSimpleList(cleanHash);

		return hashTable;
	}

	/****************************************************************************/
	/****************************************************************************/
	private void grabFilePath() throws IOException {

		JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
	}

	/****************************************************************************/
	/****************************************************************************/
	private static void hashTableGenerator() throws IOException {
		Scanner fReader = new Scanner(file);

		while (fReader.hasNext()) {
			String word = fReader.next().toLowerCase();
			hashTable.add(new Entry(word));
		}
		fReader.close();
	}

	/****************************************************************************/
	/****************************************************************************/
	private static void simpleListGenerator() throws IOException {
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

	/****************************************************************************/
	/****************************************************************************/
	private static void fileWriterForSimpleList(SimpleList list) throws IOException {

		String fileLocation = JOptionPane.showInputDialog(null, "New file name: ");

		File outFile = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

		for (int i = 0; i < list.size(); i++) {
			writer.write(String.valueOf(list.getEntry(i)));
			writer.write("\n");
		}
		writer.close();
	}

	/****************************************************************************/
	/****************************************************************************/
	private static void fileWriteForHashTable() throws IOException {
		String fileLocation = JOptionPane.showInputDialog(null, "New file name: ");

		File outFile = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

		for (int i = 0; i < hashTable.size(); i++) {
			writer.write(String.valueOf(hashTable.getEntry(i)));
			writer.write("\n");
		}
		writer.close();
	}

	/****************************************************************************/
	/****************************************************************************/
	public static SimpleList hashTableCleanUp() {
		SimpleList cleanHash = new SimpleList();

		for (int i = 0; i < hashTable.size(); i++) {
			if (hashTable.getEntry(i) != null) {
				cleanHash.add(hashTable.getEntry(i));
			}
		}

		
		
		return cleanHash;

	}

	/****************************************************************************/
	/****************************************************************************/
	public static SimpleList insertionSort(SimpleList list) {
		for (int i = 1; i < list.size(); i++) {

			Entry temp = list.getEntry(i);
			int j;

			for(j = i - 1; j >= 0 && temp.getWord().compareTo(list.getEntry(j).getWord()) < 0; j--){
				list.getEntry(j+1).equals(list.getEntry(j));
			}
			list.getEntry(j + 1).equals(temp);
		}
		
		System.out.println("something not right");
		System.out.println(list.toString());
		
		return list;
	}

}
