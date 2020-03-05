public class Node{
    public int value;
    public Node right;
    public Node left;
    public Node parent;

    public Node(int value){
        this.value = value;
        this.right = null;
        this.left = null;
        this.parent = null;
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}