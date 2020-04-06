package org.andengine.entity.primitive;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.primitive.vbo.HighPerformanceRectangleVertexBufferObject;
import org.andengine.entity.primitive.vbo.IRectangleVertexBufferObject;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.opengl.shader.PositionColorShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;

public class Rectangle extends RectangularShape {
    public static final int COLOR_INDEX = 2;
    public static final int RECTANGLE_SIZE = 12;
    public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(2).add(0, ShaderProgramConstants.ATTRIBUTE_POSITION, 2, 5126, false).add(1, ShaderProgramConstants.ATTRIBUTE_COLOR, 4, 5121, true).build();
    public static final int VERTEX_INDEX_X = 0;
    public static final int VERTEX_INDEX_Y = 1;
    public static final int VERTEX_SIZE = 3;
    public static final int VERTICES_PER_RECTANGLE = 4;
    protected final IRectangleVertexBufferObject mRectangleVertexBufferObject;

    public Rectangle(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pX, pY, pWidth, pHeight, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public Rectangle(float pX, float pY, float pWidth, float pHeight, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        HighPerformanceRectangleVertexBufferObject highPerformanceRectangleVertexBufferObject = new HighPerformanceRectangleVertexBufferObject(pVertexBufferObjectManager, 12, pDrawType, true, VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT);
        this(pX, pY, pWidth, pHeight, (IRectangleVertexBufferObject) highPerformanceRectangleVertexBufferObject);
    }

    public Rectangle(float pX, float pY, float pWidth, float pHeight, IRectangleVertexBufferObject pRectangleVertexBufferObject) {
        super(pX, pY, pWidth, pHeight, PositionColorShaderProgram.getInstance());
        this.mRectangleVertexBufferObject = pRectangleVertexBufferObject;
        onUpdateVertices();
        onUpdateColor();
        setBlendingEnabled(true);
    }

    public IRectangleVertexBufferObject getVertexBufferObject() {
        return this.mRectangleVertexBufferObject;
    }

    /* access modifiers changed from: protected */
    public void preDraw(GLState pGLState, Camera pCamera) {
        super.preDraw(pGLState, pCamera);
        this.mRectangleVertexBufferObject.bind(pGLState, this.mShaderProgram);
    }

    /* access modifiers changed from: protected */
    public void draw(GLState pGLState, Camera pCamera) {
        this.mRectangleVertexBufferObject.draw(5, 4);
    }

    /* access modifiers changed from: protected */
    public void postDraw(GLState pGLState, Camera pCamera) {
        this.mRectangleVertexBufferObject.unbind(pGLState, this.mShaderProgram);
        super.postDraw(pGLState, pCamera);
    }

    /* access modifiers changed from: protected */
    public void onUpdateColor() {
        this.mRectangleVertexBufferObject.onUpdateColor(this);
    }

    /* access modifiers changed from: protected */
    public void onUpdateVertices() {
        this.mRectangleVertexBufferObject.onUpdateVertices(this);
    }
}
