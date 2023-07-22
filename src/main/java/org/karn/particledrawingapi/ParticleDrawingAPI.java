package org.karn.particledrawingapi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.karn.particledrawingapi.command.example;


public class ParticleDrawingAPI implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess, ignored1) -> {
            example.register(dispatcher, commandRegistryAccess);
        });
    }
}
