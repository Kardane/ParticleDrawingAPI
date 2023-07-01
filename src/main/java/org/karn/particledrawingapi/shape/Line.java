package org.karn.particledrawingapi.shape;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.Vec3d;
import org.karn.particledrawingapi.util.Draw;

import java.util.Arrays;

public class Line {
    public static void main(ServerCommandSource source, ParticleEffect particle, Vec3d pos1, Vec3d pos2, int number) {
        Point[] points = generateLine(pos1, pos2, number);
        for (int i =0; i < points.length; i++) {
            Draw.main(source, particle, points[i].x, points[i].y, points[i].z, false);
        }
    }

    public static Point[] generateLine(Vec3d pos1, Vec3d pos2, int numPoints) {
        Point[] points = new Point[numPoints];
        double stepX = (pos2.getX() - pos1.getX()) / (numPoints - 1);
        double stepY = (pos2.getY() - pos1.getY()) / (numPoints - 1);
        double stepZ = (pos2.getZ() - pos1.getZ()) / (numPoints - 1);

        for (int i = 0; i < numPoints; i++) {
            double x = pos1.getX() + (stepX * i);
            double y = pos1.getY() + (stepY * i);
            double z = pos1.getZ() + (stepZ * i);
            points[i] = new Point(x, y, z);
        }

        return points;
    }
}

class Point {
    double x;
    double y;
    double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
