package client.Helpers;

public class TriangleMath {
    public static double convertToSinFromTan(double tanValue) {
        return convertToCosFromTan(tanValue) * tanValue;
    }

    public static double convertToCosFromTan(double tanValue) {
        return Math.sqrt(1 / (1 + Math.pow(tanValue, 2)));
    }
}
