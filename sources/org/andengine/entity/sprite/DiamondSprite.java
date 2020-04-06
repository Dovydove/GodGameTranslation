package org.andengine.entity.sprite;

import org.andengine.entity.sprite.vbo.HighPerformanceDiamondSpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.IDiamondSpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class DiamondSprite extends Sprite {
    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
    }

    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, pDrawType);
    }

    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }

    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, IDiamondSpriteVertexBufferObject pDiamondSpriteVertexBufferObject) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pDiamondSpriteVertexBufferObject);
    }

    public DiamondSprite(float pX, float pY, ITextureRegion pTextureRegion, IDiamondSpriteVertexBufferObject pDiamondSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        this(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pDiamondSpriteVertexBufferObject, pShaderProgram);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        this(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        HighPerformanceDiamondSpriteVertexBufferObject highPerformanceDiamondSpriteVertexBufferObject = new HighPerformanceDiamondSpriteVertexBufferObject(pVertexBufferObjectManager, 20, pDrawType, true, Sprite.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);
        this(pX, pY, pWidth, pHeight, pTextureRegion, (IDiamondSpriteVertexBufferObject) highPerformanceDiamondSpriteVertexBufferObject);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        HighPerformanceDiamondSpriteVertexBufferObject highPerformanceDiamondSpriteVertexBufferObject = new HighPerformanceDiamondSpriteVertexBufferObject(pVertexBufferObjectManager, 20, pDrawType, true, Sprite.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);
        this(pX, pY, pWidth, pHeight, pTextureRegion, (IDiamondSpriteVertexBufferObject) highPerformanceDiamondSpriteVertexBufferObject, pShaderProgram);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, IDiamondSpriteVertexBufferObject pDiamondSpriteVertexBufferObject) {
        super(pX, pY, pWidth, pHeight, pTextureRegion, (ISpriteVertexBufferObject) pDiamondSpriteVertexBufferObject);
    }

    public DiamondSprite(float pX, float pY, float pWidth, float pHeight, ITextureRegion pTextureRegion, IDiamondSpriteVertexBufferObject pDiamondSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTextureRegion, (ISpriteVertexBufferObject) pDiamondSpriteVertexBufferObject, pShaderProgram);
    }
}
