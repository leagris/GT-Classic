package gtclassic.container;

import gtclassic.tile.GTTileArcFurnace;
import ic2.core.inventory.container.ContainerTileComponent;
import ic2.core.inventory.gui.components.base.MachineProgressComp;
import ic2.core.inventory.slots.SlotCustom;
import ic2.core.inventory.slots.SlotOutput;
import ic2.core.util.math.Box2D;
import ic2.core.util.math.Vec2i;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GTContainerArcFurnace extends ContainerTileComponent<GTTileArcFurnace> {

	public static Box2D machineProgressBox = new Box2D(58, 28, 20, 11); // the progress bar and size
	public static Vec2i machineProgressPos = new Vec2i(176, 0); // where the overlay is

	public GTContainerArcFurnace(InventoryPlayer player, GTTileArcFurnace tile) {
		super(tile);
		this.addSlotToContainer(new SlotCustom(tile, 0, 34, 16, null));// main slot
		this.addSlotToContainer(new SlotCustom(tile, 1, 34, 34, null)); // second slot
		this.addSlotToContainer(new SlotCustom(tile, 2, 34, 52, null)); // second slot
		this.addSlotToContainer(new SlotOutput(player.player, tile, 3, 86, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 4, 104, 25)); // output
		this.addSlotToContainer(new SlotOutput(player.player, tile, 5, 122, 25)); // output
		this.addPlayerInventory(player);
		this.addComponent(new MachineProgressComp(tile, GTContainerArcFurnace.machineProgressBox,
				GTContainerArcFurnace.machineProgressPos));
	}

	@Override
	public ResourceLocation getTexture() {
		return this.getGuiHolder().getGuiTexture();
	}

	@Override
	public int guiInventorySize() {
		return this.getGuiHolder().slotCount;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.getGuiHolder().canInteractWith(playerIn);
	}

}