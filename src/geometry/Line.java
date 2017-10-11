package geometry;

public class Line implements HasSlope, HasIntercept {

    private double _slope;
    private double _intercept;

    public Line(Point3DH p1, Point3DH p2) {
        double deltaX = p2.getX() - p1.getX();
        double deltaY = p2.getY() - p1.getY();

        _slope = deltaY / deltaX;
        _intercept = p2.getY() - _slope * p2.getX();
    }

    public Line(double slope, double intercept) {
        _slope = slope;
        _intercept = intercept;
    }

    public Line(Line anotherLine) {
        _slope = anotherLine.getSlope();
        _intercept = anotherLine.getIntercept();
    }

    public Line(Point3DH p, double k) {
        _slope = k;
        _intercept = p.getY() - _slope * p.getX();
    }

    public double getY(double x) {
        return _slope * x + _intercept;
    }

    public double getX(double y) {
        return (y - _intercept) / _slope;
    }

    public void transformWithY(double plusY) {
        _intercept += plusY;
    }

    public void transformWithX(double plusX) {
        //y = kx + b
        //y = k(x+plusX) +b
        //y = kx + k * plusX + b
        //so new intercept is k*plusX + b
        _intercept += _slope * plusX;
    }

    public Line transformWithVector(double x, double y) {
        transformWithX(x);
        transformWithY(y);
        return this;
    }

    public boolean aboveOrOnLine(double x, double y) {
        return y >= _slope * x + _intercept;
    }

    public boolean underOrOnLine(double x, double y) {
        return y <= _slope * x + _intercept;
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
