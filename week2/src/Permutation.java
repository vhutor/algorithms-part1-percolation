import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {

        int num = Integer.parseInt(args[0]);
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        while (num-- > 0){
            String strNumber = StdIn.readString();
            if(strNumber == null || strNumber.equals("")) {
                throw new IllegalArgumentException("The number should be not empty.");
            }
            randomizedQueue.enqueue(Integer.parseInt(strNumber));
        }

        while (!randomizedQueue.isEmpty()) {
            System.out.println(randomizedQueue.dequeue());
        }
    }

}
