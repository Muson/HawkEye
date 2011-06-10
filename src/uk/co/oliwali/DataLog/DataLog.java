package uk.co.oliwali.DataLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.oliwali.DataLog.commands.BaseCommand;
import uk.co.oliwali.DataLog.commands.HelpCommand;
import uk.co.oliwali.DataLog.commands.HereCommand;
import uk.co.oliwali.DataLog.commands.PageCommand;
import uk.co.oliwali.DataLog.commands.RollbackCommand;
import uk.co.oliwali.DataLog.commands.SearchCommand;
import uk.co.oliwali.DataLog.commands.SearchHelpCommand;
import uk.co.oliwali.DataLog.commands.ToolCommand;
import uk.co.oliwali.DataLog.commands.TptoCommand;
import uk.co.oliwali.DataLog.commands.UndoCommand;
import uk.co.oliwali.DataLog.database.DataManager;
import uk.co.oliwali.DataLog.listeners.DLBlockListener;
import uk.co.oliwali.DataLog.listeners.DLEntityListener;
import uk.co.oliwali.DataLog.listeners.DLPlayerListener;
import uk.co.oliwali.DataLog.util.Config;
import uk.co.oliwali.DataLog.util.Permission;
import uk.co.oliwali.DataLog.util.Util;

public class DataLog extends JavaPlugin {
	
	public String name;
	public String version;
	public Config config;
	public static Server server;
	public static final Logger log = Logger.getLogger("Minecraft");
	public DLBlockListener blockListener = new DLBlockListener(this);
	public DLEntityListener entityListener = new DLEntityListener(this);
	public DLPlayerListener playerListener = new DLPlayerListener(this);
	public static List<BaseCommand> commands = new ArrayList<BaseCommand>();
	public static HashMap<CommandSender, PlayerSession> playerSessions = new HashMap<CommandSender, PlayerSession>();
	
	public void onDisable() {
		Util.info("Version " + version + " disabled!");
	}
	
	public void onEnable() {

		//Set up config and database
        PluginManager pm = getServer().getPluginManager();
		server = getServer();
		name = this.getDescription().getName();
        version = this.getDescription().getVersion();
        config = new Config(this);
        try {
			new DataManager(this);
		} catch (Exception e) {
			Util.severe("Error initiating DataLog database connection, disabling plugin");
			pm.disablePlugin(this);
			return;
		}
        new Permission(this);
        
        // Register events
        pm.registerEvent(Type.BLOCK_BREAK, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.BLOCK_PLACE, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.BLOCK_BURN, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.BLOCK_PHYSICS, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.SNOW_FORM, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.SIGN_CHANGE, blockListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_CHAT, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_JOIN, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_QUIT, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_TELEPORT, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.PLAYER_INTERACT, playerListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.ENTITY_DAMAGE, entityListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.ENTITY_DEATH, entityListener, Event.Priority.Monitor, this);
        pm.registerEvent(Type.ENTITY_EXPLODE, entityListener, Event.Priority.Monitor, this);
        
        //Add commands
        commands.add(new HelpCommand());
        commands.add(new SearchCommand());
        commands.add(new PageCommand());
        commands.add(new TptoCommand());
        commands.add(new SearchHelpCommand());
        commands.add(new HereCommand());
        commands.add(new RollbackCommand());
        commands.add(new UndoCommand());
        commands.add(new ToolCommand());
        
        Util.info("Version " + version + " enabled!");
        
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		if (cmd.getName().equalsIgnoreCase("datalog")) {
			if (args.length == 0)
				args = new String[]{"help"};
			BaseCommand help = null;
			for (BaseCommand command : commands.toArray(new BaseCommand[0])) {
				if (command.name.equalsIgnoreCase("help"))
					help = command;
				if (command.name.equalsIgnoreCase(args[0]))
					return command.run(sender, args, commandLabel);
			}
			return help.run(sender, args, commandLabel);
		}
		return false;
	}

}
