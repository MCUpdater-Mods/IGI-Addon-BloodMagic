package com.mcupdater.mods.igibloodmagic;

import com.mcupdater.mods.igibloodmagic.network.RemoteDataMessage;
import com.mcupdater.mods.igibloodmagic.network.ResponseMessage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(useMetadata = true, modid = "igi|bloodmagicintegration")
public class IGIBloodMagic
{
	public static ModMetadata metadata;
	public static SimpleNetworkWrapper network;
	public static NBTTagCompound cachedData =  new NBTTagCompound();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		metadata = event.getModMetadata();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("igi-bloodmagic");
		network.registerMessage(ResponseMessage.ResponseHandler.class, ResponseMessage.class, 0, Side.CLIENT);
		network.registerMessage(RemoteDataMessage.Handler.class, RemoteDataMessage.class, 1, Side.SERVER);
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
