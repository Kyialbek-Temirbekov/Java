import java.util.*;

class MyArrayList <E> {
    private int size = 0;
    private static int INITIAL_CAPACITY = 10;
    private Object[] elements;
    public MyArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }
    public void add(E e) {
        if(size == elements.length)
            ensureCapa();
        elements[size++] = e;
    }
    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements,newSize);
    }
    @SuppressWarnings("unchecked")
    public E get(int i) {
        return (E) elements[i];
    }
    public void remove(E e) {
        for(int i=0;i<size;i++)
            if(elements[i].equals(e)) {
                for(int j=i;j<size-1;j++)
                    elements[j] = elements[j+1];
                break;
            }
        elements[--size] = null;
    }
    public void remove(int i) {
        for(int j=i;j<size-1;j++)
            elements[j] = elements[j+1];
        elements[--size] = null;
    }
    public int size() {
        return size;
    }
    public String toString() {
        StringJoiner s = new StringJoiner(",");
        for(int i=0;i<size;i++)
            s.add(String.valueOf(elements[i]));
        return "[" + s.toString() + "]";
    }
}

public class ArrayListDemo {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Implementation");
        list.add("of");
        list.add("ArrayList");
        list.add("with");
        list.add("generics");
        list.add("$$$");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        System.out.println(list);
        list.remove("$$$");
        System.out.println("After removing " + list);
        list.remove(1);
        for(int i=0;i<list.size();i++)
            System.out.print(list.get(i) + " ");
        MyArrayList<Integer> intList = new MyArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        intList.remove(2);
        intList.remove(Integer.valueOf(2));
        System.out.println();
        System.out.println(intList);
    }
}
