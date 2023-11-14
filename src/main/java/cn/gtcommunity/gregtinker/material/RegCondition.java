package cn.gtcommunity.gregtinker.material;

import cn.gtcommunity.gregtinker.api.utils.OreDictUtils;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

public interface RegCondition
{
    boolean isSatisfied();

    class ModLoaded implements RegCondition {

        private final String modId;

        public ModLoaded(String modId) {
            this.modId = modId;
        }

        @Override
        public boolean isSatisfied() {
            return Loader.isModLoaded(modId);
        }

    }

    class OreDictExists implements RegCondition {

        private final String oreKey;

        public OreDictExists(String oreKey) {
            this.oreKey = oreKey;
        }

        @Override
        public boolean isSatisfied() {
            return OreDictUtils.exists(oreKey);
        }

    }

    class MaterialVisible implements RegCondition {

        private final Material material;

        public MaterialVisible(Material material) {
            this.material = material;
        }

        @Override
        public boolean isSatisfied() {
            return !material.isHidden();
        }

    }

    class MaterialCanOverride implements RegCondition {

        private final String matId;

        public MaterialCanOverride(String matId) {
            this.matId = matId;
        }

        @Override
        public boolean isSatisfied() {
            return TinkerRegistry.getMaterial(matId) == Material.UNKNOWN;
        }

    }

}
