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

	/**
	 * Calls the methods that start everything for their respective data
	 * structure
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		grabFilePath();

		generateSimpleList();

		generateHashTable();
	}

	/**
	 * This method times how long it takes to create a simpleList with the
	 * designated file It then prints out the time and size of the list finally
	 * the list converted to an array of {@code Entry} in order for the methods
	 * to be modular, it is then sorted using insertion sort the sorted list is
	 * then used to create a new file
	 * 
	 * @return The generated simpleList
	 * @throws IOException
	 */
	public static SimpleList generateSimpleList() throws IOException {

		Instant start = Instant.now();
		simpleListGenerator();
		Instant end = Instant.now();

		System.out.println("Time it took for SimpleList: ");
		System.out.println(Duration.between(start, end));

		System.out.println("Size of SimpleList: " + simpleList.size());

		Entry[] toEntry = simpleListToEntry();
		insertionSort(toEntry);

		fileWriter(toEntry);

		return simpleList;
	}

	/**
	 * Times the amount of time it takes to add the objects to a hashTable It
	 * then prints out the time and the size, size being the amount of objects
	 * in the hashTable it is then converted to an array of {@code Entry} which
	 * is then sorted and written to a file
	 * 
	 * @return The final hashTable
	 * @throws IOException
	 */
	public static HashTable generateHashTable() throws IOException {
		Instant start = Instant.now();
		hashTableGenerator();
		Instant end = Instant.now();

		System.out.println("Time it took for HashTable: ");
		System.out.println(Duration.between(start, end));

		System.out.println("Size of HashTable: " + hashTable.size());

		Entry[] cleanHash = hashTableCleanUp();
		insertionSort(cleanHash);

		fileWriter(cleanHash);

		return hashTable;
	}

	/**
	 * Grabs the file in which the user wishes to use
	 * 
	 * @throws IOException
	 */
	private void grabFilePath() throws IOException {

		JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
	}

	/**
	 * Creates the hashTable by grabbing an object at a time from the file
	 * specified which is then passed to the add method
	 * 
	 * @throws IOException
	 */
	private static void hashTableGenerator() throws IOException {
		Scanner fReader = new Scanner(file);

		while (fReader.hasNext()) {
			String word = fReader.next().toLowerCase();
			hashTable.add(new Entry(word));
		}
		fReader.close();
	}

	/**
	 * Generates the simple list by grabbing an object at a time from the
	 * specified file the objects are added depending on 2 conditions these 2
	 * conditions being if either the object is not in the list already If it is
	 * not in the list it is added If it is already added to the list it then
	 * increments the count for that word
	 * 
	 * @throws IOException
	 */
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

	/**
	 * This method converts the original hashTable list to an array of
	 * {@code Entry} Main reason being to get rid of all the nulls, which in
	 * turn makes it much easier to sort the list
	 * 
	 * @return The new List with out nulls
	 */
	public static Entry[] hashTableCleanUp() {
		Entry[] cleanHash = new Entry[(int) hashTable.size()];
		int j = 0;
		for (int i = 0; i < hashTable.maxSize(); i++) {
			if (hashTable.getEntry(i) != null) {
				cleanHash[j] = hashTable.getEntry(i);
				j++;
			}
		}
		return cleanHash;
	}

	/**
	 * Converts the simpleList to an array of {@code Entry} Mainly done just for
	 * convenience, makes the code more modular
	 * 
	 * @return The simple list converted to array of {@code Entry}
	 */
	public static Entry[] simpleListToEntry() {
		Entry[] toEntry = new Entry[simpleList.size()];
		int j = 0;
		for (int i = 0; i < simpleList.size(); i++) {
			toEntry[j] = simpleList.getEntry(i);
			j++;
		}
		return toEntry;
	}

	/**
	 * Basic Inserstion sort
	 * 
	 * @param list
	 *            The list that is going to be sorted
	 */
	public static void insertionSort(Entry[] list) {
		for (int i = 1; i < list.length; i++) {

			Entry temp = list[i];
			int j = i;

			while (j > 0 && temp.getWord().compareTo(list[j - 1].getWord()) < 0) {
				list[j] = list[j - 1];
				j--;
			}
			list[j] = temp;
		}
	}

	/**
	 * Writes the list received into a file, the file name is created by the
	 * user
	 * 
	 * @param list
	 *            The List that is to be written to a file
	 * @throws IOException
	 */
	public static void fileWriter(Entry[] list) throws IOException {
		String fileLocation = JOptionPane.showInputDialog(null, "New file name: ");

		File outFile = new File(fileLocation);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

		for (int i = 0; i < list.length; i++) {
			writer.write(String.valueOf(list[i]));
			writer.write("\n");
		}
		writer.close();
	}

}
