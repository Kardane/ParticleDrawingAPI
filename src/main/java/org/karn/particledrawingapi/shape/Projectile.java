package org.karn.particledrawingapi.shape;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.PosArgument;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.karn.particledrawingapi.util.Draw;
import org.karn.particledrawingapi.util.Scheduler;

import java.util.function.Consumer;

import static java.lang.String.valueOf;

public class Projectile {
    public static Consumer<MinecraftServer> main(ServerCommandSource source, ParticleEffect particle, Vec3d pos, PosArgument rotation, int range, int tick) {
        double yawInRadians = Math.toRadians(rotation.toAbsoluteRotation(source).y);
        double pitchInRadians = Math.toRadians(rotation.toAbsoluteRotation(source).x);

        double dx = -0.2 * Math.sin(yawInRadians) * Math.cos(pitchInRadians);
        double dy = -0.2 * Math.sin(pitchInRadians);
        double dz = 0.2 * Math.cos(yawInRadians) * Math.cos(pitchInRadians);

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        int step = 0;
        if(tick>0) {
            step = range/tick*5;
        } else {
            step = range*5;
        }

        if(step<1){
            return null;
        }

        boolean HitorEndOfRange = false;
        for (int i = 0; i < step; i++) {
            x += dx;
            y += dy;
            z += dz;
            Draw.main(source, particle, x,y,z, false);
            /* collision check here */
            BlockState blockinfo = source.getWorld().getBlockState(new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)));
            if(!blockinfo.isAir()) {
                HitorEndOfRange = true;
                break;
            }
        }

        if(HitorEndOfRange){
            source.sendFeedback(Text.literal("hit!"),false);
            return null;
        }

        if(tick>0) {
            int finalStep = step;
            source.sendFeedback(Text.literal(String.valueOf(tick)),false);
            Scheduler.INSTANCE.submit(server -> {
                Projectile.main(source,particle,new Vec3d(pos.getX() + (dx* finalStep),pos.getY() + (dy* finalStep),pos.getZ() + (dz* finalStep)),rotation,range,tick-1);
            },1);
        }
        /*
        if(tick > 0){
            for(int t = 0; t < tick; t++) {
                int time = t;
                Scheduler.INSTANCE.submit(server -> {
                    Projectile.main(source,particle,new Vec3d(),rotation,range,tick)
                    double x = pos.getX() + (dx * time * step);
                    double y = pos.getY() + (dy * time * step);
                    double z = pos.getZ() + (dz * time * step);
                    for (int i = 0; i < step; i++) {
                        x += dx;
                        y += dy;
                        z += dz;
                        Draw.main(source, particle, x,y,z, false);
                        BlockState blockinfo = source.getWorld().getBlockState(new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)));
                        if(!blockinfo.isAir()) break;
                        //source.sendFeedback(Text.literal(String.valueOf(blockinfo)).append(String.valueOf(Math.floor(x))).append(String.valueOf(Math.floor(y))).append(String.valueOf(Math.floor(z))),false);

                    }
                },t);
            }
        }*/
        return null;
    }
}
