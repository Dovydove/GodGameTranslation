package org.andengine.util.adt.pool;

import org.andengine.util.adt.pool.RunnablePoolItem;

public abstract class RunnablePoolUpdateHandler<T extends RunnablePoolItem> extends PoolUpdateHandler<T> {
    /* access modifiers changed from: protected */
    public abstract T onAllocatePoolItem();

    public RunnablePoolUpdateHandler() {
    }

    public RunnablePoolUpdateHandler(int pInitialPoolSize) {
        super(pInitialPoolSize);
    }

    public RunnablePoolUpdateHandler(int pInitialPoolSize, int pGrowth) {
        super(pInitialPoolSize, pGrowth);
    }

    public RunnablePoolUpdateHandler(int pInitialPoolSize, int pGrowth, int pAvailableItemCountMaximum) {
        super(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum);
    }

    /* access modifiers changed from: protected */
    public void onHandlePoolItem(T pRunnablePoolItem) {
        pRunnablePoolItem.run();
    }
}
