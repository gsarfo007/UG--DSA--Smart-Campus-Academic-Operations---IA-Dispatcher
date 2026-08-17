package structures;

/**
 * CustomQueue.java
 * Student: Godlove Agyei Sarfo
 * Student ID: 22263864
 *
 * Circular Queue
 * Maximum Size = 864 % 100 = 64
 */
public class CustomQueue<T> {

    private static final int MAX_SIZE = 64;

    private final Object[] queue;
    private int front;
    private int rear;
    private int size;

    /**
     * Constructor
     */
    public CustomQueue() {
        queue = new Object[MAX_SIZE];
        front = 0;
        rear = 0;
        size = 0;
    }

    /**
     * Adds an element to the rear of the queue.
     */
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full.");
        }

        queue[rear] = item;
        rear = (rear + 1) % MAX_SIZE;
        size++;
    }

    /**
     * Removes and returns the front element.
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        T item = (T) queue[front];
        queue[front] = null;
        front = (front + 1) % MAX_SIZE;
        size--;

        return item;
    }

    /**
     * Returns the front element without removing it.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        return (T) queue[front];
    }

    /**
     * Checks whether the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the queue is full.
     */
    public boolean isFull() {
        return size == MAX_SIZE;
    }

    /**
     * Returns the current number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum capacity of the queue.
     */
    public int capacity() {
        return MAX_SIZE;
    }

    /**
     * Embedded main() test runner for PR #7.
     */
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println(" CustomQueue PR #7 Test Runner");
        System.out.println(" Student: Godlove Agyei Sarfo");
        System.out.println(" Student ID: 22263864");
        System.out.println(" Maximum Size: " + MAX_SIZE);
        System.out.println("======================================");

        CustomQueue<Integer> queue = new CustomQueue<>();

        // Test 1: New queue should be empty
        System.out.println("\nTest 1: New queue is empty");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + queue.isEmpty());

        // Test 2: Enqueue elements
        System.out.println("\nTest 2: Enqueue elements");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Expected size: 3");
        System.out.println("Actual size:   " + queue.size());

        // Test 3: Peek
        System.out.println("\nTest 3: Peek front element");
        System.out.println("Expected: 10");
        System.out.println("Actual:   " + queue.peek());

        // Test 4: Dequeue
        System.out.println("\nTest 4: Dequeue");
        int removed = queue.dequeue();

        System.out.println("Expected removed: 10");
        System.out.println("Actual removed:   " + removed);

        System.out.println("Expected new front: 20");
        System.out.println("Actual new front:   " + queue.peek());

        // Test 5: Circular queue behaviour
        System.out.println("\nTest 5: Circular queue behaviour");

        queue.enqueue(40);
        queue.enqueue(50);

        System.out.println("Queue size: " + queue.size());
        System.out.println("Front element: " + queue.peek());

        System.out.println("Removing: " + queue.dequeue());
        System.out.println("Removing: " + queue.dequeue());
        System.out.println("Removing: " + queue.dequeue());
        System.out.println("Removing: " + queue.dequeue());

        System.out.println("Expected empty: true");
        System.out.println("Actual empty:   " + queue.isEmpty());

        // Test 6: Fill queue to capacity
        System.out.println("\nTest 6: Fill queue to maximum capacity");

        CustomQueue<Integer> fullQueue = new CustomQueue<>();

        for (int i = 1; i <= MAX_SIZE; i++) {
            fullQueue.enqueue(i);
        }

        System.out.println("Expected size: " + MAX_SIZE);
        System.out.println("Actual size:   " + fullQueue.size());

        System.out.println("Expected full: true");
        System.out.println("Actual full:   " + fullQueue.isFull());

        // Test 7: Verify FIFO ordering
        System.out.println("\nTest 7: FIFO ordering");

        System.out.println("First:  " + fullQueue.dequeue());
        System.out.println("Second: " + fullQueue.dequeue());
        System.out.println("Third:  " + fullQueue.dequeue());

        // Test 8: Empty queue exception
        System.out.println("\nTest 8: Empty queue protection");

        CustomQueue<String> emptyQueue = new CustomQueue<>();

        try {
            emptyQueue.dequeue();
            System.out.println("FAIL: dequeue() should throw an exception.");
        } catch (IllegalStateException e) {
            System.out.println("PASS: dequeue() correctly detected empty queue.");
        }

        try {
            emptyQueue.peek();
            System.out.println("FAIL: peek() should throw an exception.");
        } catch (IllegalStateException e) {
            System.out.println("PASS: peek() correctly detected empty queue.");
        }

        // Test 9: Full queue protection
        System.out.println("\nTest 9: Full queue protection");

        try {
            fullQueue.enqueue(999);
            System.out.println("FAIL: enqueue() should throw an exception.");
        } catch (IllegalStateException e) {
            System.out.println("PASS: enqueue() correctly detected full queue.");
        }

        System.out.println("\n======================================");
        System.out.println(" All embedded tests completed.");
        System.out.println("======================================");
    }
}