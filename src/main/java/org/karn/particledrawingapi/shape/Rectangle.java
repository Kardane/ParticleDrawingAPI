package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.karn.particledrawingapi.shape.Line.LineDots;

public class Rectangle {
    public static List<Map<String, Double>> RectangleDots(Vec3d pos1, Vec3d pos2, int numDots) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        Vec3d corner1 = pos1;
        Vec3d corner2 = new Vec3d(pos1.x, pos1.y, pos2.z);
        Vec3d corner3 = new Vec3d(pos2.x, pos1.y, pos1.z);
        Vec3d corner4 = pos2;
        DotArray.addAll(LineDots(corner1, corner2, numDots));
        DotArray.addAll(LineDots(corner2, corner4, numDots));
        DotArray.addAll(LineDots(corner4, corner3, numDots));
        DotArray.addAll(LineDots(corner3, corner1, numDots));
        return DotArray;
    }
}
