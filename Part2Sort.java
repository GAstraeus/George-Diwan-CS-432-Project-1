import java.util.ArrayList;

public class Part2Sort {
    public static void sort(int[] arr){
        BSTIterative tree = new BSTIterative(arr[0]);
        for (int i = 1; i<arr.length; i++){
            tree.insertIter(arr[i]);
        }
        ArrayList<Integer> output = new ArrayList<Integer>();
        sortHelp(output, tree.root);
        for(int i = 0; i<arr.length; i++){
            arr[i] = output.get(i);
        }

    }
    public static void sortHelp(ArrayList<Integer> arr, Node node){
        if (node.left != null){
            sortHelp(arr, node.left);
        }
        arr.add(node.value);
        if (node.right != null){
            sortHelp(arr, node.right);
        }
    }
    public static void main(String [] args){
        int[] arr = {33,21,47,100,80,60,82,50,79,81,83,43,20,22,14,13,12};
        sort(arr);
        for (int x:arr){
            System.out.print(x+" ");
        }
    }

}
