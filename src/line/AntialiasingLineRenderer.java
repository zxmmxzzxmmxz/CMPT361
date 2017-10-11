package line;

import client.Helpers.GraphicMath;
import geometry.Line;
import geometry.Point3DH;
import geometry.Vertex3D;
import javafx.util.Pair;
import windowing.drawable.Drawable;
import windowing.graphics.Color;
import windowing.graphics.Dimensions;

public class AntialiasingLineRenderer implements LineRenderer {

    private static final int PIXEL_RADIUS = 1;
    private static final double LINE_WIDTH = 0.5;
    private final LineRenderer _expensiveRender;

    private AntialiasingLineRenderer() {
        _expensiveRender = ExpensiveLineRenderer.make();
    }

    public static LineRenderer make() {
        return new AntialiasingLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {
        Point3DH p1Point3D = p1.getPoint3D();
        Point3DH p2Point3D = p2.getPoint3D();
        Line line = new Line(p1Point3D, p2Point3D);
        if (line.getSlope() == 0 || Double.isInfinite(line.getSlope())) {
            _expensiveRender.drawLine(p1, p2, panel);
        } else {
            Pair<Line, Line> lowerAndUpperLine = getLowerAndUpperLine(p1Point3D, p2Point3D, line.getSlope());
            Line lowerLine = lowerAndUpperLine.getKey();
            Line upperLine = lowerAndUpperLine.getValue();
            Dimensions dimensions = panel.getDimensions();
            for (int y = 0; y <= dimensions.getHeight(); y++) {
                for (int x = 0; x <= dimensions.getWidth(); x++) {
                    doRender(panel, line, lowerLine, upperLine, y, x);
                }
            }
        }
    }

    private void doRender(Drawable panel, Line line, Line lowerLine, Line upperLine, int y, int x) {
        if (withinRenderArea(x, y, upperLine, lowerLine)) {
            double distance = GraphicMath.distanceFromLine(line, new Point3DH(x, y, 0));
            if (distance < 2 * PIXEL_RADIUS) {
                //then render the point
                //otherwise don't
                panel.setPixel(x, y, 0, Color.WHITE.asARGB());
            }
        }
    }

    private Pair<Line, Line> getLowerAndUpperLine(Point3DH p1, Point3DH p2, double k) {
        Line lowerLine = new Line(p1, -1 / k);
        Line upperLine = new Line(p2, -1 / k);
        if (lowerLine.getIntercept() > upperLine.getIntercept()) {
            return new Pair<>(upperLine, lowerLine);
        } else {
            return new Pair<>(lowerLine, upperLine);
        }
    }

    private boolean withinRenderArea(double x, double y, Line upperLine, Line lowerLine) {
        return upperLine.underOrOnLine(x, y) && lowerLine.aboveOrOnLine(x, y);
        //return true;
    }
}
