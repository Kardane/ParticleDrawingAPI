package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arc {
    public static List<Map<String, Double>> CircleDots(double x, double y, double z, double radius, int numDots) {
        return ArcDots(x,y,z, radius, numDots, 0, 360);
    }
    public static List<Map<String, Double>> CircleDots(Vec3d center, double radius, int numDots) {
        return ArcDots(center, radius, numDots, 0, 360);
    }
    public static List<Map<String, Double>> ArcDots(Vec3d center, double radius, int numDots, double startAngle, double endAngle) {
        return ArcDots(center.x, center.y, center.z, radius, numDots, startAngle, endAngle);
    }

    public static List<Map<String, Double>> ArcDots(double x, double y, double z, double radius, int numDots, double startAngle, double endAngle) {
        List<Map<String, Double>> DotArray = new ArrayList<>();

        double startAngleRad = startAngle * Math.PI / 180;
        double endAngleRad = endAngle * Math.PI / 180;

        double angleIncrement = (endAngleRad - startAngleRad) / (numDots - 1);

        for (int i = 0; i < numDots; i++) {
            double angle = startAngleRad + i * angleIncrement;
            double arcx = x + radius * Math.cos(angle);
            double arcy = y;
            double arcz = z + radius * Math.sin(angle);
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", arcx);
            dot.put("y", arcy);
            dot.put("z", arcz);
            DotArray.add(dot);
        }

        return DotArray;
    }
}
