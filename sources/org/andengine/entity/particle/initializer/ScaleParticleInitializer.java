package org.andengine.entity.particle.initializer;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;

public class ScaleParticleInitializer<T extends IEntity> extends BaseSingleValueParticleInitializer<T> {
    public ScaleParticleInitializer(float pScale) {
        super(pScale, pScale);
    }

    public ScaleParticleInitializer(float pMinScale, float pMaxScale) {
        super(pMinScale, pMaxScale);
    }

    /* access modifiers changed from: protected */
    public void onInitializeParticle(Particle<T> pParticle, float pScale) {
        pParticle.getEntity().setScale(pScale);
    }
}
