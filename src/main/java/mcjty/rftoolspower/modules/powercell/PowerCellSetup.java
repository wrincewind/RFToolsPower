package mcjty.rftoolspower.modules.powercell;

import mcjty.rftoolspower.modules.powercell.blocks.PowerCellBlock;
import mcjty.rftoolspower.modules.powercell.blocks.PowerCellTileEntity;
import mcjty.rftoolspower.modules.powercell.data.Tier;
import mcjty.rftoolspower.modules.powercell.items.PowerCoreItem;
import mcjty.rftoolspower.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import static mcjty.rftoolspower.setup.Registration.*;

public class PowerCellSetup {

    public static void register() {
        // Needed to force class loading
    }

    public static final RegistryObject<Block> CELL1 = BLOCKS.register("cell1", () -> new PowerCellBlock(Tier.TIER1));
    public static final RegistryObject<Item> CELL1_ITEM = ITEMS.register("cell1", () -> new BlockItem(CELL1.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_CELL1 = TILES.register("cell1", () -> TileEntityType.Builder.create(() -> new PowerCellTileEntity(Tier.TIER1), CELL1.get()).build(null));

    public static final RegistryObject<Block> CELL2 = BLOCKS.register("cell2", () -> new PowerCellBlock(Tier.TIER2));
    public static final RegistryObject<Item> CELL2_ITEM = ITEMS.register("cell2", () -> new BlockItem(CELL2.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_CELL2 = TILES.register("cell2", () -> TileEntityType.Builder.create(() -> new PowerCellTileEntity(Tier.TIER2), CELL2.get()).build(null));

    public static final RegistryObject<Block> CELL3 = BLOCKS.register("cell3", () -> new PowerCellBlock(Tier.TIER3));
    public static final RegistryObject<Item> CELL3_ITEM = ITEMS.register("cell3", () -> new BlockItem(CELL3.get(), Registration.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_CELL3 = TILES.register("cell3", () -> TileEntityType.Builder.create(() -> new PowerCellTileEntity(Tier.TIER3), CELL3.get()).build(null));

    public static final RegistryObject<Item> POWER_CORE1 = ITEMS.register("power_core1", PowerCoreItem::new);
    public static final RegistryObject<Item> POWER_CORE2 = ITEMS.register("power_core2", PowerCoreItem::new);
    public static final RegistryObject<Item> POWER_CORE3 = ITEMS.register("power_core3", PowerCoreItem::new);
}
