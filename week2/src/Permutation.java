import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {

        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (num > 0) {
            String str = StdIn.readString();
            if (str == null || str.equals("")) {
                throw new IllegalArgumentException("The number should be not empty.");
            }
            randomizedQueue.enqueue(str);
            num--;
        }

        while (!randomizedQueue.isEmpty()) {
            System.out.println(randomizedQueue.dequeue());
        }
    }

}
