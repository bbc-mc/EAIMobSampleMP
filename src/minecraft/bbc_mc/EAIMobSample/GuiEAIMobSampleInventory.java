package bbc_mc.EAIMobSample;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;
import net.minecraft.src.StatCollector;

import org.lwjgl.opengl.GL11;

/**
 * EAIMobSample のインベントリ画面
 * 
 * @author bbc_mc
 */
public class GuiEAIMobSampleInventory extends GuiContainer {
    
    EntityEAIMobSample entity;
    EntityPlayer player;
    private int inventoryRows;
    private IInventory upperChestInventory;
    private IInventory lowerChestInventory;
    
    public GuiEAIMobSampleInventory(EntityEAIMobSample entity, EntityPlayer player) {
        super(new ContainerEAIMobSampleInventory(entity, player));
        this.entity = entity;
        this.player = player;
        inventoryRows = 0;
        upperChestInventory = entity.inventory;
        lowerChestInventory = player.inventory;
        allowUserInput = false;
        char c = '\336';
        int i = c - 108;
        inventoryRows = player.inventory.getSizeInventory() / 9;
        ySize = i + inventoryRows * 18;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        fontRenderer.drawString(StatCollector.translateToLocal("bbc_mc.eaimobsample.steve.inventory"), 8, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        if (entity.isDead) {
            mc.displayGuiScreen(null);
        }
        int i = mc.renderEngine.getTexture("/gui/container.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, inventoryRows * 18 + 17);
        drawTexturedModalRect(j, k + inventoryRows * 18 + 17, 0, 126, xSize, 96);
    }
    
    @Override
    public void initGui() {
        super.initGui();
        // controlList.clear();
        // controlList.add(new GuiButton(0, width / 2 - 102, height / 2 + 95, "Additinal GUI"));
    }
    /*
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == 0) {
            mc.displayGuiScreen(new GuiAdditional(entity, player));
        }
    }
    */
}
