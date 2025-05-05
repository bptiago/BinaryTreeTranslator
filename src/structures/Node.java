package structures;

public class Node<E> {
    private E element;
    private Node<E> left, right;

    public Node(E element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
}
