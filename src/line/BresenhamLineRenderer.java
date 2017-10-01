package line;

import geometry.Vertex3D;
import windowing.drawable.Drawable;

public class BresenhamLineRenderer implements LineRenderer {
    public BresenhamLineRenderer() {
    }

    public static BresenhamLineRenderer make() {
        return new BresenhamLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {

    }
}
