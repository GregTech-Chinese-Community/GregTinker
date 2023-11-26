package cn.gtcommunity.gregtinker.api.capability;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.IdentityHashMap;
import java.util.Map;

public class CapabilityBroker implements ICapabilityProvider
{
    private final Map<Capability<?>, Object> capabilities = new IdentityHashMap<>();

    public CapabilityBroker()
    {/**/}

    public <T> CapabilityBroker with(Capability<T> capability, T aspect)
    {
        this.capabilities.put(capability, aspect);
        return this;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return this.capabilities.containsKey(capability);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return (T) this.capabilities.get(capability);
    }
}
