import java.util.*;

class node <T> {
    T data;
    node<T> next;
    public node(T t) {
        data = t;
        next = null;
    }
}

class MyLinkedList <T> {
    node<T> head;
    private int size = 0;
    MyLinkedList() {
        head = null;
    }
    public void add(T t) {
        node<T> newInstance = new node<T>(t);
        if(head == null) {
            head = newInstance;
            size++;
        }
        else {
            node<T> temp = head;
            while(temp.next!=null)
                temp = temp.next;
            temp.next = newInstance;
            size++;
        }
    }
    public String toString() {
        StringJoiner str = new StringJoiner(",");
        node<T> temp = head;
        while(temp!=null) {
            str.add(String.valueOf(temp.data));
            temp = temp.next;
        }
        return "[" + str.toString() + "]";
    }
    public int size() {
        return size;
    }
    public void remove(T t) {
        node<T> prev = head;
        node<T> temp = head.next;
        if(head.data == t) {
            head = temp;
            prev = null;
            size--;
            return;
        }
        while(temp!=null) {
            if(temp.data == t) {
                prev.next = temp.next;
                temp.next = null;
                size--;
            }
            temp = temp.next;
            prev = prev.next;
        }
    }
    public T set(int index, T t) {
        T oldVal = null;
        node<T> temp = head.next;
        if(index>size-1)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        if(index == 0) {
            oldVal = head.data;
            head.data = t;
            return oldVal;
        }
        for(int i=1;i<size;i++) {
            if(i == index) {
                oldVal = temp.data;
                temp.data = t;
            }
            temp = temp.next;
        }
        return oldVal;
    }
}

public class LinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList<String> ll = new MyLinkedList<>();
        ll.add("LinkedList");
        ll.add("implementation");
        ll.add("by");
        ll.add("Kyialbek");
        System.out.println(ll);
        ll.remove("implementation");
        System.out.println(ll);
        String rem = ll.set(2,"Java");
        System.out.println(ll);
        System.out.println("Removed value " + rem);
        ll.set(10,"Dell");
    }
}
