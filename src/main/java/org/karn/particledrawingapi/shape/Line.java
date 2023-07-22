package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Line {
    public static List<Map<String, Double>> LineDots(Vec3d pos1, Vec3d pos2, int numDots) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        for (int i = 0; i < numDots; i++) {
            double t = (double) i / (numDots - 1);
            double linex = (1 - t) * pos1.x + t * pos2.x;
            double liney = (1 - t) * pos1.y + t * pos2.y;
            double linez = (1 - t) * pos1.z + t * pos2.z;
            Map<String, Double> dot = new HashMap<>();
            dot.put("x", linex);
            dot.put("y", liney);
            dot.put("z", linez);
            DotArray.add(dot);
        }
        return DotArray;
    }
}