package bbc_mc.EAIMobSample.client;

import net.minecraft.src.ModelBiped;
import net.minecraft.src.RenderBiped;
import net.minecraft.src.World;
import bbc_mc.EAIMobSample.EAIMSCommonProxy;
import bbc_mc.EAIMobSample.EntityEAIMobSample;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EAIMSClientProxy extends EAIMSCommonProxy {
    
    public void doOnLoadRegistration() {
        //
        // register tick
        //
        // TickRegistry.registerScheduledTickHandler(new EAIMSClientTicker(), Side.CLIENT);
        //
        // preload texture
        //
        // MinecraftForgeClient.preloadTexture("/items.png");
        //
        // register Renderer
        //
        RenderingRegistry.registerEntityRenderingHandler(EntityEAIMobSample.class, new RenderBiped(new ModelBiped(), 0.5F));
        //
        // register TileEntitySpecialRenderer
        //
    }
    
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }
    
    public void doPreLoadRegistration() {
        // MinecraftForge.EVENT_BUS.register(new EAIMSSounds());
    }
    
}
