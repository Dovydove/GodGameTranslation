package org.andengine.opengl.util.criteria;

import android.os.Build;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.data.operator.StringOperator;

public class BuildModelGLCriteria extends StringGLCriteria {
    public BuildModelGLCriteria(StringOperator pStringOperator, String pBuildModel) {
        super(pStringOperator, pBuildModel);
    }

    /* access modifiers changed from: protected */
    public String getActualCriteria(GLState pGLState) {
        return Build.MODEL;
    }
}
