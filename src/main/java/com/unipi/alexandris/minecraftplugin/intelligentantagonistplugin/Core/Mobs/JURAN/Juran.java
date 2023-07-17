package com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.JURAN;

import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.JURAN.Enums.JuranEmotions;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.JURAN.Enums.JuranStates;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mob;
import com.unipi.alexandris.minecraftplugin.intelligentantagonistplugin.Core.Mobs.Mobs;
import io.lumine.mythic.bukkit.MythicBukkit;

public class Juran extends Mob {


    public Juran() {
        super(Mobs.JURAN, JuranStates.Idle, JuranEmotions.Calm,
                MythicBukkit.inst().getMobManager().getMythicMob("Juran").orElse(null));
    }
}
