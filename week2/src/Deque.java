import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure. Create a generic data
 * type Deque that implements the following API
 *
 * */

// TODO: add java docs.
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
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

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item could not be null.");
        }

        if (last == null) {
            last = new Node();
            first = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.next = null;
            oldLast.next = last;
        }

        last.item = item;
        size++;
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        Item item;

        if (first == last) {
            last = null;
            item = first.item;
            first = null;
        } else {
            Node oldFirst = first;
            item = first.item;
            first = first.next;
            oldFirst.next = null;
        }

        size--;
        return item;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        Item item;

        if (first == last) {
            item = last.item;
            last = null;
            first = null;
        } else {
            Node iterator = first;
            while (iterator.next != null && iterator.next != last) {
                iterator = iterator.next;
            }

            last = iterator;
            item = last.next.item;
            last.next = null;
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

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

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst(2);
        deque.removeFirst();
        deque.addFirst(6);
        deque.addFirst(7);
        deque.removeFirst();
        deque.removeFirst();

    }

    private class Node {

        private Item item;
        private Node next;
    }



}
