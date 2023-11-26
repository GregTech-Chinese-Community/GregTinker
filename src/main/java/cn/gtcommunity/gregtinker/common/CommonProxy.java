package cn.gtcommunity.gregtinker.common;

import cn.gtcommunity.gregtinker.api.capability.GTCapabilities;
import cn.gtcommunity.gregtinker.api.utils.GTiLog;
import cn.gtcommunity.gregtinker.client.book.BookTransformerAppendModifiers;
import cn.gtcommunity.gregtinker.handler.ToolCapabilityHandler;
import cn.gtcommunity.gregtinker.material.GTinkerMaterials;
import cn.gtcommunity.gregtinker.material.MaterialDefinition;
import cn.gtcommunity.gregtinker.trait.GTinkerTraits;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;

public class CommonProxy
{
    private final ToolCapabilityHandler toolCapHandler = new ToolCapabilityHandler();

    public CommonProxy()
    {/**/}

    public ToolCapabilityHandler getToolCapHandler() {
        return toolCapHandler;
    }

    public void onPreInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(toolCapHandler);
        GTiLog.init(event.getModLog());
        GTinkerMaterials.init();
        GTCapabilities.init();
        preLoad();
    }

    public void onInit(FMLInitializationEvent event)
    {
        MaterialDefinition.initMaterialProperties();
        GTinkerTraits.initModifierMaterials();
    }

    public void onPostInit(FMLPostInitializationEvent event)
    {
        TinkerBook.INSTANCE.addTransformer(new BookTransformerAppendModifiers(
                new FileRepository("tconstruct:book"), false, c -> c.acceptAll(GTinkerTraits.MODIFIERS)));
    }

    public void preLoad()
    {

    }
}
