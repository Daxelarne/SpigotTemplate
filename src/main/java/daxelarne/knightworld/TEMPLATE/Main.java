package daxelarne.knightworld.TEMPLATE;

import static org.bukkit.Bukkit.getPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.WebSocket.Listener;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private FileConfiguration config;
	private int maxSizePseudo=0;
	private boolean ignoreEqualsSymbol;
	private String wordResetTitle;
	private File data;
	
	private static Map<String, String> titres;
	
	@Override
    public void onDisable() {
        super.onDisable();
        
        //Sauvegarde les données
        setValueInData();
	 }

    @Override
    public void onEnable() {
    	super.onEnable();
    	this.getLogger().log(Level.INFO, "Chargement...");
    	
    	/**
    	 * CONFIG
    	 */
    	saveDefaultConfig();
        config = getConfig();
        getConfigValues();
        
        
        /**
         * DATA
         */
        data = new File(getDataFolder(), "data.yml");
        createData();
        
        //Récupère les valeurs existantes
        getValueInData();
        
        /**
         * LANG
         */
        loadLang();
        
        /**
         * PAPI
         */
        new PAPITitle().register();
    	
        
    	this.getLogger().log(Level.INFO, "Chargé !");
    }
    
    
    private void loadLang() {
		File lang = new File(getDataFolder(), "lang.yml");
		YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(lang);
		//Si le fichier n'éxiste pas, on le créer
		if(!lang.exists()) {
			try {
				langConfig.save(lang);
			} catch(IOException e) {
				disablePl(e);
			}
		}
		
		//Si la config ne contient pas les valeurs, on les initialise
		for(Lang item : Lang.values()) {
			if(langConfig.getString(item.getPath()) == null) {
				langConfig.set(item.getPath(), item.getDefault());
			}
		}
		
		Lang.setFile(langConfig);
		
		try {
			langConfig.save(lang);
		} catch(IOException e) {
			disablePl(e);
		}
		
	}
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	
    	/**
		 * Affichage valeur
		 */
    	if(command.getName().toLowerCase().equals("MACOMMANDE")) {
    		
    		//TODO verification permission
    		//S'il n'à pas la permission
			if(!hasPermission(sender, "MACOMMANDE.MAPERM")) {
				
    			return true;
    			
    		//Sinon
    		} else {
    			//TODO code
    		}
    	
    	}
    	return true;
    }
    
    
    
    //Check si un joueur possède la permission
  	private boolean hasPermission(CommandSender sender, String perm) {
  		Player p = getPlayer(sender.getName());
  		
  		if(p!=null && !p.hasPermission(perm)) {
  			sendNoPerm(sender);
  			return false;
  		}
  		
  		return true;
  	}
  	
    
    //Initialisation des variable en fonction de la config
  	private void getConfigValues() {
  		try {
  			maxSizePseudo=config.getInt("maxSizePseudo");
  			ignoreEqualsSymbol=config.getBoolean("ignoreEqualsSymbol");
  			wordResetTitle=config.getString("wordResetTitle");
          } catch(Exception e) {
          	disablePl(e);
          }
  	}
  	
	//Désactive le plugin et affiche le message de l'erreur
  	private void disablePl(Exception e) {
  		this.getLogger().log(Level.SEVERE, e.getClass()+" : "+e.getMessage());
  		getServer().getPluginManager().disablePlugin(this);
  	}


  	//Retourne null ou le titre du joueur s'il en possède un
	public static String getPlayerTitre(Player player) {
		String pTitre = titres.get(player.getName());
		return pTitre == null ? null : pTitre;
	}
	
	
	/**
	 * MESSAGES
	 */

	
	private void sendNoPerm(CommandSender sender) {
		sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NO_PERM.toString());
	}
  	
  	private void sendNoValue(CommandSender sender) {
		sender.sendMessage(Lang.ERROR_PREFIX.toString() + Lang.NO_VALUE.toString());
	}
  	
  	
  	/**
  	 * DATA
  	 */
  	
  //Créer le fichier data.yml et insère les données
  	private void createData() {
      	try {
  	        if(!data.exists()) {
  	        	this.getLogger().log(Level.INFO, "Création du fichier data.yml...");
  	        	
  	        	//Création du fichier
  				if(!data.exists()) data.createNewFile();
  				
  				
  	        	this.getLogger().log(Level.INFO, "Fichier data.yml créé !");
  	        }
          } catch (IOException e) {
  			disablePl(e);
  		}
  		
  	}
  	
  	
	//Récupère value depuis data.yml
    private void getValueInData() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(data));
			
			String val = reader.readLine();
			
			while(val!=null && !val.equals("EOF")) {
				
				
				//TODO traitement des datas
				
				val = reader.readLine();
			}
	    	
	    	reader.close();
		} catch (IOException e) {
			this.getLogger().log(Level.SEVERE, e.getClass()+" : "+e.getMessage());
		}
    }
    
    //Enregistre value dans le fichier data.yml
    private void setValueInData() {
    	try {
	    	//Création du fichier
    		if(!data.exists()) data.createNewFile();
	
	    	//On enregistre la valeur la plus basse dans data
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(data));
	    	
	    	String db="";
	    	
        	//TODO sauvegade des datas
        	
        	
	    	writer.write(db);
        	
	    	writer.close();
    	} catch (IOException e) {
    		this.getLogger().log(Level.SEVERE, e.getClass()+" : "+e.getMessage());
		}
    }
}
