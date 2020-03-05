import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;


public class Parts5and6 {
    public static void main (String [] args){
        //15,6,23,4,7,71,5,50,100,51,52,53,55,54,69,70

        //==================================================================
        //  Question 5a
        //==================================================================
        part5a(); //This takes forever

        //==================================================================
        //  Question 5c-6b
        //==================================================================
        part5cTO6b();
        //==================================================================
        //  Question 6c
        //==================================================================
        part6c();




    }
    public static Integer[] getRandomArray(int n){
        HashMap<Integer, Integer> set = new HashMap<>();
        while (set.size() < n){
            set.put((int) (Math.random()*(Integer.MAX_VALUE)), 1);
        }
        return set.keySet().toArray(new Integer[0]);
    }
    public static int[] getSortedArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++){
            arr[i] = n-i;
        }
        return arr;
    }
    public static void part5a(){
        Integer[] inputArr = getRandomArray(100000);
        Arrays.sort(inputArr);
        BST bstRecursive = null;
        BBSTIterative bbstIterative = null;
        System.out.println("Inserting into Interative");
        for (int i = 0; i<inputArr.length; i++){
            if (i == 0){
                bbstIterative = new BBSTIterative(inputArr[i]);
            }
            else {
                bbstIterative.insertIter(inputArr[i]);
            }
        }
        System.out.println("Inserting into Recursive");
        for (int i = 0; i<inputArr.length; i++){
            if (i == 0){
                bstRecursive = new BST(inputArr[i]);
            }
            else {
                bstRecursive.insertRec(inputArr[i]);
            }
        }
    }
    public static void part5cTO6b(){
        Integer[] inputArray2 = getRandomArray(10000);
        BSTIterative bst = null;
        BBSTIterative bbst = null;
        System.out.println("Starting Insert");
        for (int i = 0; i<inputArray2.length; i++){
            if (i == 0){
                bst = new BSTIterative(inputArray2[i]);
                bbst = new BBSTIterative(inputArray2[i]);
            }
            else {
                bst.insertIter(inputArray2[i]);
                bbst.insertIter(inputArray2[i]);
                //System.out.println("Inserting: "+inputArr[i]);
            }
        }
        System.out.println(" BST iterative: "+bst.getCount());
        System.out.println("BBST Iterative: "+bbst.getCount());
    }
    public static void part6c(){
        int[] inputArr3 = getSortedArray(10000);
        BSTIterative bst = null;
        BBSTIterative bbst = null;
        System.out.println("Starting Insert");
        for (int i = 0; i<inputArr3.length; i++){
            if (i == 0){
                bst = new BSTIterative(inputArr3[i]);
                bbst = new BBSTIterative(inputArr3[i]);
            }
            else {
                bst.insertIter(inputArr3[i]);
                bbst.insertIter(inputArr3[i]);
                //System.out.println("Inserting: "+inputArr[i]);
            }
        }
        System.out.println(" BST iterative sorted: "+bst.getCount());
        System.out.println("BBST Iterative sorted: "+bbst.getCount());
    }
    public static void part7a(){
        Integer[] inputArr3 = getRandomArray(10);
        BSTIterative bst = null;
        BBSTIterative bbst = null;

        Instant finishBBST = Instant.now();
        System.out.println("Starting Insert");
        Instant startBST = Instant.now();
        for (int i = 0; i<inputArr3.length; i++){
            if (i == 0){
                bst = new BSTIterative(inputArr3[i]);
            }
            else {
                bst.insertIter(inputArr3[i]);
            }
        }
        System.out.println("Starting Delete");
        for (Integer integer : inputArr3) {
            bst.deleteIter(integer);
        }
        Instant finishBST = Instant.now();

        System.out.println("Starting Insert");
        Instant startBBST = Instant.now();
        for (int i = 0; i<inputArr3.length; i++){
            if (i == 0){
                bbst = new BBSTIterative(inputArr3[i]);
            }
            else {
                bbst.insertIter(inputArr3[i]);
                //System.out.println("Inserting: "+inputArr[i]);
            }
        }
        System.out.println("Starting Delete");
        for (Integer integer : inputArr3) {
            bbst.deleteIter(integer);
        }


        long timeBBST = Duration.between(finishBBST, finishBBST).toMillis();
        long timeBST = Duration.between(startBST, finishBST).toMillis();

        System.out.println(" BST iterative 10000 inserts/deletes time: "+timeBST);
        System.out.println("BBST Iterative 10000 inserts/deletes time: "+timeBBST);
    }
}
