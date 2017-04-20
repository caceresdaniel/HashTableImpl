
public class HashTable {

	private Entry[] entries;
	private static final double LOAD_THRESHOLD = 0.5;
	private static final int INITIAL_CAPACITY = 17;
	private double size;
	private double loadFactor;

	public HashTable() {
		this.entries = new Entry[INITIAL_CAPACITY];
		this.size = 0;
	}

	/**
	 * check size convert to hashcode then you check cases to see if index is
	 * empty if not empty and word matches increment coutn else not empty and
	 * word does not match that is when you do linear probing, double hasing ,
	 * or quadratic probing
	 * 
	 */
	public void add(Entry e) {

		computeLoadFactor();
		if (this.loadFactor >= LOAD_THRESHOLD) {
			this.reHash();
		}

		int n = this.entries.length;
		int key = e.hashCode() % n; // hash compression

		if (this.entries[key] == null) {
			this.entries[key] = e;
			this.size++;
		} else if (this.entries[key].getWord().compareTo(e.getWord()) == 0) {
			this.entries[key].incrementCount();
		} else if (this.entries[key].getWord().compareTo(e.getWord()) != 0) {
			collisionManager(e, key);
		}
	}

	private void computeLoadFactor() {
		double n = this.entries.length;
		this.loadFactor = this.size / n;
	}

	private void collisionManager(Entry e, int key) {

		int multiple = 0;
		int newKey = 0;
		int n = this.entries.length;

		while (this.entries[newKey] != null && this.entries[newKey].getWord().compareTo(e.getWord()) != 0) {
			newKey = (key + (multiple * secondaryHash(key))) % n;
			multiple++;
		}

		if (this.entries[newKey] == null) {
			this.entries[newKey] = e;
			this.size++;
		} else if (this.entries[newKey].getWord().compareTo(e.getWord()) == 0) {
			this.entries[newKey].incrementCount();
		}

	}

	private int secondaryHash(int key) {
		return 7 - ((key) % 7);
	}

	private void reHash() {
		Entry[] listCopy = new Entry[this.entries.length];

		listCopy = this.entries;

		this.entries = new Entry[this.entries.length * 3];
		this.size = 0;

		for (int j = 0; j < listCopy.length; j++) {
			if (listCopy[j] != null) {
				add(listCopy[j]);
			}
		}
	}

	public Entry getEntry(int index) {
		return this.entries[index];
	}

	public double size() {
		return this.size;
	}

	public String toString() {
		String result = "";

		String formatter = "%-20s%-1d";

		for (int i = 0; i < this.size; i++) {
			Entry e = this.entries[i];
			if (this.entries[i] != null) {
				result += String.format(formatter, e.getWord(), e.getCount()) + "\n";
			}
		}

		return result;
	}
}
