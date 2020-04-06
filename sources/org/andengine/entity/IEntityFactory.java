package org.andengine.entity;

import org.andengine.entity.IEntity;

public interface IEntityFactory<T extends IEntity> {
    T create(float f, float f2);
}
