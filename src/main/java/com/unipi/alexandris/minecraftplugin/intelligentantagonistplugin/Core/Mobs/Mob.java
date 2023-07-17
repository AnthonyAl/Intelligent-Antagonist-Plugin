package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class Mob {

    protected State currentState;

    protected Emotion currentEmotion;

    protected Mobs mobType;

    protected MythicMob entity;

    protected ActiveMob mob;

    protected Entity activeEntity;

    public Mob(Mobs mobType, State currentState, Emotion currentEmotion, MythicMob entity) {
        this.mobType = mobType;
        this.currentEmotion = currentEmotion;
        this.currentState = currentState;
        this.entity = entity;
    }

    public void spawn(Location location) {
        if(entity != null) {
            // spawn mob
            mob = entity.spawn(BukkitAdapter.adapt(location), 1);

            // get mob as bukkit entity
            activeEntity = mob.getEntity().getBukkitEntity();
        }
    }

    public void kill() {
        if(mob != null) {
            mob.despawn();
        }
    }

    public Mobs getMobType() {
        return mobType;
    }

}
