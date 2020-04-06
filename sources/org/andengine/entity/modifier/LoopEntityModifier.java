package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException;
import org.andengine.util.modifier.IModifier.IModifierListener;
import org.andengine.util.modifier.LoopModifier;
import org.andengine.util.modifier.LoopModifier.ILoopModifierListener;

public class LoopEntityModifier extends LoopModifier<IEntity> implements IEntityModifier {

    public interface ILoopEntityModifierListener extends ILoopModifierListener<IEntity> {
    }

    public LoopEntityModifier(IEntityModifier pEntityModifier) {
        super((IModifier<T>) pEntityModifier);
    }

    public LoopEntityModifier(IEntityModifier pEntityModifier, int pLoopCount) {
        super(pEntityModifier, pLoopCount);
    }

    public LoopEntityModifier(IEntityModifier pEntityModifier, int pLoopCount, ILoopEntityModifierListener pLoopModifierListener) {
        super((IModifier<T>) pEntityModifier, pLoopCount, (ILoopModifierListener<T>) pLoopModifierListener);
    }

    public LoopEntityModifier(IEntityModifier pEntityModifier, int pLoopCount, IEntityModifierListener pEntityModifierListener) {
        super((IModifier<T>) pEntityModifier, pLoopCount, (IModifierListener<T>) pEntityModifierListener);
    }

    public LoopEntityModifier(IEntityModifierListener pEntityModifierListener, int pLoopCount, ILoopEntityModifierListener pLoopModifierListener, IEntityModifier pEntityModifier) {
        super(pEntityModifier, pLoopCount, pLoopModifierListener, pEntityModifierListener);
    }

    protected LoopEntityModifier(LoopEntityModifier pLoopEntityModifier) throws DeepCopyNotSupportedException {
        super((LoopModifier<T>) pLoopEntityModifier);
    }

    public LoopEntityModifier deepCopy() throws DeepCopyNotSupportedException {
        return new LoopEntityModifier(this);
    }
}
