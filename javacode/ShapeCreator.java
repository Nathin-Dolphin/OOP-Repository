
/**
 * @author Nathin Wascher
 * @version 1.0.4
 * @since Februrary 12, 2020
 */

public class ShapeCreator {
    public static void main(String[] args) {
        System.out.println("Executing program (ShapeCreator)...");
        new SimpleFrame("ShapeCreator", "Simple Shape and Color Mixer", new ShapeCreator_Panel(), 1000, 600);
    }
}