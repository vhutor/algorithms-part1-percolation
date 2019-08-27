import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 *
 Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
 *
 * */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;

        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item could not be null.");
        }

        if (first == null) {
            first = new Node();
            last = first;
            first.item = item;
            size++;
            return;
        }

        Node oldFirst = first;
        if (last == first) {
            last = oldFirst;
        }

        first = new Node();
        first.item = item;
        first.next = oldFirst;

        size++;

    }

    // remove and return a random item
    public Item dequeue() {
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int number = StdRandom.uniform(size);



        return null;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomQueue();
    }

    private class RandomQueue implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("There are no more items in the deque.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue("one");
        randomizedQueue.enqueue("two");
        randomizedQueue.enqueue("three");
        Iterator<String> random = randomizedQueue.iterator();
        while (random.hasNext()) {
            System.out.println(random.next());
        }
    }

    private class Node {
        private Item item;
        private Node next;
    }

}
