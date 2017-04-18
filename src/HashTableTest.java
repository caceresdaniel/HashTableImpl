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

	public void generateSimpleList() {

		try {
			Instant start = Instant.now();
			grabFromFileForSimple();
			Instant end = Instant.now();
			fileWriterForSimpleList();
			System.out.println(simpleList.toString());
			System.out.println(Duration.between(start, end));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void generateHashTable() {

	}

	public void grabFromFileForSimple() throws IOException {

		JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
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

}
