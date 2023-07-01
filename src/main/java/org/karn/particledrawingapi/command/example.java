package org.karn.particledrawingapi.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.karn.particledrawingapi.shape.Line;
import org.karn.particledrawingapi.util.Draw;

import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class example {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("pdapi")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("particle", ParticleEffectArgumentType.particleEffect(registryAccess))
                        .then(CommandManager.literal("line")
                                .then(CommandManager.argument("pos1", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("pos2", Vec3ArgumentType.vec3())
                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                        .executes(ctx -> {
                                                            Line.main(ctx.getSource(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx,"pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
                        .executes(ctx -> {
                            ctx.getSource().sendMessage(Text.literal("asd"));
                            ServerPlayerEntity player = ctx.getSource().getPlayer();
                            Draw.main(ctx.getSource(), ParticleEffectArgumentType.getParticle(ctx,"particle"), player.getX(), player.getY(), player.getZ(), false);
                            return 1;
                        })
                )
        );
    }
}
