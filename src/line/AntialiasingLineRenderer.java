package line;

import client.Helpers.GraphicMath;
import geometry.Line;
import geometry.Point3DH;
import geometry.Vertex3D;
import windowing.drawable.Drawable;
import windowing.graphics.Color;
import windowing.graphics.Dimensions;

public class AntialiasingLineRenderer implements LineRenderer {

    private static final int PIXEL_RADIUS = 1;

    public AntialiasingLineRenderer() {
    }

    public static AntialiasingLineRenderer make() {
        return new AntialiasingLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {
        Line line = new Line(p1.getPoint3D(), p2.getPoint3D());
        Dimensions dimensions = panel.getDimensions();
        for (int y = 0; y <= dimensions.getHeight(); y++) {
            for (int x = 0; x <= dimensions.getWidth(); x++) {
                double distance = GraphicMath.distanceFromLine(line, new Point3DH(x, y, 0));
                if (distance < 2 * PIXEL_RADIUS) {
                    //then render the point
                    //otherwise don't
                    panel.setPixel(x, y, 0, Color.WHITE.asARGB());
                }
            }
        }
    }
}
