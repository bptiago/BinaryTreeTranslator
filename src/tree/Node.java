package tree;

public class Node {
    private String element;
    private Node left, right;

    public Node(String element, Node left, Node right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
