package org.andengine.entity.particle.initializer;

import org.andengine.entity.IEntity;
import org.andengine.entity.particle.Particle;

public class AlphaParticleInitializer<T extends IEntity> extends BaseSingleValueParticleInitializer<T> {
    public AlphaParticleInitializer(float pAlpha) {
        super(pAlpha, pAlpha);
    }

    public AlphaParticleInitializer(float pMinAlpha, float pMaxAlpha) {
        super(pMinAlpha, pMaxAlpha);
    }

    /* access modifiers changed from: protected */
    public void onInitializeParticle(Particle<T> pParticle, float pAlpha) {
        pParticle.getEntity().setAlpha(pAlpha);
    }
}
