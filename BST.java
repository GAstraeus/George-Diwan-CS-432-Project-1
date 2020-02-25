import java.util.LinkedList;

public class BST {
    Node root;
    public BST(final int value){
        root = new Node(value);
    }
    public void insertRec(final int value){
        insertRec(root,value);
    }
    private void insertRec(Node root, int value){
        if (value < root.value){
            if (root.left != null){
                insertRec(root.left, value);
            }
            else {
                root.left = new Node(value);
            }
        }
        else if (value > root.value){
            if (root.right != null){
                insertRec(root.right, value);
            }
            else {
                root.right = new Node(value);
            }
        }
    }

    public void deleteRec(final int value){
        deleteRec(this.root, value);
    }

    private void deleteRec(Node node, final int value){
        if (node.value == value){
            Node parent = findParent(this.root, value);

            if (node.right == null && node.left==null){
                if (parent.right == node)
                    parent.right = null;
                else
                    parent.left = null;
                node = null;
            }
            else if (parent == null){
                if (node.right != null)
                    this.root = node.right;
                else
                    this.root = node.left;
                node = null;
            }

            else if (node.right == null){
                //point over root to node.left
                if (parent.right == node){
                    parent.right = node.left;
                }
                else{
                    parent.left = node.left;
                }
                node = null;
            }
            else if (node.left==null){
                //point over root to node.right
                if (parent.left == node){
                    parent.left = node.right;
                }
                else{
                    parent.right = node.right;
                }
                node = null;
            }
            else {
                if (parent.right == node){
                    //node is the one getting deletes
                    //
                    Node successor = getNode(this.root,findNextRec(node.value));
                    successor.left = node.left;
                    successor.right = node.right;
                    parent.right = successor;
                    //find next successive node and point parent to it
                    // point it to left of node, point it to right of node
                }
                if (parent.left == node){
                    //node is the one getting deletes
                    //
                    Node successor = getNode(this.root,findNextRec(node.value));
                    successor.left = node.left;
                    successor.right = node.right;
                    parent.left = successor;
                    //find next successive node and point parent to it
                    // point it to left of node, point it to right of node
                }
            }

        }
        else if (node.value > value){
            if (node.left != null)
                deleteRec(node.left, value);
        }
        else {
            if (node.right != null)
                deleteRec(node.right, value);
        }
    }

    public int findMaxRec(){
        return findMaxHelper(this.root);
    }
    private int findMaxHelper(Node node){
        if (node.right == null){
            return node.value;
        }
        else{
            return findMaxHelper(node.right);
        }
    }

    public int findMinRec(){
        return findMinHelper(this.root);
    }
    private int findMinRec(Node n){
        return findMinHelper(n);
    }
    private int findMinHelper(Node node){
        if (node.left == null)
            return node.value;

        else
            return findMinHelper(node.left);
    }

    public int findNextRec(final int value){
        Node nextofNode = getNode(this.root, value);
        if (nextofNode.right != null){
            return findMinRec(nextofNode.right);
        }
        else {
            Node parent = findParent(value);
            return findNextHelp(parent, nextofNode).value;
        }
    }
    private Node findNextHelp(Node parent, Node node){
        if (parent != null && parent.right==node){
            return findNextHelp(findParent(parent.value), parent);
        }
        else if (parent == null)
            return null;
        else
            return parent;
    }

    public int findPrevRec(final int value){
        Node nextofNode = getNode(this.root, value);
        if (nextofNode.left != null){
            return findMinRec(nextofNode.left);
        }
        else {
            Node parent = findParent(value);
            return findPrevHelp(parent, nextofNode).value;
        }
    }
    private Node findPrevHelp(Node parent, Node node){
        if (parent != null && parent.left==node){
            return findPrevHelp(findParent(parent.value), parent);
        }
        else if (parent == null)
            return null;
        else
            return parent;
    }


    private Node getNode(Node node, final int value){
        if (node.value == value){
            return node;
        }
        else if (node.left != null && node.value > value){
            return getNode(node.left, value);
        }
        else if (node.right != null && node.value < value){
            return getNode(node.right, value);
        }
        else
            return null;
    }

    public Node findParent(final int value){
        return findParent(this.root, value);
    }
    public Node findParent(Node root, final int value){
        if (root.left != null && value == root.left.value){
            return root;
        }
        else if (root.right != null && value == root.right.value){
            return root;
        }
        else if (root.right != null && root.value < value){
            return findParent(root.right, value);
        }
        else if (root.left != null && root.value > value){
            return findParent(root.left, value);
        }
        else
            return null;
    }

    public static String printTree(Node root){
        String out = "";
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.add(root);
        return printHelp(nodes, out);
    }
    public static String printHelp(LinkedList<Node> nodes, String output) {
        if (nodes.size() > 0) {
            Node node = nodes.poll();
            output += node.toString() + " ";

            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
            printHelp(nodes, output);
        }
        return output;
    }
}


