import java.util.Arrays;

class HashTable {
    private final int[][] buckets;
    private final int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buckets = new int[capacity][];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(int key, int[] value) {
        int index = getBucketIndex(key);
        buckets[index] = value;
        size++;
    }

    public int[] get(int key) {
        int index = getBucketIndex(key);
        int[] bucket = buckets[index];
        return bucket;
    }

    public int[] getBucket(int index) {
        return buckets[index];
    }

    public void remove(int key) {
        int index = getBucketIndex(key);
        int[] bucket = buckets[index];
        if (bucket != null) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] == key) {
                    int[] newBucket = new int[bucket.length - 1];
                    System.arraycopy(bucket, 0, newBucket, 0, i);
                    System.arraycopy(bucket, i + 1, newBucket, i, bucket.length - i - 1);
                    buckets[index] = newBucket;
                    size--;
                    return;
                }
            }
        }
    }

    private int getBucketIndex(int key) {
        int hashCode = Integer.hashCode(key);
        return Math.abs(hashCode) % capacity;
    }

    public int capacity() {
        return capacity;
    }
}
