package org.karn.particledrawingapi.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.*;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;

import java.util.List;
import java.util.Map;

import static net.minecraft.server.command.CommandManager.literal;
import static org.karn.particledrawingapi.shape.Arc.ArcDots;
import static org.karn.particledrawingapi.shape.Arc.CircleDots;
import static org.karn.particledrawingapi.shape.Cubic.CubeDots;
import static org.karn.particledrawingapi.shape.Line.LineDots;
import static org.karn.particledrawingapi.shape.Sphere.sphereDots;
import static org.karn.particledrawingapi.shape.Sprial.SpiralDots;
import static org.karn.particledrawingapi.util.Draw.DotArrayDraw;
import static org.karn.particledrawingapi.util.Rotate.RotateDotArray;

public class example {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("particleDraw")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("particle", ParticleEffectArgumentType.particleEffect(registryAccess))
                        .then(CommandManager.literal("line")
                                .then(CommandManager.argument("pos1", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("pos2", Vec3ArgumentType.vec3())
                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                        .executes(ctx -> {
                                                            List<Map<String, Double>> Array = LineDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                            return 1;
                                                        })
                                                        .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> Array = LineDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                    return 1;
                                                                })
                                                                .then(CommandManager.argument("RotationAngle", Vec2ArgumentType.vec2())
                                                                        .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> Array = LineDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                                    PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                    Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                    List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                    return 1;
                                                                                })
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(CommandManager.literal("circle")
                                .then(CommandManager.argument("center", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("radius", DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                        .executes(ctx -> {
                                                            List<Map<String, Double>> Array = CircleDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"));
                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                            return 1;
                                                        })
                                                        .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> Array = CircleDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                    return 1;
                                                                })
                                                                .then(CommandManager.argument("RotationAngle", Vec2ArgumentType.vec2())
                                                                        .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> Array = CircleDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                                    PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                    Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                    List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                    return 1;
                                                                                })
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(CommandManager.literal("arc")
                                .then(CommandManager.argument("center", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("radius", DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                        .then(CommandManager.argument("startAngle", DoubleArgumentType.doubleArg(0))
                                                                .then(CommandManager.argument("endAngle", DoubleArgumentType.doubleArg(0,360))
                                                                        .executes(ctx -> {
                                                                            List<Map<String, Double>> Array = ArcDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"), DoubleArgumentType.getDouble(ctx,"startAngle"), DoubleArgumentType.getDouble(ctx,"endAngle"));
                                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                                            return 1;
                                                                        })
                                                                        .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> Array = ArcDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"), DoubleArgumentType.getDouble(ctx,"startAngle"), DoubleArgumentType.getDouble(ctx,"endAngle"));
                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                    return 1;
                                                                                })
                                                                                .then(CommandManager.argument("RotationAngle", Vec2ArgumentType.vec2())
                                                                                        .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                                .executes(ctx ->{
                                                                                                    List<Map<String, Double>> Array = ArcDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), IntegerArgumentType.getInteger(ctx, "number"), DoubleArgumentType.getDouble(ctx,"startAngle"), DoubleArgumentType.getDouble(ctx,"endAngle"));
                                                                                                    PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                                    Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                                    List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                                    return 1;
                                                                                                })
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(CommandManager.literal("spiral")
                                .then(CommandManager.argument("center", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("radius", DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("height", DoubleArgumentType.doubleArg())
                                                        .then(CommandManager.argument("rotation", IntegerArgumentType.integer(1))
                                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                                        .executes(ctx -> {
                                                                            List<Map<String, Double>> Array = SpiralDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                                            return 1;
                                                                        })
                                                                        .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> Array = SpiralDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                    return 1;
                                                                                })
                                                                                .then(CommandManager.argument("RotationAngle", RotationArgumentType.rotation())
                                                                                        .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                                .executes(ctx ->{
                                                                                                    List<Map<String, Double>> Array = SpiralDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx, "radius"), DoubleArgumentType.getDouble(ctx, "height"), IntegerArgumentType.getInteger(ctx, "rotation"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                                                    PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                                    Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                                    List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                                    return 1;
                                                                                                })
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(CommandManager.literal("cubic")
                                .then(CommandManager.argument("pos1", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("pos2", Vec3ArgumentType.vec3())
                                                .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                        .executes(ctx -> {
                                                            List<Map<String, Double>> Array = CubeDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                            return 1;
                                                        })
                                                        .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                .executes(ctx ->{
                                                                    List<Map<String, Double>> Array = CubeDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                    return 1;
                                                                })
                                                                .then(CommandManager.argument("RotationAngle", Vec2ArgumentType.vec2())
                                                                        .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                .executes(ctx ->{
                                                                                    List<Map<String, Double>> Array = CubeDots(Vec3ArgumentType.getVec3(ctx, "pos1"), Vec3ArgumentType.getVec3(ctx, "pos2"), IntegerArgumentType.getInteger(ctx, "number"));
                                                                                    PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                    Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                    List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                    return 1;
                                                                                })
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                        .then(CommandManager.literal("sphere")
                                .then(CommandManager.argument("center", Vec3ArgumentType.vec3())
                                        .then(CommandManager.argument("radius",DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("density", IntegerArgumentType.integer(1))
                                                        .then(CommandManager.argument("number", IntegerArgumentType.integer(1))
                                                                .executes(ctx -> {
                                                                    List<Map<String, Double>> Array = sphereDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx, "density"),IntegerArgumentType.getInteger(ctx, "number"));
                                                                    DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array);
                                                                    return 1;
                                                                })
                                                                .then(CommandManager.argument("NumberPerTick", IntegerArgumentType.integer(1))
                                                                        .executes(ctx ->{
                                                                            List<Map<String, Double>> Array = sphereDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx, "density"),IntegerArgumentType.getInteger(ctx, "number"));
                                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), Array, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                            return 1;
                                                                        })
                                                                        .then(CommandManager.argument("RotationAngle", Vec2ArgumentType.vec2())
                                                                                .then(CommandManager.argument("RotationPos", Vec3ArgumentType.vec3())
                                                                                        .executes(ctx ->{
                                                                                            List<Map<String, Double>> Array = sphereDots(Vec3ArgumentType.getVec3(ctx, "center"), DoubleArgumentType.getDouble(ctx,"radius"),IntegerArgumentType.getInteger(ctx, "density"),IntegerArgumentType.getInteger(ctx, "number"));
                                                                                            PosArgument Angle = RotationArgumentType.getRotation(ctx,"RotationAngle");
                                                                                            Vec2f rot = new Vec2f(Angle.toAbsoluteRotation(ctx.getSource()).x+90, Angle.toAbsoluteRotation(ctx.getSource()).y*-1);
                                                                                            List<Map<String, Double>> RotatedArray = RotateDotArray(Array, rot, Vec3ArgumentType.getVec3(ctx, "RotationPos"));
                                                                                            DotArrayDraw(ctx.getSource().getWorld(), ParticleEffectArgumentType.getParticle(ctx, "particle"), RotatedArray, IntegerArgumentType.getInteger(ctx, "NumberPerTick"));
                                                                                            return 1;
                                                                                        })
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }
}
