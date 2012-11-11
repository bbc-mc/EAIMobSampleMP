package bbc_mc.EAIMobSample;

import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;

public class InventoryEAIMobSample implements IInventory {
    
    public ItemStack Contents[];
    private EntityEAIMobSample entity;
    
    public InventoryEAIMobSample(EntityEAIMobSample entity, ItemStack[] i) {
        this.Contents = i;
        this.entity = entity;
    }
    
    @Override
    public int getSizeInventory() {
        return 36;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        return Contents[i];
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (Contents[i] != null) {
            if (Contents[i].stackSize <= j) {
                ItemStack itemstack = Contents[i];
                Contents[i] = null;
                onInventoryChanged();
                return itemstack;
            }
            
            ItemStack itemstack1 = Contents[i].splitStack(j);
            
            if (Contents[i].stackSize == 0) {
                Contents[i] = null;
            }
            
            onInventoryChanged();
            return itemstack1;
        } else {
            return null;
        }
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (Contents[i] != null) {
            ItemStack itemstack = Contents[i];
            Contents[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        Contents[i] = itemstack;
        
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
        
        onInventoryChanged();
    }
    
    @Override
    public String getInvName() {
        return "bbc_mc.eaimobsample.steve.inventory";
    }
    
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @Override
    public void onInventoryChanged() {
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return entityplayer.getDistanceSq((double) entity.posX + 0.5D, (double) entity.posX + 0.5D, (double) entity.posX + 0.5D) <= 64D
                && !entity.isDead;
    }
    
    @Override
    public void openChest() {
    }
    
    @Override
    public void closeChest() {
    }
    
    public void dropAll() {
        for (int i = 0; i < Contents.length; i++) {
            itemDrop(i);
        }
    }
    
    public void itemDrop(int i) {
        if (i < Contents.length && i >= 0 && Contents[i] != null) {
            entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, Contents[i]));
            Contents[i] = null;
        }
    }
    
    public int getItemStackSize(ItemStack item) {
        int count = 0;
        for (int i = 0; i < Contents.length; i++) {
            if (Contents[i] != null && Contents[i].isItemEqual(item)) {
                count += Contents[i].stackSize;
            }
        }
        return count;
    }
}
