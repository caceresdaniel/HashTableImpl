import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class HashTableTest {
	SimpleList simpleList = new SimpleList();
	// Entry entry;

	public void generateSimpleList() {

		try {
			grabFromFileForSimple();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(simpleList.toString());

	}

	public void generateHashTable() {

	}

	public void grabFromFileForSimple() throws IOException {
		String[] field;

		JFileChooser fc = new JFileChooser();
		int val = fc.showOpenDialog(null);

		if (val == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Scanner fReader = new Scanner(file);

			while (fReader.hasNextLine()) {
				String lineOfData;

				lineOfData = fReader.nextLine();
				
				field = lineOfData.split(" ");

				for (int i = 0; i < field.length; i++) {
					if (field[i].contains(" ")) {
					} else {
						simpleList.add(new Entry(field[i].toLowerCase()));
					}
				}

			}
			fReader.close();
		}
	}

}
