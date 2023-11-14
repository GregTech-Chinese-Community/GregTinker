package cn.gtcommunity.gregtinker.trait;

import slimeknights.tconstruct.library.traits.AbstractTrait;

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
    public static final AbstractTrait SUPERTIGHT = new TraitSupertight();
    public static final AbstractTrait INERTIA = new TraitInertia();
    public static final AbstractTrait IRRADIATION = new TraitIrradiation();

    public GTinkerTraits()
    {/**/}
}
