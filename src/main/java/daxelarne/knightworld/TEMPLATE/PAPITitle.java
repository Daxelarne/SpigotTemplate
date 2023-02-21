package daxelarne.knightworld.TEMPLATE;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PAPITitle extends PlaceholderExpansion{

	@Override
	public @NotNull String getIdentifier() {
		return "TitleCustom";
	}

	@Override
	public @NotNull String getAuthor() {
		return "Daxelarne";
	}

	@Override
	public @NotNull String getVersion() {
		return "0.1";
	}
	
	@Override
	public boolean canRegister() {
		return true;
	}

	
	@Override
	public boolean persist() {
		return true;
	}
	
	@Override
	public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
		if(player==null) return "";
		
		if( params.equals("titre") && Main.getPlayerTitre(player) != null) {
			return Main.getPlayerTitre(player)+" ";
		}
		
		return "";
	}
}
