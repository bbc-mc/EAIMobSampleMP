package bbc_mc.EAIMobSample;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class EAIMSLocalize {
    public EAIMSLocalize() {
        LanguageRegistry reg = LanguageRegistry.instance();
        
        // workers entity
        reg.addStringLocalization("entity.EAIMobSample.name", "ja_JP", "すっきりスティーブ");
        reg.addStringLocalization("entity.EAIMobSample.name", "en_US", "Steve the slender");
        
        reg.addStringLocalization("bbc_mc.eaimobsample.steve.inventory", "ja_JP", "スティーブの持ち物");
        reg.addStringLocalization("bbc_mc.eaimobsample.steve.inventory", "en_US", "Steve's Inventory");
        
    }
}
