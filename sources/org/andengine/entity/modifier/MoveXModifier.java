package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

public class MoveXModifier extends SingleValueSpanEntityModifier {
    public MoveXModifier(float pDuration, float pFromX, float pToX) {
        this(pDuration, pFromX, pToX, null, EaseLinear.getInstance());
    }

    public MoveXModifier(float pDuration, float pFromX, float pToX, IEaseFunction pEaseFunction) {
        this(pDuration, pFromX, pToX, null, pEaseFunction);
    }

    public MoveXModifier(float pDuration, float pFromX, float pToX, IEntityModifierListener pEntityModifierListener) {
        super(pDuration, pFromX, pToX, pEntityModifierListener, EaseLinear.getInstance());
    }

    public MoveXModifier(float pDuration, float pFromX, float pToX, IEntityModifierListener pEntityModifierListener, IEaseFunction pEaseFunction) {
        super(pDuration, pFromX, pToX, pEntityModifierListener, pEaseFunction);
    }

    protected MoveXModifier(MoveXModifier pMoveXModifier) {
        super(pMoveXModifier);
    }

    public MoveXModifier deepCopy() {
        return new MoveXModifier(this);
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(IEntity pEntity, float pX) {
        pEntity.setX(pX);
    }

    /* access modifiers changed from: protected */
    public void onSetValue(IEntity pEntity, float pPercentageDone, float pX) {
        pEntity.setX(pX);
    }
}
