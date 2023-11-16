package cn.gtcommunity.gregtinker.material;

import cn.gtcommunity.gregtinker.trait.GTinkerTraits;
import cn.gtcommunity.gregtinker.trait.TraitGravitation;
import com.google.common.eventbus.Subscribe;
import gregicality.multiblocks.api.unification.GCYMMaterials;
import gregtech.api.unification.material.Materials;
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
    public static Material LAPOTRON;
    public static Material ALMANDINE;
    public static Material PYROPE;
    public static Material GROSSULAR;
    public static Material SODALITE;
    public static Material LAZURITE;

    public static Material WROUGHT_IRON;
    public static Material STAINLESS_STEEL;
    public static Material DAMASCUS_STEEL;
    public static Material STERLING_SILVER;
    public static Material ROSE_GOLD;
    public static Material RED_ALLOY;
    public static Material BOROSILICATE_GLASS;
    public static Material VANADIUM_STEEL;
    public static Material TUNGSTEN_STEEL;
    public static Material BLACK_STEEL;
    public static Material POLYTETRAFLUOROETHYLENE;
    public static Material MANGANESE_PHOSPHIDE;
    public static Material SOLDERING_ALLOY;
    public static Material RURIDIT;
    public static Material BLUE_ALLOY;
    public static Material EPOXY;
    public static Material PLASTIC;
    public static Material RHODIUM_PLATED_PALLADIUM;
    public static Material ULTIMET;
    public static Material URANIUM_TRIPLATINUM;
    public static Material OSMIRIDIUM;
    public static Material HSSG;
    public static Material HSSE;
    public static Material HSSS;
    public static Material NAQUADAH;
    public static Material NAQUADAH_ENRICHED;
    public static Material NEUTRONIUM;

    //  GCYM
    public static Material TANTALUM_CARBIDE;
    public static Material TITANIUM_CARBIDE;
    public static Material ZERON_100;
    public static Material TRINAQUADALLOY;
    public static Material HASTELLOY_X;
    public static Material HASTELLOY_C276;
    public static Material INCOLOY_MA_956;
    public static Material WATERTIGHT_STEEL;
    public static Material MOLYBDENUM_DISILICIDE;
    public static Material STELLITE_100;
    public static Material TITANIUM_TUNGSTEN_CARBIDE;
    public static Material MARAGING_STEEL_300;
    public static Material HSLA_STEEL;
    public static void init()
    {
        LAPOTRON = new MaterialBuilder("lapotron", 0x0000FF, MaterialForm.GEM, "Lapotron")
                .requiresMods("gregtech")
                .requiresOres("gemLapotron")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        ALMANDINE = new MaterialBuilder("almandine", 0xFF0000, MaterialForm.GEM, "Almandine")
                .requiresMods("gregtech")
                .requiresOres("gemAlmandine")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        PYROPE = new MaterialBuilder("pyrope", 0x953553, MaterialForm.GEM, "Pyrope")
                .requiresMods("gregtech")
                .requiresOres("gemPyrope")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        GROSSULAR = new MaterialBuilder("grossular", 0xF28C28, MaterialForm.GEM, "Grossular")
                .requiresMods("gregtech")
                .requiresOres("gemGrossular")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        SODALITE = new MaterialBuilder("sodalite", 0x3F00FF, MaterialForm.GEM, "Sodalite")
                .requiresMods("gregtech")
                .requiresOres("gemSodalite")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        LAZURITE = new MaterialBuilder("lazurite", 0xB6D0E2, MaterialForm.GEM, "Lazurite")
                .requiresMods("gregtech")
                .requiresOres("gemLazurite")
                .setCraftable()
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        WROUGHT_IRON = new MaterialBuilder("wrought_iron", 0xC4A484, MaterialForm.METAL, "WroughtIron")
                .requiresMods("gregtech")
                .requiresOres("ingotWroughtIron")
                .setCastable(() -> Materials.WroughtIron.getFluid(), 380)
                .withStatsHead(350, 6.5F, 5F, HarvestLevels.DIAMOND)
                .withStatsHandle(0.9F, 125)
                .withStatsExtra(50)
                .withTraits(PartType.MAIN, TinkerTraits.dense)
                .withTraits(PartType.TOOL, TinkerTraits.magnetic)
                .build();
        STAINLESS_STEEL = new MaterialBuilder("stainless_steel", 0xE5E4E2, MaterialForm.METAL, "StainlessSteel")
                .requiresMods("gregtech")
                .requiresOres("ingotStainlessSteel")
                .setCastable(() -> Materials.StainlessSteel.getFluid(), 1400)
                .withStatsHead(1000, 7F, 8F, HarvestLevels.OBSIDIAN)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.CORROSION_RESISTANCE)
                .withTraits(PartType.TOOL, TinkerTraits.magnetic2)
                .build();
        DAMASCUS_STEEL = new MaterialBuilder("damascus_steel", 0x575757, MaterialForm.METAL, "DamascusSteel")
                .requiresMods("gregtech")
                .requiresOres("ingotDamascusSteel")
                .setCastable(() -> Materials.DamascusSteel.getFluid(), 1200)
                .withStatsHead(1000, 8F, 10F, HarvestLevels.OBSIDIAN)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.CHOPPING)
                .withTraits(PartType.TOOL, TinkerTraits.sharp)
                .build();
        STERLING_SILVER = new MaterialBuilder("sterling_silver", 0xFFF5EE, MaterialForm.METAL, "SterlingSilver")
                .requiresMods("gregtech")
                .requiresOres("ingotSterlingSilver")
                .setCastable(() -> Materials.SterlingSilver.getFluid(), 1400)
                .withStatsHead(500, 5F, 5F, HarvestLevels.IRON)
                .withStatsHandle(0.95F, 100)
                .withStatsExtra(200)
                .withTraits(PartType.MAIN, GTinkerTraits.EXORCISM)
                .withTraits(PartType.AUX, TinkerTraits.holy)
                .build();
        ROSE_GOLD = new MaterialBuilder("rose_gold", 0xFFC000, MaterialForm.METAL, "RoseGold")
                .requiresMods("gregtech")
                .requiresOres("ingotRoseGold")
                .setCastable(() -> Materials.RoseGold.getFluid(), 1300)
                .withStatsHead(50, 12F, 3F, HarvestLevels.IRON)
                .withStatsHandle(1.1F, -25)
                .withStatsExtra(250)
                .withTraits(PartType.HEAD, GTinkerTraits.VENEER2)
                .withTraits(PartType.DEFAULT, GTinkerTraits.VENEER)
                .build();
        RED_ALLOY = new MaterialBuilder("red_alloy", 0xD22B2B, MaterialForm.METAL, "RedAlloy")
                .requiresMods("gregtech")
                .requiresOres("ingotRedAlloy")
                .setCastable(() -> Materials.RedAlloy.getFluid(), 480)
                .withStatsHead(50, 12F, 3F, HarvestLevels.IRON)
                .withStatsHandle(1.1F, -25)
                .withStatsExtra(250)
                .withTraits(PartType.HEAD, GTinkerTraits.VENEER2)
                .withTraits(PartType.TOOL, GTinkerTraits.VENEER)
                .build();
        BOROSILICATE_GLASS = new MaterialBuilder("borosilicate_glass", 0xB2BEB5, MaterialForm.METAL, "BorosilicateGlass")
                .requiresMods("gregtech")
                .requiresOres("ingotBorosilicateGlass")
                .setCastable(() -> Materials.BorosilicateGlass.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        VANADIUM_STEEL = new MaterialBuilder("vanadium_steel", 0xA9A9A9, MaterialForm.METAL, "VanadiumSteel")
                .requiresMods("gregtech")
                .requiresOres("ingotVanadiumSteel")
                .setCastable(() -> Materials.VanadiumSteel.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        TUNGSTEN_STEEL = new MaterialBuilder("tungsten_steel", 0x6F8FAF, MaterialForm.METAL, "TungstenSteel")
                .requiresMods("gregtech")
                .requiresOres("ingotTungstenSteel")
                .setCastable(() -> Materials.TungstenSteel.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        BLACK_STEEL = new MaterialBuilder("black_steel", 0x71797E, MaterialForm.METAL, "BlackSteel")
                .requiresMods("gregtech")
                .requiresOres("ingotBlackSteel")
                .setCastable(() -> Materials.BlackSteel.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        POLYTETRAFLUOROETHYLENE = new MaterialBuilder("polytetrafluoroethylene", 0x818589, MaterialForm.METAL, "Polytetrafluoroethylene")
                .requiresMods("gregtech")
                .requiresOres("ingotPolytetrafluoroethylene")
                .setCastable(() -> Materials.Polytetrafluoroethylene.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        MANGANESE_PHOSPHIDE = new MaterialBuilder("manganese_phosphide", 0xE1C16E, MaterialForm.METAL, "ManganesePhosphide")
                .requiresMods("gregtech")
                .requiresOres("ingotManganesePhosphide")
                .setCastable(() -> Materials.ManganesePhosphide.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        SOLDERING_ALLOY = new MaterialBuilder("soldering_alloy", 0xA9A9A9, MaterialForm.METAL, "SolderingAlloy")
                .requiresMods("gregtech")
                .requiresOres("ingotSolderingAlloy")
                .setCastable(() -> Materials.SolderingAlloy.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        RURIDIT = new MaterialBuilder("ruridit", 0x93C572, MaterialForm.METAL, "Ruridit")
                .requiresMods("gregtech")
                .requiresOres("ingotRuridit")
                .setCastable(() -> Materials.Ruridit.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        BLUE_ALLOY = new MaterialBuilder("blue_alloy", 0x87CEEB, MaterialForm.METAL, "BlueAlloy")
                .requiresMods("gregtech")
                .requiresOres("ingotBlueAlloy")
                .setCastable(() -> Materials.BlueAlloy.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        EPOXY = new MaterialBuilder("epoxy", 0xF4C430, MaterialForm.METAL, "Epoxy")
                .requiresMods("gregtech")
                .requiresOres("ingotEpoxy")
                .setCastable(() -> Materials.Epoxy.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        PLASTIC = new MaterialBuilder("plastic", 0xD3D3D3, MaterialForm.METAL, "Plastic")
                .requiresMods("gregtech")
                .requiresOres("ingotPlastic")
                .setCastable(() -> Materials.Polyethylene.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        RHODIUM_PLATED_PALLADIUM = new MaterialBuilder("rhodium_plated_palladium", 0xF2D2BD, MaterialForm.METAL, "RhodiumPlatedPalladium")
                .requiresMods("gregtech")
                .requiresOres("ingotRhodiumPlatedPalladium")
                .setCastable(() -> Materials.RhodiumPlatedPalladium.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        ULTIMET = new MaterialBuilder("ultimet", 0xCCCCFF, MaterialForm.METAL, "Ultimet")
                .requiresMods("gregtech")
                .requiresOres("ingotUltimet")
                .setCastable(() -> Materials.Ultimet.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        URANIUM_TRIPLATINUM = new MaterialBuilder("uranium_triplatinum", 0x00A36C, MaterialForm.METAL, "UraniumTriplatinum")
                .requiresMods("gregtech")
                .requiresOres("ingotUraniumTriplatinum")
                .setCastable(() -> Materials.UraniumTriplatinum.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        OSMIRIDIUM = new MaterialBuilder("osmiridium", 0x6495ED, MaterialForm.METAL, "Osmiridium")
                .requiresMods("gregtech")
                .requiresOres("ingotOsmiridium")
                .setCastable(() -> Materials.Osmiridium.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HSSG = new MaterialBuilder("hssg", 0xC4B454, MaterialForm.METAL, "Hssg")
                .requiresMods("gregtech")
                .requiresOres("ingotHssg")
                .setCastable(() -> Materials.HSSG.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HSSE = new MaterialBuilder("hsse", 0x4F7942, MaterialForm.METAL, "Hsse")
                .requiresMods("gregtech")
                .requiresOres("ingotHsse")
                .setCastable(() -> Materials.HSSE.getFluid(), 4700)
                .withStatsHead(1500, 7F, 8F, 5)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.BETA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HSSS = new MaterialBuilder("hsss", 0x8B0000, MaterialForm.METAL, "Hsss")
                .requiresMods("gregtech")
                .requiresOres("ingotHsss")
                .setCastable(() -> Materials.HSSS.getFluid(), 4700)
                .withStatsHead(1750, 7F, 8F, 5)
                .withStatsHandle(1.2F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.OMEGA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        NAQUADAH = new MaterialBuilder("naquadah", 0x28282B, MaterialForm.METAL, "Naquadah")
                .requiresMods("gregtech")
                .requiresOres("ingotNaquadah")
                .setCastable(() -> Materials.Naquadah.getFluid(), 4700)
                .withStatsHead(3000, 10F, 15F, 6)
                .withStatsHandle(6F, 1500)
                .withStatsExtra(500)
                .withTraits(PartType.MAIN, GTinkerTraits.INERTIA)
                .withTraits(PartType.TOOL, GTinkerTraits.SUPERTIGHT)
                .build();
        NAQUADAH_ENRICHED = new MaterialBuilder("naquadah_enriched", 0x28282B, MaterialForm.METAL, "NaquadahEnriched")
                .requiresMods("gregtech")
                .requiresOres("ingotNaquadahEnriched")
                .setCastable(() -> Materials.NaquadahEnriched.getFluid(), 5700)
                .withStatsHead(3500, 12F, 20F, 7)
                .withStatsHandle(8F, 1750)
                .withStatsExtra(750)
                .withTraits(PartType.MAIN, GTinkerTraits.IRRADIATION)
                .withTraits(PartType.TOOL, GTinkerTraits.SUPERTIGHT)
                .build();
        NEUTRONIUM = new MaterialBuilder("neutronium", 0xC0C0C0, MaterialForm.METAL, "Neutronium")
                .requiresMods("gregtech")
                .requiresOres("ingotNeutronium")
                .setCastable(() -> Materials.Neutronium.getFluid(), 9999)
                .withStatsHead(9000, 15F, 105F, 10)
                .withStatsHandle(10F, 4500)
                .withStatsExtra(1000)
                .withTraits(PartType.MAIN, GTinkerTraits.GRAVITATION)
                .withTraits(PartType.TOOL, GTinkerTraits.UNBREAKABLE)
                .build();

        //  GCYM
        TANTALUM_CARBIDE = new MaterialBuilder("tantalum_carbide", 0x71797E, MaterialForm.METAL, "TantalumCarbide")
                .requiresMods("gcym")
                .requiresOres("ingotTantalumCarbide")
                .setCastable(() -> GCYMMaterials.TantalumCarbide.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        TITANIUM_CARBIDE = new MaterialBuilder("titanium_carbide", 0xA42A04, MaterialForm.METAL, "TitaniumCarbide")
                .requiresMods("gcym")
                .requiresOres("ingotTitaniumCarbide")
                .setCastable(() -> GCYMMaterials.TitaniumCarbide.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        ZERON_100 = new MaterialBuilder("zeron_100", 0x6082B6, MaterialForm.METAL, "Zeron100")
                .requiresMods("gcym")
                .requiresOres("ingotZeron100")
                .setCastable(() -> GCYMMaterials.Zeron100.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        TRINAQUADALLOY = new MaterialBuilder("trinaquadalloy", 0x483248, MaterialForm.METAL, "Trinaquadalloy")
                .requiresMods("gcym")
                .requiresOres("ingotTrinaquadalloy")
                .setCastable(() -> GCYMMaterials.Trinaquadalloy.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HASTELLOY_X = new MaterialBuilder("hastelloy_x", 0xA7C7E7, MaterialForm.METAL, "HastelloyX")
                .requiresMods("gcym")
                .requiresOres("ingotHastelloyX")
                .setCastable(() -> GCYMMaterials.HastelloyX.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HASTELLOY_C276 = new MaterialBuilder("hastelloy_c276", 0xFA5F55, MaterialForm.METAL, "HastelloyC276")
                .requiresMods("gcym")
                .requiresOres("ingotHastelloyC276")
                .setCastable(() -> GCYMMaterials.HastelloyC276.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        INCOLOY_MA_956 = new MaterialBuilder("incoloy_ma_956", 0x2AAA8A, MaterialForm.METAL, "IncoloyMa956")
                .requiresMods("gcym")
                .requiresOres("ingotIncoloyMa956")
                .setCastable(() -> GCYMMaterials.IncoloyMA956.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        WATERTIGHT_STEEL = new MaterialBuilder("watertight_steel", 0x4682B4, MaterialForm.METAL, "WatertightSteel")
                .requiresMods("gcym")
                .requiresOres("ingotWatertightSteel")
                .setCastable(() -> GCYMMaterials.WatertightSteel.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        MOLYBDENUM_DISILICIDE = new MaterialBuilder("molybdenum_disilicide", 0x5D3FD3, MaterialForm.METAL, "MolybdenumDisilicide")
                .requiresMods("gcym")
                .requiresOres("ingotMolybdenumDisilicide")
                .setCastable(() -> GCYMMaterials.MolybdenumDisilicide.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        STELLITE_100 = new MaterialBuilder("stellite_100", 0xCCCCFF, MaterialForm.METAL, "Stellite100")
                .requiresMods("gcym")
                .requiresOres("ingotStellite100")
                .setCastable(() -> GCYMMaterials.Stellite100.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        TITANIUM_TUNGSTEN_CARBIDE = new MaterialBuilder("titanium_tungsten_carbide", 0x770737, MaterialForm.METAL, "TitaniumTungstenCarbide")
                .requiresMods("gcym")
                .requiresOres("ingotTitaniumTungstenCarbide")
                .setCastable(() -> GCYMMaterials.TitaniumTungstenCarbide.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        MARAGING_STEEL_300 = new MaterialBuilder("maraging_steel_300", 0x7393B3, MaterialForm.METAL, "MaragingSteel300")
                .requiresMods("gcym")
                .requiresOres("ingotMaragingSteel300")
                .setCastable(() -> GCYMMaterials.MaragingSteel300.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
        HSLA_STEEL = new MaterialBuilder("hsla_steel", 0x808080, MaterialForm.METAL, "HslaSteel")
                .requiresMods("gcym")
                .requiresOres("ingotHslaSteel")
                .setCastable(() -> GCYMMaterials.HSLASteel.getFluid(), 3900)
                .withStatsHead(1500, 7F, 8F, HarvestLevels.COBALT)
                .withStatsHandle(1.1F, 300)
                .withStatsExtra(100)
                .withTraits(PartType.MAIN, GTinkerTraits.ALPHA)
                .withTraits(PartType.TOOL, TinkerTraits.duritos)
                .build();
    }
}
