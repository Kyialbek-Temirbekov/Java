import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.Arrays;

class KeyValueEntry <K,V> {
    private K key;
    private V value;
    public KeyValueEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    protected K getKey() {
        return key;
    }
    protected V getValue() {
        return value;
    }
    protected void setValue(V value) {
        this.value = value;
    }
    protected boolean isEqual(K key) {
        return this.key == key;
    }
}

class MyHashMap <K,V> {
    LinkedList<KeyValueEntry<K,V>>[] buckets;
    private int size = 0;
    private int CAPACITY = 16;
    private float fillRatio = 0.75f;
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[CAPACITY];
    }
    @SuppressWarnings("unchecked")
    public MyHashMap(int cap) {
        buckets = new LinkedList[cap];
    }
    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }
    private KeyValueEntry<K,V> getEntry(K key) {
        int index = getIndex(key);
        for(int i=0;i<buckets[index].size();i++) {
            KeyValueEntry<K,V> entry = buckets[index].get(i);
            if(getIndex(entry.getKey()) == index)
                return entry;
        }
        return null;
    }
    private boolean containsKey(K key) {
        return buckets[getIndex(key)] != null && getEntry(key) != null;
    }
    private void resize() {
        buckets = Arrays.copyOf(buckets, buckets.length * 2);
        for(int i=0;i<buckets.length;i++)
            if(buckets[i]!=null)
                for(int j=0;j<buckets[i].size();j++)
                    if(getIndex(buckets[i].get(j).getKey())!=i) {
                        KeyValueEntry<K,V> entry = buckets[i].get(j);
                        buckets[i].remove(j);
                        size--;
                        this.put(entry.getKey(),entry.getValue());
                    }
    }
    public void put(K key, V value) {
        if(containsKey(key))
            getEntry(key).setValue(value);
        else {
            int index = getIndex(key);
            buckets[index] = new LinkedList<>();
            buckets[index].add(new KeyValueEntry<K,V>(key, value));
            size++;
            if(size/buckets.length >= fillRatio)
                resize();
        }
    }
    public V get(K key) {
        int index = getIndex(key);
        if(buckets[index] != null)
            for(int i=0;i<buckets[index].size();i++)
                if(buckets[index].get(i).isEqual(key))
                    return buckets[index].get(i).getValue();
        return null;
    }
    public V remove(K key) {
        int index = getIndex(key);
        if(buckets[index] != null)
            for(int i=0;i<buckets[index].size();i++)
                if(buckets[index].get(i).isEqual(key)) {
                    V value = buckets[index].get(i).getValue();
                    buckets[index].remove(i);
                    size--;
                    return value;
                }
        return null;
    }
    public int size() {
        return size;
    }
    public String toString() {
        StringJoiner sj = new StringJoiner(",");
        for(int i=0;i<buckets.length;i++)
            if(buckets[i] != null)
                for(KeyValueEntry<K,V> entry : buckets[i])
                    sj.add(entry.getKey() + "=" + entry.getValue());
        return "[" + sj.toString() + "]";
    }

}

public class HashMapDemo {
    public static void main(String[] args) {
        MyHashMap<Integer,Float> map = new MyHashMap<>();

        for(int i=17;i<=32;i++)
            map.put(i,(float)i/1000);
        System.out.println(map);
        System.out.println("Size: " + map.size());

        System.out.println("Value of 20: " + map.get(20));
        System.out.println(map.remove(19) + " is removed");
        System.out.println("Value of 19: " + map.get(19));
        System.out.println("Size: " + map.size());
    }
}