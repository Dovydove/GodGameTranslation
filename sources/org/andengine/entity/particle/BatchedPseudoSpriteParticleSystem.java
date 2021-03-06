package org.andengine.entity.particle;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityFactory;
import org.andengine.entity.particle.emitter.IParticleEmitter;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.ColorUtils;

public class BatchedPseudoSpriteParticleSystem extends BlendFunctionParticleSystem<Entity> {
    protected final SpriteBatch mSpriteBatch;
    protected final ITextureRegion mTextureRegion;

    public BatchedPseudoSpriteParticleSystem(IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(0.0f, 0.0f, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager);
    }

    public BatchedPseudoSpriteParticleSystem(float pX, float pY, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, new IEntityFactory<Entity>() {
            public Entity create(float pX, float pY) {
                return new Entity(pX, pY);
            }
        }, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
        this.mTextureRegion = pTextureRegion;
        this.mSpriteBatch = new SpriteBatch(pTextureRegion.getTexture(), pParticlesMaximum, pVertexBufferObjectManager);
    }

    /* access modifiers changed from: protected */
    public void onManagedDraw(GLState pGLState, Camera pCamera) {
        this.mSpriteBatch.setIndex(0);
        Particle<Entity>[] particles = this.mParticles;
        for (int i = this.mParticlesAlive - 1; i >= 0; i--) {
            Entity entity = (Entity) particles[i].getEntity();
            float alpha = entity.getAlpha();
            this.mSpriteBatch.drawWithoutChecks(this.mTextureRegion, (IEntity) entity, this.mTextureRegion.getWidth(), this.mTextureRegion.getHeight(), ColorUtils.convertRGBAToABGRPackedFloat(entity.getRed() * alpha, entity.getGreen() * alpha, entity.getBlue() * alpha, alpha));
        }
        this.mSpriteBatch.submit();
        this.mSpriteBatch.myDraw(pGLState, pCamera);
    }
}
