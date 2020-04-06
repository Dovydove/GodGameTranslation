package org.andengine.entity.particle;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntityFactory;
import org.andengine.entity.particle.emitter.IParticleEmitter;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.UncoloredSprite;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.ColorUtils;

public class BatchedSpriteParticleSystem extends BlendFunctionParticleSystem<UncoloredSprite> {
    protected final SpriteBatch mSpriteBatch;

    public BatchedSpriteParticleSystem(IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(0.0f, 0.0f, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum, pTextureRegion, pVertexBufferObjectManager);
    }

    public BatchedSpriteParticleSystem(float pX, float pY, IParticleEmitter pParticleEmitter, float pRateMinimum, float pRateMaximum, int pParticlesMaximum, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        final ITextureRegion iTextureRegion = pTextureRegion;
        final VertexBufferObjectManager vertexBufferObjectManager = pVertexBufferObjectManager;
        super(pX, pY, new IEntityFactory<UncoloredSprite>() {
            public UncoloredSprite create(float pX, float pY) {
                return new UncoloredSprite(pX, pY, ITextureRegion.this, vertexBufferObjectManager);
            }
        }, pParticleEmitter, pRateMinimum, pRateMaximum, pParticlesMaximum);
        this.mSpriteBatch = new SpriteBatch(pTextureRegion.getTexture(), pParticlesMaximum, pVertexBufferObjectManager);
    }

    /* access modifiers changed from: protected */
    public void onManagedDraw(GLState pGLState, Camera pCamera) {
        this.mSpriteBatch.setIndex(0);
        Particle<UncoloredSprite>[] particles = this.mParticles;
        for (int i = this.mParticlesAlive - 1; i >= 0; i--) {
            Sprite sprite = (Sprite) particles[i].getEntity();
            float alpha = sprite.getAlpha();
            this.mSpriteBatch.drawWithoutChecks(sprite, ColorUtils.convertRGBAToABGRPackedFloat(sprite.getRed() * alpha, sprite.getGreen() * alpha, sprite.getBlue() * alpha, alpha));
        }
        this.mSpriteBatch.submit();
        this.mSpriteBatch.myDraw(pGLState, pCamera);
    }
}
