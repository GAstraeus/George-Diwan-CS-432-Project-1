import java.util.LinkedList;

public class BBSTRec {
    public Node root;

    public BBSTRec(final int value){
        root = new Node(value);
    }

    public void insertRec(final int value){
        insertRecHelp(root,value);
    }
    private void insertRecHelp(Node node, int value){
        if (value < node.value){
            if (node.left != null){
                insertRecHelp(node.left, value);
            }
            else {
                Node temp = new Node(value);
                temp.parent = node;
                node.left = temp;
                balance(node);
            }
        }
        else if (value > node.value){
            if (node.right != null){
                insertRecHelp(node.right, value);
            }
            else {
                Node temp = new Node(value);
                temp.parent = node;
                node.right = temp;
                balance(node);
            }
        }
    }

    /**
     * Checks the balance of the Tree
     * @param node the last modified node
     */
    public void balance(Node node){
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
        if (node != null && node.parent != null)
            balance(node);
    }

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

    /**
     * Calculates the BF of a node
     * @param node The Node to calculate the BF of
     * @return the Balance Factor of a node
     */
    private int BF(Node node){
        return heightSubtree(node.left,0)-heightSubtree(node.right, 0);
    }

    /**
     * This functions calculated the height of a SUBTREE. Never call on node itself!!!!
     * @param node The initial node of the subtree
     * @param count The initial count should be 0
     * @return the height of the subtree
     */
    private int heightSubtree(Node node, int count){
        count++;
        if (node.right == null && node.left == null)
            return count;
        else if (node.right != null && node.left != null) {
            int rightCount = heightSubtree(node.right, 0);
            int leftCount = heightSubtree(node.left, 0);
            count += Math.max(rightCount, leftCount);
        }
        else if (node.left != null){
            count += heightSubtree(node.left, 0);
        }
        else if (node.right != null){
            count += heightSubtree(node.right, 0);
        }
        return count;
    }

    public void deleteRec(final int value){
        deleteRec(this.root, value);
    }

    private void deleteRec(Node node, final int value){
        if (node.value == value) {
            Node parent = findParent(value);

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
                    Node successor = getNode(this.root,findNextRec(node.value));
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
                    Node successor = getNode(this.root,findNextRec(node.value));
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


    public Node getNode(Node node, final int value){
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

}
