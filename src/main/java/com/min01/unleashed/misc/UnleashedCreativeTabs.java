package com.min01.unleashed.misc;

import com.min01.unleashed.BossesUnleashed;
import com.min01.unleashed.item.UnleashedItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UnleashedCreativeTabs 
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BossesUnleashed.MODID);

    public static final RegistryObject<CreativeModeTab> BOSSES_UNLEASHED = CREATIVE_MODE_TAB.register("unleashed", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.unleashed"))
    		.icon(() -> new ItemStack(UnleashedItems.CELESTIAL_CORE.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			for(RegistryObject<Item> item : UnleashedItems.ITEMS.getEntries())
    			{
    				output.accept(item.get());
    			}
    		}).build());
}
