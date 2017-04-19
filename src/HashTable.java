
public class HashTable {

	private Entry[] entries;
	private static final double LOAD_THRESHOLD = 0.5;
	private static final int INITIAL_CAPACITY = 17;
	private int size;
	private double loadFactor = size / this.entries.length;

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
		if (this.loadFactor >= LOAD_THRESHOLD) {
			this.reHash();
		}

		int key = e.hashCode() % this.entries.length;

		if (this.entries[key] == null) {
			this.entries[key] = e;
			this.size++;
		} else if (this.entries[key] == e) {
			this.entries[key].incrementCount();
		} else if (this.entries[key] != e && this.entries[key] != null) {

		}
	}

	private int secondaryHash(int key){
		return (7 - (key) % 7);
	}
	
	private void reHash() {
		Entry[] listCopy = new Entry[this.entries.length];
		Entry[] newSize = new Entry[this.entries.length * 2];

		// for(int i = 0; i < entries.length; i++){
		// listCopy[i] = this.entries[i];
		// this.entries[i] = null;
		// }

		listCopy = this.entries;

		this.entries = newSize;

		for (int j = 0; j < listCopy.length; j++) {
			if (listCopy[j] != null) {
				add(listCopy[j]);
			}
		}
	}

	public int getEntry() {
		return 0;
	}

	private void collisionManager() {
		/**
		 * this is where you do double hashing and such you also do what you did
		 * in add method once again
		 * 
		 */
	}

}
