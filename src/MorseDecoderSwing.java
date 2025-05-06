import javax.swing.*;
import java.awt.*;
import tree.BinaryTree;

public class MorseDecoderSwing {
    private static final BinaryTree tree = new BinaryTree();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Decodificador e Codificador Morse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(1200, 30));
        inputField.setToolTipText("Digite Morse ou Texto aqui (use '/' para espaços em Morse)...");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setPreferredSize(new Dimension(1200, 50));

        DrawingPanel drawingPanel = new DrawingPanel();

        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(outputArea, BorderLayout.SOUTH);
        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        inputField.addCaretListener(e -> {
            String input = inputField.getText().trim();
            String output;

            if (input.matches("[.\\-/\\s]+")) {
                output = tree.translateMorseCode(input);
            } else if (input.matches("[a-zA-Z0-9\\s+=/-]+")) {
                output = tree.encodeToMorse(input);
            } else {
                output = "Entrada inválida! Use Morse (. - /) ou caracteres válidos.";
            }

            outputArea.setText(output);
            drawingPanel.repaint();
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    static class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawTree(g, tree.getRoot(), getWidth() / 2, 50, getWidth() / 4);
        }

        private void drawTree(Graphics g, tree.Node node, int x, int y, int hGap) {
            if (node == null) return;

            g.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics metrics = g.getFontMetrics();
            String element = node.getElement() == null || node.getElement().isEmpty() ? "•" : node.getElement();
            int width = metrics.stringWidth(element);
            int height = metrics.getHeight();

            int textY = y + height / 2;
            g.drawString(element, x - width / 2, textY);

            int childY = y + 60;

            if (node.getLeft() != null) {
                int childX = x - hGap;
                g.drawLine(x, textY + 5, childX, childY - height / 2);
                drawTree(g, node.getLeft(), childX, childY, hGap / 2);
            }

            if (node.getRight() != null) {
                int childX = x + hGap;
                g.drawLine(x, textY + 5, childX, childY - height / 2);
                drawTree(g, node.getRight(), childX, childY, hGap / 2);
            }
        }
    }
}
