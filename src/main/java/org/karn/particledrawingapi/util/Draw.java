package org.karn.particledrawingapi.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Draw {

    public static void DotArrayDraw(ServerWorld world, ParticleEffect Particle, List<Map<String, Double>> array) {
        DotArrayDraw(world, Particle, array, array.size());
    }
    public static void DotArrayDraw(ServerWorld world, ParticleEffect Particle, List<Map<String, Double>> array, Integer step) {
        for (int i = 0; i < step; i++) {
            Map<String, Double> Dot = array.get(0);
            ParticleDraw(world, Particle, Dot.get("x"), Dot.get("y"), Dot.get("z"));
            array.remove(0);
            if (array.size() < 1) {
                return;
            }
        }
        if (array.size() > 0) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // DotArrayDraw 함수 다시 호출
                    DotArrayDraw(world, Particle, array, step);
                }
            };
            int delay = 50; // 0.05초 뒤에 실행
            timer.schedule(task, delay);
        }
    }

    public static void ParticleDraw(ServerWorld world, ParticleEffect Particle, double x, double y, double z) {
        ParticleDraw(world, Particle, x, y, z, false);
    }

    public static void ParticleDraw(ServerWorld world, ParticleEffect Particle, double x, double y, double z, boolean force) {
        for(int j = 0; j < world.getPlayers().size(); ++j) {
            ServerPlayerEntity player = world.getPlayers().get(j);
            world.spawnParticles(player, Particle, force, x, y, z, 0, 0, 0, 0, 1);
        }

    }
}
