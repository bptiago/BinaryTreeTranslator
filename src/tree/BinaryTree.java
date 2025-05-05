package tree;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = new Node("", null, null);
    }

    public String translateMorseCode(String morse) {
        String msg = "";

        String[] codes = morse.split(" ");
        for (String code : codes) {
            msg += decode(root, code, 0);
        }

        return msg;
    }

    private String decode(Node node, String code, int index) {
        if (index == code.strip().length()) {
            return node.getElement();
        }

        char c = code.charAt(index);

        if (c == '.') {
            return decode(node.getLeft(), code, index + 1);
        } else {
            return decode(node.getRight(), code, index + 1);
        }
    }

    public void insert(String code, char letter) {
        Node node = root;
        int length = code.strip().length();

        for (int i = 0; i < length; i++) {
            char c = code.charAt(i);
            if (c == '.') {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(null, null, null));
                }
                node = node.getLeft();
            } else {
                if (node.getRight() == null) {
                    node.setRight(new Node(null, null, null));
                }
                node = node.getRight();
            }
        }
        node.setElement(String.valueOf(letter));
    }

    public void populateTree() {
        insert(".-",   'A');
        insert("-...", 'B');
        insert("-.-.", 'C');
        insert("-..",  'D');
        insert(".",    'E');
        insert("..-.", 'F');
        insert("--.",  'G');
        insert("....", 'H');
        insert("..",   'I');
        insert(".---", 'J');
        insert("-.-",  'K');
        insert(".-..", 'L');
        insert("--",   'M');
        insert("-.",   'N');
        insert("---",  'O');
        insert(".--.", 'P');
        insert("--.-", 'Q');
        insert(".-.",  'R');
        insert("...",  'S');
        insert("-",    'T');
        insert("..-",  'U');
        insert("...-", 'V');
        insert(".--",  'W');
        insert("-..-", 'X');
        insert("-.--", 'Y');
        insert("--..", 'Z');
    }
}
