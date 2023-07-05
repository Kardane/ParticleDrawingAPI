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

        int blockX = (int) Math.floor(x);
        int blockY = (int) Math.floor(y);
        int blockZ = (int) Math.floor(z);

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
            if((int) Math.floor(x) != blockX) blockX = (int) Math.floor(x);
            if((int) Math.floor(y) != blockY) blockY = (int) Math.floor(y);
            if((int) Math.floor(z) != blockZ) blockZ = (int) Math.floor(z);
            BlockState blockinfo = source.getWorld().getBlockState(new BlockPos(blockX, blockY, blockZ));
            if(!blockinfo.isAir()) {
                source.sendFeedback(Text.literal(String.valueOf(blockinfo)),false);
                HitorEndOfRange = true;
                break;
            }
        }

        if(HitorEndOfRange){
            return null;
        }

        if(tick>0) {
            int finalStep = step;
            source.sendFeedback(Text.literal(String.valueOf(tick)),false);
            Scheduler.INSTANCE.submit(server -> {
                Projectile.main(source,particle,new Vec3d(pos.getX() + (dx* finalStep),pos.getY() + (dy* finalStep),pos.getZ() + (dz* finalStep)),rotation,range,tick-1);
            },1);
        }
        return null;
    }
}
