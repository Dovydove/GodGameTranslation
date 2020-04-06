package org.andengine.util.adt.transformation;

import org.andengine.util.adt.pool.GenericPool;

public class TransformationPool {
    private static final GenericPool<Transformation> POOL = new GenericPool<Transformation>() {
        /* access modifiers changed from: protected */
        public Transformation onAllocatePoolItem() {
            return new Transformation();
        }
    };

    public static Transformation obtain() {
        return (Transformation) POOL.obtainPoolItem();
    }

    public static void recycle(Transformation pTransformation) {
        pTransformation.setToIdentity();
        POOL.recyclePoolItem(pTransformation);
    }
}
