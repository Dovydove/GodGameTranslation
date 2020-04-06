package org.andengine.util.adt.transformation;

import android.util.FloatMath;

public class Transformation {

    /* renamed from: a */
    private float f1215a = 1.0f;

    /* renamed from: b */
    private float f1216b = 0.0f;

    /* renamed from: c */
    private float f1217c = 0.0f;

    /* renamed from: d */
    private float f1218d = 1.0f;

    /* renamed from: tx */
    private float f1219tx = 0.0f;

    /* renamed from: ty */
    private float f1220ty = 0.0f;

    public String toString() {
        return "Transformation{[" + this.f1215a + ", " + this.f1217c + ", " + this.f1219tx + "][" + this.f1216b + ", " + this.f1218d + ", " + this.f1220ty + "][0.0, 0.0, 1.0]}";
    }

    public final void reset() {
        setToIdentity();
    }

    public final void setToIdentity() {
        this.f1215a = 1.0f;
        this.f1218d = 1.0f;
        this.f1216b = 0.0f;
        this.f1217c = 0.0f;
        this.f1219tx = 0.0f;
        this.f1220ty = 0.0f;
    }

    public final void setTo(Transformation pTransformation) {
        this.f1215a = pTransformation.f1215a;
        this.f1218d = pTransformation.f1218d;
        this.f1216b = pTransformation.f1216b;
        this.f1217c = pTransformation.f1217c;
        this.f1219tx = pTransformation.f1219tx;
        this.f1220ty = pTransformation.f1220ty;
    }

    public final void preTranslate(float pX, float pY) {
        this.f1219tx += (this.f1215a * pX) + (this.f1217c * pY);
        this.f1220ty += (this.f1216b * pX) + (this.f1218d * pY);
    }

    public final void postTranslate(float pX, float pY) {
        this.f1219tx += pX;
        this.f1220ty += pY;
    }

    public final Transformation setToTranslate(float pX, float pY) {
        this.f1215a = 1.0f;
        this.f1216b = 0.0f;
        this.f1217c = 0.0f;
        this.f1218d = 1.0f;
        this.f1219tx = pX;
        this.f1220ty = pY;
        return this;
    }

    public final void preRotate(float pAngle) {
        float angleRad = 0.017453292f * pAngle;
        float sin = FloatMath.sin(angleRad);
        float cos = FloatMath.cos(angleRad);
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        this.f1215a = (cos * a) + (sin * c);
        this.f1216b = (cos * b) + (sin * d);
        this.f1217c = (cos * c) - (sin * a);
        this.f1218d = (cos * d) - (sin * b);
    }

    public final void postRotate(float pAngle) {
        float angleRad = 0.017453292f * pAngle;
        float sin = FloatMath.sin(angleRad);
        float cos = FloatMath.cos(angleRad);
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        float tx = this.f1219tx;
        float ty = this.f1220ty;
        this.f1215a = (a * cos) - (b * sin);
        this.f1216b = (a * sin) + (b * cos);
        this.f1217c = (c * cos) - (d * sin);
        this.f1218d = (c * sin) + (d * cos);
        this.f1219tx = (tx * cos) - (ty * sin);
        this.f1220ty = (tx * sin) + (ty * cos);
    }

    public final Transformation setToRotate(float pAngle) {
        float angleRad = 0.017453292f * pAngle;
        float sin = FloatMath.sin(angleRad);
        float cos = FloatMath.cos(angleRad);
        this.f1215a = cos;
        this.f1216b = sin;
        this.f1217c = -sin;
        this.f1218d = cos;
        this.f1219tx = 0.0f;
        this.f1220ty = 0.0f;
        return this;
    }

    public final void preScale(float pScaleX, float pScaleY) {
        this.f1215a *= pScaleX;
        this.f1216b *= pScaleX;
        this.f1217c *= pScaleY;
        this.f1218d *= pScaleY;
    }

    public final void postScale(float pScaleX, float pScaleY) {
        this.f1215a *= pScaleX;
        this.f1216b *= pScaleY;
        this.f1217c *= pScaleX;
        this.f1218d *= pScaleY;
        this.f1219tx *= pScaleX;
        this.f1220ty *= pScaleY;
    }

    public final Transformation setToScale(float pScaleX, float pScaleY) {
        this.f1215a = pScaleX;
        this.f1216b = 0.0f;
        this.f1217c = 0.0f;
        this.f1218d = pScaleY;
        this.f1219tx = 0.0f;
        this.f1220ty = 0.0f;
        return this;
    }

    public final void preSkew(float pSkewX, float pSkewY) {
        float tanX = (float) Math.tan((double) (-0.017453292f * pSkewX));
        float tanY = (float) Math.tan((double) (-0.017453292f * pSkewY));
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        float tx = this.f1219tx;
        float ty = this.f1220ty;
        this.f1215a = (tanY * c) + a;
        this.f1216b = (tanY * d) + b;
        this.f1217c = (tanX * a) + c;
        this.f1218d = (tanX * b) + d;
        this.f1219tx = tx;
        this.f1220ty = ty;
    }

    public final void postSkew(float pSkewX, float pSkewY) {
        float tanX = (float) Math.tan((double) (-0.017453292f * pSkewX));
        float tanY = (float) Math.tan((double) (-0.017453292f * pSkewY));
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        float tx = this.f1219tx;
        float ty = this.f1220ty;
        this.f1215a = (b * tanX) + a;
        this.f1216b = (a * tanY) + b;
        this.f1217c = (d * tanX) + c;
        this.f1218d = (c * tanY) + d;
        this.f1219tx = (ty * tanX) + tx;
        this.f1220ty = (tx * tanY) + ty;
    }

    public final Transformation setToSkew(float pSkewX, float pSkewY) {
        this.f1215a = 1.0f;
        this.f1216b = (float) Math.tan((double) (-0.017453292f * pSkewY));
        this.f1217c = (float) Math.tan((double) (-0.017453292f * pSkewX));
        this.f1218d = 1.0f;
        this.f1219tx = 0.0f;
        this.f1220ty = 0.0f;
        return this;
    }

    public final void postConcat(Transformation pTransformation) {
        postConcat(pTransformation.f1215a, pTransformation.f1216b, pTransformation.f1217c, pTransformation.f1218d, pTransformation.f1219tx, pTransformation.f1220ty);
    }

    private void postConcat(float pA, float pB, float pC, float pD, float pTX, float pTY) {
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        float tx = this.f1219tx;
        float ty = this.f1220ty;
        this.f1215a = (a * pA) + (b * pC);
        this.f1216b = (a * pB) + (b * pD);
        this.f1217c = (c * pA) + (d * pC);
        this.f1218d = (c * pB) + (d * pD);
        this.f1219tx = (tx * pA) + (ty * pC) + pTX;
        this.f1220ty = (tx * pB) + (ty * pD) + pTY;
    }

    public final void preConcat(Transformation pTransformation) {
        preConcat(pTransformation.f1215a, pTransformation.f1216b, pTransformation.f1217c, pTransformation.f1218d, pTransformation.f1219tx, pTransformation.f1220ty);
    }

    private void preConcat(float pA, float pB, float pC, float pD, float pTX, float pTY) {
        float a = this.f1215a;
        float b = this.f1216b;
        float c = this.f1217c;
        float d = this.f1218d;
        float tx = this.f1219tx;
        float ty = this.f1220ty;
        this.f1215a = (pA * a) + (pB * c);
        this.f1216b = (pA * b) + (pB * d);
        this.f1217c = (pC * a) + (pD * c);
        this.f1218d = (pC * b) + (pD * d);
        this.f1219tx = (pTX * a) + (pTY * c) + tx;
        this.f1220ty = (pTX * b) + (pTY * d) + ty;
    }

    public final void transform(float[] pVertices) {
        int count = pVertices.length >> 1;
        int j = 0;
        int i = 0;
        while (true) {
            count--;
            if (count >= 0) {
                int i2 = i + 1;
                float x = pVertices[i];
                i = i2 + 1;
                float y = pVertices[i2];
                int j2 = j + 1;
                pVertices[j] = (this.f1215a * x) + (this.f1217c * y) + this.f1219tx;
                j = j2 + 1;
                pVertices[j2] = (this.f1216b * x) + (this.f1218d * y) + this.f1220ty;
            } else {
                return;
            }
        }
    }
}
