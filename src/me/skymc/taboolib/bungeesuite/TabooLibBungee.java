package me.skymc.taboolib.bungeesuite;

import me.skymc.taboolib.bungeesuite.bungee.TBungeeChannel;
import me.skymc.taboolib.bungeesuite.bungee.command.BungeeCommand;
import me.skymc.taboolib.bungeesuite.bungee.module.ModuleBungeeCord;
import me.skymc.taboolib.bungeesuite.enums.ServerType;
import me.skymc.taboolib.bungeesuite.listener.ListenerBungeeMessage;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.permission.PermissionBungeeHandler;
import me.skymc.taboolib.bungeesuite.playerdata.PlayerDataBungeeHandler;
import me.skymc.taboolib.bungeesuite.plugindata.PluginDataBungeeHandler;
import me.skymc.taboolib.bungeesuite.util.TagUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TabooLibBungee extends Plugin {
	
	private static TabooLibBungee instance;
	private ProxyServer proxyServer;
	private TBungeeChannel bungeeChannel;
	private PlayerDataBungeeHandler playerDataHandler;
	private PermissionBungeeHandler permissionHandler;
	private PluginDataBungeeHandler pluginDataHandler;

	public static TabooLibBungee getInstance() {
		return TabooLibBungee.instance;
	}

	@Override
	public void onLoad() {
		ServerType.setServerType(ServerType.BUNGEECORD);
	}
	
	@Override
	public void onEnable() {
		instance = this;
		proxyServer = ProxyServer.getInstance();
		bungeeChannel = new TBungeeChannel(this);
		playerDataHandler = new PlayerDataBungeeHandler(this);
		permissionHandler = new PermissionBungeeHandler(bungeeChannel);
		pluginDataHandler = new PluginDataBungeeHandler(this);
		
		TagUtils.getInst();
		
		proxyServer.registerChannel("taboolib:in");
		proxyServer.registerChannel("taboolib:out");
		
		proxyServer.getPluginManager().registerCommand(this, new BungeeCommand("taboolibbungeecord"));
		
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ListenerBungeeMessage());
		BungeeCord.getInstance().getPluginManager().registerListener(this, new ModuleBungeeCord());
		
		TLogger.info("插件已载入");
		TLogger.info("作者: &8" + getDescription().getAuthor());
		TLogger.info("版本: &8" + getDescription().getVersion());
		TLogger.info("框架: &8" + ServerType.getServerType());
	}
	
	@Override
	public void onDisable() {
		proxyServer.getScheduler().cancel(this);
		pluginDataHandler.saveFile();
	}

	public ProxyServer getProxyServer() {
		return this.proxyServer;
	}

	public TBungeeChannel getBungeeChannel() {
		return this.bungeeChannel;
	}

	public PlayerDataBungeeHandler getPlayerDataHandler() {
		return this.playerDataHandler;
	}

	public PermissionBungeeHandler getPermissionHandler() {
		return this.permissionHandler;
	}

	public PluginDataBungeeHandler getPluginDataHandler() {
		return this.pluginDataHandler;
	}
}
