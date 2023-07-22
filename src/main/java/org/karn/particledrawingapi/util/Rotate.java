package org.karn.particledrawingapi.util;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Map;

public class Rotate {
    public static List<Map<String, Double>> RotateDotArray(List<Map<String, Double>> array, Vec2f rotation, Vec3d rotationPos) {
        //System.out.println(rotation.x + " | " + rotation.y);
        return RotateDotArray(array, rotation.x, rotation.y, 0, rotationPos);
    }
    public static List<Map<String, Double>> RotateDotArray(List<Map<String, Double>> array, double x, double y, double z, Vec3d rotationPos) {
        double cosX = Math.cos((x / 180) * Math.PI);
        double sinX = Math.sin((x / 180) * Math.PI);
        double cosY = Math.cos((y / 180) * Math.PI);
        double sinY = Math.sin((y / 180) * Math.PI);
        double cosZ = Math.cos((z / 180) * Math.PI);
        double sinZ = Math.sin((z / 180) * Math.PI);
        for (Map<String, Double> dot : array) {
            double rx = dot.get("x") - rotationPos.x;
            double ry = dot.get("y") - rotationPos.y;
            double rz = dot.get("z") - rotationPos.z;
            double x1 = rx;
            double y1 = ry * cosX - rz * sinX;
            double z1 = ry * sinX + rz * cosX;
            double x2 = x1 * cosY + z1 * sinY;
            double y2 = y1;
            double z2 = -x1 * sinY + z1 * cosY;
            double x3 = x2 * cosZ - y2 * sinZ;
            double y3 = x2 * sinZ + y2 * cosZ;
            double z3 = z2;
            dot.put("x", x3 + rotationPos.x);
            dot.put("y", y3 + rotationPos.y);
            dot.put("z", z3 + rotationPos.z);
        }
        return array;
    }
}
