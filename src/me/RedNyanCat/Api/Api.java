package me.RedNyanCat.Api;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.mysql.fabric.xmlrpc.base.Array;

import java.util.List;
import java.util.UUID;

public class Api {

	//BOOLEAN API main class

	public static boolean isOnline(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		if(p.isOnline()){

			return true;

		} else {

			return false;

		}

	}

	public static boolean kickPlayer(UUID player, String reason){

		Player p = Bukkit.getServer().getPlayer(player);

		if(isOnline(player)){

			p.kickPlayer(reason);

			return true;

		} else {

			return false;

		}

	}

	public static boolean isFlying(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		if((p.isFlying()) && (!p.isOnGround())){

			return true;

		} else {

			return false;

		}

	}

	public static boolean setFlying(UUID player, boolean b){

		Player p = Bukkit.getServer().getPlayer(player);

		p.setFlying(b);

		return true;

	}

	public static boolean isSneaking(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		if(p.isSneaking()){

			return true;

		} else {

			return false;

		}

	}

	public static boolean setFlySpeed(UUID player, float speed){

		if(speed > 0.99f){

			return false;

		} else {

			Player p = Bukkit.getServer().getPlayer(player);

			p.setFlySpeed(speed);

			return true;

		}

	}

	public static boolean sendMsg(UUID player, String msg){

		Player p = Bukkit.getServer().getPlayer(player);

		p.sendMessage(msg);

		return true;

	}

	public static boolean announcePerm(String msg, String permission){

		for (Player p : Bukkit.getServer().getOnlinePlayers()){

			if(p.hasPermission(permission)){

				p.sendMessage(msg);

			} 

		}

		return true;

	}

	public static boolean announce(String msg){

		for (Player p : Bukkit.getServer().getOnlinePlayers()){

			p.sendMessage(msg);

		}

		return true;

	}

	public static int allPOnline(){

		int i = 0;

		for (@SuppressWarnings("unused") Player p : Bukkit.getServer().getOnlinePlayers()) {

			i++;

		}

		return i;

	}

	public static boolean getBoolean(boolean bool){

		return bool;

	}

	public static boolean killPlayer(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		if(isOnline(player)){

			if(p.getHealth() > 0.0){

				p.setHealth(0.0);

				return true;

			} else {

				return false;

			}

		} else {

			return false;

		}

	}

	public static boolean invertBool(boolean bool){

		return !bool;

	}

	public static boolean longBool(long l, int bit){

		return (l >> bit) % 2 == 1;

	}

	public static boolean intsToBool(int i, int j){

		boolean b = (i == j);

		return b;

	}

	public static boolean serverOnline(){

		if(Bukkit.getServer().getOnlineMode() == true){

			return true;

		} else {

			return false;

		}

	}

	public static boolean inVehicle(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		if(p.isInsideVehicle()){

			return true;

		} else {

			return false;

		}

	}

	public static boolean closeInv(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		p.closeInventory();

		return true;


	}

	public static boolean addEffects(UUID player, PotionEffect[] effects, boolean[] args){

		Player p = Bukkit.getServer().getPlayer(player);

		if(effects == null || args == null){

			return false;

		}

		for(int j = 0; j < effects.length; j++){

			p.addPotionEffect(effects[j], args[j]);

		}

		return true;

	}

	public static boolean summonEntity(Location loc, int amount, EntityType entity, World world){

		EntityType e = EntityType.FALLING_BLOCK;

		try{

			e = entity;

		} catch (IllegalArgumentException ex){

			ex.printStackTrace();

			return false;

		}

		int i = 0;

		for (i = 0; i < amount; i++){

			loc.getWorld().spawnEntity(loc, e);

		}

		return true;

	}

	public static boolean itemStackCreation(Material mat, int amount, List lore, String name, short durability, boolean unbreakable, int elevel, boolean unsafe, Enchantment[] enchantments, UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		try{

			ItemStack i = new ItemStack(mat, amount);
			i.setDurability(durability);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName(name);

			if(lore != null){

				m.setLore(lore);

			}

			m.setLocalizedName(name);
			m.setUnbreakable(unbreakable);

			if(enchantments == null){

				i.setItemMeta(m);

				p.getInventory().addItem(i);

				return true;

			} else {

				for(int j = 0; j < enchantments.length; j++){

					m.addEnchant(enchantments[j], elevel, unsafe);

				}

			}

			i.setItemMeta(m);

			p.getInventory().addItem(i);

			return true;

		} catch (NullPointerException e){

			e.printStackTrace();

			return false;

		}

	}

	public static boolean clearInv(UUID player){

		Player p = Bukkit.getServer().getPlayer(player);

		p.getInventory().clear();

		return true;

	}

	public static boolean setHealth(UUID player, double amount){

		Player p = Bukkit.getServer().getPlayer(player);

		if(p.getMaxHealth() == amount){

			return false;

		} else {

			p.setMaxHealth(amount);

			p.setHealth(amount);

			return true;

		}

	}

	public static boolean setHunger(UUID player, int amount){

		Player p = Bukkit.getServer().getPlayer(player);

		p.setFoodLevel(amount);

		return true;

	}

	public static String convertColorCodes(String msg){

		return ChatColor.translateAlternateColorCodes('&', msg);

	}

	public static String addArgs(String[] args){

		StringBuilder s = new StringBuilder();

		for (int i = 0; i < args.length; i++){

			if(i == args.length){

				s.append(args[i]);

			} else {

				s.append(args[i]).append(" ");

			}

		}

		String msg = s.toString();

		return msg;

	}

	public static boolean spamPlayers(int amount, String msg){

		try{

			for (int i = 0; i < amount; i++){

				for (Player p : Bukkit.getServer().getOnlinePlayers()){

					p.sendMessage(msg);

				}

			}

			return true;

		} catch(NullPointerException e){

			e.printStackTrace();

			return false;

		}

	}

	public static boolean isBoolean(UUID player){

		return false;

	}

	public static boolean isPrimed(Entity tnt){

		boolean primed = false;

		if(tnt != null){

			if(tnt.getType() == EntityType.PRIMED_TNT){

				primed = true;

			} else {
				
				primed = false;
				
			}

		} else {
			
			primed = false;
			
		}

		return primed;

	}

}
