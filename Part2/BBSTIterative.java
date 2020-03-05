import javafx.util.Pair;
import java.util.LinkedList;

public class BBSTIterative {
    public Node root;
    private long count;

    /**
     * This class represents a balanced binary search tree
     * @param value the root value of the tree
     */
    public BBSTIterative(final int value){
        root = new Node(value);
        this.count=0;
    }

    /**
     * Inserts a value into the tree
     * @param value to insert
     */
    public void insertIter(final int value){
        Node node = this.root;
        while(true){
            if (value < node.value){
                if (node.left != null){
                    node = node.left;
                    ++this.count;
                }
                else {
                    Node temp = new Node(value);
                    temp.parent = node;
                    node.left = temp;
                    balance(temp);
                    break;
                }
            }
            else if (value > node.value){
                if (node.right != null){
                    node = node.right;
                    ++this.count;
                }
                else {
                    Node temp = new Node(value);
                    temp.parent = node;
                    node.right = temp;
                    balance(temp);
                    break;
                }
            }
        }
    }


    /**
     * Calculates the Balance Factor for a given node
     * @param node the node to calculate the balance factor of
     * @return the balance factor
     */
    private int BF(Node node){
        return heightSubtree(node.left) - heightSubtree(node.right);
    }

    /**
     * Calculates the Height of a nodes subtree!!!! Do not call on node itself
     * @param node the root node of the subtree
     * @return the height of the subtree
     */
    private int heightSubtree(Node node){
        if (node == null)
            return 0;
        LinkedList<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(node, 1));
        Pair<Node, Integer> inspecting = new Pair<>(null,null);
        while (!queue.isEmpty()) {
            inspecting = queue.poll();
            Node n = inspecting.getKey();
            int height = inspecting.getValue();
            if (n.left != null){
                queue.add(new Pair<>(n.left,height+1));
            }
            if(n.right != null) {
                queue.add(new Pair<>(n.right, height + 1));
            }
        }
        return inspecting.getValue();
    }

    /**
     * Balances the tree
     * @param node the most recently modified node
     */
    public void balance(Node node){
        while (node != null && node.parent != null){
            Node parent = node.parent;
            int bfParent = BF(parent);
            int bfNode = BF(node);
            if (bfParent > 1 && bfNode > 0){
                rightRotate(parent);
            }
            else if (bfParent > 1 && bfNode < 0){
                leftRotate(node);
                rightRotate(parent);
                //Left Rotate Node
                // RIght Rotate Parent
            }
            else if (bfParent < -1 && bfNode < 0){
                leftRotate(parent);
            }
            else if (bfParent < -1 && bfNode > 0){
                rightRotate(node);
                leftRotate(parent);
                // Right Rotate Node
                // Left Rotate Parent
            }
            else if (bfNode > 1 && node.left != null){
                if (BF(node.left) < 0){
                    //right left case
                    leftRotate(node.left);
                    rightRotate(node);
                }
                else if (BF(node.left) >= 0){
                    //right right
                    rightRotate(node);
                }
            }
            else if (bfNode < -1 && node.right!=null){
                if (BF(node.right) < 0){
                    //left left case
                    leftRotate(node);
                }
                else if (BF(node.right) >= 0){
                    //left right case
                    rightRotate(node.right);
                    leftRotate(node);
                }
            }
            node = node.parent;
        }
    }

    /**
     * Does a right rotate
     * @param node the node to rotate on
     */
    public void rightRotate(Node node){
        Node rotatingNode = node.left;
        node.left = rotatingNode.right;
        if (node.left != null)
            node.left.parent = node;
        rotatingNode.parent = node.parent;
        node.parent = rotatingNode;
        rotatingNode.right = node;
        //Connect it to the rest of the tree
        if (this.root == node)
            this.root = rotatingNode;
        else if  (rotatingNode.parent.left == node)
            rotatingNode.parent.left = rotatingNode;
        else if  (rotatingNode.parent.right == node)
            rotatingNode.parent.right = rotatingNode;
    }

    /**
     * Does a left rotate
     * @param node the node to rotate on
     */
    public void leftRotate(Node node){
        Node rotatingNode = node.right;
        node.right = rotatingNode.left;
        if (node.right != null)
            node.right.parent = node;
        rotatingNode.parent = node.parent;
        node.parent = rotatingNode;
        rotatingNode.left = node;
        if (this.root == node)
            this.root = rotatingNode;
        else if  (rotatingNode.parent.left == node)
            rotatingNode.parent.left = rotatingNode;
        else if  (rotatingNode.parent.right == node)
            rotatingNode.parent.right = rotatingNode;
    }

    public void deleteIter(final int value) {
        Node node = getNode(value);
        while (node.value != value)
            if (node.value > value) {
                if (node.left != null) {
                    node = node.left;
                    ++this.count;
                }
            }
            else {
                if (node.right != null){
                    node = node.right;
                    ++this.count;
                }
            }

        //We have the node to delete we can look at what case it is and delete it

        Node parent = getParent(value);

        if (node.right == null && node.left == null) {
            if (this.root == node)
                node = null;
            else if (parent.right == node)
                parent.right = null;
            else
                parent.left = null;
            node = null;
            balance(parent);

        }
        else if (parent == null) {
            if (node.right != null) {
                this.root = node.right;
                node.right.parent = null;
            }
            else{
                this.root = node.left;
                node.left.parent = null;
            }
            node = null;
            balance(root);

        }
        else if (node.right == null) {
            //point over root to node.left
            if (parent.right == node) {
                parent.right = node.left;
                parent.right.parent = parent;
            }
            else {
                parent.left = node.left;
                parent.left.parent = parent;
            }
            node = null;
            balance(parent);
        }
        else if (node.left == null) {
            //point over root to node.right
            if (parent.left == node) {
                parent.left = node.right;
                parent.left.parent = parent;
            }
            else {
                parent.right = node.right;
                parent.right.parent = parent;
            }
            node = null;
            balance(parent);
        }
        else {
            if (parent.right == node) {
                //node is the one getting deletes
                //
                Node successor = getNode(findNextIter(node.value));
                successor.left = node.left;
                //successor.right = node.right;
                parent.right = successor;
                node = null;
                successor.parent = parent;
                if (successor.left != null)
                    successor.left.parent = successor; //Kird
                balance(successor);
                //find next successive node and point parent to it
                // point it to left of node, point it to right of node
            }
            else if (parent.left == node) {
                //node is the one getting deletes
                //
                Node successor = getNode(findNextIter(node.value));
                //successor.left = node.left;
                successor.right = node.right;
                parent.left = successor;
                node = null;
                successor.parent = parent;
                if (successor.left != null)
                    successor.left.parent = successor; //kird
                balance(successor);
                //find next successive node and point parent to it
                // point it to left of node, point it to right of node
            }
        }

    }


    public Integer findNextIter(final int value){
        Node node = getNode(value);
        Node parent = getParent(value);
        if (node.right != null)
            return findMinIter(node.right.value);
        else{
            while (parent != null && parent.right==node){
                node = parent;
                parent = getParent(parent.value);
            }
        }
        if (parent==null)
            return Integer.MIN_VALUE;
        return parent.value;
    }
    public Integer findPrevIter(final int value){
        Node node = getNode(value);
        Node parent = getParent(value);
        if (node.left != null)
            return findMaxIter(node.left.value);
        else{
            while (parent != null && parent.left==node){
                node = parent;
                parent = getParent(parent.value);
            }
        }
        if (parent==null)
            return Integer.MIN_VALUE;
        return parent.value;
    }

    public int findMaxIter(){
        Node node = this.root;
        while(node.right!=null){
            node = node.right;
            ++this.count;
        }
        return node.value;
    }
    public int findMaxIter(final int value){
        Node node = getNode(value);
        while(node.right!=null){
            node = node.right;
            ++this.count;
        }
        return node.value;
    }

    public int findMinIter(){
        Node node = this.root;
        while(node.left!=null){
            node = node.left;
            ++this.count;
        }
        return node.value;
    }
    public int findMinIter(final int value){
        Node node = getNode(value);
        while(node.left!=null){
            node = node.left;
            ++this.count;
        }
        return node.value;
    }

    public Node getNode(final int value){
        Node node = this.root;
        while(node.value != value){
            if (node.left != null && value < node.value){
                node = node.left;
            }
            else if (node.right != null && value > node.value){
                node = node.right;
            }
            ++this.count;
        }
        return node;
    }
    public Node getParent(final int value){
        Node node = this.root;
        Node prev = null;
        while(node.value != value){
            if (node.left != null && value < node.value){
                prev = node;
                node = node.left;
            }
            else if (node.right != null && value > node.value){
                prev = node;
                node = node.right;
            }
            ++this.count;
        }
        return prev;
    }

    public long getCount(){
        return this.count;
    }
}
