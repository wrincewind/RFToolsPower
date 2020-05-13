package mcjty.rftoolspower.modules.dimensionalcell;

import mcjty.lib.container.GenericContainer;
import mcjty.rftoolspower.modules.dimensionalcell.blocks.*;
import mcjty.rftoolspower.modules.dimensionalcell.items.PowerCellCardItem;
import mcjty.rftoolspower.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import static mcjty.rftoolspower.setup.Registration.*;

public class DimensionalCellSetup {

    public static void register() {
        // Needed to force class loading
    }

    public static final RegistryObject<Block> DIMENSIONAL_CELL = BLOCKS.register("dimensionalcell", () -> new DimensionalCellBlock(DimensionalCellType.NORMAL, DimensionalCellNormalTileEntity::new));
    public static final RegistryObject<Item> DIMENSIONAL_CELL_ITEM = ITEMS.register("dimensionalcell", () -> new BlockItem(DIMENSIONAL_CELL.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_DIMENSIONAL_CELL = TILES.register("dimensionalcell", () -> TileEntityType.Builder.create(DimensionalCellNormalTileEntity::new, DIMENSIONAL_CELL.get()).build(null));

    public static final RegistryObject<Block> DIMENSIONAL_CELL_ADVANCED = BLOCKS.register("dimensionalcell_advanced", () -> new DimensionalCellBlock(DimensionalCellType.ADVANCED, DimensionalCellAdvancedTileEntity::new));
    public static final RegistryObject<Item> DIMENSIONAL_CELL_ADVANCED_ITEM = ITEMS.register("dimensionalcell_advanced", () -> new BlockItem(DIMENSIONAL_CELL_ADVANCED.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_DIMENSIONAL_CELL_ADVANCED = TILES.register("dimensionalcell_advanced", () -> TileEntityType.Builder.create(DimensionalCellAdvancedTileEntity::new, DIMENSIONAL_CELL_ADVANCED.get()).build(null));

    public static final RegistryObject<Block> DIMENSIONAL_CELL_SIMPLE = BLOCKS.register("dimensionalcell_simple", () -> new DimensionalCellBlock(DimensionalCellType.SIMPLE, DimensionalCellSimpleTileEntity::new));
    public static final RegistryObject<Item> DIMENSIONAL_CELL_SIMPLE_ITEM = ITEMS.register("dimensionalcell_simple", () -> new BlockItem(DIMENSIONAL_CELL_SIMPLE.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_DIMENSIONAL_CELL_SIMPLE = TILES.register("dimensionalcell_simple", () -> TileEntityType.Builder.create(DimensionalCellSimpleTileEntity::new, DIMENSIONAL_CELL_SIMPLE.get()).build(null));

    public static final RegistryObject<Block> DIMENSIONAL_CELL_CREATIVE = BLOCKS.register("dimensionalcell_creative", () -> new DimensionalCellBlock(DimensionalCellType.CREATIVE, DimensionalCellCreativeTileEntity::new));
    public static final RegistryObject<Item> DIMENSIONAL_CELL_CREATIVE_ITEM = ITEMS.register("dimensionalcell_creative", () -> new BlockItem(DIMENSIONAL_CELL_CREATIVE.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_DIMENSIONAL_CELL_CREATIVE = TILES.register("dimensionalcell_creative", () -> TileEntityType.Builder.create(DimensionalCellCreativeTileEntity::new, DIMENSIONAL_CELL_CREATIVE.get()).build(null));

    public static final RegistryObject<ContainerType<DimensionalCellContainer>> CONTAINER_DIMENSIONAL_CELL = CONTAINERS.register("dimensionalcell", GenericContainer::createContainerType);

    public static final RegistryObject<PowerCellCardItem> POWERCELL_CARD = ITEMS.register("powercell_card", PowerCellCardItem::new);
}
