package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException;
import org.andengine.util.modifier.IModifier.IModifierListener;
import org.andengine.util.modifier.SequenceModifier;
import org.andengine.util.modifier.SequenceModifier.ISubSequenceModifierListener;

public class SequenceEntityModifier extends SequenceModifier<IEntity> implements IEntityModifier {

    public interface ISubSequenceShapeModifierListener extends ISubSequenceModifierListener<IEntity> {
    }

    public SequenceEntityModifier(IEntityModifier... pEntityModifiers) throws IllegalArgumentException {
        super((IModifier<T>[]) pEntityModifiers);
    }

    public SequenceEntityModifier(ISubSequenceShapeModifierListener pSubSequenceShapeModifierListener, IEntityModifier... pEntityModifiers) throws IllegalArgumentException {
        super((ISubSequenceModifierListener<T>) pSubSequenceShapeModifierListener, (IModifier<T>[]) pEntityModifiers);
    }

    public SequenceEntityModifier(IEntityModifierListener pEntityModifierListener, IEntityModifier... pEntityModifiers) throws IllegalArgumentException {
        super((IModifierListener<T>) pEntityModifierListener, (IModifier<T>[]) pEntityModifiers);
    }

    public SequenceEntityModifier(ISubSequenceShapeModifierListener pSubSequenceShapeModifierListener, IEntityModifierListener pEntityModifierListener, IEntityModifier... pEntityModifiers) throws IllegalArgumentException {
        super(pSubSequenceShapeModifierListener, pEntityModifierListener, pEntityModifiers);
    }

    protected SequenceEntityModifier(SequenceEntityModifier pSequenceShapeModifier) throws DeepCopyNotSupportedException {
        super((SequenceModifier<T>) pSequenceShapeModifier);
    }

    public SequenceEntityModifier deepCopy() throws DeepCopyNotSupportedException {
        return new SequenceEntityModifier(this);
    }
}
