package cn.gtcommunity.gregtinker.material;

import cn.gtcommunity.gregtinker.trait.GTinkerTraits;
import cn.gtcommunity.gregtinker.trait.TraitGravitation;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.mantle.pulsar.pulse.Pulse;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTraits;

public class GTinkerMaterials
{
    public static Material WROUGHT_IRON;
    public static Material STAINLESS_STEEL;
    public static Material DAMASCUS_STEEL;
    public static Material STERLING_SILVER;
    public static Material ROSE_GOLD;
    public static Material RED_ALLOY;
    public static Material HSSG;
    public static Material HSSE;
    public static Material HSSS;
    public static Material NAQUADAH;
    public static Material NAQUADAH_ENRICHED;
    public static Material NEUTRONIUM;
    public static void init()
    {
        WROUGHT_IRON = new MaterialBuilder("wrought_iron", 0xC4A484, MaterialForm.METAL, "WroughtIron")
                .requiresOres("ingotWroughtIron")
                .setCastable(380)
                .withStatsHead(350, 6.5F, 5F, HarvestLevels.DIAMOND)
                .withStatsHandle(0.9F, 125)
                .withStatsExtra(50)
                .withTraits(PartType.MAIN, TinkerTraits.dense)
                .withTraits(PartType.TOOL, TinkerTraits.magnetic)
                .build();
        STAINLESS_STEEL = new MaterialBuilder("stainless_steel", 0xE5E4E2, MaterialForm.METAL, "StainlessSteel")
                .requiresOres("ingotStainlessSteel")
                .setCastable(1400)
                .withStatsHead(1000, 7F, 8F, HarvestLevels.OBSIDIAN)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.CORROSION_RESISTANCE)
                .withTraits(PartType.TOOL, TinkerTraits.magnetic2)
                .build();
        DAMASCUS_STEEL = new MaterialBuilder("damascus_steel", 0x575757, MaterialForm.METAL, "DamascusSteel")
                .requiresOres("ingotDamascusSteel")
                .setCastable(1200)
                .withStatsHead(1000, 8F, 10F, HarvestLevels.OBSIDIAN)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.CHOPPING)
                .withTraits(PartType.TOOL, GTinkerTraits.UNBREAKABLE)
                .build();
        STERLING_SILVER = new MaterialBuilder("sterling_silver", 0xFFF5EE, MaterialForm.METAL, "SterlingSilver")
                .requiresOres("ingotSterlingSilver")
                .setCastable(1400)
                .withStatsHead(500, 5F, 5F, HarvestLevels.IRON)
                .withStatsHandle(0.95F, 100)
                .withStatsExtra(200)
                .withTraits(PartType.MAIN, GTinkerTraits.EXORCISM)
                .withTraits(PartType.AUX, TinkerTraits.holy)
                .build();
        ROSE_GOLD = new MaterialBuilder("rose_gold", 0xFFC000, MaterialForm.METAL, "RoseGold")
                .requiresOres("ingotRoseGold")
                .setCastable(1300)
                .withStatsHead(50, 12F, 3F, HarvestLevels.IRON)
                .withStatsHandle(1.1F, -25)
                .withStatsExtra(250)
                .withTraits(PartType.HEAD, GTinkerTraits.VENEER2)
                .withTraits(PartType.DEFAULT, GTinkerTraits.VENEER)
                .build();
        RED_ALLOY = new MaterialBuilder("red_alloy", 0xD22B2B, MaterialForm.METAL, "RedAlloy")
                .requiresOres("ingotRedAlloy")
                .setCastable(480)
                .withStatsHead(50, 12F, 3F, HarvestLevels.IRON)
                .withStatsHandle(1.1F, -25)
                .withStatsExtra(250)
                .withTraits(PartType.HEAD, GTinkerTraits.VENEER2)
                .withTraits(PartType.TOOL, GTinkerTraits.VENEER)
                .build();
        HSSG = new MaterialBuilder("hssg", 0xC4B454, MaterialForm.METAL, "Hssg")
                .requiresOres("ingotHssg")
                .setCastable(3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, GTinkerTraits.VENEER)
                .build();
        HSSE = new MaterialBuilder("hsse", 0x4F7942, MaterialForm.METAL, "Hsse")
                .requiresOres("ingotHsse")
                .setCastable(4700)
                .withStatsHead(1500, 7F, 8F, 5)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.BETA)
                .withTraits(PartType.TOOL, GTinkerTraits.VENEER)
                .build();
        HSSS = new MaterialBuilder("hsss", 0x8B0000, MaterialForm.METAL, "Hsss")
                .requiresOres("ingotHsss")
                .setCastable(4700)
                .withStatsHead(1750, 7F, 8F, 5)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.OMEGA)
                .withTraits(PartType.TOOL, GTinkerTraits.VENEER)
                .build();
        NAQUADAH = new MaterialBuilder("naquadah", 0x28282B, MaterialForm.METAL, "Naquadah")
                .requiresOres("ingotNaquadah")
                .setCastable(4700)
                .withStatsHead(3000, 10F, 15F, 6)
                .withStatsHandle(6F, 1500)
                .withStatsExtra(500)
                .withTraits(PartType.MAIN, GTinkerTraits.INERTIA)
                .withTraits(PartType.TOOL, GTinkerTraits.SUPERTIGHT)
                .build();
        NAQUADAH_ENRICHED = new MaterialBuilder("naquadah_enriched", 0x28282B, MaterialForm.METAL, "NaquadahEnriched")
                .requiresOres("ingotNaquadahEnriched")
                .setCastable(5700)
                .withStatsHead(3500, 12F, 20F, 7)
                .withStatsHandle(8F, 1750)
                .withStatsExtra(750)
                .withTraits(PartType.MAIN, GTinkerTraits.IRRADIATION)
                .withTraits(PartType.TOOL, GTinkerTraits.SUPERTIGHT)
                .build();
        NEUTRONIUM = new MaterialBuilder("neutronium", 0xC0C0C0, MaterialForm.METAL, "Neutronium")
                .requiresOres("ingotNeutronium")
                .setCastable(9999)
                .withStatsHead(9000, 15F, 105F, 10)
                .withStatsHandle(10F, 4500)
                .withStatsExtra(1000)
                .withTraits(PartType.MAIN, GTinkerTraits.GRAVITATION)
                .withTraits(PartType.TOOL, GTinkerTraits.UNBREAKABLE)
                .build();
    }
}
