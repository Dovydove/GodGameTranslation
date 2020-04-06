package org.andengine.opengl.util.criteria;

import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.data.operator.StringOperator;

public class GLVersionGLCriteria extends StringGLCriteria {
    public GLVersionGLCriteria(StringOperator pStringOperator, String pExpectedGLVersion) {
        super(pStringOperator, pExpectedGLVersion);
    }

    /* access modifiers changed from: protected */
    public String getActualCriteria(GLState pGLState) {
        return pGLState.getVersion();
    }
}
