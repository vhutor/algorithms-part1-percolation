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
        } else {
            Node oldFirst = first;
            if (last == first) {
                last = oldFirst;
            }
            first = new Node();
            first.next = oldFirst;
        }

        first.item = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        int number = StdRandom.uniform(size) + 1;
        int count = 0;
        Item item;

        Node iterator = first;
        Node preLast = null;
        while (count != number && iterator.next != null) {
            preLast = iterator;
            iterator = iterator.next;
            count++;
        }

        if (iterator == last) {
            item = last.item;
            if (first == last) {
                first = null;
                last = null;
            } else {
                last = preLast;
                last.next = null;
            }
        }
        else if (iterator == first) {
            item = first.item;
            first = first.next;
        }
        else {
            item = iterator.item;
            preLast.next = iterator.next;
            iterator.next = null;
            iterator.item = null;
        }

        size--;
        return item;
    }


    // return a random item (but do not remove it)
    public Item sample() {
        return getSample().item;
    }

    private Node getSample() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        int number = StdRandom.uniform(size) + 1;
        int count = 0;

        Node node = first;
        while (count != number && node.next != null) {
            node = node.next;
        }

        return node;
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
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        rq.enqueue(212);
        rq.enqueue(215);
        rq.enqueue(214);
        rq.enqueue(213);
        System.out.println("Item was removed: " + rq.dequeue());
        rq.enqueue(313);
        rq.enqueue(413);
        rq.enqueue(513);
        rq.enqueue(613);
        rq.enqueue(713);
        rq.enqueue(813);
        rq.enqueue(913);
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());
        System.out.println("Item was removed: " + rq.dequeue());

        Iterator<Integer> iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        rq.enqueue(302);
        rq.size();
        rq.sample();

        Iterator<Integer> iterator1 = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator1.next());
        }

    }

    private class Node {
        private Item item;
        private Node next;
    }

}
