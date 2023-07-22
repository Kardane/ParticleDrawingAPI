package org.karn.particledrawingapi.shape;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.karn.particledrawingapi.shape.Arc.CircleDots;
import static org.karn.particledrawingapi.util.Rotate.RotateDotArray;

public class Sphere {
    public static List<Map<String, Double>> sphereDots(Vec3d center, double radius, int density, int numDots) {
        List<Map<String, Double>> sphereDots = new ArrayList<>();
        int angle = 360/density;
        for (int i = 0; i < 180; i+=angle) {
            sphereDots.addAll(RotateDotArray(CircleDots(center, radius, numDots), 0, 0, i, center));
        }
        return sphereDots;
    }
}
