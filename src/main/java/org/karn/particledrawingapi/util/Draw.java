package org.karn.particledrawingapi.util;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Consumer;

public class Draw {
    public static Consumer<MinecraftServer> main(ServerCommandSource source, ParticleEffect particle, double x, double y, double z, boolean force){
        for(int j = 0; j < source.getWorld().getPlayers().size(); ++j) {
            ServerPlayerEntity player = source.getWorld().getPlayers().get(j);
            if(particle == ParticleTypes.DUST || particle == ParticleTypes.DUST_COLOR_TRANSITION){
                //source.getWorld().spawnParticles(player, particle, force, x, y, z, 0, 0, -10,0,1);
                Packet<?> packet = new ParticleS2CPacket(particle, force, x, y, z, 0, -10, 0, 1, 0);
                source.getWorld().sendToPlayerIfNearby(player, force, x, y, z, packet);
            } else {
                Packet<?> packet = new ParticleS2CPacket(particle, force, x, y, z, 0, 0, 0, 1, 0);
                source.getWorld().sendToPlayerIfNearby(player, force, x, y, z, packet);
            }

        }
        return null;
    }
}
