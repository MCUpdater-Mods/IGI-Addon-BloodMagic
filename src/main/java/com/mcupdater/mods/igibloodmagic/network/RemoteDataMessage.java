package com.mcupdater.mods.igibloodmagic.network;

import WayofTime.bloodmagic.api.saving.SoulNetwork;
import WayofTime.bloodmagic.api.util.helper.NetworkHelper;
import com.mcupdater.mods.igibloodmagic.IGIBloodMagic;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class RemoteDataMessage implements IMessage {

	public RemoteDataMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<RemoteDataMessage, ResponseMessage> {

		public Handler() {
		}

		@Override
		public ResponseMessage onMessage(RemoteDataMessage message, MessageContext ctx) {
			final NBTTagCompound data = new NBTTagCompound();
			final EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			IThreadListener mainThread = (WorldServer) player.worldObj;
			mainThread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					try {
						SoulNetwork soulNet = NetworkHelper.getSoulNetwork(player);
						data.setInteger("CurrentLP", soulNet.getCurrentEssence());
						data.setInteger("OrbTier", soulNet.getOrbTier());
						ResponseMessage response = new ResponseMessage(data);
						IGIBloodMagic.network.sendTo(response, player);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return null;
		}
	}

}
