package com.simibubi.create.content.contraptions.components.actors;

import com.simibubi.create.foundation.render.backend.gl.attrib.VertexFormat;
import com.simibubi.create.foundation.render.backend.instancing.InstanceData;
import com.simibubi.create.foundation.render.backend.instancing.InstancedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

import java.nio.ByteBuffer;

public class ContraptionActorData extends InstanceData {
    public static VertexFormat FORMAT = VertexFormat.builder()
                                                    .addAttributes(ActorVertexAttributes.class)
                                                    .build();

    private float x;
    private float y;
    private float z;
    private byte blockLight;
    private byte skyLight;
    private float rotationOffset;
    private byte rotationAxisX;
    private byte rotationAxisY;
    private byte rotationAxisZ;
    private float qX;
    private float qY;
    private float qZ;
    private float qW;
    private byte rotationCenterX = 64;
    private byte rotationCenterY = 64;
    private byte rotationCenterZ = 64;

    private float speed;

    protected ContraptionActorData(InstancedModel<?> owner) {
        super(owner);
    }


    public ContraptionActorData setPosition(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        return this;
    }

    public ContraptionActorData setBlockLight(int blockLight) {
        this.blockLight = (byte) ((blockLight & 0xF) << 4);
        return this;
    }

    public ContraptionActorData setSkyLight(int skyLight) {
        this.skyLight = (byte) ((skyLight & 0xF) << 4);
        return this;
    }

    public ContraptionActorData setRotationOffset(float rotationOffset) {
        this.rotationOffset = rotationOffset;
        return this;
    }

    public ContraptionActorData setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public ContraptionActorData setRotationAxis(Vector3f axis) {
        setRotationAxis(axis.getX(), axis.getY(), axis.getZ());
        return this;
    }

    public ContraptionActorData setRotationAxis(float rotationAxisX, float rotationAxisY, float rotationAxisZ) {
        this.rotationAxisX = (byte) (rotationAxisX * 127);
        this.rotationAxisY = (byte) (rotationAxisY * 127);
        this.rotationAxisZ = (byte) (rotationAxisZ * 127);
        return this;
    }

    public ContraptionActorData setRotationCenter(Vector3f axis) {
        setRotationCenter(axis.getX(), axis.getY(), axis.getZ());
        return this;
    }

    public ContraptionActorData setRotationCenter(float rotationCenterX, float rotationCenterY, float rotationCenterZ) {
        this.rotationCenterX = (byte) (rotationCenterX * 127);
        this.rotationCenterY = (byte) (rotationCenterY * 127);
        this.rotationCenterZ = (byte) (rotationCenterZ * 127);
        return this;
    }

    public ContraptionActorData setLocalRotation(Quaternion q) {
        this.qX = q.getX();
        this.qY = q.getY();
        this.qZ = q.getZ();
        this.qW = q.getW();
        return this;
    }

    @Override
    public void write(ByteBuffer buf) {
        putVec3(buf, x, y, z);
        putVec2(buf, blockLight, skyLight);
        put(buf, rotationOffset);
        putVec3(buf, rotationAxisX, rotationAxisY, rotationAxisZ);
        putVec4(buf, qX, qY, qZ, qW);
        putVec3(buf, rotationCenterX, rotationCenterY, rotationCenterZ);
        put(buf, speed);

    }
}