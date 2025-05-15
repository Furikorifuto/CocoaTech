package com.ib.cocoa_tech;

import com.ib.cocoa_tech.util.ModRegistry;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "cocoa_tech";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        ModRegistry.ITEMS.register(modEventBus);
        ModRegistry.BLOCKS.register(modEventBus);
    }
}
