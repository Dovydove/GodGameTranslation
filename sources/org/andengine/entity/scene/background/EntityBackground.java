package org.andengine.entity.scene.background;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.IEntity;
import org.andengine.opengl.util.GLState;

public class EntityBackground extends Background {
    protected IEntity mEntity;

    public EntityBackground(IEntity pEntity) {
        this.mEntity = pEntity;
    }

    public EntityBackground(float pRed, float pGreen, float pBlue, IEntity pEntity) {
        super(pRed, pGreen, pBlue);
        this.mEntity = pEntity;
    }

    public void myDraw(GLState pGLState, Camera pCamera) {
        super.myDraw(pGLState, pCamera);
        this.mEntity.myDraw(pGLState, pCamera);
    }
}
