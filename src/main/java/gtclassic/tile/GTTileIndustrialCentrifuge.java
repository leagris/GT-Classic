package gtclassic.tile;

import java.util.List;

import gtclassic.GTItems;
import gtclassic.GTMod;
import gtclassic.container.GTContainerIndustrialCentrifuge;
import gtclassic.gui.GTGuiMachine.GTIndustrialCentrifugeGui;
import gtclassic.util.recipe.GTBasicMachineRecipeList;
import ic2.api.classic.recipe.RecipeModifierHelpers.IRecipeModifier;
import ic2.api.classic.recipe.machine.IMachineRecipeList;
import ic2.api.classic.recipe.machine.IMachineRecipeList.RecipeEntry;
import ic2.api.classic.recipe.machine.MachineOutput;
import ic2.api.classic.tile.IStackOutput;
import ic2.api.classic.tile.MachineType;
import ic2.api.recipe.IRecipeInput;
import ic2.core.RotationList;
import ic2.core.block.base.tile.TileEntityBasicElectricMachine;
import ic2.core.block.base.util.output.SimpleStackOutput;
import ic2.core.inventory.container.ContainerIC2;
import ic2.core.inventory.filters.ArrayFilter;
import ic2.core.inventory.filters.BasicItemFilter;
import ic2.core.inventory.filters.CommonFilters;
import ic2.core.inventory.filters.MachineFilter;
import ic2.core.inventory.management.AccessRule;
import ic2.core.inventory.management.InventoryHandler;
import ic2.core.inventory.management.SlotType;
import ic2.core.item.recipe.entry.RecipeInputItemStack;
import ic2.core.item.recipe.entry.RecipeInputOreDict;
import ic2.core.platform.lang.components.base.LangComponentHolder.LocaleBlockComp;
import ic2.core.platform.lang.components.base.LocaleComp;
import ic2.core.platform.registry.Ic2Items;
import ic2.core.util.misc.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class GTTileIndustrialCentrifuge extends TileEntityBasicElectricMachine {
	public static final int slotInput = 0;
	public static final int slotCell = 1;
	public static final int slotFuel = 2;
	public static final int slotOutput = 3;
	public static final int slotOutput2 = 4;
	public static final int slotOutput3 = 5;
	public static final int slotOutput4 = 6;
	public static final int defaultLength = 200;

	public static final ResourceLocation GUI_LOCATION = new ResourceLocation(GTMod.MODID,
			"textures/gui/industrialcentrifuge.png");

	public static final String CELL_REQUIREMENT = "recipe-cells";
	public static final IMachineRecipeList RECIPE_LIST = new GTBasicMachineRecipeList("centrifuge");

	public GTTileIndustrialCentrifuge() {
		super(7, 12, defaultLength, 32);
		setFuelSlot(slotFuel);
	}

	@Override
	protected void addSlots(InventoryHandler handler) {
		this.filter = new MachineFilter(this);
		handler.registerDefaultSideAccess(AccessRule.Both, RotationList.ALL);
		handler.registerDefaultSlotAccess(AccessRule.Both, slotFuel);
		handler.registerDefaultSlotAccess(AccessRule.Import, slotInput, slotCell);
		handler.registerDefaultSlotAccess(AccessRule.Export, slotOutput, slotOutput2, slotOutput3, slotOutput4);
		handler.registerDefaultSlotsForSide(RotationList.UP, slotInput);
		handler.registerDefaultSlotsForSide(RotationList.DOWN, slotFuel);
		handler.registerDefaultSlotsForSide(RotationList.HORIZONTAL, slotCell);
		handler.registerDefaultSlotsForSide(RotationList.UP.getOppositeList(), slotOutput, slotOutput2, slotOutput3,
				slotOutput4);
		handler.registerInputFilter(new ArrayFilter(CommonFilters.DischargeEU, new BasicItemFilter(Items.REDSTONE),
				new BasicItemFilter(Ic2Items.suBattery)), slotFuel);
		handler.registerInputFilter(new BasicItemFilter(GTItems.glassTube), slotCell);
		handler.registerInputFilter(filter, slotInput);
		handler.registerOutputFilter(CommonFilters.NotDischargeEU, slotFuel);
		handler.registerSlotType(SlotType.Fuel, slotFuel);
		handler.registerSlotType(SlotType.Input, slotInput);
		handler.registerSlotType(SlotType.SecondInput, slotCell);
		handler.registerSlotType(SlotType.Output, slotOutput, slotOutput2, slotOutput3, slotOutput4);
	}

	@Override
	public LocaleComp getBlockName() {
		return new LocaleBlockComp(this.getBlockType().getUnlocalizedName());
	}

	@Override
	public ContainerIC2 getGuiContainer(EntityPlayer player) {
		return new GTContainerIndustrialCentrifuge(player.inventory, this);
	}

	@Override
	public IMachineRecipeList getRecipeList() {
		return RECIPE_LIST;
	}

	@Override
	public MachineType getType() {
		return null;
	}

	@Override
	public Class<? extends GuiScreen> getGuiClass(EntityPlayer player) {
		return GTIndustrialCentrifugeGui.class;
	}

	@Override
	public RecipeEntry getOutputFor(ItemStack input) {
		RecipeEntry entry = RECIPE_LIST.getRecipeInAndOutput(input, false);
		if (entry != null && getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput())) {
			return entry;
		}
		return null;
	}

	@Override
	protected EnumActionResult isRecipeStillValid(RecipeEntry entry) {
		if (getStackInSlot(slotCell).getCount() >= getRequiredCells(entry.getOutput())) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	@Override
	protected EnumActionResult canFillRecipeIntoOutputs(MachineOutput output) {
		List<ItemStack> result = output.getAllOutputs();
		for (int i = 0; i < result.size() && i < 4; i++) {
			ItemStack stack = getStackInSlot(slotOutput + i);
			ItemStack extra = result.get(i);
			if ((!stack.isEmpty() && !StackUtil.isStackEqual(stack, extra, false, true))
					|| stack.getCount() + extra.getCount() > extra.getMaxStackSize()) {
				return EnumActionResult.PASS;
			}
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void operateOnce(IRecipeInput input, MachineOutput output, List<IStackOutput> list) {
		List<ItemStack> result = output.getRecipeOutput(getWorld().rand, getTileData());
		for (int i = 0; i < result.size(); i++) {
			list.add(new SimpleStackOutput(result.get(i), slotOutput + (i % 4)));
		}
		consumeInput(input);
		getStackInSlot(slotCell).shrink(getRequiredCells(output));
	}

	@Override
	public ResourceLocation getGuiTexture() {
		return GUI_LOCATION;
	}

	public static int getRequiredCells(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			return 0;
		}
		return output.getMetadata().getInteger(CELL_REQUIREMENT);
	}

	public static int getRequiredEU(MachineOutput output) {
		if (output == null || output.getMetadata() == null) {
			return defaultLength * 12;
		}
		return (defaultLength + output.getMetadata().getInteger("RecipeTime")) * 12;
	}

	protected static NBTTagCompound createCellRequirement(int amount) {
		if (amount <= 0) {
			return null;
		}
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger(CELL_REQUIREMENT, amount);
		return nbt;
	}

	public static void addRecipe(ItemStack stack, int cellRequirement, OutputItem... results) {
		addRecipe(new RecipeInputItemStack(stack), cellRequirement, results);
	}

	public static void addRecipe(String id, int amount, int cellRequirement, OutputItem... results) {
		addRecipe(new RecipeInputOreDict(id, amount), cellRequirement, results);
	}

	public static void addRecipe(IRecipeInput input, int cellRequirement, OutputItem... results) {
		NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);
		for (OutputItem item : results) {
			list.set(item.slot, item.stack.copy());
		}
		addRecipe(input, new MachineOutput(createCellRequirement(cellRequirement), list));
	}

	public static void addRecipe(ItemStack stack, int cellRequirement, IRecipeModifier[] modifiers,
			OutputItem... results) {
		addRecipe(new RecipeInputItemStack(stack), cellRequirement, modifiers, results);
	}

	public static void addRecipe(String id, int amount, int cellRequirement, IRecipeModifier[] modifiers,
			OutputItem... results) {
		addRecipe(new RecipeInputOreDict(id, amount), cellRequirement, modifiers, results);
	}

	public static void addRecipe(IRecipeInput input, int cellRequirement, IRecipeModifier[] modifiers,
			OutputItem... results) {
		NonNullList<ItemStack> list = NonNullList.withSize(4, ItemStack.EMPTY);
		for (OutputItem item : results) {
			list.set(item.slot, item.stack.copy());
		}
		NBTTagCompound mods = new NBTTagCompound();
		for (IRecipeModifier modifier : modifiers) {
			modifier.apply(mods);
		}
		NBTTagCompound nbt = createCellRequirement(cellRequirement);
		if (!mods.hasNoTags()) {
			if (nbt == null)
				nbt = mods;
			else
				nbt.merge(mods);
		}
		addRecipe(input, new MachineOutput(nbt, list));
	}

	static void addRecipe(IRecipeInput input, MachineOutput output) {
		RECIPE_LIST.addRecipe(input, output, input.getInputs().get(0).getDisplayName());
	}

	public static class OutputItem {
		ItemStack stack;
		int slot;

		public OutputItem(ItemStack result, int slotIndex) {
			stack = result;
			slot = slotIndex;
		}
	}
}
