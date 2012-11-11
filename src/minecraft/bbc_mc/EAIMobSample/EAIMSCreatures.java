package bbc_mc.EAIMobSample;

import cpw.mods.fml.common.registry.EntityRegistry;

public class EAIMSCreatures {
    
    public EAIMSCreatures(EAIMobSample mod) {
        this.registerCreatures(mod);
        this.registerNonCreatures(mod);
    }
    
    private void registerNonCreatures(EAIMobSample mod) {
        // EntityRegistry.registerGlobalEntityID(EntityFWWappen.class, "WorkerWappen",
        // FactoryWorkers.idEntityWorkerWappen, 0xFFFFFF, 0x000000);
        // EntityRegistry.registerModEntity(EntityFWWappen.class, "WorkerWappen", FactoryWorkers.idEntityWorkerWappen,
        // mod, 150, 5, true);
    }
    
    private void registerCreatures(EAIMobSample mod) {
        EntityRegistry.registerGlobalEntityID(EntityEAIMobSample.class, "EAIMobSample", EAIMobSample.idEntityEAIMobSample, 0xFFFFFF, 0x000000);
        EntityRegistry.registerModEntity(EntityEAIMobSample.class, "EAIMobSample", EAIMobSample.idEntityEAIMobSample, mod, 150, 1, true);
        
        // BiomeGenBase[] spawn = UtilBiome.convertBiomeList2Array(UtilBiome.getBiomeList_almostAll());
        // EntityRegistry.addSpawn(TM_EntityZombie.class, 5, 1, 2, EnumCreatureType.monster, spawn);
        
    }
}
