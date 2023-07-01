package org.karn.particledrawingapi.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class Draw {
    public static void main(ServerCommandSource source, ParticleEffect particle, double x, double y, double z, boolean force){
        for(int j = 0; j < source.getWorld().getPlayers().size(); ++j) {
            ServerPlayerEntity player = source.getWorld().getPlayers().get(j);
            source.getWorld().spawnParticles(player, particle, force, x, y, z, 0, 0, 0,0,1);
        }
    }
}
