package org.andengine.util.algorithm.collision;

public class BaseCollisionChecker {
    public static boolean checkAxisAlignedRectangleCollision(float pLeftA, float pTopA, float pRightA, float pBottomA, float pLeftB, float pTopB, float pRightB, float pBottomB) {
        return pLeftA < pRightB && pLeftB < pRightA && pTopA < pBottomB && pTopB < pBottomA;
    }

    public static boolean checkAxisAlignedRectangleContains(float pLeft, float pTop, float pRight, float pBottom, float pX, float pY) {
        return pX > pLeft && pX < pRight && pY > pTop && pY < pBottom;
    }

    public static int relativeCCW(float pX1, float pY1, float pX2, float pY2, float pPX, float pPY) {
        float pX22 = pX2 - pX1;
        float pY22 = pY2 - pY1;
        float pPX2 = pPX - pX1;
        float pPY2 = pPY - pY1;
        float ccw = (pPX2 * pY22) - (pPY2 * pX22);
        if (ccw == 0.0f) {
            ccw = (pPX2 * pX22) + (pPY2 * pY22);
            if (ccw > 0.0f) {
                ccw = ((pPX2 - pX22) * pX22) + ((pPY2 - pY22) * pY22);
                if (ccw < 0.0f) {
                    ccw = 0.0f;
                }
            }
        }
        if (ccw < 0.0f) {
            return -1;
        }
        return ccw > 0.0f ? 1 : 0;
    }
}
