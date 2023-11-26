package cn.gtcommunity.gregtinker.trait;

import gregtech.common.items.MetaItems;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.Arrays;
import java.util.List;

public class GTinkerTraits
{

    public static final AbstractTrait GRAVITATION = new TraitGravitation();
    public static final AbstractTrait UNBREAKABLE = new TraitUnbreakable();
    public static final AbstractTrait EXORCISM = new TraitExorcism();
    public static final AbstractTrait VENEER = new TraitVeneer(1);
    public static final AbstractTrait VENEER2 = new TraitVeneer(2);
    public static final AbstractTrait CORROSION_RESISTANCE = new TraitCorrosionResistance();
    public static final AbstractTrait ALPHA = new TraitAlpha();
    public static final AbstractTrait BETA = new TraitBeta();
    public static final AbstractTrait OMEGA = new TraitOmega();
    public static final AbstractTrait CHOPPING = new TraitChopping();
    public static final AbstractTrait RAPACITY = new TraitRapacity();
    public static final AbstractTrait BLOOD_SUCKING = new TraitBloodSucking();
    public static final AbstractTrait COUNTER_MEASURES = new TraitCounterMeasures();
    public static final AbstractTrait EVENLY_MATCHED = new TraitEvenlyMatched();
    public static final AbstractTrait MAGE_HAND = new TraitMageHand();
    public static final AbstractTrait INDUCED_LIGHTNING = new TraitInducedLightning();
    public static final AbstractTrait COUNTERATTACK = new TraitCounterattack();
    public static final AbstractTrait MALICIOUS_PERCEPTION = new TraitMaliciousPerception();
    public static final AbstractTrait SUPERTIGHT = new TraitSupertight();
    public static final AbstractTrait INERTIA = new TraitInertia();
    public static final AbstractTrait IRRADIATION = new TraitIrradiation();

    public static final ModifierGTElectric MODIFIER_GT_ELECTRIC = new ModifierGTElectric();

    public static final List<Modifier> MODIFIERS = Arrays.asList(
            MODIFIER_GT_ELECTRIC
            );

    public GTinkerTraits()
    {/**/}

    public static void initModifierMaterials()
    {
        MODIFIER_GT_ELECTRIC.addItem(MetaItems.LAPOTRON_CRYSTAL.getStackForm(), 1, 1);
    }

}
