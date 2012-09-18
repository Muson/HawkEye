package uk.co.oliwali.HawkEye;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Chest;
import org.bukkit.block.Dispenser;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import uk.co.oliwali.HawkEye.database.DataManager;
import uk.co.oliwali.HawkEye.entry.ContainerEntry;
import uk.co.oliwali.HawkEye.util.InventoryUtil;
import uk.co.oliwali.HawkEye.util.Util;

/**
 * Contains methods for managing container access
 *
 * @author oliverw92
 */
public class ContainerAccessManager {

    private final List<ContainerAccess> accessList = new ArrayList<ContainerAccess>();

    /**
     * Checks whether the player's inventory was open and should now trigger a
     * container transaction
     *
     * @param player player to check
     */
    public void checkInventoryClose(Player player) {

        //Get access from list
        ContainerAccess access = null;
        for (ContainerAccess acc : accessList) {
            if (acc.player == player) {
                access = acc;
            }
        }

        //If no access, return
        if (access == null) {
            return;
        }

        //Get current inventory, create diff string and add the database
        HashMap<String, Integer> after = InventoryUtil.compressInventory(access.container.getInventory().getContents());
        String diff = InventoryUtil.createDifferenceString(access.beforeInv, after);
        if (diff.length() > 1) {
            DataManager.addEntry(new ContainerEntry(player, access.loc, diff));
        }
        accessList.remove(access);

    }

    /**
     * 'Sets' the player inventory to be open and stores the current contents of
     * the container
     *
     * @param player player to check
     * @param inventory container to store
     */
    public void checkInventoryOpen(Player player, Inventory inventory) {
        InventoryHolder container = inventory.getHolder();
        Location loc = null;

        if (container instanceof Chest) {
            loc = ((Chest) container).getLocation();
        } else if (container instanceof Furnace) {
            loc = ((Furnace) container).getLocation();
        } else if (container instanceof BrewingStand) {
            loc = ((BrewingStand) container).getLocation();
        } else if (container instanceof Dispenser) {
            loc = ((Dispenser) container).getLocation();
        } else if (container instanceof DoubleChest) {
            loc = ((DoubleChest) container).getLocation();
        } else {
            Util.debug("Opened unknown container " + container);
            return;
        }
        
        // Check block coordinates for container
        // Must be done for double chests
        loc.setX(loc.getBlockX());
        loc.setY(loc.getBlockY());
        loc.setZ(loc.getBlockZ());

        accessList.add(new ContainerAccess(container, player, InventoryUtil.compressInventory(container.getInventory().getContents()), loc));
    }

    /**
     * Class representing a container access
     *
     * @author oliverw92
     */
    public class ContainerAccess {

        public InventoryHolder container;
        public Player player;
        public HashMap<String, Integer> beforeInv;
        public Location loc;

        public ContainerAccess(InventoryHolder container, Player player, HashMap<String, Integer> beforeInv, Location loc) {
            this.container = container;
            this.player = player;
            this.beforeInv = beforeInv;
            this.loc = loc;
        }
    }
}
