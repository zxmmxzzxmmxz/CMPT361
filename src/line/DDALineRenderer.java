package line;

import geometry.Line;
import geometry.Vertex3D;
import windowing.drawable.Drawable;

public class DDALineRenderer implements LineRenderer {


    private DDALineRenderer() {
    }

    public static LineRenderer make() {
        return new AnyOctantLineRenderer(new DDALineRenderer());
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {
        Line line = new Line(p1.getPoint3D(), p2.getPoint3D());
        if (shouldRenderVertically(line)) {
            for (int yToRender = p1.getIntY(); yToRender < p2.getIntY(); yToRender++) {
                int xToRender = (int) line.getX(yToRender);
                panel.setPixel(xToRender, yToRender, 0, p1.getColor().asARGB());
            }
        } else {
            for (int xToRender = p1.getIntX(); xToRender < p2.getIntX(); xToRender++) {
                int yToRender = (int) line.getY(xToRender);
                panel.setPixel(xToRender, yToRender, 0, p1.getColor().asARGB());
            }
        }
    }

    private boolean shouldRenderVertically(Line line) {
        return Math.abs(line.getSlope()) >= 1;
    }
}
