import javax.swing.*;
import java.awt.*;
import tree.BinaryTree;

public class MorseDecoderSwing {
    private static final BinaryTree tree = new BinaryTree(); // Sua árvore binária

    public static void main(String[] args) {
        JFrame frame = new JFrame("Decodificador de Código Morse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Campo de texto para inserir código morse
        JTextField morseInput = new JTextField();
        morseInput.setPreferredSize(new Dimension(800, 30));
        morseInput.setToolTipText("Digite o código morse aqui...");

        // Área de texto para mostrar a tradução
        JTextArea translatedText = new JTextArea();
        translatedText.setEditable(false);
        translatedText.setPreferredSize(new Dimension(800, 50));

        // Painel de desenho da árvore binária
        DrawingPanel drawingPanel = new DrawingPanel();

        mainPanel.add(morseInput, BorderLayout.NORTH);
        mainPanel.add(translatedText, BorderLayout.SOUTH);
        mainPanel.add(drawingPanel, BorderLayout.CENTER);

        // Listener para atualizar a tradução de código Morse
        morseInput.addCaretListener(e -> {
            String translated = tree.translateMorseCode(morseInput.getText());
            translatedText.setText(translated);
            drawingPanel.repaint();  // Re-pinta a árvore binária sempre que o código morse for alterado
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    static class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawTree(g, tree.getRoot(), 400, 50, 100);  // Desenha a árvore binária
        }

        private void drawTree(Graphics g, tree.Node node, int x, int y, int hGap) {
            if (node == null) return;

            // Define a fonte
            g.setFont(new Font("Arial", Font.BOLD, 14));
            FontMetrics metrics = g.getFontMetrics();
            String element = node.getElement().isEmpty() ? "•" : node.getElement();
            int width = metrics.stringWidth(element);
            int height = metrics.getHeight();

            // Ajusta a altura para evitar que letras fiquem sobre as linhas
            int textY = y + height / 2;

            // Desenha o texto centralizado no nó
            g.drawString(element, x - width / 2, textY);

            int childY = y + 50;  // Espaçamento vertical para os filhos

            // Desenha linhas e chama recursivamente para o filho esquerdo
            if (node.getLeft() != null) {
                int childX = x - hGap;
                g.drawLine(x, textY + 5, childX, childY - height / 2);
                drawTree(g, node.getLeft(), childX, childY, hGap / 2);
            }

            // Desenha linhas e chama recursivamente para o filho direito
            if (node.getRight() != null) {
                int childX = x + hGap;
                g.drawLine(x, textY + 5, childX, childY - height / 2);
                drawTree(g, node.getRight(), childX, childY, hGap / 2);
            }
        }

    }
}