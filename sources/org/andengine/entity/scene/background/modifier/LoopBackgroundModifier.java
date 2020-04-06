package org.andengine.entity.scene.background.modifier;

import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.scene.background.modifier.IBackgroundModifier.IBackgroundModifierListener;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException;
import org.andengine.util.modifier.IModifier.IModifierListener;
import org.andengine.util.modifier.LoopModifier;
import org.andengine.util.modifier.LoopModifier.ILoopModifierListener;

public class LoopBackgroundModifier extends LoopModifier<IBackground> implements IBackgroundModifier {

    public interface ILoopBackgroundModifierListener extends ILoopModifierListener<IBackground> {
    }

    public LoopBackgroundModifier(IBackgroundModifier pBackgroundModifier) {
        super((IModifier<T>) pBackgroundModifier);
    }

    public LoopBackgroundModifier(IBackgroundModifier pBackgroundModifier, int pLoopCount) {
        super(pBackgroundModifier, pLoopCount);
    }

    public LoopBackgroundModifier(IBackgroundModifier pBackgroundModifier, int pLoopCount, ILoopBackgroundModifierListener pLoopModifierListener) {
        super(pBackgroundModifier, pLoopCount, pLoopModifierListener, null);
    }

    public LoopBackgroundModifier(IBackgroundModifier pBackgroundModifier, int pLoopCount, IBackgroundModifierListener pBackgroundModifierListener) {
        super((IModifier<T>) pBackgroundModifier, pLoopCount, (IModifierListener<T>) pBackgroundModifierListener);
    }

    public LoopBackgroundModifier(IBackgroundModifier pBackgroundModifier, int pLoopCount, ILoopBackgroundModifierListener pLoopModifierListener, IBackgroundModifierListener pBackgroundModifierListener) {
        super(pBackgroundModifier, pLoopCount, pLoopModifierListener, pBackgroundModifierListener);
    }

    protected LoopBackgroundModifier(LoopBackgroundModifier pLoopBackgroundModifier) throws DeepCopyNotSupportedException {
        super((LoopModifier<T>) pLoopBackgroundModifier);
    }

    public LoopBackgroundModifier deepCopy() throws DeepCopyNotSupportedException {
        return new LoopBackgroundModifier(this);
    }
}
