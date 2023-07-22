package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.karn.particledrawingapi.shape.Line.LineDots;

public class Cubic {
    public static List<Map<String, Double>> CubeDots(Vec3d pos1, Vec3d pos2, int numDots) {
        List<Map<String, Double>> DotArray = new ArrayList<>();
        Vec3d corner1 = pos1;
        Vec3d corner2 = new Vec3d(pos1.x, pos1.y, pos2.z);
        Vec3d corner3 = new Vec3d(pos1.x, pos2.y, pos2.z);
        Vec3d corner4 = pos2;
        Vec3d corner5 = new Vec3d(pos2.x, pos2.y, pos1.z);
        Vec3d corner6 = new Vec3d(pos2.x, pos1.y, pos1.z);
        Vec3d corner7 = new Vec3d(pos1.x, pos2.y, pos1.z);
        Vec3d corner8 = new Vec3d(pos2.x, pos1.y, pos2.z);
        DotArray.addAll(LineDots(corner1, corner2, numDots));
        DotArray.addAll(LineDots(corner2, corner3, numDots));
        DotArray.addAll(LineDots(corner3, corner4, numDots));
        DotArray.addAll(LineDots(corner4, corner5, numDots));
        DotArray.addAll(LineDots(corner5, corner6, numDots));
        DotArray.addAll(LineDots(corner6, corner1, numDots));

        DotArray.addAll(LineDots(corner1, corner7, numDots));
        DotArray.addAll(LineDots(corner7, corner3, numDots));
        DotArray.addAll(LineDots(corner7, corner5, numDots));
        DotArray.addAll(LineDots(corner8, corner2, numDots));
        DotArray.addAll(LineDots(corner8, corner6, numDots));
        DotArray.addAll(LineDots(corner8, corner4, numDots));
        return DotArray;
    }
}
