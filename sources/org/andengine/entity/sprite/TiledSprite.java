package org.andengine.entity.sprite;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.vbo.HighPerformanceTiledSpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TiledSprite extends Sprite {
    public static final int TILEDSPRITE_SIZE = 30;
    public static final int VERTEX_SIZE = 5;
    public static final int VERTICES_PER_TILEDSPRITE = 6;
    private int mCurrentTileIndex;
    private final ITiledSpriteVertexBufferObject mTiledSpriteVertexBufferObject;

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        this(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
    }

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        this(pX, pY, pTiledTextureRegion.getWidth(), pTiledTextureRegion.getHeight(), pTiledTextureRegion, pVertexBufferObjectManager, pDrawType);
    }

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        this(pX, pY, pTiledTextureRegion.getWidth(), pTiledTextureRegion.getHeight(), pTiledTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
        this(pX, pY, pTiledTextureRegion.getWidth(), pTiledTextureRegion.getHeight(), pTiledTextureRegion, pTiledSpriteVertexBufferObject);
    }

    public TiledSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        this(pX, pY, pTiledTextureRegion.getWidth(), pTiledTextureRegion.getHeight(), pTiledTextureRegion, pTiledSpriteVertexBufferObject, pShaderProgram);
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        this(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        HighPerformanceTiledSpriteVertexBufferObject highPerformanceTiledSpriteVertexBufferObject = new HighPerformanceTiledSpriteVertexBufferObject(pVertexBufferObjectManager, pTiledTextureRegion.getTileCount() * 30, pDrawType, true, Sprite.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);
        this(pX, pY, pWidth, pHeight, pTiledTextureRegion, (ITiledSpriteVertexBufferObject) highPerformanceTiledSpriteVertexBufferObject);
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        HighPerformanceTiledSpriteVertexBufferObject highPerformanceTiledSpriteVertexBufferObject = new HighPerformanceTiledSpriteVertexBufferObject(pVertexBufferObjectManager, pTiledTextureRegion.getTileCount() * 30, pDrawType, true, Sprite.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);
        this(pX, pY, pWidth, pHeight, pTiledTextureRegion, (ITiledSpriteVertexBufferObject) highPerformanceTiledSpriteVertexBufferObject, pShaderProgram);
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
        this(pX, pY, pWidth, pHeight, pTiledTextureRegion, pTiledSpriteVertexBufferObject, (ShaderProgram) PositionColorTextureCoordinatesShaderProgram.getInstance());
    }

    public TiledSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, (ITextureRegion) pTiledTextureRegion, (ISpriteVertexBufferObject) pTiledSpriteVertexBufferObject, pShaderProgram);
        this.mTiledSpriteVertexBufferObject = pTiledSpriteVertexBufferObject;
    }

    public ITextureRegion getTextureRegion() {
        return getTiledTextureRegion().getTextureRegion(this.mCurrentTileIndex);
    }

    public ITiledTextureRegion getTiledTextureRegion() {
        return (ITiledTextureRegion) this.mTextureRegion;
    }

    public ITiledSpriteVertexBufferObject getVertexBufferObject() {
        return (ITiledSpriteVertexBufferObject) super.getVertexBufferObject();
    }

    /* access modifiers changed from: protected */
    public void draw(GLState pGLState, Camera pCamera) {
        this.mTiledSpriteVertexBufferObject.draw(4, this.mCurrentTileIndex * 6, 6);
    }

    /* access modifiers changed from: protected */
    public void onUpdateColor() {
        getVertexBufferObject().onUpdateColor(this);
    }

    /* access modifiers changed from: protected */
    public void onUpdateVertices() {
        getVertexBufferObject().onUpdateVertices(this);
    }

    /* access modifiers changed from: protected */
    public void onUpdateTextureCoordinates() {
        getVertexBufferObject().onUpdateTextureCoordinates(this);
    }

    public int getCurrentTileIndex() {
        return this.mCurrentTileIndex;
    }

    public void setCurrentTileIndex(int pCurrentTileIndex) {
        this.mCurrentTileIndex = pCurrentTileIndex;
    }

    public int getTileCount() {
        return getTiledTextureRegion().getTileCount();
    }
}
