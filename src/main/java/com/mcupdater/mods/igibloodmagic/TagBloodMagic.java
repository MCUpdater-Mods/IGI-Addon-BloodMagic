package com.mcupdater.mods.igibloodmagic;

import WayofTime.bloodmagic.api.util.helper.NetworkHelper;
import com.github.lunatrius.ingameinfo.tag.Tag;
import com.github.lunatrius.ingameinfo.tag.registry.TagRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public abstract class TagBloodMagic extends Tag
{
	@Override
	public String getCategory() {
		return "bloodmagic";
	}

	public static class CurrentLP extends TagBloodMagic {
		@Override
		public String getValue() {
			try {
				return String.valueOf(NetworkHelper.getSoulNetwork(player).getCurrentEssence());
			} catch (Throwable e) {
				log(this, e);
			}
			return "-1";
		}
	}

	public static class OrbTier extends TagBloodMagic {
		@Override
		public String getValue() {
			try {
				return String.valueOf(NetworkHelper.getSoulNetwork(player).getOrbTier());
			} catch (Throwable e) {
				log(this, e);
			}
			return "-1";
		}
	}


	public static void register() {
		TagRegistry.INSTANCE.register(new CurrentLP().setName("bmcurrentlp"));
		TagRegistry.INSTANCE.register(new OrbTier().setName("bmorbtier"));
	}

	public void log(Tag tag, Throwable ex) {
		FMLCommonHandler.instance().getFMLLogger().warn(IGIBloodMagic.metadata.modId + ":" + tag.getName(), ex);
	}
}
