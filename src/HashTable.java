
public class HashTable {

	// The array of Entry objects to be added
	private Entry[] entries;

	// The load Thresh hold limit that is not to be surpassed
	private static final double LOAD_THRESHOLD = 0.60;

	// Initial capacity of the List
	private static final int INITIAL_CAPACITY = 19;

	// The amount of items in the List
	private double size;

	// The value which is calculated later on which tells
	// how full the list is/threshold
	private double loadFactor;

	/**
	 * Constructor that creates an empty {@code HashTable} using the initial
	 * capacity
	 */
	public HashTable() {
		this.entries = new Entry[INITIAL_CAPACITY];
		this.size = 0;
	}

	/**
	 * This method adds an {@code Entry} to the list depending on its key
	 * 
	 * @param e
	 *            The entry to add
	 * @param key
	 *            The value calculated by hashCode() which is then compressed
	 *            Key is added based on 3 conditions, first condition being if
	 *            the key assigned to that entry is null it is immediately added
	 *            if not it checks to see if the word at that key equals that of
	 *            the key it has been assigned, if they are equal the count of
	 *            that word is incremented it finally checks to see if the words
	 *            do not match, if they do not match we have a collision which
	 *            is then handled in the collisionManager() method
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

	/**
	 * Computes the load factor of the hashTable which is just basically the
	 * amount of objects in the table divided by the max capacity of the table
	 */
	private void computeLoadFactor() {
		double n = this.entries.length;
		this.loadFactor = this.size / n;
	}

	/**
	 * Here quadratic probing is done where a {@value newKey} is generated based
	 * on what is available in the hashTable
	 * 
	 * @param e
	 *            Is used to compare its new index and see if should increment
	 *            or not
	 * @param key
	 *            Is used to create the new key with primaryHashing and
	 *            secondaryHasing Creates a new key for the entry using primary
	 *            and secondary hashing It then checks if that key corresponds
	 *            to a null index if it does it is added If not that means it
	 *            can equal the word in that index, which is then checked If it
	 *            does the count is increased
	 */
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

	/**
	 * Secondary hash formula where new key is created when there is collisions
	 * 
	 * @param key
	 *            Is used to get a new Key
	 * @return returns the key after it has been run through the secondaryHash
	 *         formula
	 */
	private int secondaryHash(int key) {
		return 7 - ((key) % 7);
	}

	/**
	 * ReHash is done when the threshhold has been exceeded A new list is
	 * created which is a copy of the original table the size of the table is
	 * then increased the objects from the table are then re added to the new re
	 * sized table
	 */
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

	/**
	 * Returns the entry at that index
	 * 
	 * @param index
	 *            The index asked for
	 * @return The entry corresponding to that index
	 */
	public Entry getEntry(int index) {
		return this.entries[index];
	}

	/**
	 * This method returns the amount of objects in the able
	 * 
	 * @return The amount of objects in the table
	 */
	public int size() {
		return (int) this.size;
	}

	/**
	 * This method returns the actual size of the table
	 * 
	 * @return The max Capacity of the table
	 */
	public int maxSize() {
		return this.entries.length;
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
