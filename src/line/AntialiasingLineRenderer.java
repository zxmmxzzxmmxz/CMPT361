package line;

import geometry.Vertex3D;
import windowing.drawable.Drawable;
import windowing.graphics.Dimensions;

public class AntialiasingLineRenderer implements LineRenderer {
    public AntialiasingLineRenderer() {
    }

    public static AntialiasingLineRenderer make() {
        return new AntialiasingLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {
        Dimensions dimensions = panel.getDimensions();
        
    }
}
