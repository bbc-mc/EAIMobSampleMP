package bbc_mc.EAIMobSample;

import net.minecraftforge.common.Configuration;
import bbc_mc.utilforge.UtilForgeConfig;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "EAIMobSample", name = "The EAIMobSample", version = "1.4.2_1.0.0")
@NetworkMod(channels = { "EAIMobSample" }, clientSideRequired = true, serverSideRequired = true, packetHandler = EAIMSPacketHandler.class)
public class EAIMobSample {
    
    //
    // ID: GUI
    //
    public static int idGuiEntityEAIMobSample;
    //
    // ID: Entity
    //
    public static int idEntityEAIMobSample;
    
    public static boolean debug_mode;
    
    @SidedProxy(clientSide = "bbc_mc.EAIMobSample.client.EAIMSClientProxy", serverSide = "bbc_mc.EAIMobSample.EAIMSCommonProxy")
    public static EAIMSCommonProxy proxy;
    
    @Mod.Instance("EAIMobSample")
    public static EAIMobSample instance;
    
    // EAITickHandler tickHandler;
    
    public EAIMobSample() {
        instance = this;
    }
    
    public String getVersion() {
        return "1.4.2-1.0.0";
    }
    
    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent var1) {
        this.loadConfiguration(new Configuration(var1.getSuggestedConfigurationFile()));
        
        new EAIMSCreatures(this);
        
        proxy.doPreLoadRegistration();
    }
    
    @Mod.Init
    public void load(FMLInitializationEvent var1) {
        
        // new FWRecipes();
        new EAIMSLocalize();
        
        proxy.doOnLoadRegistration();
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        // this.tickHandler = new FWTickHandler();
        // TickRegistry.registerScheduledTickHandler(this.tickHandler, Side.SERVER);
    }
    
    public void debugPrint(String str) {
        if (this.debug_mode) {
            System.out.println(str);
        }
    }
    
    private void loadConfiguration(Configuration var1) {
        var1.load();
        //
        // System Settings
        //
        debug_mode = UtilForgeConfig.loadConfigBoolean(var1, "debug_mode", "system", true, "");
        
        idGuiEntityEAIMobSample = UtilForgeConfig.loadConfigInt(var1, "GUIID_EntityMobSample_Inventory", "setting", 300, "");
        idEntityEAIMobSample = UtilForgeConfig.loadConfigInt(var1, "EntityID_EntityMobSample", "setting", 210, "");
        
        var1.save();
    }
    
}
