import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }


        int number = StdRandom.uniform(size) + 1;
        int count = 1;

        Item item = null;

        Node iterator = first;
        Node preLast = null;
        while (count != number && iterator.next != null) {
            preLast = iterator;
            iterator = iterator.next;
            count++;
        }

        // last
        if (iterator == last) {
            item = last.item;
            if (first == last) {
                first = null;
                last = null;
            } else {
                last = preLast;
            }
            size--;
            return item;
        }

        // first
        if (iterator == first) {
            item = first.item;
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.next;
            }
            size--;
            return item;
        }
        else {
            // remove in the middle
            Node removeNode = iterator;
            item = preLast.item;
            preLast.next = removeNode.next;
            removeNode.next = null;
            removeNode.item = null;
            size--;
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        int number = StdRandom.uniform(size);
        int count = 0;

        Node iterator = first;
        while (count != number && iterator.next != null) {
            iterator = iterator.next;
        }

        return iterator.item;
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
            return current != null;
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException("You could not call the remove() method onto iterator");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(336);
        rq.enqueue(58);
        rq.enqueue(158);
        rq.enqueue(120);
        rq.enqueue(3);
        rq.enqueue(325);
        rq.enqueue(212);
        rq.dequeue();
        rq.enqueue(302);
        rq.size();
        rq.sample();

    }

    private class Node {
        private Item item;
        private Node next;
    }

}
