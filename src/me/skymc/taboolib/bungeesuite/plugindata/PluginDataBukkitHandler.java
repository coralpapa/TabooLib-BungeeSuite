package me.skymc.taboolib.bungeesuite.plugindata;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannelTask;
import me.skymc.taboolib.bungeesuite.runable.TChannelResult;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.util.ByteUtils;

/**
 * @author Bkm016
 * @since 2018-04-19
 */
public class PluginDataBukkitHandler {
	
	@Getter
	private TBukkitChannel channel;
	
	public PluginDataBukkitHandler(TBukkitChannel channel) {
		this.channel = channel;
	}
	
	public void create(TChannelResult result, String filename) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PluginData", "Create", filename)
			.result(result)
			.run();
	}
	
	public void reload(TChannelResult result, String filename) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PluginData", "Reload", filename)
			.result(result)
			.run();
	}
	
	public void delete(TChannelResult result, String filename) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command("PluginData", "Delete", filename)
			.result(result)
			.run();
	}
	
	public void set(String filename, String... args) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command(ArrayUtils.addFirst(ByteUtils.encode(args), "PluginData", "Set", filename))
			.run();	
	}
	
	public void get(TChannelResult result, String filename, String... args) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command(ArrayUtils.addFirst(ByteUtils.encode(args), "PluginData", "Get", filename))
			.result(result)
			.run();
	}
	
	public void getList(TChannelResult result, String filename, String... args) {
		TBukkitChannelTask.createTask()
			.channel(channel)
			.sender(channel.getOnlinePlayer())
			.command(ArrayUtils.addFirst(ByteUtils.encode(args), "PluginData", "GetList", filename))
			.result(result)
			.run();
	}
}