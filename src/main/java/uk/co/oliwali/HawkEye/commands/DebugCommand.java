/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oliwali.HawkEye.commands;

import uk.co.oliwali.HawkEye.util.Config;
import uk.co.oliwali.HawkEye.util.Permission;
import uk.co.oliwali.HawkEye.util.Util;

/**
 *
 * @author pasha
 */
public class DebugCommand extends BaseCommand {
    
    public DebugCommand() {
        name = "debug";
        argLength = 1;
        usage = "toggle <- toggles debug mode on/off";
        bePlayer = false;
    }

    @Override
    public boolean execute() {
        Util.info("args="+args.get(0));
        if (args.get(0).compareToIgnoreCase("toggle")==0) {
            Config.Debug = !Config.Debug;
            Util.sendMessage(sender, "&7HawkEye debug is &c"+(Config.Debug?"ON":"OFF"));
            
            return true;
        }
        return false;
    }

    @Override
    public boolean permission() {
        return Permission.debug(sender);
    }

    @Override
    public void moreHelp() {
        Util.sendMessage(sender, "&cToggles debug mode for HawkEye to on/off state. Debug state does not saving to config!");
    }
    
}
