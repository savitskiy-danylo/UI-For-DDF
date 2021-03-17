package GachiCore.Builders.Base;

import GachiCore.AI.AIUser;
import GachiCore.Entities.Base.Entity;

public interface AIBuilder {
    public AIUser build(Entity enemy);
}
