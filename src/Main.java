import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        drawTree(canvas.getGraphicsContext2D(), binaryTree.getRoot(), 400, 30, 200);

        VBox root = new VBox(canvas);
        stage.setScene(new Scene(root));
        stage.setTitle("Visualizador Árvore Morse");
        stage.show();
    }

    private void drawTree(GraphicsContext gc, BinaryTree node, double x, double y, double hGap) {
        if (node == null) return;
        gc.strokeText(node.value.isEmpty() ? "•" : node.value, x, y);
        if (node.left != null) {
            gc.strokeLine(x, y, x - hGap, y + 50);
            drawTree(gc, node.left, x - hGap, y + 50, hGap / 1.5);
        }
        if (node.right != null) {
            gc.strokeLine(x, y, x + hGap, y + 50);
            drawTree(gc, node.right, x + hGap, y + 50, hGap / 1.5);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}