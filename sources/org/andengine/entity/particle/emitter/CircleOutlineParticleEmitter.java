package org.andengine.entity.particle.emitter;

import android.util.FloatMath;
import org.andengine.util.math.MathUtils;

public class CircleOutlineParticleEmitter extends BaseCircleParticleEmitter {
    public CircleOutlineParticleEmitter(float pCenterX, float pCenterY, float pRadius) {
        super(pCenterX, pCenterY, pRadius);
    }

    public CircleOutlineParticleEmitter(float pCenterX, float pCenterY, float pRadiusX, float pRadiusY) {
        super(pCenterX, pCenterY, pRadiusX, pRadiusY);
    }

    public void getPositionOffset(float[] pOffset) {
        float random = MathUtils.RANDOM.nextFloat() * 3.1415927f * 2.0f;
        pOffset[0] = this.mCenterX + (FloatMath.cos(random) * this.mRadiusX);
        pOffset[1] = this.mCenterY + (FloatMath.sin(random) * this.mRadiusY);
    }
}
