package cn.gtcommunity.gregtinker;

import cn.gtcommunity.gregtinker.api.utils.GTiLog;
import cn.gtcommunity.gregtinker.common.CommonProxy;
import cn.gtcommunity.gregtinker.material.GTinkerMaterials;
import cn.gtcommunity.gregtinker.material.MaterialDefinition;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = GregTinker.MODID,
        name = GregTinker.NAME,
        version = GregTinker.VERSION,
        dependencies = GregTinker.DEPENDENCIES
)
public class GregTinker
{
    public static final String MODID = "gregtinker";
    public static final String NAME = "GregTinker";
    public static final String VERSION = "0.0.1";
    public static final String DEPENDENCIES = "required-after:gcym; required-after:tconstruct";

    @Mod.Instance(GregTinker.MODID)
    public static GregTinker INSTANCE;

    @SidedProxy(
            modId = MODID,
            clientSide = "cn.gtcommunity.gregtinker.client.ClientProxy",
            serverSide = "cn.gtcommunity.gregtinker.common.CommonProxy"
    )
    public static CommonProxy proxy;

    public GregTinker()
    {/**/}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GTiLog.init(event.getModLog());
        GTinkerMaterials.init();
        proxy.preLoad();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        MaterialDefinition.initMaterialProperties();
    }

    @Mod.EventHandler
    public void onImcReceived(FMLInterModComms.IMCEvent event)
    {
        // we need this to happen before tcon's post-init finishes and the imc handling event just happens to be convenient
        MaterialDefinition.activate();
    }
}

