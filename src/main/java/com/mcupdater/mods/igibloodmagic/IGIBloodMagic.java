package com.mcupdater.mods.igibloodmagic;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(useMetadata = true, modid = "IGI|BloodMagicIntegration")
public class IGIBloodMagic
{
	public static ModMetadata metadata;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		metadata = event.getModMetadata();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT) {
			return;
		}

		if (Loader.isModLoaded("BloodMagic")) {
			TagBloodMagic.register();
		}
	}
}
