package polygon;

import windowing.drawable.Drawable;

public class FilledPolygonRenderer implements PolygonRenderer {
    public FilledPolygonRenderer() {
    }

    public static FilledPolygonRenderer make() {
        return new FilledPolygonRenderer();
    }

    @Override
    public void drawPolygon(Polygon polygon, Drawable drawable, Shader vertexShader) {

    }
}
