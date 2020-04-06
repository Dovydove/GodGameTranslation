package org.andengine.entity.particle.modifier;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

public class RotationParticleModifier<T extends IEntity> extends BaseSingleValueSpanParticleModifier<T> {
    public RotationParticleModifier(float pFromTime, float pToTime, float pFromRotation, float pToRotation) {
        this(pFromTime, pToTime, pFromRotation, pToRotation, EaseLinear.getInstance());
    }

    public RotationParticleModifier(float pFromTime, float pToTime, float pFromRotation, float pToRotation, IEaseFunction pEaseFunction) {
        super(pFromTime, pToTime, pFromRotation, pToRotation, pEaseFunction);
    }

    /* access modifiers changed from: protected */
    public void onSetInitialValue(Particle<T> pParticle, float pRotation) {
        pParticle.getEntity().setRotation(pRotation);
    }

    /* access modifiers changed from: protected */
    public void onSetValue(Particle<T> pParticle, float pPercentageDone, float pRotation) {
        pParticle.getEntity().setRotation(pRotation);
    }
}
