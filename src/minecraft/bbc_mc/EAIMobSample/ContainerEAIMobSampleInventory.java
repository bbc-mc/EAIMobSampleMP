package bbc_mc.EAIMobSample;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerEAIMobSampleInventory extends Container {
    EntityEAIMobSample entity;
    EntityPlayer player;
    
    public ContainerEAIMobSampleInventory(EntityEAIMobSample entity, EntityPlayer player) {
        this.entity = entity;
        this.player = player;
        
        for (int j = 0; j < 4; j++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlotToContainer(new Slot(entity.inventory, i1 + j * 9, 8 + i1 * 18, 18 + j * 18));
            }
        }
        
        int var3;
        
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 103 + var3 * 18));
            }
        }
        
        for (var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 161));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
    
    @Override
    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int i) {
        // public ItemStack transferStackInSlot(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) inventorySlots.get(i);
        
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if (i < entity.inventory.getSizeInventory()) {
                if (!mergeItemStack(itemstack1, entity.inventory.getSizeInventory(), inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!mergeItemStack(itemstack1, 0, 10, false)) {
                return null;
            }
            
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        
        return itemstack;
    }
}
