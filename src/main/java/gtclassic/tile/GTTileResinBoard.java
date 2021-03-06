package gtclassic.tile;

import gtclassic.GTMod;
import gtclassic.util.int3;
import ic2.core.block.base.tile.TileEntityBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;

public class GTTileResinBoard extends TileEntityBlock implements ITickable {

	int progress = 0;

	public GTTileResinBoard() {
		setWorld(world);
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % 120 == 0 && checkHeat()) {
			this.progress = this.progress + 1;
			if (this.progress > 1) {
				this.setActive(true);
			}
			GTMod.logger.info("plate heat: " + this.progress);
			if (this.progress >= 3) {
				this.progress = 0;
				world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F,
						1.0F);
				world.setBlockState(this.pos, Blocks.FIRE.getDefaultState());
			}
		}
	}

	public boolean checkHeat() {
		int3 dir = new int3(getPos(), getFacing());
		int3 dir1 = new int3(getPos(), getFacing());
		if (world.isAreaLoaded(pos, 3)) {
			if (isSlab(dir.down(1)) && isFire(dir.down(1))) {
				return true;
			}
			if (isFurnace(dir1.down(1))) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canUpdate() {
		return this.isSimulating();
	}

	public boolean isFire(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getBlock() == Blocks.FIRE;
	}

	public boolean isFurnace(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getBlock() == Blocks.LIT_FURNACE;
	}

	public boolean isSlab(int3 pos) {
		return world.getBlockState(pos.asBlockPos()).getBlock() == Blocks.STONE_SLAB
				|| world.getBlockState(pos.asBlockPos()).getBlock() == Blocks.STONE_SLAB2;
	}

}
