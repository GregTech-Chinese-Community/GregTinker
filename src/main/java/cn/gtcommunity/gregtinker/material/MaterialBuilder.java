package cn.gtcommunity.gregtinker.material;

import cn.gtcommunity.gregtinker.GregTinker;
import cn.gtcommunity.gregtinker.api.reflect.CraftReflect;
import cn.gtcommunity.gregtinker.api.reflect.GTReflect;
import cn.gtcommunity.gregtinker.api.utils.GTiLog;
import cn.gtcommunity.gregtinker.api.utils.collection.LazyAccum;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.ITrait;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

public class MaterialBuilder
{
    private final String matId;
    private final int colour;
    private final MaterialForm form;
    private final String oreName;

    private boolean needsPriority = false;
    private final List<RegCondition> conditions = new ArrayList<>();
    private final Map<String, IMaterialStats> materialStats = new HashMap<>();
    private boolean craftable = false, castable = false;
    @Nullable
    private Supplier<Fluid> fluidGetter = null;
    private int fluidTemperature = 273; // used for generated fluids
    private final Map<PartType, LazyAccum<ITrait>> traits = new EnumMap<>(PartType.class);

    public MaterialBuilder(String matId, int colour, MaterialForm form, String oreName)
    {
        this.matId = matId;
        this.colour = colour;
        this.form = form;
        this.oreName = oreName;
    }

    public MaterialBuilder needsPriority()
    {
        needsPriority = true;
        return this;
    }

    public MaterialBuilder requires(RegCondition condition)
    {
        conditions.add(condition);
        return this;
    }

    public MaterialBuilder requiresMods(String... mods)
    {
        for (String mod : mods) {
            requires(new RegCondition.ModLoaded(mod));
        }
        return this;
    }

    public MaterialBuilder requiresOres(String... oreKeys)
    {
        for (String oreKey : oreKeys) {
            requires(new RegCondition.OreDictExists(oreKey));
        }
        return this;
    }

    public MaterialBuilder requiresMaterials(Material... materials)
    {
        for (Material material : materials) {
            requires(new RegCondition.MaterialVisible(material));
        }
        return this;
    }

    public MaterialBuilder overrides(String... matIds)
    {
        for (String matId : matIds) {
            requires(new RegCondition.MaterialCanOverride(matId));
        }
        return this;
    }

    public MaterialBuilder withStats(IMaterialStats statsObj)
    {
        materialStats.put(statsObj.getIdentifier(), statsObj);
        return this;
    }

    public MaterialBuilder withStatsHead(int durability, float miningSpeed, float attack, int harvestLevel)
    {
        return withStats(new HeadMaterialStats(durability, miningSpeed, attack, harvestLevel));
    }

    public MaterialBuilder withStatsHandle(float durabilityMultiplier, int durability)
    {
        return withStats(new HandleMaterialStats(durabilityMultiplier, durability));
    }

    public MaterialBuilder withStatsExtra(int durability)
    {
        return withStats(new ExtraMaterialStats(durability));
    }

    public MaterialBuilder withStatsBow(float drawSpeed, float range, float bonusDamage)
    {
        return withStats(new BowMaterialStats(drawSpeed, range, bonusDamage));
    }

    public MaterialBuilder withStatsBowString(float durabilityMultiplier)
    {
        return withStats(new BowStringMaterialStats(durabilityMultiplier));
    }

    public MaterialBuilder withStatsArrowShaft(float durabilityMultiplier, int bonusAmmo)
    {
        return withStats(new ArrowShaftMaterialStats(durabilityMultiplier, bonusAmmo));
    }

    public MaterialBuilder withStatsFletching(float accuracy, float durabilityMultiplier)
    {
        return withStats(new FletchingMaterialStats(accuracy, durabilityMultiplier));
    }

    public MaterialBuilder setCraftable()
    {
        this.craftable = true;
        return this;
    }

    public MaterialBuilder setCastable(int fluidTemperature)
    {
        this.castable = true;
        this.fluidTemperature = fluidTemperature;
        return this;
    }

    public MaterialBuilder setCastable(Supplier<Fluid> fluidGetter, int fallbackTemp)
    {
        this.castable = true;
        this.fluidGetter = fluidGetter;
        this.fluidTemperature = fallbackTemp;
        return this;
    }

    public MaterialBuilder setCastable(Fluid fluid, int fallbackTemp) {
        return setCastable(() -> fluid, fallbackTemp);
    }

    public MaterialBuilder setCastable(String fluidId, int fallbackTemp) {
        return setCastable(() -> FluidRegistry.getFluid(fluidId), fallbackTemp);
    }

    public MaterialBuilder withTraits(PartType partType, LazyAccum<ITrait> traitCollector) {
        traits.put(partType, traitCollector);
        return this;
    }

    public MaterialBuilder withTraits(PartType partType, ITrait... traits) {
        return withTraits(partType, c -> c.acceptAll(traits));
    }

    public Material build()
    {
        Material material = TinkerRegistry.getMaterial(matId);
        if (material != Material.UNKNOWN) return material;
        material = new Material(matId, colour, true);
        GTReflect.uncancelMaterial(material.identifier);
        try
        {
            material.setCraftable(craftable);
            if (castable)
            {
                material.setCastable(true);
                registerFluid(material);
            }
            else
            {
                material.setCastable(false);
            }
            for (IMaterialStats statsObj : materialStats.values())
            {
                TinkerRegistry.addMaterialStats(material, statsObj);
            }
            MaterialDefinition.register(material, form, oreName, conditions, traits);
            TinkerRegistry.addMaterial(material);
            if (needsPriority)
            {
                GTReflect.prioritizeMaterial(material);
            }
            // override material owner since libnine invokes the static initializers
            GTReflect.overrideMaterialOwnerMod(material, GregTinker.INSTANCE);
        }
        catch (Exception e)
        {
            GTiLog.logger.error("Encountered exception while building material {}", matId);
            GTiLog.logger.error("Stack trace:", e);
        }
        return material;
    }

    private void registerFluid(Material material)
    {
        if (fluidGetter != null)
        {
            Fluid fluid = fluidGetter.get();
            if (fluid != null)
            { // fall back to generating a fluid if the getter fails
                material.setFluid(fluid);
                return;
            }
        }
        Fluid fluid = new FluidMolten(material.identifier, material.materialTextColor);
        fluid.setTemperature(fluidTemperature);
        fluid.setUnlocalizedName(GregTinker.MODID + "." + material.identifier);
        FluidRegistry.registerFluid(fluid);
        CraftReflect.setFluidUniqueId(fluid, GregTinker.MODID + ":" + material.identifier);
        FluidRegistry.addBucketForFluid(fluid);
        material.setFluid(fluid);
    }
}
