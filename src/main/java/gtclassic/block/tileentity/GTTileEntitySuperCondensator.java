package gtclassic.block.tileentity;

import ic2.api.classic.network.adv.IBitLevelOverride;
import ic2.api.classic.network.adv.NetworkField;
import ic2.core.block.base.tile.TileEntityTransformer;

public class GTTileEntitySuperCondensator extends TileEntityTransformer implements IBitLevelOverride {

	public GTTileEntitySuperCondensator() {
		super(32768, 65535, 65535);
	}

	@Override
	public double getWrenchDropRate() {
		return 0.7D;
	}

	@Override
	public NetworkField.BitLevel getOverride(int fieldID, String fieldName) {
		return NetworkField.BitLevel.Bit16;
	}

	@Override
	public boolean hasOverride(int fieldID, String fieldName) {
		return fieldID == 3;
	}

}