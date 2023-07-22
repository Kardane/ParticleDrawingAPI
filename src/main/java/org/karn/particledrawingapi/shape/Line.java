package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    public static List<Map<String, Double>> LineDots(Vec3d pos1, Vec3d pos2, int numDots) {
        return LineDots(pos1.x,pos1.y,pos1.z, pos2.x,pos2.y, pos2.z, numDots);
    }
    public static List<Map<String, Double>> LineDots(double x1,double y1, double z1, double x2,double y2, double z2, int numDots) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        for (int i = 0; i < numDots; i++) {
            double t = (double) i / (numDots - 1);
            double linex = (1 - t) * x1 + t * x2;
            double liney = (1 - t) * y1 + t * y2;
            double linez = (1 - t) * z1 + t * z2;
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", linex);
            dot.put("y", liney);
            dot.put("z", linez);
            DotArray.add(dot);
        }
        return DotArray;
    }
}