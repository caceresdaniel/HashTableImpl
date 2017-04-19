
public class HashTable {
	
	private Entry[] entries;
	private static final double LOAD_THRESHOLD = 0.5;
	private static final int INITIAL_CAPACITY = 17;
	private int size;
	private double loadFactor = size / this.entries.length;
	
	public HashTable(){
		this.entries = new Entry[INITIAL_CAPACITY];
		this.size = 0;
	}
	
	public void add(){
		if(this.loadFactor >= LOAD_THRESHOLD){
			this.resize();
		}
		/**
		 * check size 
		 * convert to hashcode 
		 * then you check cases to see if index is empty
		 * if not empty and word matches increment coutn 
		 * else not empty and word does not match that is when 
		 * you do linear probing, double hasing , or quadratic probing
		 * 
		 */
	}
	
	private void resize(){
		Entry[] newList = new Entry[this.entries.length * 2];
		
		newList = this.entries.clone();
		
		/**
		 * 	you basically copy the array 
		 * make the size bigger for the array
		 * then run the add method all over again for each item in the copied array
		 */
		
	}
	
	public int getEntry(){
		return 0;
	}
	
	public void collisionManager(){
		/**
		 * this is where you do double hashing and such
		 * you also do what you did in add method once again 
		 * 
		 */
	}
	
	
	
}
