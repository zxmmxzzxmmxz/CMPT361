package line;

import geometry.Vertex3D;
import windowing.drawable.Drawable;

public class AntialiasingLineRenderer implements LineRenderer {
    public AntialiasingLineRenderer() {
    }

    public static AntialiasingLineRenderer make() {
        return new AntialiasingLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {

    }
}
