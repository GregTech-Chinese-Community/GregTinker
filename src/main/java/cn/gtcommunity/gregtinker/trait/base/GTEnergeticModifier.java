package cn.gtcommunity.gregtinker.trait.base;

public interface GTEnergeticModifier
{
    default EnergyType getEnergyType() {
        return EnergyType.GTEU;
    }

    enum EnergyType {
        GTEU,
        FORGE_ENERGY,
        EU
    }
}
