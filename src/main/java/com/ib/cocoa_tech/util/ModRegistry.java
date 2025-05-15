package com.ib.cocoa_tech.util;

import com.ib.cocoa_tech.Main;
import com.ib.cocoa_tech.blocks.WetBrick;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Main.MOD_ID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Main.MOD_ID);
//item
    public static final DeferredItem<Item> COCOA_COG = ITEMS.registerSimpleItem("cocoa_cog");
//block
    public static final DeferredBlock<Block> WET_BRICK = BLOCKS.register(
            "wet_brick",registryName ->new WetBrick(
                    BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK,registryName))));
    //blockItem
    public static final DeferredItem<BlockItem> WET_BRICK_ITEM = ITEMS.registerSimpleBlockItem(WET_BRICK);
}
