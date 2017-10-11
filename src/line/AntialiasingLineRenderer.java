package line;

import client.Helpers.GraphicMath;
import client.Helpers.TriangleMath;
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

    private AntialiasingLineRenderer() {
    }

    public static AntialiasingLineRenderer make() {
        return new AntialiasingLineRenderer();
    }

    @Override
    public void drawLine(Vertex3D p1, Vertex3D p2, Drawable panel) {
        Line line = new Line(p1.getPoint3D(), p2.getPoint3D());
        Pair<Line, Line> twoLinesAsBound = getTwoLinesAsBound(p1.getPoint3D(), p2.getPoint3D(), line.getSlope());
        Line upperLine = twoLinesAsBound.getValue();
        Line lowerLine = twoLinesAsBound.getKey();
        Dimensions dimensions = panel.getDimensions();
        for (int y = 0; y <= dimensions.getHeight(); y++) {
            for (int x = 0; x <= dimensions.getWidth(); x++) {
                if (withinRenderArea(x, y, upperLine, lowerLine)) {
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

    private Pair<Line, Line> getTwoLinesAsBound(Point3DH p1, Point3DH p2, double k) {
        //calculate upper line
        Point3DH endPoint = new Point3DH(
                p2.getX() + LINE_WIDTH * TriangleMath.convertToSinFromTan(k),
                p2.getY() - LINE_WIDTH * TriangleMath.convertToCosFromTan(k),
                0);
        Point3DH startPoint = new Point3DH(
                p2.getX() - LINE_WIDTH * TriangleMath.convertToSinFromTan(k),
                p2.getY() + LINE_WIDTH * TriangleMath.convertToCosFromTan(k),
                0);
        Line upperLine = new Line(startPoint, endPoint);

        //calculate lower line
        Line lowerLine = new Line(upperLine).transformWithVector(p2.getX() - p1.getX(), p2.getY() - p1.getY());

        return new Pair<>(lowerLine, upperLine);
    }

    private boolean withinRenderArea(double x, double y, Line upperLine, Line lowerLine) {
        return upperLine.underOrOnLine(x, y) && lowerLine.aboveOrOnLine(x, y);
        //return true;
    }
}
