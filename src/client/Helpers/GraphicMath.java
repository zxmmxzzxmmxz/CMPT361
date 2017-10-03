package client.Helpers;

import geometry.Line;
import geometry.Point3DH;

public class GraphicMath {

    public static double distanceFrom(Point3DH p1, Point3DH p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2) + Math.pow(p1.getZ() - p2.getZ(), 2));
    }

    public static double distanceFromLine(Line line, Point3DH point) {
        double k = (double) -1 / line.getSlope();
        double x0 = point.getX();
        double y0 = point.getY();

        //y-y0 = k(x-x0)
        //so it's y = kx-kx0+y0 = kx + (y0-kx0)
        //2D only, for now
        Line theOtherLine = new Line(k, y0 - k * x0);
        Point3DH intersection = intersectionOfTwoLines(line, theOtherLine);
        return distanceFrom(point, intersection);
    }

    public static Point3DH intersectionOfTwoLines(Line line1, Line line2) {
        if (line1.getSlope() == line2.getSlope()) {
            throw new IllegalArgumentException("No intersection of these two lines");
        } else {
            double k1 = line1.getSlope();
            double k2 = line2.getSlope();
            double b1 = line1.getIntercept();
            double b2 = line2.getIntercept();
            //y = k1x + b1
            //y = k2x + b2
            //thus, (k1-k2)x + b1-b2 = 0 -> x = (b2-b1)/(k1-k2)
            //and y = k1x + b1
            double x = (b2 - b1) / (k1 - k2);
            double y = k1 * x + b1;
            return new Point3DH(x, y, 0);
        }
    }
}
