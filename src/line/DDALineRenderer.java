package line;

import geometry.Vertex3D;
import windowing.drawable.Drawable;

public class DDALineRenderer implements LineRenderer {
    public DDALineRenderer() {
    }

    public static DDALineRenderer make() {
        return new DDALineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {

    }
}
