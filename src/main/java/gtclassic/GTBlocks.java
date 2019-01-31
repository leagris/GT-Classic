package gtclassic;

import gtclassic.block.GTBlockCasing1;
import gtclassic.block.GTBlockCasing2;
import gtclassic.block.GTBlockCasing2.GTBlockCasing2Variants;
import gtclassic.block.GTBlockMaterial;
import gtclassic.block.GTBlockOre;
import gtclassic.block.GTBlockOre.GTBlockOreVariants;
import gtclassic.block.GTBlockSandIron;
import gtclassic.block.GTBlockTileBasic;
import gtclassic.block.GTBlockTileCustom;
import gtclassic.block.GTBlockTileCustom.GTBlockTileCustomVariants;
import gtclassic.block.test.GTBlockTestLayer;
import gtclassic.tileentity.GTTileEntityBasicEnergyStorage;
import gtclassic.tileentity.GTTileEntityBookshelf;
import gtclassic.tileentity.GTTileEntityComputerCube;
import gtclassic.tileentity.GTTileEntityDigitalChest;
import gtclassic.tileentity.GTTileEntityDigitalTransformer;
import gtclassic.tileentity.GTTileEntityFusionComputer;
import gtclassic.tileentity.GTTileEntityIndustrialCentrifuge;
import gtclassic.tileentity.GTTileEntityLargeChest;
import gtclassic.tileentity.GTTileEntityLightningRod;
import gtclassic.tileentity.GTTileEntityMultiEnergyStorage;
import gtclassic.tileentity.GTTileEntityQuantumEnergyStorage;
import gtclassic.tileentity.GTTileEntitySmallChest;
import gtclassic.tileentity.GTTileEntitySuperConductor;
import gtclassic.tileentity.GTTileEntityWorkbench;
import gtclassic.util.color.GTColorItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class GTBlocks {

	public static final String[] textureTileCustom = {
			/*
			 * This will be where a textures can iterate and be loaded instead of the enum
			 * system
			 */
	};

	public static final GTBlockCasing1 casingAluminium = new GTBlockCasing1("Aluminium"),
			casingBrass = new GTBlockCasing1("Brass"),
			casingBronze = new GTBlockCasing1("Bronze"),
			casingChrome = new GTBlockCasing1("Chrome"),
			casingCopper = new GTBlockCasing1("Copper"),
			casingElectrum = new GTBlockCasing1("Electrum"),
			casingInvar = new GTBlockCasing1("Invar"),
			casingIridium = new GTBlockCasing1("Iridium"),
			casingIron = new GTBlockCasing1("Iron"),
			casingLead = new GTBlockCasing1("Lead"),
			casingNickel = new GTBlockCasing1("Nickel"),
			casingOsmium = new GTBlockCasing1("Osmium"),
			casingPlatinum = new GTBlockCasing1("Platinum"),
			casingSilver = new GTBlockCasing1("Silver"),
			casingSteel = new GTBlockCasing1("Steel"),
			casingTin = new GTBlockCasing1("Tin"),
			casingTitanium = new GTBlockCasing1("Titanium"),
			casingTungsten = new GTBlockCasing1("Tungsten"),
			casingZinc = new GTBlockCasing1("Zinc");

	public static final GTBlockCasing2 superCasingBlock = new GTBlockCasing2(GTBlockCasing2Variants.SUPERCONDUCTOR),
			fusionCasingBlock = new GTBlockCasing2(GTBlockCasing2Variants.FUSION),
			fissionCasingBlock = new GTBlockCasing2(GTBlockCasing2Variants.FISSION),
			crystalCasingBlock = new GTBlockCasing2(GTBlockCasing2Variants.CRYSTAL);

	public static final GTBlockMaterial rubyBlock = new GTBlockMaterial("Ruby", 1),
			sapphireBlock = new GTBlockMaterial("Sapphire", 1),
			aluminiumBlock = new GTBlockMaterial("Aluminium", 0),
			titaniumBlock = new GTBlockMaterial("Titanium", 0),
			chromeBlock = new GTBlockMaterial("Chrome", 0),
			steelBlock = new GTBlockMaterial("Steel", 0),
			brassBlock = new GTBlockMaterial("Brass", 0),
			leadBlock = new GTBlockMaterial("Lead", 0),
			electrumBlock = new GTBlockMaterial("Electrum", 0),
			zincBlock = new GTBlockMaterial("Zinc", 0),
			olivineBlock = new GTBlockMaterial("Olivine", 1),
			greenSapphireBlock = new GTBlockMaterial("SapphireGreen", 1),
			platinumBlock = new GTBlockMaterial("Platinum", 0),
			tungstenBlock = new GTBlockMaterial("Tungsten", 0),
			nickelBlock = new GTBlockMaterial("Nickel", 0),
			tungstensteelBlock = new GTBlockMaterial("TungstenSteel", 0),
			invarBlock = new GTBlockMaterial("Invar", 0),
			osmiumBlock = new GTBlockMaterial("Osmium", 0),
			iridiumBlock = new GTBlockMaterial("Iridium", 0);

	public static final GTBlockOre galenaOre = new GTBlockOre(GTBlockOreVariants.GALENA),
			iridiumOre = new GTBlockOre(GTBlockOreVariants.IRIDIUM),
			rubyOre = new GTBlockOre(GTBlockOreVariants.RUBY),
			sapphireOre = new GTBlockOre(GTBlockOreVariants.SAPPHIRE),
			bauxiteOre = new GTBlockOre(GTBlockOreVariants.BAUXITE),
			pyriteOre = new GTBlockOre(GTBlockOreVariants.PYRITE),
			cinnabarOre = new GTBlockOre(GTBlockOreVariants.CINNABAR),
			sphaleriteOre = new GTBlockOre(GTBlockOreVariants.SPHALERITE),
			tungstateOre = new GTBlockOre(GTBlockOreVariants.TUNGSTATE),
			sheldoniteOre = new GTBlockOre(GTBlockOreVariants.SHELDONITE),
			olivineOre = new GTBlockOre(GTBlockOreVariants.OLIVINE),
			sodaliteOre = new GTBlockOre(GTBlockOreVariants.SODALITE);

	public static final GTBlockSandIron sandIron = new GTBlockSandIron();

	public static final String[] textureTileBasic = {
			"machine_autocrafter_lv",
			"machine_chargeomat_ev",
			"machine_computercube_ev",
			"machine_industrialcentrifuge_lv", 
			"machine_matterfabricator_ev", 
			"machine_uumassembler_ev",
			"machine_playerdetector_lv",
			"machine_echophone_lv",
			"machine_fusioncomputer_iv",
			"machine_lightningrod_iv",
			"machine_quantumenergystorage_ev",
			"machine_basicenergystorage_ev",
			"machine_multienergystorage_mv",
			"machine_digitaltransformer_iv",
			"wire_energium_luv",
			"wire_lapotron_zpm",
			"tile_smallchest_lv",
			"tile_largechest_lv",
			"tile_digitalchest_lv",
			"tile_bookshelf_lv",
			"tile_workbench_lv",
			"tile_smallchest_mv",
			"tile_largechest_mv",
			"tile_digitalchest_mv", 
			"tile_bookshelf_mv",
			"tile_workbench_mv"
	};

	public static final GTBlockTileBasic 
	        autoCrafter = new GTBlockTileBasic("machine_autocrafter_lv"),
			chargeOMat = new GTBlockTileBasic("machine_chargeomat_ev"),
			computerCube = new GTBlockTileBasic("machine_computercube_ev"),
			industrialCentrifuge = new GTBlockTileBasic("machine_industrialcentrifuge_lv"),
			matterFabricator = new GTBlockTileBasic("machine_matterfabricator_ev"),
			uuMatterAssembler = new GTBlockTileBasic("machine_uumassembler_ev"),
			playerDetector = new GTBlockTileBasic("machine_playerdetector_lv"),
			echophoneBlock = new GTBlockTileBasic("machine_echophone_lv"),
			fusionComputer = new GTBlockTileBasic("machine_fusioncomputer_iv"),
			lightningRod = new GTBlockTileBasic("machine_lightningrod_iv"),
			IDSU = new GTBlockTileBasic("machine_quantumenergystorage_ev"),
			HESU = new GTBlockTileBasic("machine_basicenergystorage_ev"),
			LESU = new GTBlockTileBasic("machine_multienergystorage_mv"),
			digitalTransformerIV = new GTBlockTileBasic("machine_digitaltransformer_iv"),
			energiumWire = new GTBlockTileBasic("wire_energium_luv"),
			lapotronWire = new GTBlockTileBasic("wire_lapotron_zpm"),
			smallChestLV = new GTBlockTileBasic("tile_smallchest_lv"),
			largeChestLV = new GTBlockTileBasic("tile_largechest_lv"),
			digitalChestLV = new GTBlockTileBasic("tile_digitalchest_lv"),
			bookShelfLV = new GTBlockTileBasic("tile_bookshelf_lv"),
			workBenchLV = new GTBlockTileBasic("tile_workbench_lv"),
			smallChestMV = new GTBlockTileBasic("tile_smallchest_mv"),
			largeChestMV = new GTBlockTileBasic("tile_largechest_mv"),
			digitalChestMV = new GTBlockTileBasic("tile_digitalchest_mv"),
			bookShelfMV = new GTBlockTileBasic("tile_bookshelf_mv"),
			workBenchMV = new GTBlockTileBasic("tile_workbench_mv");

	public static final GTBlockTileCustom smallCoolant = new GTBlockTileCustom(
			GTBlockTileCustomVariants.COOLANT_HELIUM_SMALL),
			medCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLANT_HELIUM_MED),
			largeCoolant = new GTBlockTileCustom(GTBlockTileCustomVariants.COOLANT_HELIUM_LARGE),

			smallThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_SMALL),
			medThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_MED),
			largeThorium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_THORIUM_LARGE),

			smallPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_SMALL),
			medPlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_MED),
			largePlutonium = new GTBlockTileCustom(GTBlockTileCustomVariants.ROD_PLUTONIUM_LARGE),

			smallLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_SMALL),
			medLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_MED),
			largeLithium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LITHIUM_LARGE),

			tinyLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_TINY),
			smallLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_SMALL),
			medLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_MED),
			largeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_LARGE),
			hugeLapotron = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_LAPOTRON_HUGE),

			tinyEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_TINY),
			smallEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_SMALL),
			medEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_MED),
			largeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_LARGE),
			hugeEnergium = new GTBlockTileCustom(GTBlockTileCustomVariants.BATTERY_ENERGIUM_HUGE);

	public static final GTBlockTestLayer testBlock = new GTBlockTestLayer();

	public static final Block[] blocks = {

			casingAluminium,
			casingBrass,
			casingBronze,
			casingChrome,
			casingCopper,
			casingElectrum,
			casingInvar,
			casingIridium,
			casingIron,
			casingLead,
			casingNickel,
			casingOsmium,
			casingPlatinum,
			casingSilver,
			casingSteel,
			casingTin,
			casingTitanium,
			casingTungsten,
			casingZinc,

			superCasingBlock, fusionCasingBlock, fissionCasingBlock, crystalCasingBlock,

			rubyBlock,
			sapphireBlock,
			aluminiumBlock,
			titaniumBlock,
			chromeBlock,
			steelBlock,
			brassBlock,
			leadBlock,
			electrumBlock,
			zincBlock,
			olivineBlock,
			greenSapphireBlock,
			platinumBlock,
			tungstenBlock,
			nickelBlock,
			tungstensteelBlock,
			invarBlock,
			osmiumBlock,
			iridiumBlock,

			galenaOre,
			iridiumOre,
			rubyOre,
			sapphireOre,
			bauxiteOre,
			sandIron,
			pyriteOre,
			cinnabarOre,
			sphaleriteOre,
			tungstateOre,
			sheldoniteOre,
			olivineOre,
			sodaliteOre,

			autoCrafter,
			chargeOMat,
			computerCube,
			industrialCentrifuge,
			matterFabricator,
			uuMatterAssembler,
			playerDetector,
			echophoneBlock,
			fusionComputer,
			lightningRod,

			IDSU,
			HESU,
			LESU,
			digitalTransformerIV,
			energiumWire,
			lapotronWire,

			digitalChestLV, bookShelfLV, workBenchLV, smallChestLV, largeChestLV,
			digitalChestMV, bookShelfMV, workBenchMV, smallChestMV, largeChestMV,

			smallCoolant, medCoolant, largeCoolant,

			smallThorium, medThorium, largeThorium, smallPlutonium, medPlutonium, largePlutonium,

			smallLithium, medLithium, largeLithium,
			tinyLapotron, smallLapotron, medLapotron, largeLapotron, hugeLapotron,

			tinyEnergium, smallEnergium, medEnergium, largeEnergium, hugeEnergium,

			testBlock,

	};

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
		GTMod.logger.info("Registering Blocks");
		for (Block block : blocks) {
			registry.register(block);
		}
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		for (Block block : blocks) {
			registry.register(new GTColorItemBlock(block).setRegistryName(block.getRegistryName())
					.setUnlocalizedName(block.getUnlocalizedName()).setCreativeTab(GTMod.creativeTabGT));
		}
	}

	public static void registerTiles() {
		GameRegistry.registerTileEntity(GTTileEntityIndustrialCentrifuge.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIndustrialCentrifuge"));
		GameRegistry.registerTileEntity(GTTileEntityComputerCube.class,
				new ResourceLocation(GTMod.MODID, "tileEntityComputerCube"));
		GameRegistry.registerTileEntity(GTTileEntityDigitalTransformer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityDigitalTransformer"));

		GameRegistry.registerTileEntity(GTTileEntitySmallChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySmallChest"));
		GameRegistry.registerTileEntity(GTTileEntityLargeChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLargeChest"));
		GameRegistry.registerTileEntity(GTTileEntityDigitalChest.class,
				new ResourceLocation(GTMod.MODID, "tileEntityQuantumChest"));
		GameRegistry.registerTileEntity(GTTileEntityBookshelf.class,
				new ResourceLocation(GTMod.MODID, "tileEntityBookshelf"));
		GameRegistry.registerTileEntity(GTTileEntityWorkbench.class,
				new ResourceLocation(GTMod.MODID, "tileEntityWorkbench"));

		GameRegistry.registerTileEntity(GTTileEntityBasicEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityHESU"));
		GameRegistry.registerTileEntity(GTTileEntityQuantumEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityIDSU"));
		GameRegistry.registerTileEntity(GTTileEntityMultiEnergyStorage.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLESU"));

		GameRegistry.registerTileEntity(GTTileEntityLightningRod.class,
				new ResourceLocation(GTMod.MODID, "tileEntityLightningRod"));
		GameRegistry.registerTileEntity(GTTileEntityFusionComputer.class,
				new ResourceLocation(GTMod.MODID, "tileEntityFusionComputer"));
		GameRegistry.registerTileEntity(GTTileEntitySuperConductor.class,
				new ResourceLocation(GTMod.MODID, "tileEntitySuperConductor"));
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		// overloads deprecated method to properly register tiles
		GameRegistry.registerTileEntity(tileEntityClass, GTMod.MODID + ":" + name);
	}
}