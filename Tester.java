public class Tester {
    public static void main(String [] args){
        /*
        BST tree = new BST (15);
        tree.insertRec( 20);
        tree.insertRec( 22);
        tree.insertRec( 14);
        tree.insertRec( 13);
        tree.insertRec( 12);
        tree.insertRec( 56);
        tree.insertRec( 45);
        tree.insertRec( 32);
        tree.insertRec( 33);
        tree.insertRec( 64);
        tree.insertRec( 47);
        tree.insertRec( 21);
*/

        BST tree = new BST (33);
        tree.insertRec( 21);
        tree.insertRec( 47);
        tree.insertRec( 100);
        tree.insertRec( 80);
        tree.insertRec( 60);
        tree.insertRec( 82);
        tree.insertRec( 50);
        tree.insertRec( 79);
        tree.insertRec( 81);
        tree.insertRec( 83);
        tree.insertRec( 43);



        //System.out.println(tree.findParent( 20));

        tree.deleteRec(80);
        tree.deleteRec(100);
        tree.deleteRec(47);


        //System.out.println(tree.findPrevRec(15));
    }
}
