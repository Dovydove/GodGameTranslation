package org.andengine.util.modifier;

import org.andengine.util.modifier.IModifier.IModifierListener;

public abstract class BaseSingleValueChangeModifier<T> extends BaseDurationModifier<T> {
    private final float mValueChangePerSecond;

    /* access modifiers changed from: protected */
    public abstract void onChangeValue(float f, T t, float f2);

    public BaseSingleValueChangeModifier(float pDuration, float pValueChange) {
        this(pDuration, pValueChange, null);
    }

    public BaseSingleValueChangeModifier(float pDuration, float pValueChange, IModifierListener<T> pModifierListener) {
        super(pDuration, pModifierListener);
        this.mValueChangePerSecond = pValueChange / pDuration;
    }

    protected BaseSingleValueChangeModifier(BaseSingleValueChangeModifier<T> pBaseSingleValueChangeModifier) {
        super((BaseDurationModifier<T>) pBaseSingleValueChangeModifier);
        this.mValueChangePerSecond = pBaseSingleValueChangeModifier.mValueChangePerSecond;
    }

    /* access modifiers changed from: protected */
    public void onManagedInitialize(T t) {
    }

    /* access modifiers changed from: protected */
    public void onManagedUpdate(float pSecondsElapsed, T pItem) {
        onChangeValue(pSecondsElapsed, pItem, this.mValueChangePerSecond * pSecondsElapsed);
    }
}
