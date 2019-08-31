import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure. Create a generic data
 * type Deque that implements the following API:
 *
 * */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    public Deque() {
        first = null;
        last = first;
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

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item could not be null.");
        }

        if (last == null) {
            last = new Node();
            first = last;
            last.item = item;
            size++;
            return;
        }

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;
        oldLast.next = last;

        size++;
    }

    public Item removeFirst() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        Node oldFirst = first;
        if (first.next == null) {
            first = null;
            last = null;
        }

        Item item = oldFirst.item;
        oldFirst.next = null;
        oldFirst = null;


        if (first == last) {
            last = null;
        }

        size--;

        return item;
    }

    public Item removeLast() {
        if (size() == 0) {
            throw new NoSuchElementException("There are no elements in the deque to delete");
        }

        if (size() == 1) {
            Item item = last.item;
            last = null;
            first = null;
            size--;
            return item;
        }

        Node iterator = first;
        while (iterator.next != null && iterator.next != last) {
            iterator = iterator.next;
        }

        last = iterator;
        Item item = last.next.item;
        last.next = null;

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
        Deque<String> deque = new Deque<>();
        deque.addFirst("one");
        deque.addLast("last");
        deque.addFirst("two");
        deque.addFirst("three");

        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private class Node {

        private Item item;
        private Node next;
    }



}
