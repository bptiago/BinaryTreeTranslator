package tree;

public class BinaryTree {
    private Node root;

    public BinaryTree() {
        this.root = new Node("", null, null);
        populateTree();
    }

    public String translateMorseCode(String morse) {
        StringBuilder msg = new StringBuilder();

        String[] codes = morse.split(" ");
        for (String code : codes) {
            if (code.equals("/")) {
                msg.append(" ");
            } else {
                msg.append(decode(root, code, 0));
            }
        }

        return msg.toString();
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

    public String encodeToMorse(String text) {
        StringBuilder morseCode = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (c == ' ') {
                morseCode.append("/ ");
            } else {
                String code = searchMorseCode(root, c, "");
                if (code != null) {
                    morseCode.append(code).append(" ");
                }
            }
        }
        return morseCode.toString().strip();
    }


    private String searchMorseCode(Node node, char letter, String path) {
        if (node == null) return null;

        if (node.getElement() != null && node.getElement().equals(String.valueOf(letter))) {
            return path;
        }

        // Busca à esquerda (ponto)
        String leftPath = searchMorseCode(node.getLeft(), letter, path + ".");
        if (leftPath != null) return leftPath;

        // Busca à direita (traço)
        String rightPath = searchMorseCode(node.getRight(), letter, path + "-");
        return rightPath;
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

    private void populateTree() {
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
        insert("-----", '0');
        insert(".----", '1');
        insert("..---", '2');
        insert("...--", '3');
        insert("....-", '4');
        insert(".....", '5');
        insert("-....", '6');
        insert("--...", '7');
        insert("---..", '8');
        insert("----.", '9');
        insert(".-.-.", '+');
        insert("-....-", '-');
        insert("-...-", '=');
        insert("-..-.", '/');
    }

    public Node getRoot() {
        return root;
    }
}
