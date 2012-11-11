package bbc_mc.EAIMobSample;

import java.util.Collections;
import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Container;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.World;
import bbc_mc.EAIMobSample.util.SorterDistanceToEntity;
import cpw.mods.fml.common.network.IGuiHandler;

public class EAIMSCommonProxy implements IGuiHandler {
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // return ContainerHogehoge
        Container ret = null;
        if (ID == EAIMobSample.idGuiEntityEAIMobSample) {
            // find nearest entity related to this mod
            List list_ent = this.listupNearbyEntities(EntityEAIMobSample.class, player, world, x, y, z);
            EntityEAIMobSample targetEntity = null;
            if (list_ent != null && list_ent.size() > 0) {
                targetEntity = (EntityEAIMobSample) list_ent.get(0);
                ret = new ContainerEAIMobSampleInventory(targetEntity, player);
            }
            EAIMobSample.instance.debugPrint("getServerGuiElement : ID : " + ID + " pl:" + player + " ret:" + ret + " list:" + list_ent);
        }
        return ret;
    }
    
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // return GUIhogehoge
        GuiScreen gui = null;
        if (ID == EAIMobSample.idGuiEntityEAIMobSample) {
            // find
            List list_ent = this.listupNearbyEntities(EntityEAIMobSample.class, player, world, x, y, z);
            EntityEAIMobSample targetEntity = null;
            if (list_ent != null && list_ent.size() > 0) {
                targetEntity = (EntityEAIMobSample) list_ent.get(0);
                gui = new GuiEAIMobSampleInventory(targetEntity, player);
            }
            EAIMobSample.instance.debugPrint("getClientGuiElement : ID : " + ID + " pl:" + player + " ret:" + gui + " list:" + list_ent);
        }
        return gui;
    }
    
    public void doPreLoadRegistration() {
    }
    
    public void doOnLoadRegistration() {
    }
    
    // ====================================================
    
    /**
     * search 1 x 1 area to listup entities of target type.
     * 
     * @return
     */
    private List listupNearbyEntities(Class targetTypeClass, Entity sourceEntity, World world, int x, int y, int z) {
        double minX = x - 1;
        double minY = y - 0.1;
        double minZ = z - 1;
        double maxX = x + 1;
        double maxY = y + 0.1;
        double maxZ = z + 1;
        List list_entity = world.getEntitiesWithinAABB(targetTypeClass, AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ));
        Collections.sort(list_entity, new SorterDistanceToEntity(sourceEntity));
        return list_entity;
    }
}
