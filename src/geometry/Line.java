package geometry;

public class Line implements HasSlope, HasIntercept {

    private double _slope;
    private double _intercept;

    public Line(Point3DH p1, Point3DH p2) {
        double deltaX = p2.getIntX() - p1.getIntX();
        double deltaY = p2.getIntY() - p1.getIntY();

        _slope = deltaY / deltaX;
        _intercept = p2.getIntY() - _slope * p2.getIntX();
    }

    public Line(double slope, double intercept) {
        _slope = slope;
        _intercept = intercept;
    }

    public double getY(double x) {
        return _slope * x + _intercept;
    }

    public double getX(double y) {
        return (y - _intercept) / _slope;
    }

    public void transform(double plusY) {
        _intercept += plusY;
    }

    @Override
    public double getIntercept() {
        return _intercept;
    }

    @Override
    public double getSlope() {
        return _slope;
    }
}
