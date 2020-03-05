public class BSTIterative {
    public Node root;
    private long count;
    public BSTIterative(final int value){
        root = new Node(value);
        this.count = 0;
    }

    public void insertIter(final int value){
        Node node = this.root;
        while(true){
            if (value < node.value){
                if (node.left != null){
                    node = node.left;
                    ++this.count;
                }
                else {
                    node.left = new Node(value);
                    break;
                }
            }
            else if (value > node.value){
                if (node.right != null){
                    node = node.right;
                    ++this.count;
                }
                else {
                    node.right = new Node(value);
                    break;
                }
            }
        }
    }

    public void deleteIter(final int value) {
        Node node = getNode(value);
        while (node.value != value) {
            if (node.value > value) {
                if (node.left != null)
                    node = node.left;
            } else {
                if (node.right != null)
                    node = node.right;
            }
            ++this.count;
        }

        //We have the node to delete we can look at what case it is and delete it
        Node parent = getParent(value);

        if (node.right == null && node.left == null) {
            if (parent.right == node)
                parent.right = null;
            else
                parent.left = null;
            node = null;
        }
        else if (parent == null) {
            if (node.right != null)
                this.root = node.right;
            else
                this.root = node.left;
            node = null;
        }
        else if (node.right == null) {
            //point over root to node.left
            if (parent.right == node) {
                parent.right = node.left;
            }
            else {
                parent.left = node.left;
            }
            node = null;
        }
        else if (node.left == null) {
            //point over root to node.right
            if (parent.left == node) {
                parent.left = node.right;
            }
            else {
                parent.right = node.right;
            }
            node = null;
        }
        else {
            if (parent.right == node) {
            //node is the one getting deletes
            //
                Node successor = getNode(findNextIter(node.value));
                successor.left = node.left;
                successor.right = node.right;
                parent.right = successor;
                node = null;
                //find next successive node and point parent to it
                // point it to left of node, point it to right of node
            }
            else if (parent.left == node) {
                //node is the one getting deletes
                //
                Node successor = getNode(findNextIter(node.value));
                successor.left = node.left;
                successor.right = node.right;
                parent.left = successor;
                node = null;
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

    private Node getNode(final int value){
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
