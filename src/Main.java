import tree.BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.populateTree();

        String morse = "... --- ..."; // SOS
        String msg = tree.translateMorseCode(morse);
        System.out.println(msg);
    }
}