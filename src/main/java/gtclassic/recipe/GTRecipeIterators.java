package gtclassic.recipe;

import gtclassic.block.GTBlockTileStorage;
import gtclassic.material.GTMaterial;
import gtclassic.material.GTMaterialFlag;
import gtclassic.material.GTMaterialGen;
import gtclassic.tool.GTToolChainsaw;
import gtclassic.tool.GTToolFile;
import gtclassic.tool.GTToolHammer;
import gtclassic.tool.GTToolMiningDrill;
import gtclassic.tool.GTToolWrench;
import gtclassic.util.GTValues;
import ic2.api.classic.recipe.ClassicRecipes;
import ic2.api.classic.recipe.crafting.ICraftingRecipeList;
import ic2.core.block.machine.low.TileEntityCompressor;
import ic2.core.block.machine.low.TileEntityMacerator;
import ic2.core.platform.registry.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GTRecipeIterators {

	public static ICraftingRecipeList recipes = ClassicRecipes.advCrafting;
	static GTMaterialGen GT;
	static GTMaterial M;

	public static void recipeIterators1() {
		/*
		 * The statements below iterate through the material registry to create recipes
		 * for the correct corresponding items and blocks.
		 */

		for (GTMaterial mat : GTMaterial.values()) {

			String smalldust = "dustSmall" + mat.getDisplayName();
			String dust = "dust" + mat.getDisplayName();
			String gem = "gem" + mat.getDisplayName();
			String ingot = "ingot" + mat.getDisplayName();
			String nugget = "nugget" + mat.getDisplayName();
			String stick = "stick" + mat.getDisplayName();
			String plate = "plate" + mat.getDisplayName();
			String block = "block" + mat.getDisplayName();

			if (mat.hasFlag(GTMaterialFlag.SMALLDUST)) {
				// Regular dust to small dust,
				recipes.addShapelessRecipe(GT.getSmallDust(mat, 4), new Object[] { dust });

				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					// Small dust to regular dust
					recipes.addShapelessRecipe(GT.getDust(mat, 1),
							new Object[] { smalldust, smalldust, smalldust, smalldust });

					if (mat != M.Graphite) {
						// Small dust to regular dust compressor
						TileEntityCompressor.addRecipe(smalldust, 4, GT.getDust(mat, 1), 0.0F);
					}
				}

			}

			if (mat.hasFlag(GTMaterialFlag.INGOT)) {
				// Ingot crafting recipe
				recipes.addRecipe(GT.getIngot(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', nugget });

				if (mat.hasFlag(GTMaterialFlag.DUST) && mat.getSmeltable()) {
					GameRegistry.addSmelting(GT.getDust(mat, 1), (GT.getIngot(mat, 1)), 0.1F);
				}

			}

			if (mat.hasFlag(GTMaterialFlag.GEM)) {
				// Dust to gem
				TileEntityCompressor.addRecipe(dust, 1, GT.getGem(mat, 1), 0.0F);

				// Inverse
				TileEntityMacerator.addRecipe(gem, 1, GT.getDust(mat, 1), 0.0F);

				if (mat.hasFlag(GTMaterialFlag.BLOCK)) {
					// Block and gem related logic
					recipes.addShapelessRecipe(GT.getGem(mat, 9), new Object[] { block });
					TileEntityCompressor.addRecipe(gem, 9, GT.getMaterialBlock(mat, 1), 0.0F);
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
					recipes.addRecipe(GT.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', gem });
				}
			}

			if (mat.hasFlag(GTMaterialFlag.NUGGET)) {
				// Nugget crafting recipe
				recipes.addShapelessRecipe(GT.getNugget(mat, 9), new Object[] { ingot });
			}

			if (mat.hasFlag(GTMaterialFlag.PLATE) && (mat != M.Silicon)) {
				// Plate crafting recipe
				recipes.addRecipe(GT.getPlate(mat, 1),
						new Object[] { "H", "X", 'H', "craftingToolForgeHammer", 'X', ingot });

				// If a dust is present create a maceration recipe
				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					TileEntityMacerator.addRecipe(plate, 1, GT.getDust(mat, 1), 0.0F);
				}
			}

			if (mat.hasFlag(GTMaterialFlag.STICK)) {
				// Stick crafting recipe
				recipes.addShapelessRecipe(GT.getStick(mat, 2), new Object[] { "craftingToolFile", ingot });

				// If a dust is present create a maceration recipe
				if (mat.hasFlag(GTMaterialFlag.DUST)) {
					TileEntityMacerator.addRecipe(stick, 1, GT.getSmallDust(mat, 2), 0.0F);
				}
			}

			if (mat.hasFlag(GTMaterialFlag.BLOCK)) {

				if (mat.hasFlag(GTMaterialFlag.INGOT)) {
					// Block crafting recipe
					recipes.addRecipe(GT.getMaterialBlock(mat, 1), new Object[] { "XXX", "XXX", "XXX", 'X', ingot });
					TileEntityCompressor.addRecipe(ingot, 9, GT.getMaterialBlock(mat, 1), 0.0F);

					// Inverse
					recipes.addShapelessRecipe(GT.getIngot(mat, 9), new Object[] { block });
					TileEntityMacerator.addRecipe(block, 1, GT.getDust(mat, 9), 0.0F);
				}

			}

			if (mat.hasFlag(GTMaterialFlag.CASING)) {
				// Casing crafting recipe
				recipes.addRecipe(GT.getCasing(mat, 1),
						new Object[] { "SXX", "XWX", "XXS", 'X', plate, 'S', stick, 'W', "craftingToolWrench" });
			}
		}
	}

	public static void recipeIterators2() {
		/*
		 * This is where I will store recipes that are part of the material registry but
		 * are tied to other mods/vanilla so they cannot be created through iteration.
		 */

		final ItemStack dustGlowstone = new ItemStack(Items.GLOWSTONE_DUST);
		final ItemStack dustGunpowder = new ItemStack(Items.GUNPOWDER);
		final ItemStack dustRedstone = new ItemStack(Items.REDSTONE);

		dustUtil(dustGlowstone, M.Glowstone);
		dustUtil(dustGunpowder, M.Gunpowder);
		dustUtil(Ic2Items.tinDust, M.Tin);
		dustUtil(Ic2Items.obsidianDust, M.Obsidian);
		dustUtil(Ic2Items.bronzeDust, M.Bronze);
		dustUtil(Ic2Items.coalDust, M.Coal);
		dustUtil(Ic2Items.silverDust, M.Silver);
		dustUtil(dustRedstone, M.Redstone);
		dustUtil(Ic2Items.clayDust, M.Clay);
		dustUtil(Ic2Items.goldDust, M.Gold);
		dustUtil(Ic2Items.copperDust, M.Copper);
		dustUtil(Ic2Items.netherrackDust, M.Netherrack);
		dustUtil(Ic2Items.ironDust, M.Iron);
		dustUtil(Ic2Items.charcoalDust, M.Charcoal);

		ingotUtil(Ic2Items.copperIngot, M.Copper);
		ingotUtil(Ic2Items.tinIngot, M.Tin);
		ingotUtil(Ic2Items.bronzeIngot, M.Bronze);
		ingotUtil(Ic2Items.silverIngot, M.Silver);

	}

	public static void recipeIterators3() {
		for (Item item : Item.REGISTRY) {
			if (item instanceof GTToolFile) {
				GTToolFile file = (GTToolFile) item;
				String plate = "plate" + file.getMaterial().getDisplayName();
				recipes.addRecipe(new ItemStack(item), new Object[] { "P", "P", "S", 'P', plate, 'S', "stickWood" });
			}
			if (item instanceof GTToolHammer) {
				GTToolHammer hammer = (GTToolHammer) item;
				String ingot = "ingot" + hammer.getMaterial().getDisplayName();
				recipes.addRecipe(new ItemStack(item),
						new Object[] { "II ", "IIS", "II ", 'I', ingot, 'S', "stickWood" });
			}
			if (item instanceof GTToolWrench) {
				GTToolWrench wrench = (GTToolWrench) item;
				String ingot = "ingot" + wrench.getMaterial().getDisplayName();
				recipes.addRecipe(new ItemStack(item), new Object[] { "I I", "III", " I ", 'I', ingot });
			}
			if (GTValues.debugMode) { // disabling these in game until the tools are damagable
				if (item instanceof GTToolMiningDrill) {
					// TODO not finished yet
				}
				if (item instanceof GTToolChainsaw) {
					// TODO not finished yet
				}
			}
		}
		for (Block block : Block.REGISTRY) {
			if (block instanceof GTBlockTileStorage) {
				GTBlockTileStorage tile = (GTBlockTileStorage) block;
				GTMaterial material = tile.getMaterial();
				String cabinet = "chest" + material.getDisplayName();
				String casing = "casingMachine" + material.getDisplayName();
				String plate = "plate" + material.getDisplayName();
				String stick = "stick" + material.getDisplayName();
				String wrench = "craftingToolWrench";
				String hammer = "craftingToolForgeHammer";
				if (tile.getType() == 0) { // cabinet
					recipes.addRecipe(new ItemStack(block),
							new Object[] { "HPW", "P P", "PPP", 'P', plate, 'S', stick, 'H', hammer, 'W', wrench });
				}
				if (tile.getType() == 1) {// large cabinet
					recipes.addRecipe(new ItemStack(block),
							new Object[] { "SPS", "CMC", "SPS", 'P', plate, 'S', stick, 'C', cabinet, 'M', casing });
				}
				if (tile.getType() == 2) {// bookshelf
					recipes.addRecipe(new ItemStack(block),
							new Object[] { "PPP", "H W", "PPP", 'P', plate, 'S', stick, 'H', hammer, 'W', wrench });
				}
				if (tile.getType() == 3) {// workbench
					recipes.addRecipe(new ItemStack(block), new Object[] { "PHP", "SWS", "PCP", 'P', plate, 'S', stick,
							'H', hammer, 'W', "workbench", 'C', "chestWood" });
				}
			}
		}
	}

	public static void dustUtil(ItemStack stack, GTMaterial material) {
		String smalldust = "dustSmall" + material.getDisplayName();
		recipes.addShapelessRecipe(stack, new Object[] { smalldust, smalldust, smalldust, smalldust });
		TileEntityCompressor.addRecipe(smalldust, 4, GT.getDust(material, 1), 0.0F);
	}

	public static void ingotUtil(ItemStack stack, GTMaterial material) {
		String nugget = "nugget" + material.getDisplayName();
		recipes.addRecipe(stack, new Object[] { "XXX", "XXX", "XXX", 'X', nugget });
	}

}
