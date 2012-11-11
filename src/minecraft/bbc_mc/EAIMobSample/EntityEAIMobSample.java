package bbc_mc.EAIMobSample;

import java.lang.reflect.Method;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityTameable;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.World;
import bbc_mc.EasyAIInterface.EAI_Manager;

public class EntityEAIMobSample extends EntityTameable {
    
    /**
     * AIチップ配置用のインベントリを準備します。
     */
    public InventoryEAIMobSample inventory;
    
    /*
     * AI処理用のマネージャクラスインスタンス
     */
    private EAI_Manager manager;
    
    public EntityEAIMobSample(World par1World) {
        super(par1World);
        // this.texture = "";
        this.moveSpeed = 0.5F;
        this.inventory = new InventoryEAIMobSample(this, new ItemStack[36]);
        try {
            Class c = Class.forName("bbc_mc.EasyAIInterface.EasyAIInterface");
            Method method = c.getDeclaredMethod("getManagerInstance");
            this.manager = (EAI_Manager) method.invoke(null);
            /**
             * マネージャを初期化してください。引数は以下の通りです
             * 
             * @param entity
             *        AI処理で動作元となる entity
             * @param inventory
             *        AIチップを配置するインベントリ
             * @param slotnum
             *        AIチップ処理を開始する、インベントリ上のスロット番号
             * @param maxcol
             *        AIチップを配置するインベントリの幅
             */
            this.manager.init(this, this.inventory, 0, 9);
            int slot_start = this.manager.findStartTip(this.inventory);
            this.manager.setCurrentSlot(slot_start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getMaxHealth() {
        return 20;
    }
    
    @Override
    public boolean isAIEnabled() {
        return true;
    }
    
    @Override
    protected boolean canDespawn() {
        return false;
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.getOwner() == null) {
            // this.setOwner(ModLoader.getMinecraftInstance().thePlayer.username);
        }
        if (this.manager == null || !this.manager.execute()) {
            // something trouble
            // System.out.println("something trouble " + this.manager);
        }
    }
    
    @Override
    public boolean interact(EntityPlayer par1EntityPlayer) {
        // for debug
        if (this.getOwner() == null) {
            this.setOwner(par1EntityPlayer.username);
        }
        //
        par1EntityPlayer.openGui(EAIMobSample.instance, EAIMobSample.idGuiEntityEAIMobSample, this.worldObj, (int) this.posX, (int) this.posY,
                (int) this.posZ);
        // ModLoader.openGUI(par1EntityPlayer, new GuiEAIMobSampleInventory(this, par1EntityPlayer));
        return true;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        inventory.Contents = new ItemStack[inventory.getSizeInventory()];
        
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < inventory.Contents.length) {
                inventory.Contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < inventory.Contents.length; i++) {
            if (inventory.Contents[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                inventory.Contents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
    }
    
    @Override
    public void onDeath(DamageSource par1DamageSource) {
        this.inventory.dropAll();
    }
    
    @Override
    public EntityAnimal spawnBabyAnimal(EntityAnimal var1) {
        return null;
    }
}
