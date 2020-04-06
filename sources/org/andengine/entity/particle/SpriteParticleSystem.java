package org.andengine.entity.particle;

import org.andengine.entity.IEntityFactory;
import org.andengine.entity.particle.emitter.IParticleEmitter;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SpriteParticleSystem extends ParticleSystem<Sprite> {
    public SpriteParticleSystem(IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(0.0f, 0.0f, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager);
    }

    public SpriteParticleSystem(float pX, float pY, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        final ITextureRegion iTextureRegion = pTextureRegion;
        final VertexBufferObjectManager vertexBufferObjectManager = pVertexBufferObjectManager;
        super(pX, pY, new IEntityFactory<Sprite>() {
            public Sprite create(float pX, float pY) {
                return new Sprite(pX, pY, ITextureRegion.this, vertexBufferObjectManager);
            }
        }, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
    }

    protected SpriteParticleSystem(float pX, float pY, IEntityFactory<Sprite> pEntityFactory, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum) {
        super(pX, pY, pEntityFactory, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
    }
}
