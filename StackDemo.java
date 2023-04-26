import java.util.Arrays;

class MyStack <E> {
    private int size = 0;
    private static int INITIAL_CAPACITY = 10;
    private Object[] elements;
    public MyStack() {
        elements = new Object[INITIAL_CAPACITY];
    }
    public void push(E e) {
        if(size == elements.length)
            ensureCapacity();
        elements[size++] = e;
    }
    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
    @SuppressWarnings("unchecked")
    public E pop() {
        Object value = elements[--size];
        elements[size] = null;
        return (E) value;
    }
    @SuppressWarnings("unchecked")
    public E peek() {
        return (E) elements[size-1];
    }
    public boolean empty() {
        return size == 0;
    }

}

public class StackDemo {
    public static void main(String[] args) {
        MyStack<Character> st = new MyStack<>();
        st.push('K');
        st.push('E');        
        st.push('B');
        st.push('L');
        st.push('A');
        st.push('I');
        st.push('Y');
        st.push('K');
        System.out.println(st.peek());
        while(!st.empty())
            System.out.print(st.pop());
    }
}
